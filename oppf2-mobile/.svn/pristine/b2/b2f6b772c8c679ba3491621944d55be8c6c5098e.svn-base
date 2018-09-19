cordova.define("cordova-plugin-certificate.Certificate", function(require, exports, module) {


    var exec = require('cordova/exec');
    var certificateExport = {};

    certificateExport.CODE_SUCCESS = 2000;   /* 인증서 성공 */
    certificateExport.CODE_FAIL = 3000;   /* 인증서 실패 */
    certificateExport.CODE_CANCEL = -1;   /* 사용자가 취소함 */
    certificateExport.CODE_FAIL_CERT_NULL = 5000;   /* 서버에 등록된 인증번호로 인증서가 존재하지 아니함 */
    certificateExport.CODE_FAIL_CERT_INTERNAL = 5001;   /* 내부 오류가 발생하였습니다. */
    certificateExport.CODE_FAIL_CERT_REGISTRATION = 5002;   /* 인증서 저장에 문제가 생겼습니다. */
    certificateExport.CODE_FAIL_CERT_STORAGE_PERMISSION  = 5003;   /* 단말기 외장 Storage 접근 권한 없음 */
    certificateExport.CODE_FAIL_SIGNED_PASSWD = 5100;   /* 인증서 비빌번호 틀림  */
    certificateExport.CODE_FAIL_SIGNED_INTERNAL = 5101;   /* 전자 서명 내부 실패 */
    certificateExport.CODE_FAIL_SIGNED_CERT_NULL = 5102;   /* 전자 서명 인증서가 없음 실패 */ 
    certificateExport.CODE_FAIL_DELETE_CERT_PASSWD = 5200;   /** 인중서 비밀번호 오류 */
    certificateExport.CODE_FAIL_DELETE_CERT_NULL = 5201;   /** 인증서 없음 */
    certificateExport.CODE_FAIL_DELETE_CERT_INTERNAL = 5202;   /** 인증서 삭제 내부 오류 */

    /**
     * 인증서 정보 목록을 조회한다. 
     * @param callback function(json-object) 없으면 빈 배열
     * <pre>
     * - code : int, 성공(CODE_SUCCESS:2000)
     *               실패(CODE_FAIL_CERT_STORAGE_PERMISSION:5003)
     * - certList : object array. 인증서가 없으면 빈 배열 
     *   - subjectName : string
	 *   - expiredImg : string
	 *   - expiredTime : string
	 *   - issuerName : string
	 *   - policy : string
	 * </pre>
     */
    certificateExport.getUserCertList = function(callback) {
    	
    	var args = [];
    	exec(callback, function(){}, "Certificate", "getUserCertList", args );
    };

    /**
     * 인증서 삭제 요청
     * @param subjectDn String, 선택한 인증서의 subjectDn
     * @param cipherPwd String, 인증서 비밀번호. 보안 키패드(SEED) 입력값
     * @param callback function(json-object)
     * - code : int, CODE_SUCCESS:2000
     *               CODE_FAIL_DELETE_CERT_PASSWD:5200, 인중서 비밀번호 오류
     *               CODE_FAIL_DELETE_CERT_NULL:5201, 인증서 없음
     *               CODE_FAIL_DELETE_CERT_INTERNAL:5202, 인증서 삭제 내부 오류
     * - message String, 결과 메세지
     * - certList : object array. 인증서가 없으면 빈 배열
     *   - subjectName : string
     *   - expiredImg : string
     *   - expiredTime : string
     *   - issuerName : string
     *   - policy : string
     */
    certificateExport.deleteCert = function(subjectDn, cipherPwd, callback) {

        var args = [subjectDn, cipherPwd];
        exec(callback, function(){}, "Certificate", "deleteCert", args );
    };

    /**
     * 인증서 비밀번호 확인
     * @param subjectDn String, 선택한 인증서의 subjectDn
     * @param cipherPwd String, 인증서 비밀번호. 보안 키패드(SEED) 입력값  
     * @param callback function(int)
     *  - CODE_SUCCESS:2000
     *  - CODE_FAIL:3000
     *  - CODE_FAIL_CERT_NULL:5000, 인증서 없음
     */
    certificateExport.checkPwd = function(subjectDn, cipherPwd, callback) {
    	
    	var args = [subjectDn, cipherPwd];
    	exec(callback, function(){}, "Certificate", "checkPwd", args );
    };
    
    
    /**
     * 인증서 전자 서명
     * @param certType int, 1: 축약서명, 2: 일반서명
     * @param subjectDn String, 선택한 인증서의 subjectDn
     * @param cipherPwd String, 인증서 비밀번호. 보안 키패드(SEED) 입력값  
     * @param plainText String, 서명 데이터
     * @param callback function(json-object)
     * <pre>
     * - code : int, 성공(CODE_SUCCESS:2000)
     *               실패(CODE_FAIL_SIGNED_PASSWD:5100)
     *               실패(CODE_FAIL_SIGNED_INTERNAL:5101)
     *               실패(CODE_FAIL_SIGNED_CERT_NULL:5102)
     * - data : string
     * </pre>
     */
    certificateExport.getSignedData = function(certType, subjectDn, certPwd, plainText, callback) {
    	
    	var args = [certType, subjectDn, certPwd, plainText];
    	exec(callback, function(){}, "Certificate", "getSignedData", args );
    };
    
    /**
     * 승인번호 가져오기
     * @param @param callback function(code, message, num1, num2, num3)
     * <pre>
     * - code : int, 성공(CODE_SUCCESS:2000)
     *               실패(CODE_FAIL_CERT_INTERNAL:5001)
	 * - message : string
	 * - num1 : string
	 * - num2 : string
	 * - num3 : string
	 * </pre>
     */
    certificateExport.simpleCertImport1 = function(callback) {
    	
    	var args = [];
    	exec(callback, function(){}, "Certificate", "simpleCertImport1", args );
    };

    /**
     * 인증서 가져오기(다운로드)
     * @param callback function(code, message, certDn, certPolicy)
     * <pre>
     * - code : int, 성공(CODE_SUCCESS:2000)
     *               실패(CODE_FAIL_CERT_NULL:5000)
     *               실패(CODE_FAIL_CERT_INTERNAL:5001)
     *               실패(CODE_FAIL_CERT_REGISTRATION:5002)
	 * - message : string
	 * - certDn : string, 실패 시 null
	 * - certPolicy : string, 실패 시 null
	 * </pre>
     */
    certificateExport.simpleCertImport2 = function(callback) {
    	
    	var args = [];
    	exec(callback, function(){}, "Certificate", "simpleCertImport2", args );
    };
    
    module.exports = certificateExport;
});
