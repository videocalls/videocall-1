/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("Sample", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup, $koscomLoading){
	var vm = this;
	
	/* event */
	vm.setAppData = function(){
		$koscomBridge.setAppData("testName", { data: 1}).then(function(){
			console.log("setAppData success");
		}).catch(function(){
			console.log("setAppData error");
		});
	};
	vm.getAppData = function(){
		$koscomBridge.getAppData("testName").then(function(res){
			console.log("getAppData success", res);
		}).catch(function(){
			console.log("getAppData error");
		});
	};
	vm.setPageData = function(){
		$koscomBridge.setPageData("testName", { data: 1}).then(function(){
			console.log("setAppData success");
		}).catch(function(){
			console.log("setAppData error");
		});
	};
	vm.getPageData = function(){
		$koscomBridge.getPageData("testName").then(function(res){
			console.log("getPageData success", res);
		}).catch(function(){
			console.log("getPageData error");
		});
	};
	
	vm.alert = function(){
		var c = $koscomPopup.alert(null, "내용입니다.");
		c.then(function(){
			console.log("click!!");
		});
	};
	vm.confirm = function(){
		var c = $koscomPopup.confirm("제목", "내용입니다.");
		c.then(function(res){
			alert(res);
		});
	};
	
	vm.modelTemplate = function(){
		var html = '<ion-modal-view ng-controller="Modal as vm">'
		    + '<ion-header-bar>'
		    + '<h1 class="title">My Modal title</h1>'
		    + '<button ng-click="vm.close()">버튼</button>'
		    + '</ion-header-bar>'
		    + '<ion-content>Hello!</ion-content>'
		    + '</ion-modal-view>';
		$koscomPopup.modalFromTemplate(html, {
			controllerUrl: "/resources/controllers/sample/modal.js"
		}).then(function(modal){
			modal.show();
		});
	};
	vm.modelTemplateUrl = function(){
		$koscomPopup.modalFromTemplateUrl("/resources/views/sample/modal.html", {
			name: "aa",
			controllerUrl: "/resources/controllers/sample/modal.js"
		}).then(function(modal) {
			modal.show();
		});
	};
	
	vm.go = function(){
		$koscomState.go("sampleList");
	};
	
	vm.href = function(){
		$koscomState.href("#/sampleList");
	};
	
	vm.goParam = function(){
		$koscomState.go("sampleDetail", {data: 1000, id: 10});
	};
	
    vm.goParamCert = function(){
        $koscomState.go("signKoreaCert", {signPlainText : "테스트 plain Text"} );
    };
	
	vm.fabEvent = function(){
		$koscomState.href("#/sampleForm");
	};
	
	
	var cipherData = "";
	vm.keypadOpen = function(){
		$koscomBridge.keypadOpen("보안입력하세여.", 20, 
				$koscomBridge.PAD_TYPE_ALPHA_U, $koscomBridge.ENCRYPT_RSA).then(function(res){
			cipherData = res.cipherData;
		}).catch(function(){
			console.log("error");
		});
	};
	
	vm.keypadOpenForCertPwd = function(){
		$koscomBridge.keypadOpenForCertPwd("보안입력하세여.", 20, 
				$koscomBridge.PAD_TYPE_ALPHA_U,
				"cn=김재훈-5101640,ou=HTS,ou=한국투자,ou=증권,o=SignKorea,c=KR").then(function(res){
			cipherData = res.cipherData;
		}).catch(function(){
			console.log("error");
		});
	};
	
	vm.keypadOpen2 = function(){
		$koscomBridge.keypadOpenForCertPwd("보안입력하세여.", 20, 
				$koscomBridge.PAD_TYPE_ALPHA_U,
				"cn=김재훈-5101640,ou=HTS,ou=한국투자,ou=증권,o=SignKorea,c=KR", 0, 0).then(function(res){
			
		}).catch(function(){
			console.log("error");
		});
	};
	
	
	vm.cipherToText = function(){
		$koscomBridge.cipherToText(cipherData).then(function(data){
			console.log(data);
		});
	};
	
	vm.getUserCertList = function(){
		$koscomBridge.getUserCertList().then(function(data){
			console.log(data);
		});
	};
	
	vm.checkPwd = function(){
		$koscomBridge.checkPwd("cn=김재훈-5101640,ou=HTS,ou=한국투자,ou=증권,o=SignKorea,c=KR",
				"qwer1234").then(function(){
			console.log("비밀번호 일치");
		}).catch(function(code){
			if(code === 3000){
				console.log("비밀번호 불일치");
			}
			if(code === 5000){
				console.log("인증서 없음");
			}
		});
	};
	
	vm.getSignedData = function(){
		$koscomBridge.getSignedData(2,
				"cn=김재훈-5101640,ou=HTS,ou=한국투자,ou=증권,o=SignKorea,c=KR",
				"qwer1234", "약관내용").then(function(data){
					console.log(data);
				}).catch(function(code){
					console.log(code);
				});
	};
	
	
	vm.simpleCertImport1 = function(){
		$koscomBridge.simpleCertImport1().then(function(message, num1, num2, num3){
			console.log(message, num1, num2, num3);
		}).catch(function(code){
			console.log(code);
		});
	};
	
	vm.simpleCertImport2 = function(){
		$koscomBridge.simpleCertImport2().then(function(message, certDn, certPolicy){
			console.log(message, certDn, certPolicy);
		}).catch(function(code){
			console.log(code);
		});
	};
	
	vm.openUrl = function(){
		$koscomBridge.openUrl("http://daum.net").then(function(){
			console.log("SUCCESS");
		});
	};
	
	
	vm.deviceInfo = function(){
        $koscomBridge.getDeviceInfo().then(function(res){
			vm.deviceInfoText = res;
		});
	};
	
	/* page event */
	$scope.$on('$ionicView.loaded', function(){
		console.log('sample page Loaded');
	});
	
	// 페이지 접근시(뒤로가기 허용) 호출
	$scope.$on('$ionicView.enter', function(){
		console.log('sample page enter');
		vm.signKoreaResultData = '';
        $koscomBridge.getAppData("signKoreaCertResultData").then(function(res){
            if(res.result == 1) {
                $koscomPopup.alert("공인인증 결과값 확인",angular.toJson(res))
            }
            vm.signKoreaResultData = res;

            $koscomBridge.setAppData("signKoreaCertResultData", {});
            
        }).catch(function(){
            console.log("공인인증서 결과값 확인 실패!!!!!");
        });
	});
	
	
	console.log($rootScope.isAppToApp);
	if($rootScope.isAppToApp){
		//app to app 일 경우
	}
	else{
		//아닐 경우
	}
	
	
	

	// 공지사항 팝업 호출 
	vm.sampleNoticePopup = function(){
        $koscomPopup.modalFromTemplateUrl("/resources/views/notice/noticePopup.html", {
            name: "noticeNoticePopup",
            controllerUrl: "/resources/controllers/notice/noticePopup.js",
        }).then(function(modal) {
            modal.show();
        });
	};

	// [s] 공인인증서 리스트 팝업 호출 
	vm.modelSingKoreaCertUrl = function(){
//	    $scope.reqSignPlainText = "전자서명 원문 데이터";
	    $scope.reqSignPlainText = "";
	    $koscomPopup.modalFromTemplateUrl("/resources/views/signKorea/signKoreaListPop.html", {
            name: "signKoreaSignKoreaListPop",
			controllerUrl: "/resources/controllers/signKorea/signKoreaListPop.js",
			scope: $scope // $scope.singKoreaCertCallBack 호출을 위해 추가
		}).then(function(modal) {
			modal.show();
		});
	};
	// 공인인증서 처리완료 후 콜백
	$scope.singKoreaCertCallBack = function(data){
		vm.signKoreaResultData = angular.toJson(data);
		// 처리내용 구현
	}
	// [e] 공인인증서 리스트 팝업 호출 
	
	// [s] 공인인증서 가져오기 팝업 호출 
    vm.modelSignKoreaImportUrl = function(){
        $koscomPopup.modalFromTemplateUrl("/resources/views/signKorea/signKoreaImportPop.html", {
            controllerUrl: "/resources/controllers/signKorea/signKoreaImportPop.js",
        }).then(function(modal) {
            modal.show();
        });
    };
    // [e] 공인인증서 가져오기 팝업 호출 
	
    
    vm.eversafeVerifySession = function(str){
        $koscomBridge.req("ITR_02_010",{deviceId : "deviceId",sessionId: "sessionId", sessionToken: "sessionToken"}).then(function(res){
            alert(angular.toJson(res));
        }).catch(function(e){
            console.log("error");
        });
    };
    
    vm.loading = function(){
    	$koscomLoading.show();
    	
    	window.setTimeout(function(){
    		$koscomLoading.hide();
    	}, 3000);
    };
    
    vm.tooltipsConfirmClick = function(){
    	alert(1111111);
    };
});