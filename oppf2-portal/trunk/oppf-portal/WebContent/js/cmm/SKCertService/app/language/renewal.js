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

function renewalLang(index) {
    var type = {
        'ko':  0,
        'ko-kr': 0,
        'en-us': 1,
        'ja': 2,
        'ja-jp': 2
    };

    var text = [
        [
            "인증서 만료공지",     // 0
            "인증서의 유효기간이 얼마남지 않았습니다.",	
            "인증서 만료일 : ",
            "인증서를 갱신하여 주십시오.",
            "'갱신'버튼을 클릭하면 현재 수행되던 절차를 중단하고 인증서 갱신 화면으로 연결됩니다.",	
            "갱신",	// 5
            "나중에 갱신 하시려면 '확인'을 클릭하십시오.",
            " 이 메시지를 더 이상 표시하지 않습니다.",
            "확인",
            "구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n'"
        ],
        [
            "Expiration Notice",    // 0
            "This certificate is going to be expire on a near days.",	
            "Expiration date: ",
            "Please renew your certificate.",
            "This page will be chaged when you click 'renew' button, suspending  your signing process.",	
            "Renewal",      // 5
            "If you want renew later, please click 'OK'.",
            "Don't show me this message later.",
            "OK",
            "Failedtoinitialize,  IE internet Option -> security->   internet protected mode check, -> trusted site protecet mode check \n"
        ],
        [
            "認証書の有効期間満了のお知らせ",      // 0
            "認証書の有効期限が近づいています。",
            "認証書の満了日　: ",
            "認証書を更新してください。",
            "'更新'ボタンをクリックすると、実行された作業を中断して認証書の更新画面に移動します。",
            "更新",	    // 5
            "後で更新する場合は'確認'をクリックしてください。",
            "このメッセージは表示されません。",
            "確認",
            "駆動に失敗しました。\n\nIEインターネットオプション -> セキュリティ\nインターネット保護モードcheck、信頼サイト保護モードcheck \n"
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