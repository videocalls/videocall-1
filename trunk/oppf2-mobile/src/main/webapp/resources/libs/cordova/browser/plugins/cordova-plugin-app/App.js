cordova.define("cordova-plugin-app.App", function(require, exports, module) {
               
               
    var exec = require('cordova/exec');

    var appExport = {};

    appExport.CODE_SUCCESS = 2000;
	appExport.CODE_CANCEL = -1;
	appExport.CODE_FAIL_FINTECH_REJECT = "4001";
    
    appExport.CODE_NETWORK_NONE = 0;
    appExport.CODE_NETWORK_WIFI = 1;
    appExport.CODE_NETWORK_MOBILE = 2;
    
    /**
	 * 업부 페이지에서 등록한 Push Message 수신 리스너
	 */
    var regPushListener = null;
    /**
	 *  Native로 부터 수신한 Push Message. String
	 */
    var regPushData = null;
    
    /**
	 * 업부 페이지에서 등록한 Push Message 수신 리스너
	 */
    var regPushListener = null;
    /**
	 *  Native로 부터 수신한 Push Message. String
	 */
    var regPushData = null;
    
    /**
     * App to App 요청을 수신하기 위한 콜백 리스너 등록
     * @param callback function(string)
     * <pre> 
     *  - fn : String, 요청을 구분하기 위한 이름 
     *  - data " json-object 
     * <pre>
     * @param delayMilisecond int, 리스너 등록 지연 시간을 1/1000 초 단위로 지정한다, 0 ~ 1000(1초)
     */
	appExport.fintechListener = function(callback, delayMilisecond) {

        delayMilisecond = delayMilisecond || 0;

	    if(delayMilisecond < 0) {
            delayMilisecond = 0;
        }
        else if(delayMilisecond > 1000) {
            delayMilisecond = 1000; // 최대 1초 대기
        }

        regFintechListener = callback;
        /*브라우저는 기능 제공함. 
        if(regFintechData) {

            window.setTimeout(function(){
                fintechReqListener(regFintechData);
                regFintechData = null;
            },delayMilisecond);
        }
		*/
	};


	/**
     * App to App 요청에 대한 처리 결과를 전달
     * @param code String, 서비스에서 정의한 code
     * @param message String, 서비스 처리 메세지
     * @param data JSON Object, 처리 결과
     * @param callback function, 처리 결과 전송 직후 호출 받을 callback, 필요 없으면 null 
     */
	appExport.fintechSuccess = function(code, message, data, callback) {

	    if((data instanceof Object) == false) {
	        return;
	    }

	    callback = callback || function() {}
		var args = [code, message, data];

		/*브라우저는 기능 제공함. 
		exec(callback, function(){}, "App", "fintechResponse", args);
		*/
	};

	/**
     * App to App 요청에 대한 처리 실패를 전달
     * @param code String, 서비스에서 정의한 code
     * @param message String, 서비스 처리 메세지
     * @param callback function, 처리 결과 전송 직후 호출 받을 callback, 필요 없으면 null 
     */
	appExport.fintechFail = function(code, message, callback) {

        var args = [code, message];

        callback = callback || function() {}
        /*브라우저는 기능 제공함. 
        exec(callback, function(){}, "App", "fintechResponse", args);
        */
    };

    /**
     * Push Message를 수신하기 위한 콜백 리스너 등록
     * @param callback function(string)
     * @param delayMilisecond int, 리스너 등록 지연 시간을 1/1000 초 단위로 지정한다, 0 ~ 1000(1초)
     */
    appExport.pushListener = function(callback, delayMilisecond) {

        delayMilisecond = delayMilisecond || 0;

        if(delayMilisecond < 0) {
            delayMilisecond = 0;
        }
        else if(delayMilisecond > 1000) {
            delayMilisecond = 1000; // 최대 1초 대기
        }

        regPushListener = callback;

        /*브라우저는 기능 제공함. 
        if(regPushData) {

            window.setTimeout(function() {
                regPushListener(regPushData);
                regPushData = null;
            }, delayMilisecond);
        }
		*/
    };

    /**
     * WebView의 URL History를 전부 삭제한다.
     * android only
     */
    appExport.clearHistory = function() {

        var args = [];
        /*브라우저는 기능 제공함. 
         exec(function(){}, function(){}, "App", "clearHistory", args);
         */
    };

    /**
     * App의 Splash 화면을 제거한다. 
     * @param delayMilisecond int, Splash 화면을 제거 지연 시간을 1/1000 초 단위로 지정한다, 0 ~ 20000(20초) 
     */
    appExport.closeSplash = function(delayMilisecond) {

    	if(delayMilisecond < 0) {
    		delayMilisecond = 0;
    	}
    	else if(delayMilisecond > 20000) {
    		delayMilisecond = 20000; // 최대 20초 대기
    	} 
        var args = [];
        /*브라우저는 기능 제공함. 
        window.setTimeout(function(){
        	exec(function(){}, function(){}, "App", "closeSplash", args);
        }, delayMilisecond);
        */
        
    };

    /**
     * 외부 브라우저에 URL을 전달하여 페이지를 연다
     * @param url string, 웹 URL
     * @param callback function(json-object)
     * <pre>
     * - code : int , 2000 성공, 실패 4001
     * - message : string
     * </pre>
     */
    appExport.openUrl = function(url, callback) {

        callback = callback || function(){};
        
        window.open(url);
        
        
        callback({code:2000, message:"SUCCESS"});
    };
    
    /**
     * 네트워크 상태를 전달한다
     * @param callback function(int code)
     * <pre>
     * - CODE_NETWORK_NONE : 0 네트워크 연결 안됨
     * - CODE_NETWORK_WIFI : 1 WiFi 네트워크 연결
     * - CODE_NETWORK_MOBILE : 2 Mobile 네트워크 연결
     * </pre>
     */
    appExport.networkStatus = function(callback) {

        callback = callback || function(){};
        window.setTimeout(function(){
        	callback(appExport.CODE_NETWORK_WIFI);
        }, 0);
    };
    
	/**
	 * App 종료
	 */
	appExport.closeApp = function() {


	};

    module.exports = appExport;
});
