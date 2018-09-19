cordova.define("cordova-plugin-restful.APIServiceLoader", function(require, exports, module) {
               
    var OPPF2 = require("cordova-plugin-app.OPPF2");
    function findCordovaPath() {
       var path = null;
       var scripts = document.getElementsByTagName('script');
       var term = '/cordova.js';
       for (var n = scripts.length-1; n>-1; n--) {
           var src = scripts[n].src.replace(/\?.*$/, ''); // Strip any query param (CB-6007).
           if (src.indexOf(term) == (src.length - term.length)) {
               path = src.substring(0, src.length - term.length) + '/';
               break;
           }
       }
    return path;
    }

    module.exports.load = function(callback) {

        var pathPrefix = findCordovaPath();
        loadServiceFile(pathPrefix, callback);

    };


    function loadServiceFile(pathPrefix, callback) {
       //var loadPath = "/miniwas/service.js";//pathPrefix + "service.js";
       //var loadPath = window.location.origin + "/resources/service.json";
    	var loadPath ="/apis/version";

       var rawFile = new XMLHttpRequest();
       rawFile.open("GET", loadPath, false);
       rawFile.onreadystatechange = function () {
           if(rawFile.readyState === 4) {
               if(rawFile.status === 200 || rawFile.status == 0) {
                   var json = rawFile.responseText;
                   var serviceMap = parseServiceMetaData(json);
                   callback(serviceMap);
               }
               else {
                   callback({});
               }
           }
       };
       rawFile.send(null);


    };

               

    function parseServiceMetaData(json) {
       var serviceMap = {};

       var objService = JSON.parse(json);
       objService = objService.body.serviceVersion;
       var version = objService["version"];
       var baseUrl = objService["baseUrl"];
       var arrService = objService["service"];
       for(var i in arrService) {

           var service = arrService[i];
       
           var serviceObject = createServiceAttr(baseUrl, service);
           serviceMap[serviceObject.name] = serviceObject;

       }

       return serviceMap;

    };
               
               
    function createServiceAttr(baseUrl, objService) {

        var OPPF2 = require("cordova-plugin-app.OPPF2");

               
        var name = objService["name"];
        var desc = objService["desc"];
        var method = objService["method"];
        var url = objService["url"];
        
        var resCache = getCacheBlock(objService["resCache"])

        return {name:name, desc:desc, method:method, url:url, resCache:resCache};
    };
               
    function getCacheBlock(arrCacheBlocks) {

       var cacheBlock = {};
       for(var i in arrCacheBlocks) {
       
           // ["${userId}@app", "data.user.userId", "string", 1],
           var cache = arrCacheBlocks[i];
           var cacheRule = cache[0];
           var path = cache[1];
           var type = cache[2];
           if(type == "int") type = OPPF2.DATA_TYPE_INT;
           else if(type == "long") type = OPPF2.DATA_TYPE_LONG;
           else if(type == "float") type = OPPF2.DATA_TYPE_FLOAT;
           else if(type == "double") type = OPPF2.DATA_TYPE_DOUBLE;
           else if(type == "boolean") type = OPPF2.DATA_TYPE_LONG;
           else if(type == "long") type = OPPF2.DATA_TYPE_BOOLEAN;
           else type = OPPF2.DATA_TYPE_OBJECT;
           
           var display = (cache[2] == 1)?true : false;
           
           cacheBlock[cacheRule] = {cacheRule:cacheRule, path:path, type:type, display:display};
       
       }
       return cacheBlock;
    };

});
