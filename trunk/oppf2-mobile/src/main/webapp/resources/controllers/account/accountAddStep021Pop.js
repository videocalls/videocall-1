/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("AccountAddStep021Pop", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;
    
    var appParams=$stateParams.appParams||{}; //앱신청,신청정보 수정으로 들어왔을 경우
    
    vm.sptCustAccList = [];
    vm.sptCustAccList2 = [];
    vm.companyNameKorAlias = "";
    vm.selectedIndex = -1;
    vm.customerVtaccountAlias = "";
    
    vm.companyProfileRegNo = "";
    vm.companyCodeId = "";
    vm.accessToken = "";
    vm.companyNameEngAlias = "";

    vm.customerId = "";
    vm.customerRegNo = "";
    vm.customerCi = "";
    
    vm.resAccList = [];
    vm.rsComOauthTokenVO=[];
    vm.cmmAccTypeList = [];
    vm.sptCustomerCompanyTermsProfileRes = [];
    


    vm.companyTermsType = "";
    vm.companyTermsContentRegSeq = "";
    
    vm.agree = false;
    //vm.selectedIndex = 0;

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
    
    /* 가상계좌[발급,교체,폐기]전문요청 함수 */
    vm.save = function(){
    	console.log(vm.selectedIndex);
    	if(vm.selectedIndex == -1){

    		$koscomPopup.alert("", "선택된 계좌가 없습니다.").then(function(){
            	return;
    		});
    	}
    	var item = vm.sptCustAccList[parseInt(vm.selectedIndex)];
    	
    	var realAccNo   = item.customerRealaccountNo;					//	실계좌번호
    	var realAccType = item.customerRealaccountTypeNmEng;			//	실계좌형식
    	//var vtAccAlias  = item.customerVtaccountAlias;					//	가상계좌별칭
    	var customerRealaccountType = item.customerRealaccountType;		//	실계좌유형
    	var customerVtaccountAlias = vm.customerVtaccountAlias;			//	별칭
    	
        //전문요청 발급(REQ)/교체(REP)/폐기(DIS)
        var accInfo = new Object();
        accInfo.realAccNo   = gfnBase64.encode(realAccNo);
        accInfo.realAccType = realAccType;

        accInfo.vtAccAlias = customerVtaccountAlias;
        
        var account = new Array();
        account.push(accInfo);
  
		$koscomBridge.req("ACC_01_090",{
	        "apiKey"        : "",
	        "Authorization" : "",
	        "companyNameEngAlias" : vm.companyNameEngAlias,
	        "trCode"          : "REQ",
	        "companyCode"     : vm.companyCodeId,
	        "account"         : account,
	        "requestUserId"   : vm.customerId,
	        "requestUniqueId" : vm.customerRegNo,
	        "userCi"          : vm.customerCi,
	        "accessToken"     : vm.accessToken,
	        "userDn"          : ''
		},{} ).then(function(res){

            if(res.statusCode == '200'){
	        		if(vm.sptCustomerCompanyTermsProfileRes != null){
		            	$koscomBridge.req("ACC_01_100",{
		                    "companyCodeId" : vm.companyCodeId,
		                    "companyTermsType" : vm.companyTermsType,
		                    "companyTermsContentRegSeq" : vm.companyTermsContentRegSeq
		        		},{} ).then(function(res){
		        			
		        			
		        			//$scope.$emit("account_insert", res);

		                	if(appParams.appId){
		                		$koscomPopup.alert("", "정상적으로 저장되었습니다.").then(function(){
		                        	$koscomState.back(-2);	
		                		});
		                	} else {
		                		$koscomPopup.alert("", "정상적으로 저장되었습니다.").then(function(){
		                        	//$koscomState.back(-3);
		                        	$koscomState.go("accountList");
		                        	
		                		});
		                	}
		        			
		         		}).catch(function(e){
		
		         			console.log("error");
		         		});
	        		} else {
	        			$koscomState.back(-2);
	        		}
            	
            }else{
            	$koscomPopup.alert("", "증권사 검증에 실패하였습니다.\n계좌 정보를 다시 확인해주세요.")
            }
    	

 		}).catch(function(e){

 			console.log("error");
 		});

		
		
    };
    
	$koscomBridge.req("ACC_01_071",{
		"companyProfileRegNo":$stateParams.companyProfileRegNo,
		"companyCodeId":$stateParams.companyCodeId
	}).then(function(res){

	    vm.companyProfileRegNo = $stateParams.companyProfileRegNo;
	    vm.companyCodeId = $stateParams.companyCodeId;
		vm.sptCustAccList = res.sptCustAccList;
		
		var comProfile = res.ComCompanyProfileRes;
		vm.companyNameKorAlias = comProfile.companyNameKorAlias;
		vm.companyNameEngAlias = comProfile.companyNameEngAlias;
		vm.accessToken = res.rsComOauthTokenVO.accessToken;
		vm.cmmAccTypeList = res.cmmAccTypeList;
		


	    vm.customerId = res.memberRes.customerId;
	    vm.customerRegNo = res.memberRes.customerRegNo;
	    vm.customerCi = res.memberRes.customerCi;
		vm.sptCustomerCompanyTermsProfileRes = res.sptCustomerCompanyTermsProfileRes;
		
		if(vm.sptCustomerCompanyTermsProfileRes != null){
			vm.companyTermsContentRegSeq = vm.sptCustomerCompanyTermsProfileRes.companyTermsContentRegSeq;
			vm.companyTermsType = vm.sptCustomerCompanyTermsProfileRes.companyTermsType;
		}
        
		$koscomBridge.req("ACC_01_020",{
			  "companyNameEngAlias": vm.companyNameEngAlias,
			  "accessToken": vm.accessToken
		},{
            "apiKey"              : ""
            ,"Authorization"       : ""
            ,"x-credential-userId" : vm.customerId
            ,"x-api-requestId"     : vm.customerRegNo
            ,"x-credential-ci"     : vm.customerCi
            ,"x-credential-dn"     : "aaa"
         } ).then(function(res){
		   if(res.statusCode == '200' || res.statusCode == 'OK'){
			   
			   
			   resAccList = JSON.parse(res.strResBody).account;

			   if( resAccList == 'undefined' || resAccList == null){
				    
			   }else{
				   
				   if(resAccList.length > 0){
			    	   accList  = resAccList;
			           accList2 = resAccList;
			           vm.sptCustAccList2 = vm.sptCustAccList;
			           vm.sptCustAccList = [];
			           
			           for(var j=0; j<vm.sptCustAccList2.length; j++){
			
			        	   var item = vm.sptCustAccList2[j];
			               var dAccNo = item.customerRealaccountNo;
			               var dAccTypeNmEng = item.customerRealaccountTypeNmEng;
			               
			               for(var i=0; i<accList.length; i++){
			                   var cAccNo = accList[i].accNo;
			                   var cAccType = accList[i].accType;
			                   if(dAccNo == cAccNo){
			                       accList2.splice(i,1);
			                   }
			               }
			           }
		               
			           
			           for(var i=0; i<accList2.length; i++){
			               var cAccNo = accList2[i].accNo;
			               var cAccType = accList2[i].accType;
			               
			               var accInfo = new Object();
			               accInfo.customerRealaccountNo   = cAccNo;
			               accInfo.customerRealaccountTypeNmEng = cAccType;
			
			               for(var j=0; j<vm.cmmAccTypeList.length; j++){
			            	   var codeNameEng = vm.cmmAccTypeList[j].codeNameEng;
			            	   if(cAccType == codeNameEng){
			            		   accInfo.customerRealaccountTypeNm = vm.cmmAccTypeList[j].codeNameKor;
			            		   break;
			            	   }
			               }

			               vm.sptCustAccList.push(accInfo);
				        }   
		           }   
			   }
           }
		   
		}).catch(function(e){
		console.log("error");
		});

	}).catch(function(e){

		console.log("error");
		
	});
	
    vm.close = function(){
        $koscomPopup.close();

    };

    vm.cancel = function(){
    	$koscomState.back(-1);
    };
    
	
    
});