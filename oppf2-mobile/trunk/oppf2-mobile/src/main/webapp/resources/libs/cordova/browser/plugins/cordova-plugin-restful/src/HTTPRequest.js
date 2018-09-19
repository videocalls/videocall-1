cordova.define("cordova-plugin-restful.HTTPRequest", function(require, exports, module) {
               


               
    module.exports.doRequest = function(url, method, headers, data, timeout, success, fail) {

        var req = new XMLHttpRequest();
               
        req.timeout = timeout; // 10000, time in milliseconds

               
        req.onreadystatechange = function(e) {
            var xhr = e.target,
            res = xhr.responseText;

            if (xhr.readyState === 4 && xhr.status === 200 && res) {
                //res = JSON.parse(res);
                if(success) {
                    success(res);
                }
            }
            else if (xhr.readyState === 4 && xhr.status !== 200) {
                if(fail) {
                    fail(xhr.status, xhr.statusText);
                }
            }
        };

        req.open(method, url, true);

        headers = headers || {};
        for(var n in headers)
        {
            req.setRequestHeader(n,headers[n]);
        }
               
        if(data){
            if(typeof data === 'object') {
                req.send(JSON.stringify(data));
            }
            else {
                req.send(data);
            }
        }
        else{
            req.send(null);
        }


    };


});
