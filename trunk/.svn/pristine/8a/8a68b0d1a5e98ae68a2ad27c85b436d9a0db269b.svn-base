

//----------------------------------------------------------------------------------------------------
//인증서
//----------------------------------------------------------------------------------------------------

var CertificateManager = {};

CertificateManager.callbackFunc = null;

CertificateManager.certUpdateCallbackFunc = null;
CertificateManager.certIssueCallbackFunc = null;

CertificateManager.certWorkDone = function(code, data)
{
	CertificateManager.callbackFunc(code, data);
	CertificateManager.callbackFunc = null;
};

CertificateManager.functionCheck = function(callback)
{
	if(CertificateManager.callbackFunc) 
	{
		callback(0);
		return false;
	}
	CertificateManager.callbackFunc = callback;
	return true;
};

//--------------------------------------------------------------------------------
//	유저 인증서 리스트 표시
//	@function getUserCertList(Function); 
//	callback(Array);
/* Array 
[{
	subjectName : 인증서 주체의 dn값
	expiredImg : 인증서 만료여부 (0:유효, 1:만료, 2:만료예정)
	expiredTime : 인증서 만료일 ( 2013.12.11 )
	issuerName : 인증서 발급 기관
	policy : 인증서의 정책정보 ( 은행개인 ) 

},,,]
*/
CertificateManager.getUserCertList = function(callback)
{
	cordova.exec(callback, null, "CertificatePlugin", "getUserCertList", []);
};

//--------------------------------------------------------------------------------
//	간편 인증서import 1단계
//	@function simpleCertImport1(Function); 
//	callback(int code, String data);
CertificateManager.simpleCertImport1 = function(callback)
{
	if(CertificateManager.functionCheck(callback)) 
		cordova.exec(null, null, "CertificatePlugin", "simpleCertImport1", []);
};

//--------------------------------------------------------------------------------
//	간편 인증서import 2단계
//	@function simpleCertImport2(Function); 
//	callback(int code, String data);
CertificateManager.simpleCertImport2 = function(callback)
{
	if(CertificateManager.functionCheck(callback))
		cordova.exec(null, null, "CertificatePlugin", "simpleCertImport2", []);
};

//--------------------------------------------------------------------------------
//	간편 인증서export
//	@function simpleCertExport1(Array, Function); 
/*
[
	String subjectDn,
	String cipherData,
	String userNum1,
	String userNum2,
	String userNum3
];
*/
//	callback(int code, String data);
CertificateManager.simpleCertExport1 = function(exportArr, callback)
{
	if(CertificateManager.functionCheck(callback))
		cordova.exec(null, null, "CertificatePlugin", "simpleCertExport1", exportArr);
};

//--------------------------------------------------------------------------------
//	비밀번호 변경
//	@function changePwd([String certDn, String oldPw, String newPw, String confPw], Function); 
//	callback(int code, data);
CertificateManager.changePwd = function(changeData, callback)
{
	cordova.exec(callback, null, "CertificatePlugin", "changePwd", changeData);
};

//--------------------------------------------------------------------------------
//	인증서 삭제
//	@function deleteCert(String certDn, String chipherData, Function); 
//	callback(int code);
CertificateManager.deleteCert = function(certDn, chipherData, callback)
{
	cordova.exec(callback, null, "CertificatePlugin", "deleteCert", [certDn, chipherData]);
};

//비밀번호 확인
//	@function checkPwd(String certDn, String cipherData, Function); 
//	callback(int code);
CertificateManager.checkPwd = function(certDn, cipherData, callback)
{
	cordova.exec(callback, null, "CertificatePlugin", "checkPwd", [certDn, cipherData]);
};

//--------------------------------------------------------------------------------
//	서명
//	@function getSignedData(Object, String, Function); 
//	signInfo = { signFlag, certDn, certPw };
//  signFlag = 1 : 축약서명, 2:전자서명
//	callback(String signedData);
CertificateManager.getSignedData = function(signInfo, plainText, callback)
{
	cordova.exec(callback, null, "CertificatePlugin", "getSignedData", [signInfo.signFlag, signInfo.certDn, signInfo.certPw, plainText]);
};

// 인증서 발급 / 재발급
CertificateManager.certIssue = function(refValue, secretValue, certPassword, callback)
{
	cordova.exec(callback, null, "CertificatePlugin", "certIssue", [refValue, secretValue, certPassword]);
};

// 인증서 갱신
CertificateManager.certUpdate = function(certDn, certPassword, callback)
{
	cordova.exec(callback, null, "CertificatePlugin", "certUpdate", [certDn, certPassword]);
};


