/**
 * @author kyo
 */

//----------------------------------------------------------------------------------------------------
//보안키패드
//----------------------------------------------------------------------------------------------------

var SecurePadManager = {};

SecurePadManager.callbackFunc = null;
SecurePadManager.padWorkDone = function(code, data, len)
{
	SecurePadManager.callbackFunc(code, data, len);
	SecurePadManager.callbackFunc = null;
};

SecurePadManager.functionCheck = function(callback)
{
	if(SecurePadManager.callbackFunc) 
		SecurePadManager.callbackFunc(0);
		
	SecurePadManager.callbackFunc = callback;
	return true;
};

//--------------------------------------------------------------------------------
// 패드 띄우기
/*  option = 
 {
  title: '비밀번호 입력',  //키패드 타이틀
  padType: 'alpha_l',  //키패드 타입('alpha_u' 대문자 /'alpha_l'/'number' 숫자) 
  inputType: 'pwd',  //텍스트 표시형태 ('pwd' : '*' 로 보임 [ 마지막 입력 글자 2초간 보이기] / 'pwdl' :'*' 로 보임 ) 
  maxLength: 20,   //max 길이
  minLength: 4,   //min 길이
  encryptMethod:'rsa' // encryption 방식 ( 'rsa' / 'seed')
  }
*/
//	callback(int code, String data);
//
SecurePadManager.openPad = function( option, callback)
{
	if(SecurePadManager.functionCheck(callback))
		cordova.exec(null, null, "SecurePadPlugin", "openPad", [option]);
};

//--------------------------------------------------------------------------------
//	보안키 셋팅
//	@function setSecureKey(String secureKey); 
SecurePadManager.setSecureKey = function(secureKey)
{
	cordova.exec(null, null, "SecurePadPlugin", "setSecureKey", [secureKey]);
};


//--------------------------------------------------------------------------------
//	평문값 가져오기
SecurePadManager.cipherToPlain = function(cipherData, callback)
{
	if(!cipherData) cipherData = '';
	cordova.exec(callback, null, "SecurePadPlugin", "cipherToText", [cipherData]);
};







