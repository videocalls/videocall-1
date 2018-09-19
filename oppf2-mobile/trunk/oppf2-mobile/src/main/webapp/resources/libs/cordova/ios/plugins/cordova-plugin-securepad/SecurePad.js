cordova.define("cordova-plugin-securepad.SecurePad", function(require, exports, module) {


    var exec = require('cordova/exec');
    var securepadExport = {};

    securepadExport.CODE_SUCCESS = 2000;
    securepadExport.CODE_FAIL = 3000
    securepadExport.CODE_CANCEL = -1;

    securepadExport.PAD_TYPE_ALPHA_U = "alpha_u";   /* 영문 보안 패드 타입 - 영문 */
    securepadExport.PAD_TYPE_ALPHA_L = "alpha_l";   /* 영문 보안 패드 타입 - 영문 */
    securepadExport.PAD_TYPE_NUMBER = "number";   /* 영문 보안 패드 타입 - 영문 */

    securepadExport.ENCRYPT_RSA = "rsa";   /* 보안키패드방식이 RSA, default */
    securepadExport.ENCRYPT_SEED = "seed";   /* 보안키패드방식이 SEED */

    /**
     * 보안 입력 키패드를 연다.
     * @param title string, 보안 패드 타이틀
     * @param maxLength int, 최대 글자 수
     * @param padType string, 보안 패드 타입
     * <pre>
     *   - "alpha_u" : 영문
     *   - "alpha_l" : 영문
     *   - "number" : 숫자
     * </pre>
     * @param encryptMethod  string, 보안키패드가 RSA인지 SEED 방식인지 구분하기 위함. default는 RSA 방식. RMS계좌에서만 SEED 방식 사용
     * <pre>
     *   - "seed" : 보안키패드방식이 SEED
     *   - "rsa" : 보안키패드방식이 RSA
     * </pre>
     * @param callback function(json-object), 보안 키패드 입력값을 수신하기 위한 callback
     * <pre>
     * - code : int, CODE_SUCCESS:2000, CODE_CANCEL:-1 취소
     * - encryptMethod : string, "rsa", "seed"
     * - oriInputLength : int, 입력 글자 수
     * - cipherData : string, 입력 문자 암호화 데이터
     * - displyText : string, 입력 글자 수 만큼 "*"
     * </pre>
     */
    securepadExport.open = function(title, maxLength, padType, encryptMethod, callback) {
        var data = {};
        data.title = title || "보안입력";
        data.maxLength = maxLength || 16;
        data.padType = padType || securepadExport.PAD_TYPE_ALPHA_U;
        data.encryptMethod = encryptMethod || securepadExport.ENCRYPT_RSA;
        data.certDn = null;
    	var args = [data];
    	exec(callback, function(){}, "SecurePad", "openPad", args );
    };

    /**
     * 인증서 비번 입력용 보안 키패드를 연다.
     * @param title string, 보안 패드 타이틀
     * @param maxLength int, 최대 글자 수
     * @param padType string, 보안 패드 타입
     * <pre>
     *   - "alpha_u" : 영문
     *   - "alpha_l" : 영문
     *   - "number" : 숫자
     * </pre>
     * @param checkCertDn string, 인증서 비빌번호 입력용(SEED)으로 사용할 경우 인증서 Subject 전달하면 입력 완료 시 인증서 비빌번호를 체크한다.
     *                            default null
     * @param maxCheckCount int, 인증서 비번 입력 재시도 가능 횟수
     * @param currentTryCount int, 인증서 비번 입력 시도 시작 횟수 지정, 지정한 값부터 count 시작. default 0
     * @param callback function(json-object), 보안 키패드 입력값을 수신하기 위한 callback
     * <pre>
     * - code : int, CODE_SUCCESS:2000, CODE_FAIL:3000 실패, CODE_CANCEL:-1 취소
     * - encryptMethod : string, "rsa", "seed"
     * - oriInputLength : int, 입력 글자 수
     * - cipherData : string, 입력 문자 암호화 데이터
     * - displyText : string, 입력 글자 수 만큼 "*"
     * - maxCheckCount: int, 인증서 비번 입력 재시도 가능 횟수
     * - currentTryCount: int, 현재 인증서 비번 입력 재시도 횟수
     * </pre>
     */
    securepadExport.openForCertPwd = function(title, maxLength, padType, checkCertDn, maxCheckCount, currentTryCount, callback) {

        var data = {};
        data.title = title || "보안입력";
        data.maxLength = maxLength || 16;
        data.padType = padType || securepadExport.PAD_TYPE_ALPHA_U;
        data.encryptMethod = securepadExport.ENCRYPT_SEED;
        data.certDn = checkCertDn || null;
        data.maxCheckCount = maxCheckCount === undefined ? 10 : maxCheckCount;
        data.currentTryCount = currentTryCount === undefined ? 0 : currentTryCount;
    	var args = [data];
    	exec(callback, function(){}, "SecurePad", "openPad", args );
    };
    
    /**
     * "seed"로 입력 받은 cipher-data에서 원문을 반환한다. 
     * @param cipherData string, seed 입력 문자 암호화 데이터 
     * @param callback function(string)
     */
    securepadExport.cipherToText = function(cipherData, callback) {

        var args = [cipherData];
        exec(callback, function(){}, "SecurePad", "cipherToText", args );
    };

    module.exports = securepadExport;
});