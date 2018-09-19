cordova.define("cordova-plugin-restful.APIDataFieldMapping", function(require, exports, module) {
               
    var parser = document.createElement('a');
    var regex = "^(\\$\\{)([A-Za-z0-9_\\-]*)(\\})(@)(app|page|param)$";

    var OPPF2 = require("cordova-plugin-app.OPPF2");
    var cacheMmgr = require("cordova-plugin-datacache.DataCacheManager");
               
    module.exports.getJSONPayloadMapping = function(objParam) {
        var objMappingParam = {};
               
        if(!objParam) {
            return objMappingParam;
        }

        for(var name in objParam) {
            var value = objParam[name];
            if(typeof value === 'string') {
                var regMatche = findDataCacheMatchPattern(value);
                if(regMatche) {

                    var sReposType = regMatche[1];
                    var fieldName = regMatche[2];

                    var reposType = (sReposType == "app") ? OPPF2.DATA_CACHE_APP : OPPF2.DATA_CACHE_PAGE;
                    var data = cacheMmgr.getDataCache(reposType, fieldName);
                    if(data && data[0] == OPPF2.DATA_TYPE_PUBLIC) {

                       objMappingParam[name] = data[2];

                    }
                    else {
                       objMappingParam[name] = value;
                    }


                }
               else {
                   objMappingParam[name] = value;
               }
            }
            else {
                objMappingParam[name] = value;
            }
        }
               
        return objMappingParam;
    };

    module.exports.getRestUrlMapping = function(url, data) {


        parser.href = url;

        var protocol = parser.protocol;
        var host = parser.host;

        var urlPath = parser.pathname;
        var queryString = parser.search;

        if(urlPath) {
            var urlPath_dec = decodeURIComponent(urlPath);
            urlPath = module.exports.getUrlFieldMapping(urlPath_dec, data);
        }

        if(queryString) {
            queryString = module.exports.getUrlParameterMapping(queryString, data);
        }


        var mappingUrl = protocol +"//" + host + urlPath + queryString;

        return mappingUrl;
    };
               
    module.exports.getUrlFieldMapping = function(urlPath, data) {

        ///api/users/$%7BUSER-NAME%7D@app/
        var urlPaths = urlPath.split("/");
        for(var i in urlPaths) {
            var value = urlPaths[i];
            var regMatche = findDataCacheMatchPattern(value);
            if(regMatche) {

                var sReposType = regMatche[1];
                var fieldName = regMatche[2];

               if(sReposType == "param") {
                   if(data[fieldName]) {
                        urlPaths[i] = data[fieldName];
                   }
                   else {
                	   //URL에는 반듯이 값이 존재해야함 오류 상황임.
                	   return null;
                   }
               }
               else {
                    var reposType = (sReposType == "app") ? OPPF2.DATA_CACHE_APP : OPPF2.DATA_CACHE_PAGE;
                    var cacheData = cacheMmgr.getDataCache(reposType, fieldName);
               
                    if(cacheData && cacheData[0] == OPPF2.DATA_TYPE_PUBLIC) {
                   
                        if(typeof cacheData[1] != OPPF2.DATA_TYPE_OBJECT) {
                            urlPaths[i] = cacheData[2];
                        }
                        else {
                        	//URL에는 Object는 들어갈 수 없음.
                        	return null;
                        }
                   
                    }
                    else {
                    	// URL에는 반듯이 값이 존재해야함 오류 상황임.
                    	return null;
                    }
               }

            }
        }


        return urlPaths.join("/");
    };

    module.exports.getUrlParameterMapping = function(query, data) {

        //?search=test&name=${USER-NAME}@app
        var mappingParams = [];
        query = query.substring(1);
        var params = query.split("&");
        for(var i in params) {
            var spt = params[i].split("=");
            var name = spt[0];
            var value = spt[1];
               
            var regMatche = findDataCacheMatchPattern(value);
            if(regMatche) {

                var sReposType = regMatche[1];
                var fieldName = regMatche[2];
                var fieldValue = value;
                if(sReposType == "param") {
                   if(data[fieldName]) {
                	   fieldValue = encodeURIComponent(data[fieldName]);
                   }
                   else {
                       fieldValue = "";
                   }
               
                   mappingParams.push(name + "=" + fieldValue);
                }
                else {
                    var reposType = (sReposType == "app") ? OPPF2.DATA_CACHE_APP : OPPF2.DATA_CACHE_PAGE;
                    var data = cacheMmgr.getDataCache(reposType, fieldName);

                    if(data && data[0] == OPPF2.DATA_TYPE_PUBLIC) {
                        fieldValue = data[2];

                    }
                    else {
                    	fieldValue = "";
                    }
                    mappingParams.push(name + "=" + fieldValue);
               }

            }
            else {
                mappingParams.push(name + "=" );
            }

        }
                   
        return "?" + mappingParams.join("&");
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

               
});
