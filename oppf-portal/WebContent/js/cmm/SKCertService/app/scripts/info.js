/**
 * Created by nori on 2015. 8. 7..
 */
var info = (function (doc, $, vest, vestSign) {

    if (vestSign === undefined) {
        //alert('구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n');
        alert(infoLang(6));
        //window.close();
        return false;
    }

    var _parent = vestSign;
    var _isLoad;
    var _config = vestSign.getConfig();

    var setTimeoutVestCert = function (func) {
        if (_isLoad) {
            clearTimeout(_isLoad);
        }

        _isLoad = setTimeout(function () {
            vest.token.getVersion(func, function () {
                setTimeoutVestCert(func);
            });
        }, 5000);
    };

    $("#certInstall").click(function(){
        parent.window.open("../help/certInstall.html");
    });
    
    $("#vestCertSetup").click(function () {
        parent.window.location.href = _config.SKCertServiceDownload;

        vest.token.getVersion(function () {
            _parent.close();
        }, function () {
            setTimeoutVestCert(function () {
                _parent.close();
            })
        });
    });

    $("#refresh").click(function () {
        parent.window.location.href = 'santiago://';

        vest.token.getVersion(function () {
            _parent.close();
        }, function () {
            setTimeoutVestCert(function () {
                _parent.close();
            })
        });
    });

    $("#x_btn").click(function () {
        _parent.close();
    });
    
    $("#doActiveX").click(function (){
        vest.util.cookie.setCookie("koscomActiveX", true);
        //parent.document.cookie = "koscomActiveX=true";
        _parent.close();
    });

    $(document).ready(function () {
        // 체크 후... 아래부분안보여줘야함.
    });

})(document, jQuery, (function () {
    'use strict';
    var vest = undefined;
    if (window.opener) {
        if (!(window.opener.vest == undefined || window.opener.vest == "")) return window.opener.vest;
        //} else if (window.parent.vestSign !== 'undefined') {
    } else if (window.parent.vest) {
        if (!(window.parent.vest == undefined || window.parent.vest == "")) return window.parent.vest;
    }
    else {
        return vest;
    }
})(), (function () {
    'use strict';
    var vestSign = undefined;
    if (window.opener) {
        if (!(window.opener.vestSign == undefined || window.opener.vestSign == "")) return window.opener.vestSign;
        //} else if (window.parent.vestSign !== 'undefined') {
    } else if (window.parent.vestSign) {
        if (!(window.parent.vestSign == undefined || window.parent.vestSign == "")) return window.parent.vestSign;
    }
    else {
        return vestSign;
    }
})());
//version: _9e92e1a4679ae67ad09a3dab0da4932d22385cc9
//update: Mon May 23 16:07:48 2016 +0900
//version:  v1.0.0
