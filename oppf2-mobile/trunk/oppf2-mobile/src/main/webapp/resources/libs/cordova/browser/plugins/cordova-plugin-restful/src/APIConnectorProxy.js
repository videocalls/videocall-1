cordova.define("cordova-plugin-restful.APIConnectorProxy", function(require, exports, module) {
               
    var http = require("cordova-plugin-restful.HTTPRequest");
    var service = require("cordova-plugin-restful.APIServiceConnector");
    var apiService = require("cordova-plugin-app.OPPF2");
    var fieldMapping = require("cordova-plugin-restful.APIDataFieldMapping");
               
    module.exports.doRequest = function(success, error, args) {
               var serviceId = args[0];
               
               var header = (args.length == 4)? args[1] : null;
               var data = (args.length == 4)? args[2] : null;
               var timeout = (args.length == 4)? args[3] : 10000;
               console.log("doRequest serviceId = " + serviceId);
               
               service.doRequest(serviceId, header, data, timeout, function(res){
                                 
                                    successCallback(success, res);
                                 
                                 }, function(status, statusText){
                                 
                                    failureCallback(error, status, statusText);
                                 });
    };
                              
    function createServiceRequestHeader() {

        var headers = {};
        headers["Content-Type"] = "application/json; charset=utf-8";


        return headers;

    };
               
    function successCallback(callback, resData) {

        var resData = resData || "";
        window.setTimeout(function(){
                         callback(resData);
                         
                         }, 0);

    };

    function failureCallback(callback, code, message) {
               
        var resData = '{"code":code, "message":"'+message+'"}';
        window.setTimeout(function(){
                         callback(resData);
                         
                         }, 0);

    };
    require("cordova/exec/proxy").add("Api",module.exports);
               
});
