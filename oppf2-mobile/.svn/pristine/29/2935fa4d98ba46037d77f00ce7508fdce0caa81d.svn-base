cordova.define("cordova-plugin-datacache.DataCacheProxy", function(require, exports, module) { /*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
    var OPPF2 = require("cordova-plugin-app.OPPF2");
    var cacheMmgr = require("cordova-plugin-datacache.DataCacheManager");

    module.exports.getData = function(success, error, args) {

        var cacheType = OPPF2.DATA_TYPE_PUBLIC;
        var reposType = args[0];
        var name = args[1];

        var data = getDataCacheValue(reposType, cacheType, name);

        responseCallback(success, data);


    };

    module.exports.setData = function(success, error, args) {

        var cacheType = OPPF2.DATA_TYPE_PUBLIC;
        var reposType = args[0];
        var name = args[1];
        var value = args[2];

        var resultObject = setDataCacheValue(reposType, cacheType, name, value);

        responseCallback(success, resultObject);



    };

    module.exports.setObject = function(success, error, args) {

        var resultObject = {};
        var cacheType = OPPF2.DATA_TYPE_PUBLIC;
        var reposType = args[0];
        var objValue = args[1];
        for(var name in objValue) {
            var result = setDataCacheValue(reposType, cacheType, name, objValue[name]);
            resultObject[name] = result[name];
        }

        responseCallback(success, resultObject);

    };

    module.exports.removeData = function(success, error, args) {

        var cacheType = OPPF2.DATA_TYPE_PUBLIC;
        var reposType = args[0];
        var name = args[1];

        var resultObject = removeDataCacheValue(reposType, cacheType, name);

        responseCallback(success, resultObject);

    };

    module.exports.clearPageData = function(success, error, args) {
               
        cacheMmgr.clearPageData();
               responseCallback(success, null);
               
    };


    function getDataCacheValue(reposType, cacheType, name) {
       var objData = null;
       if(typeof name === 'string') {
           var data = cacheMmgr.getDataCache(reposType, name);
           if(data && data[0] == cacheType) {
               objData = {};
               objData[name] = data[2];
           }
           else {
        	   objData = {};
        	   objData[name] = null;
           }
       }
       else {
           objData = {};
           for(var n in name) {
               var data = cacheMmgr.getDataCache(reposType, name[n]);
               if(data && data[0] == cacheType) {
                   objData[name[n]] = data[2];
               }
               else {
                   objData[name[n]] = null;
               }
           }
       }

       return objData;
    };
               
               
    function setDataCacheValue(reposType, cacheType, name, value) {
        var result = cacheMmgr.setDataCache(reposType, cacheType, name, value);
        var objData = {};
        objData[name] = result;
        return objData;
    };

    function removeDataCacheValue(reposType, cacheType, name) {
        var resultObject = {};
        var result = false;
        if(name instanceof String) {
            result = cacheMmgr.removeDataCache(reposType, cacheType, name);
            resultObject[name] = result;
        }
        else {
            objData = {};
            for(var n in name) {
                result = cacheMmgr.removeDataCache(reposType, cacheType, name[n]);
                resultObject[name[n]] = result;
            }
        }


        return resultObject;

    }

    function responseCallback(callback, resData) {

        var resData = resData || {};
        window.setTimeout(function(){
                         callback(resData);
                         
                         }, 0);

    };


    require("cordova/exec/proxy").add("DataCache",module.exports);

});
