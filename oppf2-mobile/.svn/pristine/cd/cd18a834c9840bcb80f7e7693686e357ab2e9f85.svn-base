cordova.define("cordova-plugin-restful.APIServiceConnector", function(require, exports, module) {
               
    var serviceMap = null;
    var loader = require("cordova-plugin-restful.APIServiceLoader");
    var fieldMapping = require("cordova-plugin-restful.APIDataFieldMapping");
    var cacheMmgr = require("cordova-plugin-datacache.DataCacheManager");
    var OPPF2 = require("cordova-plugin-app.OPPF2");           
    var regex = "^(\\$\\{)([A-Za-z0-9_\\-]*)(\\})(@)(app|page}param)$";
               
    module.exports.initialize = function(callback) {
               
       if(!serviceMap) {
        loader.load(function(map){
                   serviceMap = map;
                   callback();
                   
                   });
        }
       else {
           callback();
       }
               
    };
               
    module.exports.doRequest = function(serviceId, header, data, timeout, success, fail) {
        
               
        var service = serviceMap[serviceId];
               
               
        // 1 url field mapping
        var url = service["url"];
        if(!url) {
        	
        	window.setTimeout(function(){
        		fail("{\"code\":"+OPPF2.CODE_UNKNWON_SERVICE+", \"message\":\"service unknwon\"}");
        		
        	},0);
        }
        
        url = fieldMapping.getRestUrlMapping(url, data);
        if(!url) {
        	window.setTimeout(function(){
        		fail("{\"code\":"+OPPF2.CODE_PARAMETER+", \"message\":\"bad parameter\"}");
        		
        	},0);
        }
        
        
        
        var method = service["method"];
        
        // 2. create headers
        var headers = createServiceRequestHeader(header);

        // 3. data file mapping
        if(data) {
            data = fieldMapping.getJSONPayloadMapping(data);
        }

       var http = require("cordova-plugin-restful.HTTPRequest");
        http.doRequest(url,
                      method,
                      headers,
                      data,
                      timeout,
                      function(res) {
                       
                            registrationCacheData(res, service["resCache"]);
                            success(res);
                      
                      },
                      function(status, statusText) {
                      
                          fail(status, statusText);
                      
                      });
               
               
               
    };
              
               
    function createServiceRequestHeader(header) {

        var headers = {};
        headers["Content-Type"] = "application/json; charset=utf-8";
        //headers['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
        //headers['Content-Type'] = 'text/plain; charset=UTF-8';
        if(header) {
	        for(var n in header) {
	        	
	        	if(header[n]) {
	        		headers[n] = header[n];
	        	}
	        }
        }
        return headers;
               
    };
               
    function registrationCacheData(strRes, cacheBlock) {

               if(!strRes || !cacheBlock) {
               return;
               }
               
               console.log("registrationCacheData >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
               var resObject = null;
               try {
                   resObject = JSON.parse(strRes);
               } catch(e) {
                   resObject = null;
               }
               
               if(!resObject) {
                   return;
               }
               
               var OPPF2 = require("cordova-plugin-app.OPPF2");
               var result = false;
               for(var i in cacheBlock) {
                   var cache = cacheBlock[i];
                   var cacheRule = cache["cacheRule"];
                   var dataPath = cache["path"];
                   var isDisplay = cache["display"];
                   
                   console.log("  cacheRule["+cacheRule+"] path[" + dataPath +"]");
                   
                   var regMatche = findDataCacheMatchPattern(cacheRule);
                   if(regMatche) {
               
                       var sReposType = regMatche[1];
                       var fieldName = regMatche[2];
 
                       var value = findResCacheData(dataPath, resObject, isDisplay);
                       
                       var reposType = (sReposType == "app") ? OPPF2.DATA_CACHE_APP : OPPF2.DATA_CACHE_PAGE;
                       var cacheType = OPPF2.DATA_TYPE_PUBLIC;
                       
                       result = cacheMmgr.setDataCache(reposType, cacheType, fieldName, value);
                       console.log("DataCache[" + cacheRule +"][" + value +"]= " + result);
                   
                   }
                   else {
                       console.log("DataCache[" + cacheRule +"]= " + false);
                   }
                   
                   
               }
               
               
               
    };
               
    function findDataCacheMatchPattern(source) {
       var match = source.match(regex);
       
       /*
        0:"${USER-NAME}@app"
        1:"${"
        2:"USER-NAME"
        3:"}"
        4:"@"
        5:"app"
        index:0
        input:"${USER-NAME}@app"
        length:6
        */
       if(match) {
       return [match.input, match[5], match[2]];
       }
       
       return null;
    };

    function findResCacheData(dataPath, resObject, isDisplay) {

       var obj = null;
       try {
           eval('obj = ('+('resObject.'+dataPath) +')');
           
           if(obj != null && isDisplay == false) {
        	   eval('delete resObject.'+dataPath);
           }
           
       } catch(e) {
           obj = null;
       }
       return obj;
    };

});
