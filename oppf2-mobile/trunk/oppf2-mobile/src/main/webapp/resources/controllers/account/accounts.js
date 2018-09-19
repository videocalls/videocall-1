/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("Accounts", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;


	vm.companyNameKorAlias = "";
	vm.customerRealaccountTypeNm = "";
	vm.customerRealaccountNo = "";
	vm.customerVtaccountNo = "";
	vm.customerVtaccountAlias = "";

    vm.companyCodeId = "";
	vm.accessToken = "";
    vm.customerId = "";
    vm.customerRegNo = "";
    vm.customerCi = "";
    vm.companyProfileRegNo = "";
    vm.customerRealaccountTypeNmEng = "";
	
    /**
     * 설명       : base64 encode, decode
     * 사용방식     : gfnBase64 
     * 주의       : 
     * 리턴       : 
     */
    var gfnBase64 = {
        // private property
        _keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="

        // public method for encoding
        ,encode : function(input){
            var output = "";
            var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
            var i = 0;
            input = gfnBase64._utf8_encode(input);
            while(i < input.length){
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);

                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;

                if(isNaN(chr2)){
                    enc3 = enc4 = 64;
                }else if(isNaN(chr3)){
                    enc4 = 64;
                }
                output = output +
                    this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
                    this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
            }
            return output;
        }

        // public method for decoding
        ,decode : function(input){
            var output = "";
            var chr1, chr2, chr3;
            var enc1, enc2, enc3, enc4;
            var i = 0;
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
            while(i < input.length){
                enc1 = this._keyStr.indexOf(input.charAt(i++));
                enc2 = this._keyStr.indexOf(input.charAt(i++));
                enc3 = this._keyStr.indexOf(input.charAt(i++));
                enc4 = this._keyStr.indexOf(input.charAt(i++));
                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;
                output = output + String.fromCharCode(chr1);
                if(enc3 != 64){
                    output = output + String.fromCharCode(chr2);
                }
                if(enc4 != 64){
                    output = output + String.fromCharCode(chr3);
                }
            }
            output = gfnBase64._utf8_decode(output);
            return output;
        }

        // private method for UTF-8 encoding
        ,_utf8_encode : function (string) {
            string = string.replace(/\r\n/g,"\n");
            var utftext = "";
            for(var n = 0; n < string.length; n++){
                var c = string.charCodeAt(n);
                if(c < 128){
                    utftext += String.fromCharCode(c);
                }else if((c > 127) && (c < 2048)) {
                    utftext += String.fromCharCode((c >> 6) | 192);
                    utftext += String.fromCharCode((c & 63) | 128);
                }else{
                    utftext += String.fromCharCode((c >> 12) | 224);
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                    utftext += String.fromCharCode((c & 63) | 128);
                }
            }
            return utftext;
        }

        // private method for UTF-8 decoding
        ,_utf8_decode : function (utftext) {
            var string = "";
            var i = 0;
            var c = c1 = c2 = 0;
            while( i < utftext.length ){
                c = utftext.charCodeAt(i);
                if(c < 128){
                    string += String.fromCharCode(c);
                    i++;
                }else if((c > 191) && (c < 224)){
                    c2 = utftext.charCodeAt(i+1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2;
                }else{
                    c2 = utftext.charCodeAt(i+1);
                    c3 = utftext.charCodeAt(i+2);
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                    i += 3;
                }
            }
           return string;
        }

        ,URLEncode : function (string){
            return escape(this._utf8_encode(string));
        }

        // public method for url decoding
        ,URLDecode : function (string){
            return this._utf8_decode(unescape(string));
        }
    }

    $scope.$on('$ionicView.enter', function(){
    	
		$koscomBridge.req("ACC_01_011", {"customerVtaccountNo" : $stateParams.customerVtaccountNo, "companyProfileRegNo" : $stateParams.companyProfileRegNo}).then(function(res){
			
			vm.resultAccountList = res.resultAccountList;
			vm.companyProfileRegNo = $stateParams.companyProfileRegNo;
	
			for(var i=0; i<vm.resultAccountList.length; i++){
	
	     	   var item = vm.resultAccountList[i];
	        	   
	    		vm.companyNameKorAlias = item.companyNameKorAlias;
	    		vm.customerRealaccountTypeNm = item.customerRealaccountTypeNm;
	
	    	    vm.customerRealaccountTypeNmEng = item.customerRealaccountTypeNmEng;
	    		vm.customerRealaccountNo = item.customerRealaccountNo;
	    		vm.customerVtaccountNo = item.customerVtaccountNo;
	    		vm.customerVtaccountAlias = item.customerVtaccountAlias;
				
			}
	
			vm.accessToken = res.rsComOauthTokenVO.accessToken;
		    vm.customerId = res.memberRes.customerId;
		    vm.customerRegNo = res.memberRes.customerRegNo;
		    vm.customerCi = res.memberRes.customerCi;
	
			var comProfile = res.ComCompanyProfileRes;
	
			vm.companyNameKorAlias = comProfile.companyNameKorAlias;
			vm.companyNameEngAlias = comProfile.companyNameEngAlias;
			vm.companyCodeId = comProfile.companyCodeId;
			
			
		}).catch(function(e){
			
			console.log("error");
			
		});
	

    });
    
    vm.deleteAlias = function(){
    	vm.customerVtaccountAlias = "";
	}
    
    vm.save = function(trCode){
    	if(trCode == "DIS"){
	    	var result = $koscomPopup.confirm("계좌 삭제", "계좌정보를 삭제하시겠습니까?");
			result.then(function(res){
				if(res==true){
					vm.procAccc(trCode);
				}
			});
    	} else {
			vm.procAccc(trCode);	
    	}
		
    };
    
    
    vm.procAccc = function(trCode){
    	

    	var realAccNo   = vm.customerRealaccountNo						//	실계좌번호
    	var realAccType = vm.customerRealaccountTypeNmEng;				//	구분
    	var vtAccAlias  = vm.customerVtaccountAlias;					//	별칭
    	var vtAccNo		= vm.customerVtaccountNo;						//  가상계좌번호
      
        //전문요청 발급(REQ)/교체(REP)/폐기(DIS)

        var accInfo = new Object();
        accInfo.realAccNo   = gfnBase64.encode(realAccNo);
        accInfo.realAccType = realAccType;

        accInfo.vtAccAlias = vtAccAlias;
        accInfo.vtAccNo = vtAccNo;
        
        var account = new Array();
        account.push(accInfo);
        
		$koscomBridge.req("ACC_01_090",{
	        "apiKey"        : "",
	        "Authorization" : "",
	        "companyNameEngAlias" : vm.companyNameEngAlias,
	        "trCode"          : trCode,
	        "companyCode"     : vm.companyCodeId,
	        "account"         : account,
	        "requestUserId"   : vm.customerId,
	        "requestUniqueId" : vm.customerRegNo,
	        "userCi"          : vm.customerCi,
	        "accessToken"     : vm.accessToken,
	        "userDn"          : ''
		},{} ).then(function(res){
            	//if(trCode == "REP"){
//            		$koscomPopup.alert("", "정상적으로 저장되었습니다.").then(function(){
                    	//$koscomState.back(-1);	
            		//});

            if(res.statusCode == '500'){
                        var message = "";
                        var code = "";

                        var msgText = "";

                        try{
                            message = res.responseBody.message;
                            code = res.responseBody.code;

                            //msgText = "관리자에게 문의하세요.\n["+message+"("+res.statusCode+ " " +code+")]";
                            msgText = "관리자에게 문의하세요.";
                        }catch(e){
                            //msgText = "관리자에게 문의하세요.\n["+res.statusCode+"]";
                            msgText = "관리자에게 문의하세요.";
                        }

                		$koscomPopup.alert("", "관리자에게 문의하세요.").then(function(){
                        	$koscomState.back(-1);		
                		});
                        
            }else {


            	if(res.statusCode == '200'){
                    if(res.responseBody.status == 'SUCCESS' || res.responseBody.message == '처리성공'){
                        resAccList = res.account;
                        if(resAccList == null || resAccList == 'undefined'){
                            //실패
                        	//$koscomPopup.alert("", "관리자에게 문의하세요.\n["+res.responseBody.message+"("+res.responseBody.code+")]");

                    		$koscomPopup.alert("", "관리자에게 문의하세요.").then(function(){
                            	$koscomState.back(-1);		
                    		});
                    		
                        }else{
                            if(resAccList.length > 0){
                                if(resAccList[0].status == "FAILURE" || resAccList[0].status == "FAILED"){
                                    //실패
                                	//$koscomPopup.alert("", "관리자에게 문의하세요.\n["+resAccList[0].message+"]");

                            		$koscomPopup.alert("", "관리자에게 문의하세요.").then(function(){
                                    	$koscomState.back(-1);		
                            		});
                                }else{
                                    /*
                                     if(util_chkReturn(resRealAccNo, "s") != "" && util_chkReturn(resVtAccNo, "s") != "" && util_chkReturn(resVtAccAlias, "s") != ""){
                                     returnFlag = true;
                                     }
                                     */

                            		$koscomPopup.alert("", "정상적으로 저장되었습니다.").then(function(){
                                    	$koscomState.back(-1);		
                            		});
                                    //returnFlag = true;
                                }
                            }
                        }
                    }else{
                        /*
                    	if(util_chkReturn(res.account, "s") == ""){
                        	//$koscomPopup.alert("", res.responseBody.message+'\n['+res.responseBody.code+']');

                    		$koscomPopup.alert("", "관리자에게 문의하세요.").then(function(){
                            	$koscomState.back(-1);		
                    		});
                        } else {
						*/
                    		$koscomPopup.alert("", "정상적으로 저장되었습니다.").then(function(){
                            	$koscomState.back(-1);		
                    		});
                        //}
                    }
                }else if(res.statusCode == '400' || res.statusCode == 'BAD_REQUEST'){
                    //Access token does not exist -> access token 발급 상황 문제 발생
                    //Access token has expired -> access token 갱신일 문제
                    if(res.message.indexOf('Access token does not exist') >= 0 || res.message.indexOf('Access token has expired') >= 0){
                        if(reqCnt >= 3){ //400에러 무한루프 막기 분기문
                        	//$koscomPopup.alert("", "관리자에게 문의하세요.\n["+res.message+"("+res.error+")]");

                    		$koscomPopup.alert("", "관리자에게 문의하세요.").then(function(){
                            	$koscomState.back(-1);		
                    		});
                            reqCnt = 0;
                        }else{
                            //fn_ajaxCallGetOAuthToken(); //oauthToken요청 호출
                            //reqCnt++;
                        }
                    }else{
                    	//$koscomPopup.alert("", "관리자에게 문의하세요.\n["+res.responseBody.message+"("+res.responseBody.code+")]");

                		$koscomPopup.alert("", "관리자에게 문의하세요.").then(function(){
                        	$koscomState.back(-1);		
                		});	
                    }
                }else{
                	//$koscomPopup.alert("", "관리자에게 문의하세요.\n["+res.responseBody.message+"("+res.responseBody.code+")]");

            		$koscomPopup.alert("", "관리자에게 문의하세요.").then(function(){
                    	$koscomState.back(-1);		
            		});
                }
            }
    	

 		}).catch(function(e){

 			console.log("error");
 		});
    	
    }
	
	
});