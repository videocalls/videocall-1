cordova.define("cordova-plugin-securepad.SecurePadProxy", function(require, exports, module) {

   var OPPF2 = require("cordova-plugin-app.OPPF2");
   
   module.exports.openPad = function(success, error, args) {
       var title = args[0]["title"];
       var maxLength = args[0]["maxLength"];
       var padType = args[0]["padType"];
       var encryptMethod = args[0]["encryptMethod"];
       var keydata = (args[0]["keydata"] ? args[0]["keydata"] : null);
       var maxCheckCount = (args[0]["maxCheckCount"] ? args[0]["maxCheckCount"] : 0);
       var currentTryCount = (args[0]["currentTryCount"] ? args[0]["currentTryCount"] : 0);
               
       var inputTxt = window.prompt(title, "");
       var resData = {};
       if(inputTxt) {
           
          //var chipher = SEED.encrypt(inputTxt, mSeedKey, { mode: SEED.mode.ECB, padding: SEED.pad.NoPadding });
           resData["code"] = OPPF2.CODE_SUCCESS;
           resData["encryptMethod"] = encryptMethod;
           resData["oriInputLength"] = inputTxt.length;
           resData["cipherData"] = inputTxt;
           resData["displyText"] = inputTxt;
           resData["maxCheckCount"] = maxCheckCount;
           resData["currentTryCount"] = currentTryCount + 1;
       
       }
       else {
           resData["code"] = OPPF2.CODE_CANCEL;
           resData["encryptMethod"] = encryptMethod;
           resData["oriInputLength"] = -1;
           resData["cipherData"] = null;
           resData["displyText"] = "";
           resData["maxCheckCount"] = maxCheckCount;
           resData["currentTryCount"] = currentTryCount + 1;	
       }
       
       window.setTimeout(function(){
                            success(resData);
                         
       }, 0);
   };
   
   module.exports.cipherToText = function(success, error, args) {
       var cipherData = args[0];
       window.setTimeout(function(){
                         success(cipherData);
                         
                         }, 0);

               
   };
               
               
   require("cordova/exec/proxy").add("SecurePad",module.exports);
});
