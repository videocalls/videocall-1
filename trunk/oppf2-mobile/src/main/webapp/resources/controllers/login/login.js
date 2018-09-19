/*
 * author : SH.Lee
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("Login", function($scope, $rootScope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
	var vm = this;
	
	$scope.$on('$ionicView.enter', function(){
		vm.customerId = "";
		vm.customerPassword = "";
		vm.passwordDisplayText = "";

		vm.osType = "";
		vm.osVersion = "";
		vm.appVersion = "";
		vm.deviceToken = "";
		vm.appKey = "";
		vm.companyCodeId = "";
		//아이디 에러 message
		vm.idFocusOut = false;
		//비밀번호 에러 message
		vm.passwordFocusout = false;

		vm.idSaveChecked = vm.customerId ? true : false;
	});

	vm.customerId = "";
	vm.customerPassword = "";

	vm.osType = "";
	vm.osVersion = "";
	vm.appVersion = "";
	vm.deviceToken = "";
	vm.appKey = "";
	vm.companyCodeId = "";
	//아이디 에러 message
	vm.idFocusOut = false;
	//비밀번호 에러 message
	vm.passwordFocusout = false;

	vm.idLogin = function(){
		if(vm.customerId == null || vm.customerId == "" || vm.customerPassword == null || vm.customerPassword == ""){
			vm.idFocusOut = true;
			vm.passwordFocusOut = true;
		}else{
			vm.submit();
		}


	}

	vm.submit = function(){
		$koscomBridge.getDeviceInfo().then(function(deviceInfo){

			vm.osType = deviceInfo.osType;
			vm.osVersion = deviceInfo.osVersion;
			vm.appVersion = deviceInfo.appVersion;
			vm.deviceToken = deviceInfo.deviceToken;


			$koscomBridge.req("MBR_02_100",{
				  "customerId": vm.customerId,
				  "customerPassword": vm.customerPassword,
				  "deviceType": vm.osType,
				  "deviceUniqueId": vm.deviceToken,
				  "appKey": vm.appKey,
				  "companyCodeId": vm.companyCodeId,
				  "osVersion": vm.osVersion,
				  "appVersion": vm.appVersion
				}).then(function(res){

				vm.loginRes = res.loginRes;
                vm.termsResult = res.termsResult;
                vm.companyTermsResult = res.companyTermsResult;

				if(vm.loginRes.customerExpirePasswordYn == "Y" || vm.loginRes.customerFinalPasswordYn == "Y"){
					//팝업
					$koscomPopup.modalFromTemplateUrl("/resources/views/member/passwordModifyPopup.html",{
						name: "passwordModify",
						controllerUrl: "/resources/controllers/member/passwordModifyPopup.js",
						scope: $scope
					}).then(function(modal) {
						modal.show();
					});


				}else{
					if(vm.termsResult != null || vm.companyTermsResult != null){
						$koscomPopup.modalFromTemplateUrl("/resources/views/member/memberTermsPopup.html",{
							name: "memberTermsPopup",
							controllerUrl: "/resources/controllers/member/memberTermsPopup.js",
							scope: $scope
						}).then(function(modal) {
							modal.show();
						});
					}else{
						$scope.$emit("login");
						//무조건 메인 페이지로 이동후 메인페이지에서 리다이렉트 페이지로 이동한다.
						$koscomState.go("main");
					}
				}

			}).catch(function(e){
				console.log("error");
				//아이디 없는 경우, 사용정지된 계정,
				if(e.code == '9101' || e.code == '9121' || e.code == '9122' || e.code == '9123' || e.code == '9124' || e.code == '9121'){
					$koscomPopup.alert("",e.message);
				}
				else if(e.code == '9115'){
					$koscomPopup.alert("","비밀번호를 확인해주세요.");
				}

			});

		});
	};


//테스트로그인
	vm.testLoginAction = function(){

        $koscomBridge.getDeviceInfo().then(function(res){
			vm.deviceInfoText = res;
		});
        
        // 디바이스 정보 취득
        $koscomBridge.getDeviceInfo().then(function(res){
			vm.deviceInfoText = res;
		});

        if(vm.deviceInfoText != null){
	        vm.osType = vm.deviceInfoText.osType;
	        vm.deviceToken = vm.deviceInfoText.deviceToken;
	        vm.osVersion = vm.deviceInfoText.osVersion;
	        vm.appVersion = vm.deviceInfoText.appVersion;
        }
        
		$koscomBridge.req("MBR_02_160",{
			"customerId": vm.customerId,
		}).then(function(res){
			vm.loginRes = res.loginRes;
            vm.termsResult = res.termsResult;
            vm.companyTermsResult = res.companyTermsResult;

			if(vm.loginRes.customerExpirePasswordYn == "Y" || vm.loginRes.customerFinalPasswordYn == "Y"){
				//팝업
				$koscomPopup.modalFromTemplateUrl("/resources/views/member/passwordModifyPopup.html",{
					name: "passwordModify",
					controllerUrl: "/resources/controllers/member/passwordModifyPopup.js",
					scope: $scope
				}).then(function(modal) {
					modal.show();

				});
			}else{
                if(vm.termsResult != null || vm.companyTermsResult != null){
                    $koscomPopup.modalFromTemplateUrl("/resources/views/member/memberTermsPopup.html",{
                        name: "memberTermsPopup",
                        controllerUrl: "/resources/controllers/member/memberTermsPopup.js",
                        scope: $scope
                    }).then(function(modal) {
                        modal.show();
                    });
                }else{
                    $scope.$emit("login");
                    //무조건 메인 페이지로 이동후 메인페이지에서 리다이렉트 페이지로 이동한다.
                    $koscomState.go("main");
                }
			}


		}).catch(function(e){
			console.log("error");
			$koscomPopup.alert("",e.message);
		});

	}
		


	/**
	 * 회원가입 클릭
	 * */
	vm.memberCreate = function(){
		$koscomState.go("memberPrepare");
	};
	

	/**
	 * 보안 키패드 호츨
	 */
	vm.customerPassword = "";
	vm.keypadOpen = function(){
		$koscomBridge.keypadOpen("비밀번호를 입력하세요.", 20, $koscomBridge.PAD_TYPE_ALPHA_U, $koscomBridge.ENCRYPT_RSA).then(function(res){
			vm.customerPassword = res.cipherData;
			vm.passwordDisplayText = res.displayText;
			// $koscomPopup.alert(JSON.stringify(res));
		}).catch(function(){
			console.log("error");
		});
	};
	
    /**
     * 공인인증서 로그인
     * */
    vm.memberLoginCert = function(){
        $koscomState.go("signKoreaCert",{isfailUpCount : true});
//        vm.goParam2('signKoreaCert', {});
    };
    
    // 페이지 접근시(뒤로가기 허용) 호출
    $scope.$on('$ionicView.enter', function(){
        vm.signKoreaResultData = '';
        $koscomBridge.getAppData("signKoreaCertResultData").then(function(res){
            if(res.result == 1) {
                // $koscomPopup.alert("공인인증 결과값 확인\n 공인인증서 결과값으로 로그인 처리 후 페이지 이동",angular.toJson(res));
				//공인인증서 로그인 API
				if(res.customerId == null){
					$koscomPopup.alert("", "회원으로 가입되지 않은 사용자이거나, 공인인증서가 등록되지 않은 회원입니다. 회원가입 및 공인인증서 등록 후 이용해주세요.");
				}else{
					vm.loginProfileDn(res);
				}
            }
            vm.signKoreaResultData = res;
            $koscomBridge.setAppData("signKoreaCertResultData", {});

        })
    });

	/**
	 * 공인인증서 로그인 API
	 * */
	vm.loginProfileDn = function(resultDn){
        $koscomBridge.getDeviceInfo().then(function(deviceInfo){

            vm.osType = deviceInfo.osType;
            vm.osVersion = deviceInfo.osVersion;
            vm.appVersion = deviceInfo.appVersion;
            vm.deviceToken = deviceInfo.deviceToken;

	    
        		$koscomBridge.req("MBR_02_150",{
        			customerId: resultDn.customerId,
        			customerVerifyDn: resultDn.customerDn,
        			signData: resultDn.signData,
                    "deviceType": vm.osType,
                    "deviceUniqueId": vm.deviceToken,
                    "osVersion": vm.osVersion,
                    "appVersion": vm.appVersion
        		}).then(function(res){
        			vm.loginRes = res.loginRes;
                    vm.termsResult = res.termsResult;
                    vm.companyTermsResult = res.companyTermsResult;
        
        			if(vm.loginRes.customerExpirePasswordYn == "Y" || vm.loginRes.customerFinalPasswordYn == "Y"){
        				//팝업
        				$koscomPopup.modalFromTemplateUrl("/resources/views/member/passwordModifyPopup.html",{
        					name: "passwordModify",
        					controllerUrl: "/resources/controllers/member/passwordModifyPopup.js",
        					scope: $scope
        				}).then(function(modal) {
        					modal.show();
        
        				});
        			}else{
                        if(vm.termsResult != null || vm.companyTermsResult != null){
                            $koscomPopup.modalFromTemplateUrl("/resources/views/member/memberTermsPopup.html",{
                                name: "memberTermsPopup",
                                controllerUrl: "/resources/controllers/member/memberTermsPopup.js",
                                scope: $scope
                            }).then(function(modal) {
                                modal.show();
                            });
                        }else{
                            $scope.$emit("login");
                            //무조건 메인 페이지로 이동후 메인페이지에서 리다이렉트 페이지로 이동한다.
                            $koscomState.go("main");
                        }
        			}
        
        		}).catch(function(e){
        			$koscomPopup.alert("",e.message);
        		});
    		
        });

    		
	}


	/*
	* 비밀번호찾기
	* */
	vm.findPassword = function(){
		$koscomState.go("findPassword", {
			customerId : vm.customerId
		})
	}
});