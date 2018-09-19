/*
koscomApp.directive("pwdValid", function (){
	return {
		require: "ngModel",
		link: function(scope, elem, attr, ngModel){
			ngModel.$parsers.unshift(function(value) {
				var korean_reg = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힝]/;
				var korean = !korean_reg.test(value);
				ngModel.$setValidity("onlyeng", korean);
				
				if(korean === false){
					return value;
				}
				
				var not_reg = /[\"\\]/;
				var notstr = !not_reg.test(value);
				ngModel.$setValidity("notstr", notstr);
				
				if(notstr === false){
					return value;
				}
				
				var sameStr4_reg =  /(\w)\1\1\1/;
				var sameStr4 = !sameStr4_reg.test(value);
				ngModel.$setValidity("samestr4", sameStr4);
				
				if(sameStr4 === false){
					return value;
				}
				
				var pwChkCnt1 = 0;
		        if (/[0-9]/.test(value)) { //숫자
		            pwChkCnt1++;
		        }
		        var pwChkCnt2 = 0;
		        if (/[A-Z]/.test(value)) { //대문자
		            pwChkCnt2++;
		        }
		        var pwChkCnt3 = 0;
		        if (/[~!@\#$%<>^&*\()\-=+_\’]/.test(value)) { //특수문자
		            pwChkCnt3++;
		        }
		        if (/[a-z]/.test(value)) { //대문자
		            pwChkCnt2++;
		        }

		        var pwChkCnt = pwChkCnt1 + pwChkCnt2 + pwChkCnt3;
		        if (pwChkCnt <= 2) {
		        	ngModel.$setValidity("syntax", false);
		        	return value;
		        }
		        else{
		        	ngModel.$setValidity("syntax", true);
		        }
		        
		        //연속된 숫자
		        if (/(0123)|(1234)|(2345)|(3456)|(4567)|(5678)|(6789)|(7890)|(0987)|(9876)|(8765)|(7654)|(6543)|(5432)|(4321)|(3210)/.test(value)){
	        		ngModel.$setValidity("consecutively", false);
	        		return value;
	        	}
	        	else{
	        		ngModel.$setValidity("consecutively", true);
	        	}
		        
		        //연속된 문자 확인
	            var ckAlpa = "abcdefghijklmnopqrstuvwxyz";
	            for (var i = 0; i < ckAlpa.length; i++) {
	                if (i + 4 > ckAlpa.length) {
	                    break;
	                } else {
	                    if (value.toLowerCase().indexOf(ckAlpa.substring(i, i + 4)) != -1) {
	                        if (ckAlpa.substring(i, i + value.length).length > 3) {
	                        	ngModel.$setValidity("consecutively", false);
	                        	return value;
	                        }
	                        else{
	                        	ngModel.$setValidity("consecutively", true);
	                        }
	                    }
	                }
	            }

	            //연속된 문자 확인
	            var ckAlpa2 = "zyxwvutsrqponmlkjihgfedcba";
	            for (var i = 0; i < ckAlpa2.length; i++) {
	                if (i + 4 > ckAlpa2.length) {
	                    break;
	                } else {
	                    if (value.toLowerCase().indexOf(ckAlpa2.substring(i, i + 4)) != -1) {
	                    	if (ckAlpa2.substring(i, i + value.length).length > 3) {
	                        	ngModel.$setValidity("consecutively", false);
	                        	return value;
	                        }
	                        else{
	                        	ngModel.$setValidity("consecutively", true);
	                        }
	                    }
	                }
	            }
		        
				return value;
			});
		}
	}
});
*/

koscomApp.directive("pwdValid", function ($koscomBridge){
	return {
		link: function(scope, elem, attr){
			scope.$watch("vm.customerPassword", function(newValue, oldValue){
				if(!scope.vm.customerPassword && !scope.vm.customerPasswordConfirm){
					return;
				}
				$koscomBridge.req("MBR_02_182",{
		            customerPassword : scope.vm.customerPassword,
		            customerPasswordConfirm : scope.vm.customerPasswordConfirm
		        }).then(function(res){
		        	scope.vm.passwordError = res.passwordError;               //비밀번호 에러메세지 show hide 여부
		            scope.vm.passwordConfirmError = res.passwordConfirmError;        //비밀번호 확인 에러메세지 show hide 여부
		            //vm.passwordErrorMsg = res.passwordErrorMsg;               //비밀번호 에러메세지
		            //vm.passwordConfirmErrorMsg = res.passwordConfirmErrorMsg;        //비밀번호 확인 에러메세지
		        }).catch(function(e){

		        });
			});
		}
	}
});
/*
koscomApp.directive("pwdConfirmValid", function (){
	return {
		require: "ngModel",
		link: function(scope, elem, attr, ngModel){
			ngModel.$parsers.unshift(function(value) {
				var pwd = scope.vm.customerPassword;
				if(pwd === value){
					ngModel.$setValidity("same", true);
				}
				else{
					ngModel.$setValidity("same", false);
				}
				return value;
			});
		}
	}
});
*/

koscomApp.directive("pwdConfirmValid", function ($koscomBridge){
	return {
		link: function(scope, elem, attr){
			scope.$watch("vm.customerPasswordConfirm", function(newValue, oldValue){
				if(!scope.vm.customerPassword && !scope.vm.customerPasswordConfirm){
					return;
				}
				$koscomBridge.req("MBR_02_182",{
		            customerPassword : scope.vm.customerPassword,
		            customerPasswordConfirm : scope.vm.customerPasswordConfirm
		        }).then(function(res){
		            //vm.passwordError = res.passwordError;               //비밀번호 에러메세지 show hide 여부
		            //vm.passwordConfirmError = res.passwordConfirmError;        //비밀번호 확인 에러메세지 show hide 여부
		            scope.vm.passwordErrorMsg = res.passwordErrorMsg;               //비밀번호 에러메세지
		            scope.vm.passwordConfirmErrorMsg = res.passwordConfirmErrorMsg;        //비밀번호 확인 에러메세지
		        }).catch(function(e){

		        });
			});
		}
	}
});