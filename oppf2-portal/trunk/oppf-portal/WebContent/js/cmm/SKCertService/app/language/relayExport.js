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

function relayExportLang(index) {
    var type = {
        'ko':  0,
        'ko-kr': 0,
        'en-us': 1,
        'ja': 2,
        'ja-jp': 2
    };

    var text = [
        [
            "확인",
            "취소",        // 1
            "구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n",
            "인증서 내보내기",
            "스마트폰에서 “인증서 가져오기”를 실행해 주십시오.",
            "스마트폰에 표시된 승인번호 12자리를 \n아래에 입력해주십시오.",
            "아래 확인 버튼을 누르면 인증서가 스마트폰으로 전송됩니다.",
            "스마트폰에 표시된 승인번호"
        ],
        [
            "OK",
            "Cancel",        // 1
            "Failedtoinitialize,  IE internet Option -> security->   internet protected mode check, -> trusted site protecet mode check \n",
            "Expor Certificate",
            "Please run the “Import Certificate” in smartphone .",
            "Please enter under the approval umber 12 \ndigits that appear wo of the smartphone",
            "When you press the bottom [OK] button , \nthe certificate will be saved to the SmartPhone.",
            "Approval number , which is displayed on the smartphone"
        ],
        [
            "確認",
            "取消",        // 1
            "駆動に失敗しました。\n\nIEインターネットオプション -> セキュリティ\nインターネット保護モードcheck、信頼サイト保護モードcheck \n",
            "証明書のエクスポート",
            "スマートフォンで「証明書のインポート」を実行してください。",
            "スマートフォンに表示される承認番号12桁のを \nの下に入力してください。",
            "下[OK]ボタンを押すと、証明書がSmartPhoneに \n保存されます。",
            "スマートフォンに表示された承認番号"
        ]
    ];

    var brwoserLang = function () {
        if (typeof (window.navigator.browserLanguage) === 'undefined')
            return window.navigator.language.toLowerCase();
        return window.navigator.browserLanguage;
    };

    var _config = VestSign.getConfig();
    if(_config.language === undefined)
        return text[type[brwoserLang]][index];

    return text[_config.language][index];
}