package kr.co.koscom.oppf.apt.sif.web;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.oppf.cmm.service.LoginVO;
import kr.co.koscom.oppf.cmm.service.OppfProperties;

/**
* @Project  : 자본시장 공동 핀테크 오픈플랫폼 구축
* @FileName : SIFManageController.java
* @Comment  : SIF의 모니터링용 RESTful API를 호출하기 위한 Controller
* @author   : 신영규
* @since    : 2016.08.02
* @version  : 1.0
*
* << 개정이력(Modification Information) >>
* 수정일                수정자        수정내용
* ----------- ------  ----------
* 2016.08.02  신영규        최초생성
*
*/
@Controller
public class SIFManageController {
	private static final Logger log = Logger.getLogger(SIFManageController.class);

	/**
     * @Method Name        : getURL
     * @Method description : URL 생성
     * @param              : 
     * @return             : String
     * @throws             : 
     */
	private String getURL(String uri, String ip, String comAlias, String msgType) {
        String manageapiURL = "https://" + ip + uri + "?comNameAlias=" + comAlias + "&msgType=" + msgType;
        return manageapiURL;
	}
	
	/**
     * @Method Name        : handleHTTPsRequest
     * @Method description : 금투사 연계 서버로 요청을 보내고 응답을 받는다.
     * @param              : 
     * @return             : ResponseEntity
     * @throws             : 
     */
	@SuppressWarnings("deprecation")
	private ResponseEntity<String> handleHTTPsRequest(String url) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        SSLContextBuilder builder = new SSLContextBuilder();
        
        try{
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(),SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            requestFactory.setHttpClient(httpclient);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            
            ResponseEntity<String> responseEntity = null;
            
            restTemplate.setErrorHandler(new ResponseErrorHandler() {
                @Override
                public boolean hasError(ClientHttpResponse response) throws IOException {
                    return false;
                }
                
                @Override
                public void handleError(ClientHttpResponse response) throws IOException {
                }
            });

            responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

            // handle response
            return new ResponseEntity<String>(responseEntity.getBody(), HttpStatus.OK);
        }catch(KeyManagementException e){
            log.error(e);
            e.printStackTrace();
        }catch(NoSuchAlgorithmException e){
            log.error(e);
            e.printStackTrace();
        }catch(KeyStoreException e){
            log.error(e);
            e.printStackTrace();
        }catch(Exception e){
            log.error(e);
            e.printStackTrace(System.out);
        }
        
        return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
     * @Method Name        : reload
     * @Method description : 선택한 금투사 API를 재기동한다.
     * @param              : 
     * @return             : ResponseEntity
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sif/reload.ajax", produces = { "application/json;charset=UTF-8" }, method = {RequestMethod.POST, RequestMethod.GET})
	private ResponseEntity<String> reload(@RequestParam String ip, @RequestParam String comAlias, @RequestParam String msgType, HttpServletRequest request, ModelMap model)throws Exception{
        return handleHTTPsRequest(getURL("/reload", ip, comAlias, msgType));
	}

	/**
     * @Method Name        : disconnect
     * @Method description : 선택한 금투사 API의 연결을 끊는다.
     * @param              : 
     * @return             : ResponseEntity
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sif/disconnect.ajax", produces = { "application/json;charset=UTF-8" }, method = {RequestMethod.POST, RequestMethod.GET})
	private ResponseEntity<String> disconnect(@RequestParam String ip, @RequestParam String comAlias, @RequestParam String msgType, HttpServletRequest request, ModelMap model)throws Exception{
        return handleHTTPsRequest(getURL("/disconnect", ip, comAlias, msgType));
	}
	
	/**
     * @Method Name        : checkConnection
     * @Method description : 선택한 금투사 API의 연결상태를 확인한다.
     * @param              : 
     * @return             : ResponseEntity
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sif/checkConnection.ajax", produces = { "application/json;charset=UTF-8" }, method = {RequestMethod.POST, RequestMethod.GET})
	private ResponseEntity<String> connectionCheck(@RequestParam String ip, @RequestParam String comAlias, @RequestParam String msgType, HttpServletRequest request, ModelMap model)throws Exception{
        return handleHTTPsRequest(getURL("/connectionCheck", ip, comAlias, msgType));
	}
	
	/**
     * @Method Name        : getCompany
     * @Method description : 금투사 별칭 요청.
     * @param              : 
     * @return             : ResponseEntity
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sif/getCompany.ajax", produces = { "application/json;charset=UTF-8" }, method = {RequestMethod.POST, RequestMethod.GET})
	private ResponseEntity<String> getCompany(@RequestParam String ip, HttpServletRequest request, ModelMap model)throws Exception{
        return handleHTTPsRequest(getURL("/getCompany", ip, "", ""));
	}
	
	/**
     * @Method Name        : getMsgType
     * @Method description : 선택한 금투사의 사용중인 API목록
     * @param              : 
     * @return             : ResponseEntity
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sif/getMsgType.ajax", produces = { "application/json;charset=UTF-8" }, method = {RequestMethod.POST, RequestMethod.GET})
	private ResponseEntity<String> getMsgType(@RequestParam String ip, @RequestParam String comAlias, HttpServletRequest request, ModelMap model)throws Exception{
        return handleHTTPsRequest(getURL("/getMsgType", ip, comAlias, ""));
	}
	
	/**
     * @Method Name        : getServer
     * @Method description : 금투사 서버 IP/Port정보
     * @param              : 
     * @return             : ResponseEntity
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sif/getServer.ajax", produces = { "application/json;charset=UTF-8" }, method = {RequestMethod.POST, RequestMethod.GET})
	private ResponseEntity<String> getServer(HttpServletRequest request, ModelMap model)throws Exception{
		ObjectMapper objMapper = new ObjectMapper();
		String serverStr = OppfProperties.getProperty("Globals.sifmanageapi.ipport");
		String[] serverList = serverStr.split(",");
		return new ResponseEntity<String>(objMapper.writeValueAsString(serverList), HttpStatus.OK);
	}
	
	/**
     * @Method Name        : manageSIFAPI
     * @Method description : 금투사 연계서버의 API 관리 페이지
     * @param              : 
     * @return             : ResponseEntity
     * @throws             : Exception
     */
	@RequestMapping(value="/apt/sif/manageSIFAPI.do", method = {RequestMethod.POST, RequestMethod.GET})
	private String manageSIFAPI(HttpServletRequest request, ModelMap model)throws Exception{
        String modelView = "/apt/sif/manageSIFAPI";
		//1.로그인관련
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("LoginVO");
        if(loginVO == null){
        	model.addAttribute("error", "-1");
        	return modelView;
        }
        
	    return modelView;
	}
}