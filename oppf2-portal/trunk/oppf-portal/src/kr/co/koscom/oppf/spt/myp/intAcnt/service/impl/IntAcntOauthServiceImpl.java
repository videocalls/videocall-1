package kr.co.koscom.oppf.spt.myp.intAcnt.service.impl;

import kr.co.koscom.oppf.cmm.IntegratedAccount.service.CmmSIFInternalService;
import kr.co.koscom.oppf.cmm.service.OppfProperties;
import kr.co.koscom.oppf.spt.myp.intAcnt.service.IntAcntOauthService;
import kr.co.koscom.oppf.spt.myp.intAcnt.service.SptCustomerAcntOauthTokenVO;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;


/**
* @Project  : 코스콤 오픈플렛폼 2차
* @FileName : SptOauthLoginService.java
* @Comment  : Oauth Login service
* @author   : 이희태
* @since    : 2017.02.28
* @version  : 1.0
* @see
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2017.02.28  이희태        최초생성
*
*/
@Service("IntAcntOauthService")
public class IntAcntOauthServiceImpl implements IntAcntOauthService {
    
    @Resource(name = "IntAcntOauthDAO")
    private IntAcntOauthDAO intAcntOauthDAO;
    @Resource(name = "CmmSIFInternalService")
    private CmmSIFInternalService cmmSIFInternalService;

    /**
     * @Method Name        : selectUserOauthTokenInfo
     * @Method description : [OAuthToken]정보를 조회한다.
     * @param              : SptCustomerAcntOauthTokenVO
     * @param              : tokenCheck
     * @return             : SptCustomerAcntOauthTokenVO
     * @throws             : Exception
     */
    @Override
    public SptCustomerAcntOauthTokenVO selectCustomerAcntOauthToken(SptCustomerAcntOauthTokenVO paramVO, boolean tokenCheck) throws Exception {
        SptCustomerAcntOauthTokenVO sptCustomerAcntOauthTokenVO =  intAcntOauthDAO.selectCustomerAcntOauthToken(paramVO);
        //AccessToken 유효성 검증
        if(null != sptCustomerAcntOauthTokenVO && tokenCheck){
            //account API 헤더정보
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
            httpHeaders.add("apiKey", OppfProperties.getProperty("Globals.integrated.account.apiKey"));
            httpHeaders.add("Authorization", "Bearer " + sptCustomerAcntOauthTokenVO.getAccessToken());
            String  accountApiUrl = OppfProperties.getProperty("Globals.domain.exconn") + "/v1/daishin/account/balance/search";
            ResponseEntity<String> responseEntity = cmmSIFInternalService.sendRestTemplate(accountApiUrl, httpHeaders, HttpMethod.POST, "");
            //400 Bad Request "error_description" : "Access Token does not exist(expired, revoked, replaced, unknown...)"
            if(HttpStatus.BAD_REQUEST.equals(responseEntity.getStatusCode()) || HttpStatus.UNAUTHORIZED.equals(responseEntity.getStatusCode())){
                sptCustomerAcntOauthTokenVO = null;
            }
        }

        return sptCustomerAcntOauthTokenVO;
    }

    /**
     * @Method Name        : insertCustomerAcntOauthToken
     * @Method description : [OAuthToken]정보를 등록한다.
     * @param              : SptCustomerAcntOauthTokenVO
     * @throws             : Exception
     */
    @Override
    public String insertCustomerAcntOauthToken(SptCustomerAcntOauthTokenVO paramVO) throws Exception {
        return intAcntOauthDAO.insertCustomerAcntOauthToken(paramVO);
    }

    /**
     * @param paramVO
     * @throws : Exception
     * @Method Name        : deleteCustomerAcntOauthToken
     * @Method description : [OAuthToken]정보를 삭제한다.
     */
    @Override
    public int deleteCustomerAcntOauthToken(SptCustomerAcntOauthTokenVO paramVO) throws Exception {
        return intAcntOauthDAO.deleteCustomerAcntOauthToken(paramVO);
    }

}
