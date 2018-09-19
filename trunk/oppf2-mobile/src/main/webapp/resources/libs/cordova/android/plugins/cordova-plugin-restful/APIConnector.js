cordova.define("cordova-plugin-restful.APIConnector", function(require, exports, module) {

	var exec = require('cordova/exec');
	var restExport = {};

	/* HTTP(s) 내부 오류 코드 정의 */
    restExport.HTTP_CODE_TIMEOUT = 6000;    /* Connection/Read Timeout */
    restExport.HTTP_CODE_PROTOCAL = 6001;   /* Protocol 오류 발생 */
    restExport.HTTP_CODE_OPEN = 6003;   /* 서버 연결 오류 발생 */
    restExport.HTTP_CODE_NETWORK_NONE = 6004;   /* 네크워크에 연결되어 있지 않습니다 */
    restExport.HTTP_CODE_SSL_SOCKET = 6010; /* SSL SOCKET 관련 오류 발생 */
    restExport.HTTP_CODE_SSL_CERTIFICATION = 6011;  /* SSL 관련 오류 발생 */
    restExport.HTTP_CODE_BAD_URL = 6100;    /*  잘못된 서버 URL 사용 시 오류 발생 */
    restExport.HTTP_CODE_PARAMETER	= 6101; /* 잘못된 Request Parameter 사용 시 오류 발생 */
    restExport.HTTP_CODE_SEND_IO = 6200;    /* 서버 Request 데이터 Send-IO 오류 발생 */
    restExport.HTTP_CODE_READ_IO = 6300;    /* 서버 응답 데이터 Read-IO 오류 발생 */
    restExport.HTTP_CODE_UNKNOWN = 6400;    /* HTTP(s) 내부 오류 코드 Unknown */
    restExport.HTTP_CODE_CANCEL = 6500;    /* Request cancel */
    restExport.HTTP_CODE_UNKNOWN_SERVICE = 6700;    /* 서비스 데이터 정의에서 해당 서비스 정보를 찾을 수 없음 */

    /**
     * Service 메타정보에서 sId에 해달하는 서비스를 찾아 
     * HTTP(s) 서비스 요청을 전송한다.
     * @param sId string, 서비스 아이디
     * @param header json-object, 서버에 전송할 header 데이터
     * @param data json-object, 서버에 전송할 데이터
     * @param timeout long, request time-out. 1/1000 초 
     * @param successCallback function(string), 서비스 성공 응답을 수신하기 위한 callback 
     * @param errorCallback function(string), 서비스 오류 응답을 수신하기 위한 callback 
     * <pre>
     * - code : int
     * - message : string
     * </pre>
     */
    restExport.doRequest = function(sId, header, data, timeout, successCallback, errorCallback) {
         header = header || {};
         data = data || {};
         timeout = timeout || (10 * 1000);
		 var args = [sId, header, data, timeout];
		 exec(successCallback, errorCallback, "Api", "doRequest", args );
	};

	module.exports = restExport;
});