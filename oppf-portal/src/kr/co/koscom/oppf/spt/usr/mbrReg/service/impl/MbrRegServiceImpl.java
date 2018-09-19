package kr.co.koscom.oppf.spt.usr.mbrReg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.koscom.oppf.cmm.service.AccListAttributeREQVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.cmm.util.CodeConstants;
import kr.co.koscom.oppf.cmm.util.OppfStringUtil;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegService;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegTermsContentVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegTermsVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MbrRegVO;
import kr.co.koscom.oppf.spt.usr.mbrReg.service.MobileTermsVO;
import lombok.extern.slf4j.Slf4j;



/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : MbrRegServiceImpl.java
* @Comment  : [회원가입]정보관리를 위한 Service 클래스
* @author   : 포털 이환덕
* @since    : 2016.05.02
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.05.02  이환덕        최초생성
*
*/
@Slf4j
@Service("MbrRegService")
public class MbrRegServiceImpl implements MbrRegService{
    
    @Resource(name = "MbrRegDAO")
    private MbrRegDAO mbrRegDAO;
    
    /**
     * @Method Name        : procMbrReg1
     * @Method description : [회원가입:1.본인인증]정보 처리를 한다.
     * @param              : MbrRegVO
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    @Transactional
    public MbrRegVO procMbrReg1(MbrRegVO paramVO) throws Exception{
        log.info("------------- procMbrReg1 START ------------------------");
        MbrRegVO returnMbrRegVO = new MbrRegVO();
        int rs = 0;  //전체
        int rs1 = 0; //기본정보
        int rs2 = 0; //인증정보
        int rs3 = 0; //기본hist정보
        int rs4 = 0; //인증hist정보
        int rs5 = 0; //step update
        
        MbrRegVO rsMbrRegVO = selectSptCustomerProfileInfo(paramVO);
        String customerRegNo = "";
        String customerStep = "G006001";
        String customerRegStatus = "";
        if(rsMbrRegVO != null){
            customerRegNo = (String) rsMbrRegVO.getCustomerRegNo();
            customerStep = (String) rsMbrRegVO.getCustomerStep();
            customerRegStatus = (String) rsMbrRegVO.getCustomerRegStatus();
        }
        
        //1-1.DB인증정보테이블에 회원검증값이 없는 경우 insert
        if(OppfStringUtil.isEmpty(customerRegNo)){
            //1-1-1.기본정보 DB등록
            log.info("============= 1-1-1.기본정보 DB등록前 param정보=============");
            log.debug("회원가입Step(paramVO.customerStep) : {} ", paramVO.getCustomerStep());
            log.debug("회원휴대폰번호(paramVO.customerPhone) : {} ", paramVO.getCustomerPhone());
            log.debug("회원명한글(paramVO.customerNameKor) : {} ", paramVO.getCustomerNameKor());
            log.info("======================================================");
            customerRegNo = mbrRegDAO.insertSptCustomerInfoProfile(paramVO);
            log.debug("1-1-1.기본정보 DB등록後:customerRegNo : {} ", customerRegNo);
            rs1++;
            paramVO.setCustomerRegNo(customerRegNo);
            
            //1-1-2.인증 DB등록
            log.info("============= 1-1-2.인증 DB등록前 param정보=============");
            log.debug("회원OP등록번호(paramVO.customerRegNo) : {} ", paramVO.getCustomerRegNo());
            log.debug("회원검증값(paramVO.customerVerify) : {} ", paramVO.getCustomerVerify());
            log.debug("회원검증종류(paramVO.customerVerifyType) : {} ", paramVO.getCustomerVerifyType());
            log.info("===================================================");
            rs2 = mbrRegDAO.insertSptCustomerVerifyProfile(paramVO);
            log.debug("1-1-2.인증 DB등록後:rs2 : {} ", rs2);
        
        }else if("G005004".equals(customerRegStatus)){ //탈퇴회원의 경우
            //1-1-1.기본정보 DB등록
            log.info("============= 1-1-1.기본정보 DB등록前 param정보=============");
            log.debug("회원가입Step(paramVO.customerStep) : {} ", paramVO.getCustomerStep());
            log.debug("회원휴대폰번호(paramVO.customerPhone) : {} ", paramVO.getCustomerPhone());
            log.debug("회원명한글(paramVO.customerNameKor) : {} ", paramVO.getCustomerNameKor());
            log.info("======================================================");
            customerRegNo = mbrRegDAO.insertSptCustomerInfoProfile(paramVO);
            log.debug("1-1-1.기본정보 DB등록後:customerRegNo : {} ", customerRegNo);
            rs1++;
            paramVO.setCustomerRegNo(customerRegNo);
            
            //1-1-2.인증 DB등록
            log.info("============= 1-1-2.인증 DB등록前 param정보=============");
            log.debug("회원OP등록번호(paramVO.customerRegNo) : {} ", paramVO.getCustomerRegNo());
            log.debug("회원검증값(paramVO.customerVerify) : {} ", paramVO.getCustomerVerify());
            log.debug("회원검증종류(paramVO.customerVerifyType) : {} ", paramVO.getCustomerVerifyType());
            log.info("===================================================");
            rs2 = mbrRegDAO.insertSptCustomerVerifyProfile(paramVO);
            log.debug("1-1-2.인증 DB등록後:rs2 : {} ", rs2);
        
        //1-2.그외의 경우(DB인증정보테이블에 회원검증값이 있는 경우 update)
        }else{
            
            //1-2-1.기본정보 DB수정
            customerRegNo = rsMbrRegVO.getCustomerRegNo();
            paramVO.setCustomerRegNo(rsMbrRegVO.getCustomerRegNo());
            log.info("============= 1-2-1.기본정보 DB수정前 param정보=============");
            log.debug("회원가입Step(paramVO.customerStep) : {} ", paramVO.getCustomerStep());
            log.debug("회원휴대폰번호(paramVO.customerPhone) : {} ", paramVO.getCustomerPhone());
            log.debug("회원명한글(paramVO.customerNameKor) : {} ", paramVO.getCustomerNameKor());
            log.info("======================================================");
            paramVO.setCustomerStep(null);
            rs1 = mbrRegDAO.updateSptCustomerInfoProfile(paramVO);
            log.debug("1-2-1.기본정보 DB수정後:rs1 : {} ", rs1);
            
            //1-2-2.인증 DB수정
            log.info("============= 1-2-2.인증 DB수정前 param정보=============");
            log.debug("회원OP등록번호(paramVO.customerRegNo) : {} ", paramVO.getCustomerRegNo());
            log.debug("회원검증값(paramVO.customerVerify) : {} ", paramVO.getCustomerVerify());
            log.debug("회원검증종류(paramVO.customerVerifyType) : {} ", paramVO.getCustomerVerifyType());
            log.info("===================================================");
            rs2 = mbrRegDAO.updateSptCustomerVerifyProfile(paramVO);
            log.debug("1-2-2.인증 DB수정後:rs2 : {} ", rs2);
        }
        
        //2.기본정보hist DB등록
        log.info("============= 2.기본정보hist DB등록前 param정보=============");
        log.debug("회원OP등록번호(paramVO.customerRegNo) : {} ", paramVO.getCustomerRegNo());
        log.info("======================================================");
        paramVO.setCustomerRegNo(customerRegNo);
        rs3 = mbrRegDAO.insertSptCustomerInfoProfileHist(paramVO);
        log.debug("2.기본정보hist DB등록後:rs3 : {} ", rs3);
        
        //3.인증hist DB등록
        log.info("============= 3.인증hist DB등록前 param정보===============");
        log.debug("회원OP등록번호(paramVO.customerRegNo) : {} ", paramVO.getCustomerRegNo());
        log.debug("회원검증종류(paramVO.customerVerifyType) : {} ", paramVO.getCustomerVerifyType());
        log.info("======================================================");
        rs4 = mbrRegDAO.insertSptCustomerVerifyProfileHist(paramVO);
        log.debug("3.인증hist DB등록後:rs4 : {} ", rs4);
        
        //4.회원가입스텝update
        if(rsMbrRegVO == null){
            MbrRegVO stepVO = new MbrRegVO();
            stepVO.setCustomerRegNo(paramVO.getCustomerRegNo());
            stepVO.setCustomerStep("G006002");
            customerStep = "G006002";
            log.info("============= 4.회원가입스텝update DB수정前 param정보=============");
            log.debug("회원OP등록번호(stepVO.customerRegNo) : {} ", stepVO.getCustomerRegNo());
            log.debug("회원가입Step(stepVO.customerStep) : {} ", stepVO.getCustomerStep());
            log.info("===========================================================");
            rs5 = mbrRegDAO.updateSptCustomerInfoProfile(stepVO);
            log.debug("4.회원가입스텝update後:rs5 : {} ", rs5);
        }else if("G005004".equals(customerRegStatus)){ //탈퇴회원의 경우
            MbrRegVO stepVO = new MbrRegVO();
            stepVO.setCustomerRegNo(paramVO.getCustomerRegNo());
            stepVO.setCustomerStep("G006002");
            customerStep = "G006002";
            log.info("============= 4.회원가입스텝update DB수정前 param정보=============");
            log.debug("회원OP등록번호(stepVO.customerRegNo) : {} ", stepVO.getCustomerRegNo());
            log.debug("회원가입Step(stepVO.customerStep) : {} ", stepVO.getCustomerStep());
            log.info("===========================================================");
            rs5 = mbrRegDAO.updateSptCustomerInfoProfile(stepVO);
            log.debug("4.회원가입스텝update後:rs5 : {} ",  rs5);
        }
        
        rs = rs1 + rs2 + rs3 + rs4 + rs5;
        log.debug("procMbrReg1:rs : {} ", rs);
        
        //5.리턴값셋팅
        returnMbrRegVO.setCustomerRegNo(customerRegNo);
        returnMbrRegVO.setCustomerStep(customerStep);
        log.info("============= 5.리턴값셋팅 정보=================================");
        log.debug("회원OP등록번호(returnMbrRegVO.customerRegNo) : {}", returnMbrRegVO.getCustomerRegNo());
        log.debug("회원가입Step(returnMbrRegVO.customerStep) : {}", returnMbrRegVO.getCustomerStep());
        log.info("===========================================================");
        log.info("------------- procMbrReg1 END --------------------------");
        return returnMbrRegVO;
    }
    
    /**
     * @Method Name        : procMbrReg2
     * @Method description : [회원가입:2.공인인증서등록]정보 처리를 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int procMbrReg2(MbrRegVO paramVO) throws Exception{
        log.info("------------- procMbrReg2 START ------------------------");
        int rs = 0;  //전체
        int rs1 = 0; //1.약관&약관Hist
        int rs2 = 0; //2.회원가입스텝update
        int rs3 = 0; //3.공인인증서
        int rs4 = 0; //4.공인인증서Hist
        
        //1.약관&약관hist DB등록
        rs1 = insertSptCustomerTermsProfile(paramVO);
        log.debug("1.약관&약관hist DB등록後:rs1 : {}", rs1);
        
        //2.회원가입스텝update
        MbrRegVO stepVO = new MbrRegVO();
        stepVO.setCustomerRegNo(paramVO.getCustomerRegNo());
        stepVO.setCustomerStep("G006003");
        rs2 = mbrRegDAO.updateSptCustomerInfoProfile(stepVO);
        log.debug("2.회원가입스텝update後:rs2 : {} ", rs2);
        
        //3.공인인증서
        MbrRegVO pMbrRegVO = new MbrRegVO();
        pMbrRegVO.setCustomerVerifyType(paramVO.getCustomerVerifyType());
        pMbrRegVO.setCustomerRegNo(paramVO.getCustomerRegNo());
        pMbrRegVO.setCustomerVerify(paramVO.getCustomerVerify());
        log.debug("3.공인인증서:회원OP등록번호(pMbrRegVO.customerRegNo) : {} ", pMbrRegVO.getCustomerRegNo());
        log.debug("3.공인인증서:회원검증값(pMbrRegVO.customerVerify) : {} ", pMbrRegVO.getCustomerVerify());
        log.debug("3.공인인증서:회원검증종류(pMbrRegVO.customerVerifyType) : {} ", pMbrRegVO.getCustomerVerifyType());
        rs3 = mbrRegDAO.insertSptCustomerVerifyProfile(pMbrRegVO);
        
        //4.공인인증서Hist
        log.debug("4.공인인증서Hist:회원OP등록번호(pMbrRegVO.customerRegNo) : {} ", pMbrRegVO.getCustomerRegNo());
        log.debug("4.공인인증서Hist:회원검증종류(pMbrRegVO.customerVerifyType) : {} ", pMbrRegVO.getCustomerVerifyType());
        rs4 = mbrRegDAO.insertSptCustomerVerifyProfileHist(pMbrRegVO);
        
        rs = rs1 + rs2;
        log.debug("procMbrReg2:rs : {} ", rs);
        log.info("------------- procMbrReg2 END --------------------------");
        return rs;
    }
    
    /**
     * @Method Name        : procMbrReg3
     * @Method description : [회원가입:3.약관동의]정보 처리를 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int procMbrReg3(MbrRegVO paramVO) throws Exception{
        log.info("------------- procMbrReg3 START ------------------------");
        int rs = 0;  //전체
        int rs1 = 0; //1.약관&약관hist
        int rs2 = 0; //2.회원가입스텝update
        
        //1.약관&약관hist DB등록
        rs1 = insertSptCustomerTermsProfile(paramVO);
        log.debug("1.약관&약관hist DB등록後:rs1 : {} ", rs1);
        
        //2.회원가입스텝update
        MbrRegVO stepVO = new MbrRegVO();
        stepVO.setCustomerRegNo(paramVO.getCustomerRegNo());
        stepVO.setCustomerStep("G006004");
        rs2 = mbrRegDAO.updateSptCustomerInfoProfile(stepVO);
        log.debug("2.회원가입스텝update後:rs2 : {} ", rs2);
        
        rs = rs1 + rs2;
        log.debug("procMbrReg3:rs : {} ", rs);
        log.info("------------- procMbrReg3 END --------------------------");
        return rs;
    }
    
    
    /**
     * @Method Name        : procMbrReg4
     * @Method description : [회원가입:4.개인정보입력]정보 처리를 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int procMbrReg4(MbrRegVO paramVO) throws Exception{
        log.info("------------- procMbrReg4 START ------------------------");
        int rs = 0;
        int rs1 = 0; //1.기본정보 DB수정
        int rs2 = 0; //2.기본정보hist DB등록
        int rs3 = 0; //3.이메일발송정보 DB등록
        int rs4 = 0; //4.
        
        int rs5 = 0; //otp관련
        
        //1.기본정보 DB수정
        paramVO.setCustomerRegStatus("G005002");	//활성화로 셋팅
        rs1 = mbrRegDAO.updateSptCustomerInfoProfile(paramVO);
        log.debug("1.기본정보 DB수정後:rs1 : {} ", rs1);
        
        //2.기본정보hist DB등록
        rs2 = mbrRegDAO.insertSptCustomerInfoProfileHist(paramVO);
        log.debug("2.기본정보hist DB등록後:rs2 : {} ", rs2);
        
        //3.이메일발송정보 DB등록
        log.debug("3.기본정보hist DB등록後:rs3 : {}", rs3);
        
        //4.회원가입스텝update
        MbrRegVO stepVO = new MbrRegVO();
        stepVO.setCustomerRegNo(paramVO.getCustomerRegNo());
        stepVO.setCustomerStep("G006005");
        stepVO.setCustomerRegStatus("G005002");	//활성화로 셋팅
        rs4 = mbrRegDAO.updateSptCustomerInfoProfile(stepVO);
        log.debug("4.회원가입스텝update後:rs4 : {}", rs4);
                        
        rs = rs1 + rs2;
        log.debug("rs : {} ", rs);
        log.info("------------- procMbrReg4 END --------------------------");
        return rs;
    }
        
    /**
     * @Method Name        : procMbrReg5
     * @Method description : [회원가입:5.이메일인증]정보 처리를 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int procMbrReg5(MbrRegVO paramVO) throws Exception{
        log.info("------------- procMbrReg5 START ------------------------");
        int rs = 0;
        
        //1.?
        
        log.info("------------- procMbrReg5 END --------------------------");
        return rs;
    }
    
    /**
     * @Method Name        : selectCheckId
     * @Method description : [회원가입:ID중복확인]ID정보를 조회한다.
     * @param              : String
     * @return             : String
     * @throws             : Exception
     */
    public String selectCheckId(MbrRegVO paramVO) throws Exception{
        String rs = mbrRegDAO.selectCheckId(paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : selectSptCustomerInfoProfile
     * @Method description : [회원가입:기본]정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    public MbrRegVO selectSptCustomerInfoProfile(MbrRegVO paramVO) throws Exception{
        return (MbrRegVO) mbrRegDAO.selectSptCustomerInfoProfile(paramVO);
    }

    /**
     * @Method Name        : selectSptCustomerVerifyProfile
     * @Method description : [회원가입:인증]정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    public MbrRegVO selectSptCustomerVerifyProfile(MbrRegVO paramVO) throws Exception{
        return (MbrRegVO) mbrRegDAO.selectSptCustomerVerifyProfile(paramVO);
    }

    /**
     * @Method Name        : selectSptWithDrawCustomerVerifyProfile
     * @Method description : 탈퇴회원 인증 정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    public int selectSptWithDrawCustomerVerifyProfile(MbrRegVO paramVO) throws Exception{
        return mbrRegDAO.selectSptWithDrawCustomerVerifyProfile(paramVO);
    }
    
    /**
     * @Method Name        : selectSptCustomerVerifyProfileList
     * @Method description : [회원가입:인증목록]정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : List<MbrRegVO>
     * @throws             : Exception
     */
    public List<MbrRegVO> selectSptCustomerVerifyProfileList(MbrRegVO paramVO) throws Exception{
        return (List<MbrRegVO>) mbrRegDAO.selectSptCustomerVerifyProfileList(paramVO);
    }
    

    /**
     * @Method Name        : selectSptCustomerCiInfo
     * @Method description : [회원정보:기본&인증]정보를 조회한다.
     * @param paramVO
     * @return
     */
	public MbrRegVO selectSptCustomerCiInfo(AccListAttributeREQVO paramVO) throws Exception{
        return mbrRegDAO.selectSptCustomerCiInfo(paramVO);
    }
	
    /**
     * @Method Name        : selectSptCustomerVerifyProfile
     * @Method description : [회원가입:약관]정보를 조회한다.
     * @param              : MbrRegTermsVO
     * @return             : MbrRegTermsVO
     * @throws             : Exception
     */
    public MbrRegVO selectSptCustomerTermsProfile(MbrRegTermsVO paramVO) throws Exception{
        return (MbrRegVO) mbrRegDAO.selectSptCustomerTermsProfile(paramVO);
    }
    
    /**
     * @Method Name        : selectSptCustomerTermsContentInfo
     * @Method description : [회원가입:약관내용]정보를 조회한다.
     * @param              : MbrRegTermsContentVO
     * @return             : MbrRegTermsContentVO
     * @throws Exception 
     */
    public MbrRegTermsContentVO selectSptCustomerTermsContentInfo(MbrRegTermsContentVO paramVO) throws Exception{
        return (MbrRegTermsContentVO) mbrRegDAO.selectSptCustomerTermsContentInfo(paramVO);
    }

    /**
     * @Method Name        : selectSptCustomerTermsContentList
     * @Method description : [회원가입:약관내용목록]정보를 조회한다.
     * @param              : MbrRegTermsContentVO
     * @return             : List<MbrRegTermsContentVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<MbrRegTermsContentVO> selectSptCustomerTermsContentList(MbrRegTermsContentVO paramVO) throws Exception{
        return (List<MbrRegTermsContentVO>) mbrRegDAO.selectSptCustomerTermsContentList(paramVO);
    }

    /**
     * @Method Name        : selectSptCustomerTermsContentList
     * @Method description : [회원가입:약관내용목록]정보를 조회한다.
     * @param              : MbrRegTermsContentVO
     * @return             : List<MbrRegTermsContentVO>
     * @throws             : Exception
     */
    @SuppressWarnings("unchecked")
    public List<MbrRegTermsVO> selectCustomerTermsContentList(MbrRegTermsContentVO paramVO) throws Exception{
        return (List<MbrRegTermsVO>) mbrRegDAO.selectCustomerTermsContentList(paramVO);
    }
    
    
    
    /**
     * @Method Name        : selectSptCustomerProfileInfo
     * @Method description : [회원가입:기본&인증&약관정보들]를 조회한다.
     * @param              : MbrRegVO
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    public MbrRegVO selectSptCustomerProfileInfo(MbrRegVO paramVO) throws Exception{
        MbrRegVO rsMbrRegVO = new MbrRegVO();
        List<MbrRegTermsVO> termsList = null;
        //1.기본&인증 정보
        rsMbrRegVO = mbrRegDAO.selectSptCustomerProfileInfo(paramVO);
        
        if(rsMbrRegVO != null){
            //2.약관정보들
            MbrRegTermsVO mbrRegTermsVO = new MbrRegTermsVO();
            String customerRegNo = rsMbrRegVO.getCustomerRegNo();
            if(!OppfStringUtil.isEmpty(customerRegNo)){
                mbrRegTermsVO.setCustomerRegNo(customerRegNo);
                termsList = mbrRegDAO.selectSptCustomerTermsProfileList(mbrRegTermsVO);
            }
            if(termsList != null){
                rsMbrRegVO.setCustomerTermsList(termsList);
            }
        }
        
        return rsMbrRegVO;
    }
    
    /**
     * @Method Name        : insertSptCustomerInfoProfile
     * @Method description : [회원가입:기본]정보등록을 한다.
     * @param              : MbrRegVO
     * @return             : String customerRegNo(회원OP등록번호)
     * @throws             : Exception
     */
    @Transactional
    public String insertSptCustomerInfoProfile(MbrRegVO paramVO) throws Exception{
        String customerRegNo = (String) mbrRegDAO.insertSptCustomerInfoProfile(paramVO);
        return customerRegNo;
    }
    
    /**
     * @Method Name        : insertSptCustomerInfoProfileHist
     * @Method description : [회원가입:기본정보history]등록을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int insertSptCustomerInfoProfileHist(MbrRegVO paramVO) throws Exception{
        int rs = mbrRegDAO.insertSptCustomerInfoProfileHist(paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : insertSptCustomerVerifyProfile
     * @Method description : [회원가입:인증]정보등록을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int insertSptCustomerVerifyProfile(MbrRegVO paramVO) throws Exception{
        int rs = mbrRegDAO.insertSptCustomerVerifyProfile(paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : insertSptCustomerTermsProfile
     * @Method description : [회원가입:약관]정보등록을 한다.
     * @param              : MbrRegTermsVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int insertSptCustomerTermsProfile(MbrRegVO paramVO) throws Exception{
        int rs = 0;
        List<MbrRegTermsVO> paramTermsList = paramVO.getCustomerTermsList();
        if(paramTermsList.size() > 0){
            for(int i=0; i<paramTermsList.size(); i++){
                MbrRegTermsVO mbrRegTermsVO = (MbrRegTermsVO) paramTermsList.get(i); 
                mbrRegTermsVO.setCustomerRegNo(paramVO.getCustomerRegNo());
                if(!OppfStringUtil.isEmpty(paramVO.getCustomerTermsAuthYn())){
                	mbrRegTermsVO.setCustomerTermsAuthYn(paramVO.getCustomerTermsAuthYn());
                }
                log.debug("customerRegNo["+i+"]="+paramVO.getCustomerRegNo());
                log.debug("customerTermsType["+i+"]="+mbrRegTermsVO.getCustomerTermsType());
                log.debug("customerTermsContentRegSeq["+i+"]="+mbrRegTermsVO.getCustomerTermsContentRegSeq());
                log.debug("customerTermsAuthYn["+i+"]="+mbrRegTermsVO.getCustomerTermsAuthYn());
                
                //1.약관 DB등록
                int rs1 = mbrRegDAO.insertSptCustomerTermsProfile(mbrRegTermsVO);
                
                //2.약관hist DB등록
                int rs2 = mbrRegDAO.insertSptCustomerTermsProfileHist(mbrRegTermsVO);
                
                log.debug("insertSptCustomerTermsProfile:rs1["+i+"]="+rs1);
                log.debug("insertSptCustomerTermsProfileHist:rs2["+i+"]="+rs2);
                
                rs += rs1 + rs2;
            }
        }
        log.debug("insertSptCustomerTermsProfile:rs="+rs);
        return rs;
    }
    
    /**
     * @Method Name        : saveSptCustomerTermsProfile
     * @Method description : [회원가입:약관]정보저장을 한다.
     * @param              : MbrRegTermsVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveSptCustomerTermsProfile(MbrRegVO paramVO) throws Exception{
        int rs = 0;
        
        MbrRegTermsVO paramMbrRegTermsVO = new MbrRegTermsVO();
        paramMbrRegTermsVO.setCustomerRegNo(paramVO.getCustomerRegNo());
        List<MbrRegTermsVO> termsList = mbrRegDAO.selectSptCustomerTermsProfileList(paramMbrRegTermsVO);
        
        //등록된정보가 있다면 update,없다면insert 로직구현예정
        
        List<MbrRegTermsVO> paramTermsList = paramVO.getCustomerTermsList();
        if(paramTermsList.size() > 0){
            for(int i=0; i<paramTermsList.size(); i++){
                MbrRegTermsVO mbrRegTermsVO = (MbrRegTermsVO) paramTermsList.get(i); 
                log.debug("customerRegNo["+i+"]="+mbrRegTermsVO.getCustomerRegNo());
                log.debug("customerTermsType["+i+"]="+mbrRegTermsVO.getCustomerTermsType());
                log.debug("customerTermsAuthYn["+i+"]="+mbrRegTermsVO.getCustomerTermsAuthYn());
                
                //1.약관 DB등록
                int rs1 = mbrRegDAO.insertSptCustomerTermsProfile(mbrRegTermsVO);
                
                //2.약관hist DB등록
                int rs2 = mbrRegDAO.insertSptCustomerTermsProfileHist(mbrRegTermsVO);
                
                log.debug("insertSptCustomerTermsProfile:rs1["+i+"]="+rs1);
                log.debug("insertSptCustomerTermsProfileHist:rs2["+i+"]="+rs2);
                
                rs += rs1 + rs2;
            }
        }
        log.debug("insertSptCustomerTermsProfile:rs : {}", rs);
        return rs;
    }
    
    /**
     * @Method Name        : updatesptCustomerInfoProfile
     * @Method description : [회원가입:기본]정보수정을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateSptCustomerInfoProfile(MbrRegVO paramVO) throws Exception{
        int rs = mbrRegDAO.updateSptCustomerInfoProfile(paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : updatesptCustomerInfoProfile
     * @Method description : [회원가입:인증]정보수정을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateSptCustomerVerifyProfile(MbrRegVO paramVO) throws Exception{
        int rs = mbrRegDAO.updateSptCustomerVerifyProfile(paramVO);
        return rs;
    }
    
    /**
     * @Method Name        : updateSptCustomerTermsProfile
     * @Method description : [회원가입:약관]정보수정을 한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int updateSptCustomerTermsProfile(MbrRegVO paramVO) throws Exception{
        int rs = 0;
        List<MbrRegTermsVO> paramTermsList = paramVO.getCustomerTermsList();
        if(paramTermsList.size() > 0){
            for(int i=0; i<paramTermsList.size(); i++){
                MbrRegTermsVO mbrRegTermsVO = (MbrRegTermsVO) paramTermsList.get(i); 
                log.debug("customerRegNo["+i+"]="+mbrRegTermsVO.getCustomerRegNo());
                log.debug("customerTermsType["+i+"]="+mbrRegTermsVO.getCustomerTermsType());
                log.debug("customerTermsContentRegSeq["+i+"]="+mbrRegTermsVO.getCustomerTermsContentRegSeq());
                log.debug("customerTermsAuthYn["+i+"]="+mbrRegTermsVO.getCustomerTermsAuthYn());
               
                //1.약관 DB수정
                int rs1 = mbrRegDAO.updateSptCustomerTermsProfile(mbrRegTermsVO);
                
                //2.약관hist DB등록
                int rs2 = mbrRegDAO.insertSptCustomerTermsProfileHist(mbrRegTermsVO);
                
                log.debug("insertSptCustomerTermsProfile:rs1["+i+"]="+rs1);
                log.debug("insertSptCustomerTermsProfileHist:rs2["+i+"]="+rs2);
                
                rs += rs1 + rs2;
            }
        }
        log.debug("updateSptCustomerTermsProfile:rs : {}", rs);
        return rs;
    }
    
    /**
     * @Method Name        : selectMainSptCustomerTermsList
     * @Method description : [메인]메인 접속 시 해당사용자의 약관동의 정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : List<MbrRegTermsContentVO>
     * @throws             : Exception
     */
    public List<MbrRegTermsContentVO> selectMainSptCustomerTermsList(MbrRegVO paramVO) throws Exception{
    	return mbrRegDAO.selectMainSptCustomerTermsList(paramVO);
    }
    
    /**
     * @Method Name        : saveCustomerTermsListPopup
     * @Method description : [메인-로그인시]변경약관 (재)동의 팝업 저장
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public int saveCustomerTermsListPopup(MbrRegVO paramVO) throws Exception{
        int rs = 0;

        log.debug("paramVO.getCustomerTermsList().size() : {}", paramVO.getCustomerTermsList().size());
        log.debug("paramVO.getCompanyTermsList().size() : {}", paramVO.getCompanyTermsList().size());
        
        List<MbrRegTermsVO> paramTermsList = paramVO.getCustomerTermsList();
        if(paramTermsList != null && paramTermsList.size() > 0){
            for(int i=0; i<paramTermsList.size(); i++){
                MbrRegTermsVO mbrRegTermsVO = (MbrRegTermsVO) paramTermsList.get(i);
                mbrRegTermsVO.setCustomerRegNo(paramVO.getCustomerRegNo());
                                
                log.debug("customerRegNo["+i+"]="+mbrRegTermsVO.getCustomerRegNo());
                log.debug("customerTermsType["+i+"]="+mbrRegTermsVO.getCustomerTermsType());
                log.debug("customerTermsContentRegSeq["+i+"]="+mbrRegTermsVO.getCustomerTermsContentRegSeq());
                log.debug("customerTermsAuthYn["+i+"]="+mbrRegTermsVO.getCustomerTermsAuthYn());
                
                int chk = mbrRegDAO.checkMainSptCustomerTerms(mbrRegTermsVO);
                int rs1 = 0;
                
                //기존정보가 있으면 update
                if(chk > 0){
                	rs1 = mbrRegDAO.updateSptCustomerTermsProfile(mbrRegTermsVO);
                //없으면 insert
                }else{
                	rs1 = mbrRegDAO.insertSptCustomerTermsProfile(mbrRegTermsVO);
                }
                
                //2.약관hist DB등록
                int rs2 = mbrRegDAO.insertSptCustomerTermsProfileHist(mbrRegTermsVO);
                
                log.debug("saveCustomerTermsListPopup:rs1["+i+"]="+rs1);
                log.debug("insertSptCustomerTermsProfileHist:rs2["+i+"]="+rs2);
                
                rs += rs1 + rs2;
            }
        }
        log.debug("saveCustomerTermsListPopup:rs : {}", rs);
        

        int rs3 = 0;
        int rs4 = 0;
		if(paramVO.getCompanyTermsList() != null){
			if(paramVO.getCompanyTermsList().size() > 0){
				for (MbrRegTermsVO terms : paramVO.getCompanyTermsList()) {
					terms.setCustomerRegNo(paramVO.getCustomerRegNo());
					int cnt = mbrRegDAO.checkCompanyTermsProfile(terms);
					if(cnt > 0){
						rs3 = mbrRegDAO.updateCompanyTermsProfile(terms);
					}else{
						rs3 = mbrRegDAO.insertCompanyTermsProfile(terms);
					}

					if(rs > 0){
						mbrRegDAO.insertCompanyTermsProfileHist(terms);
					}

	                rs += rs3;
				}

			}
		}
		return rs;
    }

    /**
     * @Method Name        : selectSptCustomerProfileInfo
     * @Method description : [회원정보:기본&인증]정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : MbrRegVO
     * @throws             : Exception
     */
    @Override
    public MbrRegVO selectSptCustomerVerifyProfileInfo(MbrRegVO paramVO) throws Exception {
        return (MbrRegVO) mbrRegDAO.selectSptCustomerVerifyProfileInfo(paramVO);
       
    }

    /**
     * @Method Name        : selectMobileTermsList
     * @Method description : 통신사 약관 정보를 조회한다.
     * @param              : 
     * @return             : Map<String, Object>
     * @throws             : Exception
     */
    @Override
    public Map<String, Object> selectMobileTermsList() throws Exception{

        Map<String, Object> body = new HashMap<>();
        try{

        	String mobileCopany = "";
        	for(int t=0; t<3; t++){

                List<MobileTermsVO> termsList = new ArrayList();

        		if(t==0) mobileCopany = "skm";
        		if(t==1) mobileCopany = "ktm";
        		if(t==2) mobileCopany = "lgm";
        		
	        	for(int i=1; i<5; i++){
		            Document doc = Jsoup.connect("http://203.234.219.124/app/agree/app_agree_m_"+mobileCopany+".jsp?gubun=0"+i).get(); 
		            String div = doc.getElementsByTag("body").toString();
		            div = div.replaceAll("<body>", "");
		            div = div.replaceAll("</body>", "");
/*		            div = div.replaceAll("<br />", "");
		            div = div.replaceAll("<strong>", "");
		            div = div.replaceAll("</strong>", "");
		            div = div.replaceAll("&quot;", "\"");
*/		            
		            MobileTermsVO term = new MobileTermsVO();
		            term.setMobileTermsContent(div);
		            term.setMobileTermsType("G04200"+i);

		            /** 임시 하드 코딩 **/
		        	if(i==1) term.setMobileTermsSubject("개인정보 수집 이용 동의"); // 개인정보이용동의
		        	if(i==2) term.setMobileTermsSubject("고유식별정보 처리 동의"); // 고유식별정보 처리 동의
		        	if(i==3) term.setMobileTermsSubject("통신사 이용약관 동의"); // 통신사 이용약관 동의
		        	if(i==4) term.setMobileTermsSubject("서비스 이용약관 동의"); // 서비스이용약관
		            
		            termsList.add(term);	            
	        	}

	        	if(t==0) body.put("skTermsList", termsList);
	        	if(t==1) body.put("ktTermsList", termsList);
	        	if(t==2) body.put("lgTermsList", termsList);
	        	
        	}
        	
        }catch (Exception e){
        	
        }
        
        
        return body;
    }
    

    /**
     * @Method Name        : createMember
     * @Method description : [회원가입:4.개인정보입력] 정보를 저장한다.
     * @param              : MbrRegVO
     * @return             : int
     * @throws             : Exception
     */
    @Transactional
    public String createMember(MbrRegVO paramVO) throws Exception{
        log.info("------------- createMember START ------------------------");
        int rs = 0;
        int rs1 = 0; //1.기본정보 DB수정
        int rs2 = 0; //2.기본정보hist DB등록
        int rs3 = 0; //3.이메일발송정보 DB등록
        int rs4 = 0; //4.
        
        int rs5 = 0; //otp관련
        
        String customerRegNo = "";
        if(OppfStringUtil.isEmpty(paramVO.getCustomerRegNo())){
        	
        	//1.회원 가입
        	if("M".equals(paramVO.getCustomerSex())){
        		paramVO.setCustomerSex(CodeConstants.MEMBER_SEX_MALE.toString());
        	} else {
        		paramVO.setCustomerSex(CodeConstants.MEMBER_SEX_FEMALE.toString());
        	}
        	
        	customerRegNo = mbrRegDAO.insertSptCustomerInfoProfile(paramVO);
        	
        	paramVO.setCustomerRegNo(customerRegNo);
        	
        	//2.기본정보hist DB등록
        	rs2 = mbrRegDAO.insertSptCustomerInfoProfileHist(paramVO);
        	
        	//G007001
        	paramVO.setCustomerVerifyType(CodeConstants.VERIFY_TYPE_CI.toString());
        	// 인증정보 DB 등록
        	rs3 = mbrRegDAO.insertSptCustomerVerifyProfile(paramVO);
        	
        	//4.회원가입스텝update
        	paramVO.setCustomerRegNo(customerRegNo);
        	
        	rs4 = insertSptCustomerTermsProfile(paramVO);
        	
        	rs = rs1 + rs2 + rs3 + rs4;
        	
        } else {
        	

			// 비회원(임시회원 여부 N으로 변경)
        	paramVO.setTemporaryMemberYn("N");
        	customerRegNo = modifyUserInfo(paramVO);
			
        	
        }
                
        
        return customerRegNo;
    }
        

    /**
	 * 회원 정보 수정
	 * */
	@Transactional
	public String  modifyUserInfo(MbrRegVO paramVO){


		if(!OppfStringUtil.isEmpty(paramVO.getCustomerPassword())){
			paramVO.setCustomerExpirePasswordDate(OppfProperties.getProperty("Globals.user.policy.password.expire"));
			paramVO.setCustomerFinalPasswordDate(OppfProperties.getProperty("Globals.user.policy.password.final"));
		}

		paramVO.setCustomerPhone(OppfStringUtil.phoneConvertMinus(paramVO.getCustomerPhone()));

		int rs = mbrRegDAO.updateUserInfo(paramVO);
		
		
		// update 실패시 에러처리
		if (rs != 1) {
			return "";
		} else {
			//성공 시 히스토리
				mbrRegDAO.insertSptCustomerHist(paramVO);	
		}
		
		return paramVO.getCustomerRegNo();
		
	}
	

    /**
     * @Method Name        : selectMainCompanyTermsList
     * @Method description : [메인]메인 접속 시 기업 약관동의 정보를 조회한다.
     * @param              : MbrRegVO
     * @return             : List<MbrRegTermsContentVO>
     * @throws             : Exception
     */
	public List<MbrRegTermsContentVO> selectMainCompanyTermsList(MbrRegVO mbrRegVO) throws Exception{
    	return mbrRegDAO.selectMainCompanyTermsList(mbrRegVO);
    }
    
	
}

