/**
 * Created by nori on 2015. 10. 21..
 */
var VestSign = (function (vestSign) {
    return vestSign;
})((function () {
    'use strict';
    var vestSign = undefined;
    if (window.opener) {
        if (!(typeof(window.opener.vestSign) === 'undefined' || window.opener.vestSign == "")) return window.opener.vestSign;
    } else if (window.parent.vestSign) {
        if (!(typeof(window.parent.vestSign) === 'undefined' || window.parent.vestSign == "")) return window.parent.vestSign;
    }
    else {
        return vestSign;
    }
})());

function clauseLang(index) {
    var type = {
        'ko':  0,
        'ko-kr': 0,
        'en-us': 1,
        'ja': 2,
        'ja-jp': 2
    };

    var text = [
        [
            "가입자 이용약관",     // 0
            "가입자 이용약관에 동의합니다.",
            "가입자 이용약관에 동의하지 않습니다.",
            "이용약관에 동의해주세요.",
            "확인",
            "취소"    // 5
        ],
        [
            "Subscriber Terms of Use",     // 0
            "I agree to the subscriber Terms of Use.",
            "You do not agree to the subscriber Terms of Use.",
            "Please agree to the Terms of Use.",
            "OK",
            "Cancel"    // 5
        ],
        [
            "加入者利用規約",     //0
            "加入者利用規約に同意します。",
            "加入者利用規約に同意しません。",
            "利用規約に同意してください。",
            "確認",
            "取消"        // 5
        ]
    ];

    var brwoserLang = (function () {
        if (typeof (window.navigator.browserLanguage) === 'undefined')
            return window.navigator.language.toLowerCase();
        return window.navigator.browserLanguage;
    })();

    var _config = VestSign.getConfig();
    if(_config.language === undefined)
        return text[type[brwoserLang]][index];

    return text[_config.language][index];
}