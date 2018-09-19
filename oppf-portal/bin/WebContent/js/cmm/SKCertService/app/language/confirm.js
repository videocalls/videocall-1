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

function confirmLang(index) {
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
            "인증서 발급을 위한 다음 단계로 진행합니다.\n'취소' 버튼을 누르시면 인증서 발급과정이 중단됩니다.",
            "인증서 갱신을 위한 다음 단계로 진행합니다.\n'취소' 버튼을 누르시면 인증서 갱신과정이 중단됩니다.",
            "원본 인증서를 저장매체로부터 삭제하시겠습니까? \n"
        ],
        [
            "OK",
            "Cancel",        // 1
            "Failedtoinitialize,  IE internet Option -> security->   internet protected mode check, -> trusted site protecet mode check ",
            "Go to the next step for the certificate issuance.\nWhen you press the 'Cancel' button , certificate issuance process is interrupted .",
            "Go to the next step for the certificate renewal.\nWhen you press the 'Cancel' button , certificate update process is interrupted .",
            "Are you sure you want to delete the original certificate from the storage medium ? \n"
        ],
        [
            "確認",
            "取消",        // 1
            "駆動に失敗しました。\n\nIEインターネットオプション -> セキュリティ\nインターネット保護モードcheck、信頼サイト保護モードcheck \n",
            "証明書発行のための次のステップに進みます。 \n 「キャンセル」ボタンを押すと、証明書発行のプロセスが中断されます。",
            "証明書の更新のための次のステップに進みます。 \n 「キャンセル」ボタンを押すと、証明書更新プロセスが中断されます。",
            "元の証明書を記憶媒体から削除しますか？ \n"
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