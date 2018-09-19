package kr.co.koscom.oppfm.eversafe.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.koscom.oppfm.cmm.exception.ErrorCodeConstants;
import kr.co.koscom.oppfm.cmm.message.MessageUtil;
import kr.co.koscom.oppfm.cmm.model.CommonResponse;
import kr.co.koscom.oppfm.cmm.util.CommonResponseUtil;
import kr.co.koscom.oppfm.eversafe.model.EverSafeReq;
import kr.co.koscom.oppfm.eversafe.model.EverSafeRes;
import kr.co.koscom.oppfm.eversafe.service.EverSafeService;
import lombok.extern.slf4j.Slf4j;


/**
 * @author unionman
 *
 */
@Api(tags = "EverSafe-Controller", description = "EverSafe 보안")
@RestController
@RequestMapping(value = "/apis")
@Slf4j
public class EverSafeController {
    
    @Autowired
    MessageUtil messageUtil;
    
    @Autowired
    EverSafeService everSafeService;
    
//    @Value("#{config['Globals.skverifyd.ip']}")
//    String globalsSkverifydIp;
    
    
    /**
     * EverSafe 보안 Token 확인
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "EverSafe 보안 Token 확인", response = CommonResponse.class)
    @RequestMapping(value  = "/eversafe/verifySession", method = RequestMethod.POST)
    private CommonResponse checkVerifySession(@RequestBody EverSafeReq everSafeReq, HttpServletRequest request)throws Exception{
    
        log.debug("everSafeReq :: {}" , everSafeReq);
        
        EverSafeRes everSafeRes =  everSafeService.checkVerifySession(everSafeReq);
        
        CommonResponse response = CommonResponseUtil.commonResponseData(messageUtil, ErrorCodeConstants.SUCCESS, null);
        response.getBody().put("eversafeInfo", everSafeRes );
        
        return response;

    }
        
    
    

}
