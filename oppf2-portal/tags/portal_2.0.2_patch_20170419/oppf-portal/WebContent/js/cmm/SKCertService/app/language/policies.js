/**
 * Created by nori on 2016. 1. 14..
 */


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

function policiesLang(index) {
    var type = {
        'ko':  0,
        'ko-kr': 0,
        'en-us': 1,
        'ja': 2,
        'ja-jp': 2
    };

    var text = [
        [
            "범용(개인)",    // 0
            "범용(법인)",
            "은행/보험(개인)",
            "은행/보험(법인)",
            "신용카드용",

            "전자세금용",    // 5
            "테스트",
            "범용(범용)",
            "은행/보험",
            "1등급(개인)",
            "1등급(법인)",   // 10

            "범용(법인)",
            "증권(법인)",
            "증권(개인)",
            "카드(개인)"
        ],
        [
            "General purpose(personal)",    // 0
            "General purpose(corp)",
            "Limited(personal)",
            "Limited(corp)",
            "Limited",

            "Limited",  // 5
            "Test",
            "General purpose(corp)",
            "Limited",
            "1st(personal)",
            "1st(corp)",    // 10

            "General purpose(corp)",
            "Stock(corp)",
            "Stock(personal)",
            "Card(personal)"
        ],
        [
            "汎用（個人）",   // 0
            "汎用（法人）",
            "銀行/保険（個人）",
            "銀行/保険（法人）",
            "クレジットカード用",

            "電子税金用",    // 5
            "テスト",
            "汎用（法人）",
            "銀行/保険",
            "１等級（個人）",
            "１等級（法人）",  // 10

            "汎用（法人）",
            "証券（法人）",
            "証券（個人）",
            "カード（個人）",
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