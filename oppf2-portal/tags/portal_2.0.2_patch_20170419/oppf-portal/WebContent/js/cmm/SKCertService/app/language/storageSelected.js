/**
 * Created by wjdcks725 on 2015. 10. 5..
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

function storageSelectedLang(index) {
    var type = {
        'ko':  0,
        'ko-kr': 0,
        'en-us': 1,
        'ja': 2,
        'ja-jp': 2
    };

    var text = [
        [
            "인증서 저장매체 선택",	// 0
            "저장매체 선택",
            "[설명]",
            "확인",
            "취소",
            "이동식매체 선택",	// 5

            "하드디스크",    // 0 + 5 + 1
            "이동디스크",
            "보안토큰",
            "저장토큰",
            "안전디스크",

            "구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n",   // 5 + 5 + 1
            "지원되지 않습니다.",

            "인증서의 유출가능성이 있어 안전하지 않습니다.<br><br>PC를 공용으로 상용하는 경우는 더 위험하오니,<br>USB메모리, 보안토큰, IC카드 등을 이용하십시오.", // 7 + 5 + 1
            "USB 메모리의 경우,<br>하드디스크와 동일하게 인식되어 이용이 매우 편리합니다.<br>",
            " - 보안기능이 탑재된 가장 우수한 장치입니다.<br> - 국내 표준 인증을 받은 제품 이외에는<br>&nbsp;&nbsp;해당매체를 지원하지 못합니다<br> - 인증서 발급시 몇 분이 소요될 수 있습니다.",
            " - 접근속도가 저장매체중 비교적 낮습니다<br> - 저장매체의 안전성(보안성)이 우수합니다."
        ],
        [
            "Select certificate storage",	// 0
            "Select storage",
            "[description]",
            "OK",
            "Cancel",
            "Select Removable Disk",	// 5
            
            "HardDisk", // 0 + 5 + 1
            "Removable",
            "CryptoToken",
            "SaveToken",
            "SecureDisk",

            "Failedtoinitialize,  IE internet Option -> security->   internet protected mode check, -> trusted site protecet mode check ",  // 5 + 5 + 1
            "Not Supported.",

            "This storage isn't safe, there is the possibility of certificate leakage.<br><br>It could be very unsafe, if you are using public PC.<br>It is stongly recommended using Removable Disk, Crypto Token, IC Card.", // 7 + 5 + 1
            "The removable disk is very easy to use, because it is recognized in the same manner as a hard disk.<br>",
            " - This is the best storage device that is equipped with security functions.<br> - Except for products received national certification standards <br>&nbsp;&nbsp;do not support the device.<br> - It may take a few minutes during certificate issuance.",
            " - This storage is relatively low access speed of the storage media.<br> - This storage has excellent the safety and security."
        ],
        [
            "認証書保存媒体の選択",	// 0
            "保存媒体の選択",
            "[説明]",
            "確認",
            "取消",
            "USB媒体の選択",	// 5

            "ハードディスク",    // 0 + 5 + 1
            "USBディスク",
            "セキュリティトークン",
            "ICトークン",
            "安全ディスク",

            "駆動に失敗しました。\n\nIEインターネットオプション -> セキュリティ\nインターネット保護モードcheck、信頼サイト保護モードcheck \n",   // 5 + 5 + 1
            "サポートされていません。",

            "認証書がハッキングされる可能性があって不安全です。<br><br>共用PCを使用する場合はより危険なので、<br>USBメモリー、セキュリティトークン、ICカードなどを利用してください。", // 7 + 5 + 1
            "USBメモリーの場合、<br>ハードディスクと同一に認識されて利用が非常に便利です。<br>",
            " -セキュリティ機能がある最も優れた装置です。<br>- 国内標準の認証を受けた製品以外には<br>&nbsp;&nbsp;その媒体をサポートしていません。<br>- 認証書を発給時に数分時間がかかる場合があります。",
            " -アクセル速度が保存媒体の中で比較的低いです。<br>-保存媒体の安全（セキュリティ）性が優れます。"
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