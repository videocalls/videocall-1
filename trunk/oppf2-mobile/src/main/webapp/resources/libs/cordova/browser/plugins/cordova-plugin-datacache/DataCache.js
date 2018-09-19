cordova.define("cordova-plugin-datacache.DataCache", function(require, exports, module) {

	var exec = require('cordova/exec');

	var datacacheExport = {};

	/**
	 * OS 종류 조회 키  
	 * "android" , "ios", "web"
	 */
	datacacheExport.DATA_OS_TYPE = "osType";
	/**
	 * OS Version 조회 키  
	 * ios:"10.3.1" , android:"5.0.1", web "0.0.0"
	 */
	datacacheExport.DATA_OS_VERSION = "osVersion";
	/**
	 * OPPF mobile App Version  조회 키 
	 * "x.x.x"
	 */
	datacacheExport. DATA_APP_VERSION = "appVersion";
	/**
	 * PUSH Device-Token 
	 */
	datacacheExport.DATA_DEVICE_TOKEN = "deviceToken";
	
    var DATA_CACHE_APP = 1; /* life cycle, app 생명 주기와 동일 */
    var DATA_CACHE_PAGE = 2; /* life cycle, 개별 web page 생명 주기,페이지 전환 시 clear */

    /**
     * DataCache App 유형 데이터를 조회한다.
     * @param name 데이터 이름, 1개 조회일 경우 string, 여러개 일 경우 ["데이터 이름" ,,,]
     * @param callback, 조회 데이터를 전달 받기 위한 callback
     * <pre>
     * callbaack 데이터 : object, 데이터가 없으면 { name: null ,,,,}
     * < name: value ,,,,}
     * </pre>
     */
	datacacheExport.getAppData = function(name, callback) {

		 var args = [DATA_CACHE_APP, name];
		 exec(callback, function(){}, "DataCache", "getData", args);
	};

    /**
     * DataCache App 유형에 데이터를 저장한다.
     * @param name 데이터 이름
     * @param value 데이터, string, number(long, double), boolean, object
     * @param callback, 저장 결과를 전달 받기 위한 callback
     * <pre>
     * callbaack 데이터 : object
     * { name: true|false ,,,,}
    * </pre>
     */
	datacacheExport.setAppData = function(name, value, callback) {
		 var args = [DATA_CACHE_APP, name, value];

		 exec(callback, function(){}, "DataCache", "setData", args);
	};

    /**
     * DataCache App 유형에 여러개의 데이터를 저장한다.
     * @param data object {name: value,,,,,}
     * @param callback, 저장 결과를 전달 받기 위한 callback
     * <pre>
     * callbaack 데이터 : object
     * { name: true|false ,,,,}
    * </pre>
     */
	datacacheExport.setAppObject = function(data, callback) {
         var args = [DATA_CACHE_APP, data];

         exec(callback, function(){}, "DataCache", "setObject", args);
    };

    /**
     * DataCache App 유형 데이터를 삭제한다.
     * @param name 데이터 이름, 1개 조회일 경우 string, 여러개 일 경우 ["데이터 이름" ,,,]
     * @param callback, 삭제 결과를 전달 받기 위한 callback
     * <pre>
     * callbaack 데이터 : object
     * { name: true|false ,,,,}
    * </pre>
     */
    datacacheExport.removeAppData = function(name, callback) {
		 var args = [DATA_CACHE_APP, name];

		 exec(callback, function(){}, "DataCache", "removeData", args);
	};

    /**
     * DataCache Page 유형 데이터를 조회한다.
     * @param name 데이터 이름, 1개 조회일 경우 string, 여러개 일 경우 ["데이터 이름" ,,,]
     * @param callback 조회 데이터를 전달 받기 위한 callback
     * <pre>
     * callbaack 데이터 : object, 데이터가 없으면 { name: null ,,,,}
     * { name: value ,,,,}
     * </pre>
     */
    datacacheExport.getPageData = function(name, callback) {

		 var args = [DATA_CACHE_PAGE, name];
		 exec(callback, function(){}, "DataCache", "getData", args);
	};

    /**
     * DataCache Page 유형에 데이터를 저장한다.
     * @param name 데이터 이름
     * @param value 데이터, string, number(long, double), boolean, object
     * @param callback, 저장 결과를 전달 받기 위한 callback
     * <pre>
     * callbaack 데이터 : object
     * { name: true|false ,,,,}
     * </pre>
     */
	datacacheExport.setPageData = function(name, value, callback) {
		 var args = [DATA_CACHE_PAGE, name, value];

		 exec(callback, function(){}, "DataCache", "setData", args);
	};

    /**
     * DataCache Page 유형에 여러개의 데이터를 저장한다.
     * @param data object {name: value,,,,,}
     * @param callback, 저장 결과를 전달 받기 위한 callback
     * <pre>
     * callbaack 데이터 : object
     * { name: true|false ,,,,}
     * </pre>
     */
    datacacheExport.setPageObject = function(data, callback) {
         var args = [DATA_CACHE_PAGE, data];

         exec(callback, function(){}, "DataCache", "setObject", args);
    };

    /**
     * DataCache App 유형 데이터를 삭제한다.
     * @param name 데이터 이름, 1개 조회일 경우 string, 여러개 일 경우 ["데이터 이름" ,,,]
     * @param callback, 삭제 결과를 전달 받기 위한 callback
     * <pre>
     * callbaack 데이터 : object
     * { name: true|false ,,,,}
     * </pre>
     */

    datacacheExport.removePageData = function(name, callback) {
		 var args = [DATA_CACHE_PAGE, name];

		 exec(callback, function(){}, "DataCache", "removeData", args);
	};

	datacacheExport.clearPageData = function() {
		 var args = [];

		 exec(function(){}, function(){}, "DataCache", "clearPageData", args);
	};

	module.exports = datacacheExport;
});
