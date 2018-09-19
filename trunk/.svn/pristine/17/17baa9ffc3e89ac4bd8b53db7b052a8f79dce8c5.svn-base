cordova.define("cordova-plugin-certificate.CertificateProxy", function(require, exports, module) {
               
	
	var OPPF2 = require("cordova-plugin-app.OPPF2");
    // 공인인증서 리스트 정보
	var certList =  [
         	{
        	    "subjectName":"cn=김재훈-5101640,ou=HTS,ou=한국투자,ou=증권,o=SignKorea,c=KR",
        	    "expiredImg":1,
        	    "expiredTime":"2099.01.16",
        	    "issuerName":"SignKorea",
        	    "policy":"증권/보험용"
            },
         	{
        	    "subjectName":"cn=허영남-15030488,ou=HTS,ou=신한금융투자,ou=증권,o=SignKorea,c=KR",
        	    "expiredImg":1,
        	    "expiredTime":"2099.01.16",
        	    "issuerName":"SignKorea",
        	    "policy":"증권/보험용"
            },
            {
                "subjectName":"cn=김충열-15030488,ou=HTS,ou=신한금융투자,ou=증권,o=SignKorea,c=KR",
                "expiredImg":1,
                "expiredTime":"2099.01.16",
                "issuerName":"SignKorea",
                "policy":"증권/보험용"
            },
        	{
        	    "subjectName":"cn=이선하,ou=HTS,ou=신한금융투자,ou=증권,o=SignKorea,c=KR",
        	    "expiredImg":1,
        	    "expiredTime":"2018.01.16",
        	    "issuerName":"SignKorea",
        	    "policy":"증권/보험용"
                },
        	{
        	    "subjectName":"cn=최판광,ou=HTS,ou=키움,ou=증권,o=SignKorea,c=KR",
        	    "expiredImg":1,
        	    "expiredTime":"2018.01.16",
        	    "issuerName":"SignKorea",
        	    "policy":"증권/보험용"
                },
            {
                "subjectName":"cn=이희태-4159962,ou=HTS,ou=동양,ou=증권,o=SignKorea,c=KR",
                "expiredImg":1,
                "expiredTime":"2018.01.16",
                "issuerName":"SignKorea",
                "policy":"증권/보험용"
                }
	]; 
	
    module.exports.getUserCertList = function(success, error, args) {
       
           window.setTimeout(function(){
                             success({code:2000, certList:certList});
                             
                             }, 0);
    };
           
    module.exports.simpleCertImport1 = function(success, error, args) {
           var resData = {};
           resData.code = OPPF2.CODE_SUCCESS;
           resData.message = "SUCCESS";
           resData.num1 = "1122";
           resData.num2 = "3344";
           resData.num3 = "5566";
           window.setTimeout(function(){
                             success(resData);
                             
                             }, 0);
           
           
    };
               
    module.exports.simpleCertImport2 = function(success, error, args) {
           var resData = {};
    //       resData.code = OPPF2.CODE_SUCCESS;
           resData.code = OPPF2.CODE_FAIL_CERT_INVALID_TYPE;
           resData.message = "SUCCESS";
           resData.certDn = "cn=김재훈-5101640,ou=HTS,ou=한국투자,ou=증권,o=SignKorea,c=KR";
           resData.certPolicy = "구분 : 증권/보험용";
          window.setTimeout(function(){
                            success(resData);
                                 
                                 }, 0);
               
               
    };
          
    module.exports.deleteCert = function(success, error, args) {
        var subjectDn = args[0];
        var cipherPwd = args[1];
        window.setTimeout(function(){
                          success(OPPF2.CODE_SUCCESS);
                          
                          }, 0);
        
        
    };
    
   module.exports.checkPwd = function(success, error, args) {
       var subjectDn = args[0];
       var cipherPwd = args[1];
       window.setTimeout(function(){
                         success(OPPF2.CODE_SUCCESS);
                         
                         }, 0);
       
       
   };
       
   module.exports.getSignedData = function(success, error, args) {
       var certType = args[0];
       // 인증서 subjectDn
       var subjectDn = args[1];
       // 인증서 비밀번호
       var certPw = args[2];
       // 서명 데이터
       var plainText = args[3];
       
       var resData = {
    		   code:OPPF2.CODE_SUCCESS, 
    		   data:plainText
       };
       window.setTimeout(function(){
                         success(resData);
                         
                         }, 0);
   
   
   };
    require("cordova/exec/proxy").add("Certificate",module.exports);
});
