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

function signLang(index) {
    var type = {
        'ko':  0,
        'ko-kr': 0,
        'en-us': 1,
        'ja': 2,
        'ja-jp': 2
    };

    var text = [
        [
            "가입자명",	// 0
            "만료일",
            "정책등급",
            "발급기관",
            "인증서 보기",
            "인증서 삭제",	// 5
            "인증서 암호",
            "인증서 비밀번호 (대소문자 구분)",   //"인증서 암호는 대소문자를<br> 구분합니다",
            "확인",
            "취소",

            "“인증서 관리 프로그램이 설치 또는 실행되지 않았습니다.”",	// 10
            "설치안내",
            "설치",
            "실행",

            "“인증서찾기 메뉴를 이용하시면프로그램 설치 없이 <br>전자서명이 가능합니다.”",	// 14
            "인증서 찾기 안내",	// 15
            "여기에 인증서파일(*.PFX, *P12)을 <br>끌어다 놓으세요.",
            "인증서 찾기",

            "내용보기",	// 18    //"인증서 내용보기"
            "속성",
            "값",	// 20

            "보안매체 선택",
            "인증서 검증",
            "최상위인증기관 인증서 신뢰성 확인",	// 23
            "선택하신 최상위 인증기관의 인증서 해쉬값은 다음과 같습니다.",
            "안전한 인증서 사용을 위해 KISA 홈페이지(https://www.rootca.or.kr/cert/potency.html) 확인 후, 최상위 인증기관에 게시한 해쉬값과 일치하는지 확인하여 주십시오.",	// 25
            "인증서 만료공지",
            "인증서의 유효기간이 얼마남지 않았습니다.",	// 27
            "인증서 만료일 : ",
            "인증서를 갱신하여 주십시오.",
            "'갱신'버튼을 클릭하면 현재 수행되던 절차를 중단하고 인증서 갱신 화면으로 연결됩니다.",	// 30
            "갱신",	// 31
            "나중에 갱신 하시려면 '확인'을 클릭하십시오.",
            " 이 메시지를 더 이상 표시하지 않습니다.",
            "Report",	// 34
            
            "하드디스크",    // 35
            "이동디스크",
            "보안토큰",
            "인증서찾기",
            "안전디스크",

            "인증서를 선택해주세요.",  // 40   
            "비밀번호를 입력해주세요.",
            "구동에 실패하였습니다.\n\n IE인터넷 옵션 -> 보안 \n 인터넷 보호모드 check, 신뢰사이트 보호모드 check \n",
            "일반 인증서",

            "가입자",  // 44
            "버전",
            "일련번호",
            "발급자 식별명",
            "다음부터 유효함",
            "다음까지 유효함",
            "서명알고리즘",   // 50
            "피발급자 공개키 알고리즘",
            "공개키",
            "서명값",
            "키 사용용도",
            "발급자키 식별자", // 55
            "피발급자키 식별자",
            "확장키 사용용도",
            "추가적인 피발급자명",
            "기본제한",
            "정책",   // 60
            "분배점",
            "OCSP서비스",
            "저장위치",

            "전자서명용",    // 29 + 35
            "암호화용",
            "인증서발급기관",
            "최상위인증기관",

            "준비중입니다.",  // 33 + 35

            "해쉬값 검증",
            "추가정보",
            "인증서 검증",

            "$해당 인증서는 다음과 같은 내용을 포함하고 있습니다",    // 37 + 35
            "-발급자: ",
            "-가입자: ",
            "-발급일: ",    // 40 + 35
            "-만료일: ",
            "$검증결과: ",
            "모든 인증서 검증조건을 만족합니다.",
            "인증서 검증에 실패하였습니다.",
            "$오류코드",    // 45 + 35
            "없음",
            "$오류정보",
            "$인증서 검증절차가 완료되었습니다.",
            "[*] 컴퓨터의 시간을 반드시 확인하십시오.",
            "\n",    // 50 + 35

            "성공",
            "인증서찾기안내",
            "세션이 종료되었습니다.",
            "만료된 인증서입니다.",
            "전자서명 중 에러가 발생하였습니다.",  // 55 + 35
            "인증서 파일을 가져오세요.",
            "PFX파일이 아니거나 비밀번호가 일치하지 않습니다.",
            "허용되지 않은 인증서 입니다.",
            "업데이트",

            "보안 매체 비밀번호가 5회이상 틀렸습니다.",  // 60 + 35
            "SKCert가 잠겼습니다.",
            "비밀번호가 일치하지 않습니다.",
            "비밀번호가 다수 틀렸습니다.",
            "보안토큰이 잠겼습니다.",
            "보안 매체를 사용하기 위해서는 초기화가 필요합니다.", // 65 + 35
            "윈도우 우측(Tray)에서 초기화를 진행해주세요.",
            "infovine 모듈 설치가 필요합니다. 설치 페이지로 연결합니다.",
            "infovine 모듈 버전이 낮습니다. 업그레이드 페이지로 연결합니다.",

            "하드 디스크가 존재하지 않습니다. 하드 디스크를 확인해 주십시오.", // 69 + 35
            "이동식 디스크가 존재하지 않습니다. 이동식 디스크를 확인해 주십시오.",
            "보안 토큰이 연결되어 있지 않습니다. 보안 토큰을 연결해 주십시오.",
            "저장 토큰이 연결되어 있지 않습니다. 저장 토큰을 연결해 주십시오.",

            "PFX 가져오기를 눌러주세요.", // 73 + 35
            "'https:/'로 접속 가능합니다.",

            "취소되었습니다.",      // 75 + 35
            "인포바인",
            "저장토큰",
            "휴대폰인증",
            "저장된 인증서가 없습니다.",
            "지원되지 않는 서비스 입니다.",  // 80 + 35
            "비밀번호 입력이 사용자에 의해 취소 되었습니다.",
            // title
            "인증서 선택",
            "인증서 갱신",
            "인증서 효력정지",
            "인증서 폐지",
            "암호화용 인증서 추가 발급",
            "인증서 재발급",
            "인증서 검색",
            "인증서 비밀번호 변경",
            
            // detailCert
            "표준화 이전",       // 125
            "표준화 이후",
            
            "저장매체 선택",

            // VESTCERT-406 추가사항
            "보안토큰 비밀번호 (대소문자 구분)"
        ],
        [
            "Subject",	// 0
            "Expiration",
            "Policy",
            "Issuer",
            "View Detatils",
            " Delete　Cert ",	// 5
            "Password",
            "Password for the certificate is case sensitive.",
            "OK",
            "Cancel",

            "”VestCert program is not installed or not running”",	// 10
            "Installation guide",
            "Installation",
            "Execution",

            "”You can make signature without any installation if you use [Find certificate] menu.”",	// 14
            "Find certificate guide",	// 15
            "Drag and drop your (*.PFX, *P12) file <br>here.",
            "Find certificate",

            "View Certificate",	// 18
            "Attribute",
            "Value",		// 20

            "Select secure stroage",
            "Certificate Verify",
            "Verificateion Root Certificate reliability",	// 23
            "The hash value of selected Root Certificate is below.",
            "Please verify that the ROOT certifcate hash value correspond with distributed hash value in KISA hompage(https://www.rootca.or.kr/cert/potency.html) for safer usage.",	// 25
            "Expiration Notice",
            "This certificate is going to be expire on a near days.",	// 27
            "Expiration date: ",
            "Please renew your certificate.",
            "This page will be chaged when you click 'renew' button, suspending  your signing process.",	// 30
            "Renewal",	// 31
            "If you want renew later, please click 'OK'.",
            "Don't show me this message later.",
            "Report",	// 34

            "HardDisk", // 0 + 35
            "Removable",
            "HSM",
            "Find Cert",
            "SecureDisk",

            "Select certificate.",  // 5 + 35
            "Input password.",
            "Failedtoinitialize,  IE internet Option -> security->   internet protected mode check, -> trusted site protecet mode check ",
            "General certificate",

            "Subject",  // 9 + 35
            "Version",
            "Serial",
            "Issuer",
            "Issue date",
            "Expiration date",
            "Signature Algorithm",  // 15 + 35
            "Public Key Algogrithm",
            "Public Key",
            "Signature",
            "Key usage",
            "Issuer ID",    // 20 + 35
            "Subject ID",
            "Extented key usage",
            "Additional subject name",
            "Basic limit",
            "Policy",   // 25 + 35
            "Distribution point",
            "OCSP service",
            "Storage",

            "Signature",    // 29 + 35
            "Encryption",
            "CA",
            "Root CA",

            "Service will be opened soon.", // 33 + 35

            "Hash Verification",
            "Additional Info",
            "Cert Verification",

            "$This certificate include below information.",  // 37 + 35
            "-Issuer: ",
            "-Subject: ",
            "-Issuedate: ", // 40 + 35
            "-Expirationdate: ",
            "$Verification result: ",
            "Verification success",
            "Verification fail",
            "$Error code: ", // 45 + 35
            "N/A",
            "$Error info: ",
            "$Verification chain is succeeded.",
            "[*] Check your system date and time.",
            "\n",   // 50 + 35

            "Success",
            "Find Certificate Guide",
            "Session is expired.",
            "Expired certificate.",
            "Error while making a signature.",   // 55 + 35
            "Select pfx file",
            "The file is not pfx format file or has wrong password.",
            "The certifiacte is not allowed.",
            "Update",

            "You have input worng password over 5 times.",  // 60 + 35
            "SKCert program is locked.",
            "Wrong password.",
            "You have input wrong password many times.",
            "Crypto Token is locked.",
            "You need to initialize Security Token.",   // 65 + 35
            "Initailize Security Token using VestCert Manager.",
            "You need to install infovine program. This page will be changed to install page.",
            "You need to upgrade infovine program. This page will be changed to upgrade page.",

            "There is no certificate in hard disk. Please check the hard disk.",    // 69 + 35
            "There is no certificate in removable disk. Please check the removable disk",
            "There is no connection for Crypto Token. Please check connection of Security Token",
            "There is no connection for Storage Token. Please check connection of Storage Token",

            "Click PFX impmort button.",    // 73 + 35
            "You need to connect by 'https:/' protocol.",

            "It has been canceled.",      // 75 + 35
            "infovaine",
            "Save token",
            "Mobile phone authentication",
            "There is no stored certificate.",
            "It is unsupported service.",  // 80 + 35
            "Enter the password has been canceled by the user.",
            // title
            "Certificate selection",
            "Recertification",
            "Potency stop of certificate",
            "Certificate abolition",
            "Issuance of additional certificate for encryption",
            "Reissue of certificate",
            "Search certificate",
            "Changing the password for the certificate",

            // detailCert
            "Before standardization",       // 125
            "After standardization",
            
            "The choice of storage medium",

            "Security Token Password"
        ],
        [
            "加入者名",	// 0
            "満了日",
            "政策等級",
            "発給機関",
            "認証書を見る",
            "認証書の削除",	// 5
            "パスワード",
            "認証書のパスワードは",//"認証書のパスワードは<br>大文字・小文字を区別します。",
            "確認",
            "取消",

            "“認証書の管理プログラムがインストールまたは実行されていません。”",	// 10
            "インストールの案内",
            "インストール",
            "実行",

            "“認証書の検索メニューを利用すれば、プログラムのインストールなしに<br>電子署名が可能です。“",
            "認証書の検索の案内",	// 15
            "ここに認証書ファイル(*.PFX, *P12)を<br>ドラッグ＆ドロップしてください。",
            "認証書の検索",

            "認証書の内容を見る",	// 18
            "属性",
            "値",	// 20

            "セキュリティ媒体の選択",
            "認証書の検証",
            "最上位認証機関の認証書の信頼性を確認",		// 23
            "選択した最上位認証機関の認証書ハッシュ値は次と同じです。",
            "安全な認証書の使用をためKISAホームページ(https://www.rootca.or.kr/cert/potency.html)を確認した後、最上位認証機関にて公開したハッシュ値と一致していることを確認してください。",
            "認証書の有効期間満了のお知らせ",
            "認証書の有効期限が近づいています。",	// 27
            "認証書の満了日　: ",
            "認証書を更新してください。",
            "'更新'ボタンをクリックすると、実行された作業を中断して認証書の更新画面に移動します。",
            "更新",	// 31
            "後で更新する場合は'確認'をクリックしてください。",
            "このメッセージは表示されません。",
            "Report",	// 34

            "ハードディスク",  // 0 + 35
            "リムーバブルディスク",
            "セキュリティトークン",
            "認証書の検索",
            "安全ディスク",

            "認証書を選択してください。",    // 5 + 35
            "パスワードを入力してください。",
            "駆動に失敗しました。\n\nIEインターネットオプション -> セキュリティ\nインターネット保護モードcheck、信頼サイト保護モードcheck \n",
            "一般認証書",

            "加入者",  // 9 + 35
            "バージョン",
            "シリアル番号",
            "発給者識別名",
            "次から有効",
            "次まで有効",
            "署名アルゴリズム", // 15 + 35
            "公開キーアルゴリズム",
            "公開キー",
            "署名値",
            "キー使用用途",
            "発給者キー識別者", // 20 + 35
            "被発給者キー識別者",
            "拡張キー使用用途",
            "追加被発給者名",
            "基本制限",
            "ポリシー", // 25 + 35
            "分配点",
            "OCSPサービス",
            "保存位置",

            "電子署名用",    // 29 + 35
            "暗号化用",
            "認証書発給機関",
            "最上位認証機関",

            "準備中です。",   // 33 + 35

            "ハッシュ値の検証",
            "追加情報",
            "認証書の検証",

            "$該当の認証書は次と同じ内容を含めています。\n", // 37 + 35
            "-発給者: ",
            "-加入者: ",
            "-発給日: ",   // 40 + 35
            "-満了日: ",
            "$検証結果: ",
            "すべての認証書の検証条件を満足します。",
            "認証書の検証に失敗しました。",
            "$エラーコード: ",    // 45 + 35
            "なし",
            "$エラー情報: ",
            "$認証書の検証手順が完了しました。",
            "[*] パソコンの時間を必ず確認してください。",
            "\n",   // 50 + 35

            "成功",
            "認証書の検索の案内",
            "セッションが終了されました。",
            "有効期間満了の認証書です。",
            "電子署名中にエラーが発生しました。",    // 55 + 35
            "認証書のファイルをインポートしてください。",
            "PFXファイルではないか、またはパスワードが一致しません。",
            "許可されない認証書です。",
            "アップデート",

            "セキュリティ媒体のパスワードが５回以上間違えました。",   // 60 + 35
            "SKCertがロックされました。",
            "パスワードが一致しません。",
            "パスワードが何度か間違えました。",
            "セキュリティトークンがロックされました。",
            "セキュリティ媒体を使用するためには初期化が必要です。",   // 65 + 35
            "ウィンドウの右側(Tray)にて初期化を進行してください。",
            "infovine モジュールのインストールが必要です。インストールページへ移動します。",
            "infovineモジュールバージョンが低いです。アップグレードページへ移動します。",

            "ハードディスクが存在しません。ハードディスクを確認してください。", // 69 + 35
            "リムーバブルディスクが存在しません。リムーバブルディスクを確認してください。",
            "セキュリティトークンが連結されていません。セキュリティトークンを連結してください。",
            "ICカードが連結されていません。ICカードを連結してください。",

            "PFXインポートをクリックしてください。", // 73 + 35
            "'https:/'に接続可能です。",

            "キャンセルされました.",      // 75 + 35
            "インフォバイン",
            "保存トークン",
            "携帯電話認証",
            "保存された証明書がありません。",
            "サポートされていないサービスです。",  // 80 + 35
            "パスワードの入力がユーザーによってキャンセルされました。",
            // title
            "証明書の選択",
            "証明書の更新",
            "証明書の効力停止",
            "証明書廃止",
            "暗号化用の証明書の追加発行",
            "証明書の再発行",
            "証明書を検索",
            "証明書のパスワードの変更",

            // detailCert
            "標準化前の",        // 125
            "標準化の後",

            "記憶媒体の選択",

            "セキュリティトークンパスワード"
        ]
    ];

    var brwoserLang = (function () {
        if (typeof (window.navigator.browserLanguage) === 'undefined')
            return window.navigator.language.toLowerCase()
        return window.navigator.browserLanguage;
        // return 'ja-JP';
    })();

    var _config = VestSign.getConfig();
    if(_config.language === undefined)
        return text[type[brwoserLang]][index];

    return text[_config.language][index];
}