/**
 * Created by nori on 2015. 9. 1..
 */

var params = (function () {
    'use strict';

    var Parameters = function () {
        var _plain,
            _option,
            _callback,
            _errorcallback;
        var params = this;

        params.setParameters = function (plain, option, callback, errorcallback) {
            _plain = plain;
            _option = JSON.parse(option);
            _callback = callback;
            _errorcallback = errorcallback;
        };

        params.getPlain = function () {
            return _plain;
        };

        params.getOption = function () {
            return _option;
        };

        params.getCallback = function () {
            return _callback;
        };

        params.getErrorcallback = function () {
            return _errorcallback;
        };

        params.getParameters = function () {
            return {
                plain: _plain,
                option: _option,
                callback: _callback,
                errorcallback: _errorcallback
            };
        };
    };

    return new Parameters();
})();

var relayImport = (function (doc, $, vest, params, vestSign) {

    if (vestSign === undefined) {
        //alert('구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n');
        alert(relayImportLang(2));
        //window.close();
        return false;
    }

    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;


    $("#certAgree_confirmBtn").click(function(){
        var option = _param.option;
        option.code = "" + $("#code1").val() + $("#code2").val() + $("#code3").val();
        _param.callback(option);
        //_parent.close();
    });

    $("#certAgree_cancelBtn").click(function(){
        //_param.callback(_param.option, 0);
        //_parent.close();
        closeEvent();
    });

    $("#CERT_util_btnClose").click(function(){
        //_param.callback(_param.option, 0);
        //_parent.close();
        closeEvent();
    });

    $("#x_btn").click(function(){
        //_param.callback(_param.option, 0);
        //_parent.close();
        closeEvent();
    });

    function closeEvent() {
        var error = {
            code: 12025
        };
        _param.errorcallback(error);
        _parent.close();
    }


    function setCode() {
        var refNum = _param.option.refNum;

        $("#code1").val(refNum.substring(0,4));
        $("#code2").val(refNum.substring(4,8));
        $("#code3").val(refNum.substring(8,12));

        $("#codeTitle").attr('title', relayImportLang(6));

        $("#code1").attr('title', refNum.substring(0,1) + ' ' + refNum.substring(1,2) + ' ' + refNum.substring(2,3) + ' ' + refNum.substring(3,4) + ' ');
        $("#code2").attr('title', refNum.substring(4,5) + ' ' + refNum.substring(5,6) + ' ' + refNum.substring(6,7) + ' ' + refNum.substring(7,8) + ' ');
        $("#code3").attr('title', refNum.substring(8,9) + ' ' + refNum.substring(9,10) + ' ' + refNum.substring(10,11) + ' ' + refNum.substring(11,12) + ' ');

        $("#code1").readOnly = true;
        $("#code2").readOnly = true;
        $("#code3").readOnly = true;
    }

    $(document).ready(function () {
        // 체크 후... 아래부분안보여줘야함.
        try {
            _parent.getParameters();
        } catch (e){
            alert(e);
            _parent.close();
        }

        _param = params.getParameters();

        setCode();

        if(_config.kos_var.webAccess){
            webAccess.webAccessInit(1, 9999);
        }
    });

})(document, jQuery, (function () {
    'use strict';
    var vest = undefined;
    if (window.opener) {
        if (!(typeof(window.opener.vest) === 'undefined' || window.opener.vest == "")) return window.opener.vest;
        //} else if (window.parent.vestSign !== 'undefined') {
    } else if (window.parent.vest) {
        if (!(typeof(window.parent.vest) === 'undefined' || window.parent.vest == "")) return window.parent.vest;
    }
    else {
        return vest;
    }
})(), params, (function () {
    'use strict';
    var vestSign = undefined;
    if (window.opener) {
        if (!(typeof(window.opener.vest) === 'undefined' || window.opener.vestSign == "")) return window.opener.vestSign;
        //} else if (window.parent.vestSign !== 'undefined') {
    } else if (window.parent.vestSign) {
        if (!(typeof(window.parent.vest) === 'undefined' || window.parent.vestSign == "")) return window.parent.vestSign;
    }
    else {
        return vestSign;
    }
})());


//version: _9e92e1a4679ae67ad09a3dab0da4932d22385cc9
//update: Mon May 23 16:07:48 2016 +0900
//version:  v1.0.0
