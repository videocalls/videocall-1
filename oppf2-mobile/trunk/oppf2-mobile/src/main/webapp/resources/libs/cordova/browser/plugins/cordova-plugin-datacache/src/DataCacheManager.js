cordova.define("cordova-plugin-datacache.DataCacheManager", function(require, exports, module) {

          
    var pageCache = {};
    
               
    var OPPF2 = require("cordova-plugin-app.OPPF2");
               
    function getDataCacheRepos(reposType) {
       if(reposType === OPPF2.DATA_CACHE_APP) {
               
           var sData = window.sessionStorage['appCache'] || "{}";
           var oAppData = JSON.parse(sData)
           return oAppData;
       }
       else {
               return pageCache;
       }
    };
   
    function getDataType(value) {
        if(typeof value === 'string') {
            return OPPF2.DATA_TYPE_STRING;
        }
        else if(typeof value === 'boolean') {
            return OPPF2.DATA_TYPE_BOOLEAN;
        }
        else if(typeof value === 'number') {
            var reg = /^[0-9]+$/;
            if(reg.test(""+value)) {
               return OPPF2.DATA_TYPE_LONG
            }

            return OPPF2.DATA_TYPE_DOUBLE;
        }
        return OPPF2.DATA_TYPE_OBJECT;
    };
               
    module.exports.getDataCache = function(reposType, name) {
        var cacheData = getDataCacheRepos(reposType);
               
        if(!cacheData[name]) {
            return null;
        }
        var data = cacheData[name];
        return data;
    };
   
    module.exports.setDataCache = function(reposType, cacheType, name, value) {
               
        var result = false;
        if(!value) {
            return result;
        }
        var cacheData = getDataCacheRepos(reposType);

        var valueType = getDataType(value);
        cacheData[name] = [cacheType, valueType, value];
         
       if(reposType == OPPF2.DATA_CACHE_APP) {
           window.sessionStorage['appCache'] = JSON.stringify(cacheData);
       }
        return true;
    };
   
    module.exports.removeDataCache = function(reposType, cacheType, name) {
               
        var cacheData = getDataCacheRepos(reposType);
        if(cacheData[name] && cacheData[name][0] == cacheType) {

            delete cacheData[name];
            if(reposType == OPPF2.DATA_CACHE_APP) {
                window.sessionStorage['appCache'] = JSON.stringify(cacheData);
            }
            return true;
        }

        return false
    };
   
   
    module.exports.clearPageData = function() {
        window.sessionStorage['pageCache'] = {};
    };
   

    module.exports.setDataCache(OPPF2.DATA_CACHE_APP, 
    							OPPF2.DATA_TYPE_PUBLIC, 
    							"osType", "web");
    
    module.exports.setDataCache(OPPF2.DATA_CACHE_APP, 
								OPPF2.DATA_TYPE_PUBLIC, 
								"osVersion", "0.0.0");
    
    module.exports.setDataCache(OPPF2.DATA_CACHE_APP, 
								OPPF2.DATA_TYPE_PUBLIC, 
								"appVersion", "0.0.9");
    
    module.exports.setDataCache(OPPF2.DATA_CACHE_APP, 
								OPPF2.DATA_TYPE_PUBLIC, 
								"deviceToken", "");
   
});
