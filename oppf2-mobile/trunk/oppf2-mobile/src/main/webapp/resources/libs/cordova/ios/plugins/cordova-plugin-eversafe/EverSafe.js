cordova.define("cordova-plugin-eversafe.EverSafe", function(require, exports, module) {

// 수정 전입니다. 
    var exec = require('cordova/exec');
    var eversafeExport = {};


    eversafeExport.REQUEST_SUCCESS = 0;
    eversafeExport.REQUEST_TIMEOVER = 4;
    eversafeExport.curCntMgr = null;

    eversafeExport.relaunch = function()
    {
    	cordova.exec(null , null, "EverSafePlugin" , "relaunch", []);
    };

    eversafeExport.getSessionIdAndToken = function()
    {
    	cordova.exec(null , null, "EverSafePlugin" , "getSessionIdAndToken", []);
    };

    eversafeExport.getSessionIdAndTokenTask = function(connectManager)
    {
    	eversafeExport.curCntMgr = connectManager;
    	
    	if(Define.EVER_SAFE) cordova.exec(null , null, "EverSafePlugin" , "getSessionIdAndTokenTask", []);
    	else 
    	{
    		setTimeout(function()
    		{
    			EverSafeManager.getSessionIdAndTokenDone('KOSCOM','KOSCOM','KOSCOM');
    		
    		}, 300);
    	}
    };


    //-----------------------------------------------------------------------------------------------------
//    	네이티브에서 호출하는 콜백 함수

    eversafeExport.getSessionIdAndTokenDone = function(sessionId, token, deviceId)
    {
    	//afc.log('★ id : ' + sessionId + '// toekn : ' + token + '// deviceId : ' + deviceId + '★');
    	
    	if(EverSafeManager.curCntMgr)
    	{
    		EverSafeManager.curCntMgr.everSafeCallback(sessionId, token, deviceId);
    		EverSafeManager.curCntMgr = null;
    	}
    };

    module.exports = eversafeExport;
});