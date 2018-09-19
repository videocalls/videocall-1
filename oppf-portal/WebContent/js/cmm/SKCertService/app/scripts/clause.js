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

var clause = (function (doc, $, vest, params, vestSign) {

    var _parent = vestSign;
    var _config = vestSign.getConfig();
    var _param;

    //확인버튼 클릭
    $("#certAgree_confirmBtn").click(function() {
        if ($("#certAgree_radio:checked").val() != "")
            _param.callback();
        else {
            alert(clauseLang(3));
            return;
        }
    });

    //X버튼,취소버튼 클릭
    $("#certAgree_xBtn").click(function() {
        closeEvent();
    });
    $("#certAgree_cancelBtn").click(function() {
        closeEvent();
    });

    function closeEvent() {
        var error = {
            code: 12025
        };
        _param.errorcallback(error);
        _parent.close();
    }

    $(document).ready(function () {
        // 체크 후... 아래부분안보여줘야함.
        var _msg = '';
        try {
            _parent.getParameters();
        } catch (e){
            alert(e);
            _parent.close();
        }

        _param = params.getParameters();

        if(_config.language == 0){
            document.getElementById("textKo").style.display = '';
            document.getElementById("textEn").style.display = 'none';
        }

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