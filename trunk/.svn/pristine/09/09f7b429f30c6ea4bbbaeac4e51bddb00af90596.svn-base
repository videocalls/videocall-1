package kr.co.koscom.oppfm.commoncode.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import kr.co.koscom.oppfm.cmm.exception.CommonException;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.OppfStringUtil;
import kr.co.koscom.oppfm.commoncode.dao.CommonCodeMapper;
import kr.co.koscom.oppfm.commoncode.model.CommonCompanyProfileRes;
import kr.co.koscom.oppfm.commoncode.model.CommonCodeReq;
import kr.co.koscom.oppfm.commoncode.model.CommonCodeRes;
import kr.co.koscom.oppfm.commoncode.service.CommonCodeService;

/**
 * CommonCodeServiceImpl
 * <p>
 * Created by Yoojin Han on 2017-05-11.
 */

@Service
public class CommonCodeServiceImpl implements CommonCodeService {

    @Autowired
    private CommonCodeMapper commonCodeMapper;

    @Autowired
    private MessageUtil messageUtil;

    /**
     * 공통 코드 조회 getCommonCode
     */
    @Override
    public CommonResponse getCommonCode(CommonCodeReq commonCodeReq) {
        Map<String, Object> body = new HashMap<>();

        // 시스템 그룹 코드 description 조회
        String systemGrpCodeDesc = commonCodeMapper.selectSystemGrpCodeDesc(commonCodeReq);

        // 시스템 그룹 코드 목록 조회
        List<CommonCodeRes> commonCodeList = commonCodeMapper.selectCommonCode(commonCodeReq);

        if (!(OppfStringUtil.isEmpty(systemGrpCodeDesc))) {
            body.put("systemGrpCodeDesc", systemGrpCodeDesc);
            body.put("commonCodeList", commonCodeList);
            
        }else{
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_EXCEPTION, new String[] {"systemGrpCode"});
        }
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * 시스템 코드 상세 조회 getCommonCodeDetail
     */
    @Override
    public CommonResponse getCommonCodeDetail(CommonCodeReq commonCodeReq) {
        Map<String, Object> body = new HashMap<>();

        // 시스템 그룹 코드 description 조회
        String systemGrpCodeDesc = commonCodeMapper.selectSystemGrpCodeDesc(commonCodeReq);

        // 시스템 그룹 코드 상세 조회
        CommonCodeRes commonCodeDetail = commonCodeMapper.selectCommonCodeDetail(commonCodeReq);

        if (!(OppfStringUtil.isEmpty(systemGrpCodeDesc)) ) {
            body.put("systemGrpCodeDesc", systemGrpCodeDesc);
            
            if (!(ObjectUtils.isEmpty(commonCodeDetail))){
                body.put("commonCodeDetail", commonCodeDetail);
            }else{
                throw new CommonException(ErrorCodeConstants.NOT_FOUND_EXCEPTION, new String[] {"systemCode"});
            }
        }else{
            throw new CommonException(ErrorCodeConstants.NOT_FOUND_EXCEPTION, new String[] {"systemGrpCode"});
        }
        
        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

    /**
     * 기업 코드 조회 getCompanyCodeList
     */
    @Override
    public CommonResponse getCompanyCodeList(CommonCodeReq commonCodeReq) {
        Map<String, Object> body = new HashMap<>();
        
        // 기업 코드 목록 조회
        List<CommonCompanyProfileRes> companyCodeList = commonCodeMapper.selectCompanyCodeList(commonCodeReq);
        body.put("companyCodeList", companyCodeList);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null, body);
    }

}
