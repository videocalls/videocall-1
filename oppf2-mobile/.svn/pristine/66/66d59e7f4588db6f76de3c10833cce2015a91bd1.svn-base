package kr.co.koscom.oppfm.version.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.cmm.util.CommonUtil;
import kr.co.koscom.oppfm.commoncode.dao.CommonCodeMapper;
import kr.co.koscom.oppfm.commoncode.model.CommonCodeReq;
import kr.co.koscom.oppfm.commoncode.model.CommonCodeRes;
import kr.co.koscom.oppfm.version.service.VersionManageService;
import lombok.extern.slf4j.Slf4j;

/**
 * VersionManageServiceImpl
 * <p>
 * Created by Yoojin Han on 2017-05-17.
 */

@Service
@Slf4j
public class VersionManageServiceImpl implements VersionManageService {
    
    @Autowired
    private MessageUtil messageUtil;
    
    @Autowired
    CommonCodeMapper commonCodeMapper;

    @Value("#{config['globals.mobile.domain']}")
    String globalsMobileDomain;
    
    /* 버전확인 파일 가져오기 */
    @Override
    public CommonResponse getVersionList(String fileName) {

        CommonCodeReq commonCodeReq = new CommonCodeReq();
        commonCodeReq.setSystemGrpCode("G046");
        List<CommonCodeRes> cmmAccTypeList = commonCodeMapper.selectCommonCode(commonCodeReq);

        // 파일 읽어오기
        String fileContents = CommonUtil.readStringFromFile(fileName);
        
        JSONObject jsonObject = new JSONObject(fileContents);

        jsonObject.put("baseUrl", globalsMobileDomain);     
        
        JSONObject versionObj = jsonObject.getJSONObject("version");
        for(CommonCodeRes comCode : cmmAccTypeList){
            if( "001".equals(comCode.getSystemCode()) ) {
                versionObj.put("ios"                    , comCode.getCodeExtend1() );
                versionObj.put("iosUpdateMandatory"     , comCode.getCodeExtend2() );
                versionObj.put("iosInstallUrl"          , comCode.getCodeExtend3() );
            }if( "002".equals(comCode.getSystemCode()) ) {
                versionObj.put("android"                , comCode.getCodeExtend1() );
                versionObj.put("androidUpdateMandatory" , comCode.getCodeExtend2() );
                versionObj.put("androidInstallUrl"      , comCode.getCodeExtend3() );
                
            }
        }
        jsonObject.put("version", versionObj);     
        
        
        log.debug("globalsMobileDomain :: {}", globalsMobileDomain);
        
        Map<String, Object> body = new HashMap<>();
        body.put("serviceVersion", jsonObject.toMap());

        log.debug("jsonObject : {}", jsonObject);

        return CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS , null, body);
    }

}
