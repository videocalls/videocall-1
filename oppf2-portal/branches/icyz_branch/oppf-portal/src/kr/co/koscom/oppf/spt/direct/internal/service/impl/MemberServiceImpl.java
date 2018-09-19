package kr.co.koscom.oppf.spt.direct.internal.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.spt.direct.common.util.CommonCodeConstants;
import kr.co.koscom.oppf.spt.direct.internal.dao.MemberDAO;
import kr.co.koscom.oppf.spt.direct.internal.dto.MemberDTO;
import kr.co.koscom.oppf.spt.direct.internal.exception.InternalApiException;
import kr.co.koscom.oppf.spt.direct.internal.exception.InternalApiExceptionCode;
import kr.co.koscom.oppf.spt.direct.internal.model.InternalResponse;
import kr.co.koscom.oppf.spt.direct.internal.model.MemberRequest;
import kr.co.koscom.oppf.spt.direct.internal.model.MemberResponse;
import kr.co.koscom.oppf.spt.direct.internal.model.MemberVerifyRequest;
import kr.co.koscom.oppf.spt.direct.internal.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDao;

    @Override
    public MemberResponse selectMember(String userCi) {

        try {
            MemberDTO memberDto = memberDao.selectMember(userCi);

            if (memberDto == null) {
                throw new InternalApiException(InternalApiExceptionCode.NOT_FOUND_USER, userCi);
            }

            MemberResponse response = new MemberResponse();
            // property copy
            PropertyUtils.copyProperties(response, memberDto);

            return response;
        } catch (InternalApiException caex) {
            throw caex;
        } catch (Exception ex) {
            throw new InternalApiException(InternalApiExceptionCode.UNKNOWN_ERROR, ex, userCi);
        }
    }

    @Transactional
    public InternalResponse insertMember(MemberRequest request) {
        try {

            MemberDTO existMember = memberDao.selectMember(request.getUserCi());

//            if (existMember != null) {
//                throw new InternalApiException(InternalApiExceptionCode.EXIST_USER, request.getUserCi());
//            }

            if(existMember == null) {
                log.info("========================================= 신규 사용자 등록 : {}", request.toString());
                MemberDTO memberDto = new MemberDTO();
    
                // property copy
                PropertyUtils.copyProperties(memberDto, request);
                memberDto.setCustomerNameKor(request.getCustomerUserNm());
    
                // 비회원 ID, PASSWORD는 임시로 생성 됨 추후 삭제
                Random random = new Random();
                SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
                String format = String.format("%s%04d", sdf.format(new Date()), random.nextInt(9999));
                memberDto.setCustomerId("Z" + format);
                memberDto.setCustomerPassword("Z" + format);
    
                int dupCnt = memberDao.selectFindUserId(memberDto.getCustomerId());
    
                while (dupCnt > 0) {
    
                    random = new Random();
                    sdf = new SimpleDateFormat("MMddHHmmss");
                    format = String.format("%s%04d", sdf.format(new Date()), random.nextInt(9999));
                    memberDto.setCustomerId("Z" + format);
                    memberDto.setCustomerPassword("Z" + format);
    
                    dupCnt = memberDao.selectFindUserId(memberDto.getCustomerId());
    
                }
    
                log.info("아이디 {}", memberDto.getCustomerId());
                
                memberDto.setCustomerRegNoPrefix("C");
                memberDto.setCustomerRegStatus(CommonCodeConstants.MBR_REG_STATUS_ACTIVE);
                memberDto.setCustomerStep(CommonCodeConstants.MBR_REG_STEP_EMAIL_VERIFIED);
                memberDao.insertMember(memberDto);
                
                log.debug("========================================= 등록한 회원정보 : {}", memberDto.getCustomerRegNo());
                
                /* 사용자 CI 저장 */
                MemberVerifyRequest memberVerifyRequest = new MemberVerifyRequest();
                memberVerifyRequest.setUserCi(memberDto.getUserCi());
                memberVerifyRequest.setCustomerRegNo(memberDto.getCustomerRegNo());
                memberVerifyRequest.setUserDn(memberDto.getUserDn());
                insertMemberVerify(memberVerifyRequest);
            } else {
                log.info("========================================= 기존 사용자 갱신 : {}", request.toString());
                
                // property copy
                PropertyUtils.copyProperties(existMember, request);
                existMember.setCustomerNameKor(request.getCustomerUserNm());

                memberDao.updateMember(existMember);
                
                /* 사용자 DN 업데이트 */
                MemberVerifyRequest memberVerifyRequest = new MemberVerifyRequest();
                memberVerifyRequest.setUserCi(existMember.getUserCi());
                memberVerifyRequest.setUserDn(request.getUserDn());
                insertMemberVerify(memberVerifyRequest);
            }
            
            return InternalResponse.SUCCESS;
        } catch (InternalApiException caex) {
            throw caex;
        } catch (Exception ex) {
            throw new InternalApiException(InternalApiExceptionCode.UNKNOWN_ERROR, ex, request.getUserCi());
        }
    }

    
    @Override
    @Transactional
    public InternalResponse updateMember(MemberRequest request) {
        try {

            MemberDTO existMember = memberDao.selectMember(request.getUserCi());

            if (existMember == null) {
                throw new InternalApiException(InternalApiExceptionCode.NOT_FOUND_USER, request.getUserCi());
            }

            // property copy
            PropertyUtils.copyProperties(existMember, request);
            existMember.setCustomerNameKor(request.getCustomerUserNm());

            memberDao.updateMember(existMember);

            return InternalResponse.SUCCESS;
        } catch (InternalApiException caex) {
            throw caex;
        } catch (Exception ex) {
            throw new InternalApiException(InternalApiExceptionCode.UNKNOWN_ERROR, ex, request.getUserCi());
        }
    }

    @Override
    @Transactional
    public InternalResponse insertMemberVerify(MemberVerifyRequest request) {
        try {

            if (request.getCustomerRegNo() != null) {
                // customerRegNo 가 있는 경우 : CI/DN 모두 등록
                if (request.getUserCi() != null) {
                    MemberDTO memberDto = new MemberDTO();
                    memberDto.setCustomerRegNo(request.getCustomerRegNo());
                    memberDto.setCustomerVerifyType(CommonCodeConstants.VERIFY_TYPE_CI);
                    memberDto.setCustomerVerify(request.getUserCi());
                    memberDao.insertMemberVerify(memberDto);
                    log.debug("========================================= 등록한 CI 정보 : {}", memberDto.getCustomerVerify());
                }
    
                if (request.getUserDn() != null) {
                    MemberDTO memberDto = new MemberDTO();
                    memberDto.setCustomerRegNo(request.getCustomerRegNo());
                    memberDto.setCustomerVerifyType(CommonCodeConstants.VERIFY_TYPE_DN);
                    memberDto.setCustomerVerify(request.getUserDn());
                    memberDao.insertMemberVerify(memberDto);
                    log.debug("========================================= 등록한 DN 정보 : {}", memberDto.getCustomerVerify());
                }                
            } else {
                // customerRegNo 가 없는 경우 : DN만 등록
                MemberDTO existMember = memberDao.selectMember(request.getUserCi());
                
                if (existMember == null) {
                    throw new InternalApiException(InternalApiExceptionCode.NOT_FOUND_USER, request.getUserCi());
                }
                
                request.setCustomerRegNo(existMember.getCustomerRegNo());
                
                if (request.getUserDn() != null && existMember.getUserDn() == null) {
                    MemberDTO memberDto = new MemberDTO();
                    memberDto.setCustomerRegNo(request.getCustomerRegNo());
                    memberDto.setCustomerVerifyType(CommonCodeConstants.VERIFY_TYPE_DN);
                    memberDto.setCustomerVerify(request.getUserDn());
                    memberDao.insertMemberVerify(memberDto);
                    log.debug("========================================= 등록한 DN 정보 : {}", memberDto.getCustomerVerify());
                } else {
                    existMember.setCustomerVerifyType(CommonCodeConstants.VERIFY_TYPE_DN);
                    existMember.setCustomerVerify(request.getUserDn());
                    memberDao.updateMemberVerify(existMember);
                }
            }
            
            return InternalResponse.SUCCESS;
        } catch (InternalApiException caex) {
            throw caex;
        } catch (Exception ex) {
            throw new InternalApiException(InternalApiExceptionCode.UNKNOWN_ERROR, ex, request.getUserCi());
        }        
    }

    
    @Override
    @Transactional
    public InternalResponse updateMemberVerify(MemberVerifyRequest request) {
        try {

            MemberDTO existMember = memberDao.selectMember(request.getUserCi());

            if (existMember == null) {
                throw new InternalApiException(InternalApiExceptionCode.NOT_FOUND_USER, request.getUserCi());
            }

            existMember.setCustomerVerifyType(CommonCodeConstants.VERIFY_TYPE_DN);
            existMember.setCustomerVerify(request.getUserDn());
            
            memberDao.updateMemberVerify(existMember);

            return InternalResponse.SUCCESS;
        } catch (InternalApiException caex) {
            throw caex;
        } catch (Exception ex) {
            throw new InternalApiException(InternalApiExceptionCode.UNKNOWN_ERROR, ex, request.getUserCi());
        }
    }
}
