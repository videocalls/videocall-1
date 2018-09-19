/**
 * Created by nori on 2016. 1. 14..
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

function identifyLang(index) {
    var type = {
        'ko':  0,
        'ko-kr': 0,
        'en-us': 1,
        'ja': 2,
        'ja-jp': 2
    };

    var text = [
        [
            "주민등록번호(사업자등록번호)",     // 0
            "주민등록번호(사업자등록번호)를 구분자(-)없이 입력하여주십시오.",
            "확인",
            "취소",
            "구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n",
            "값을 입력해주세요."
        ],
        [
            "Resident registration number (business registration number)",     // 0
            "Please enter without (-) resident registration number (business registration number) delimiter.",
            "OK",
            "Cancel",
            "Failedtoinitialize,  IE internet Option -> security->   internet protected mode check, -> trusted site protecet mode check ",
            "Please enter a value"
        ],
        [
            "住民登録番号（事業者登録番号）",     //0
            "住民登録番号（事業者登録番号）を区切り文字（ - ）なしで入力してください。",
            "確認",
            "取消",
            "駆動に失敗しました。\n\nIEインターネットオプション -> セキュリティ\nインターネット保護モードcheck、信頼サイト保護モードcheck \n",
            "値を入力してください。"
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