/*
 * author : chungyeol.kim
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("AppTerms", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
	var vm = this;
	
	/*ars인증 false 처리
	 app.module.js에 $rootScope.isArs=false 처리
	 
	 ars 인증 사용시
	 app.module.js에 $rootScope.isArs=true 처리 -> 사용 가능
	 */
	vm.isArs=$rootScope.isArs;
	
	vm.appTermsInfo = {};
	vm.appId = $stateParams.appId;
	$scope.accountList = $stateParams.data.accountList;
	$scope.subCompanyName = "";
	$scope.subCompanyCodeId = "";
	vm.type = $stateParams.type;
	vm.beforeAppTermsInfo = {};
	vm.btnType = "";
	vm.arsUseYn = "";
	vm.arsCompanyLimit = "";

	$scope.$on('$ionicView.enter', function(){

		$koscomBridge.req("APP_01_040", {
			appId: $stateParams.appId,
			checkedPubCompanyList: $stateParams.checkedPubCompanyList,
			type: vm.type
		}).then(function(res){
			vm.appTermsInfo = res.appTermsInfo;
			if(res.appTermsInfo.customerSignData != null && res.appTermsInfo.customerSignData.length > 60) {
				vm.appTermsInfo.customerSignData = res.appTermsInfo.customerSignData.substring(1,60);
			}
			$scope.subCompanyName = vm.appTermsInfo.subCompanyName;
			$scope.subCompanyCodeId = vm.appTermsInfo.subCompanyCodeId;
			vm.beforeAppTermsInfo = res.beforeAppTermsInfo;
			vm.arsUseYn = res.arsUseYn;
			vm.arsCompanyLimit = res.arsCompanyLimit;
		}).catch(function(e){
		});

		vm.signKoreaResultData = '';
		$koscomBridge.getAppData("signKoreaCertResultData").then(function(res){
			if(res.result == 1) {
				$koscomPopup.alert("", "공인인증서 인증이 완료되었습니다.").then(function(){
					// 앱 신청 등록
					var termsHtml = $("#appTerms").html().replace(/\(/g, "&#40;");
					termsHtml = termsHtml.replace(/\(/g, "&#40;");
					termsHtml = termsHtml.replace(/\)/g, "&#41;");
					var appCreateReq = {
						"appId" : $stateParams.appId,
						"customerSignData" : res.signData,
						"customerSignDn" : res.customerDn,
						"reqHtml" : termsHtml,
						"termsAuthType" : "G032001",
						"subCompanyCodeId" : $scope.subCompanyCodeId,
						"accountList" : $stateParams.data.accountList,
						"termsCreatedYn" : "N"
					};
					var serviceId = "APP_01_090";
					if($stateParams.type === "modify") {
						serviceId = "APP_01_110";
					} else if($stateParams.type === "detail" || $stateParams.type === "reSync") {
						serviceId = "APP_01_150";
					}
					$koscomBridge.req(serviceId, appCreateReq).then(function(res) {
						$koscomBridge.setAppData("signKoreaCertResultData", {});
						if(serviceId === "APP_01_150") {
							$koscomState.go("main");
						} else {
							$koscomState.go("appCreateComplete", {appId: $stateParams.appId, type:$stateParams.type, finTechInfo:$stateParams.finTechInfo});
						}
					}).catch(function(e){
						if(e.code === "9502") {
							$koscomPopup.alert("", e.message);
						}
					});
				});
			}
			vm.signKoreaResultData = res;
			$koscomBridge.setAppData("signKoreaCertResultData", {});

		}).catch(function(){
		});
	});

/*	vm.init = function() {
		vm.appTermsInfo = {};
		$koscomBridge.req("APP_01_040", {appId: $stateParams.appId, checkedPubCompanyList: $stateParams.data.checkedPubCompanyList}).then(function(res){
			vm.appTermsInfo = res.appTermsInfo;
			$scope.subCompanyName = vm.appTermsInfo.subCompanyName;
		}).catch(function(e){
		});
	};*/

	vm.signKorea = function() {
		var termsHtml = $("#appTerms").html().replace(/\(/g, "&#40;");
		termsHtml = termsHtml.replace(/\(/g, "&#40;");
		termsHtml = termsHtml.replace(/\)/g, "&#41;");
		$koscomState.go("signKoreaCert", {signPlainText : termsHtml});
	};

	vm.cancel = function() {
		/*var params = {
			"type" : $stateParams.data.type,
			"appName" : $stateParams.data.appName,
			"companyName" : $stateParams.data.companyName
		};
		$koscomState.go("appAccountList", params);*/
		$koscomState.back(-1);
	};

	vm.arsPopUp = function() {
		$koscomPopup.modalFromTemplateUrl("/resources/views/apps/appArsPopUp.html", {
			name: "appArsPopUp",
			controllerUrl: "/resources/controllers/apps/appArsPopUp.js",
			scope:$scope
		}).then(function(modal) {
			modal.show();
		});
	};

	$scope.arsResultCallBack = function(appTermsArsRes) {
		// 앱 신청 등록
		var appCreateReq = {
			"appId" : $stateParams.appId,
			"termsAuthType" : "G032002",
			"subCompanyCodeId" : vm.appTermsInfo.subCompanyCodeId,
			"accountList" : $stateParams.data.accountList,
			"termsCreatedYn" : "N",
			"arsResultCd" : appTermsArsRes.arsResultCode,
			"arsResultMessage" : appTermsArsRes.arsResultMessage,
			"arsTrCd" : appTermsArsRes.arsTransactionCode,
			"arsRecordData" : appTermsArsRes.arsRecordData,
			"arsTxtNo" : appTermsArsRes.arsTxtNo
		};
		var serviceId = "APP_01_090";
		if($stateParams.type === "modify") {
			serviceId = "APP_01_110";
		} else if($stateParams.type === "detail" || $stateParams.type === "reSync") {
			serviceId = "APP_01_150";
		}
		$koscomBridge.req(serviceId, appCreateReq).then(function(res) {
			if(serviceId === "APP_01_150") {
				$koscomState.go("main");
			} else {
				$koscomState.go("appCreateComplete", {appId: $stateParams.appId, type:$stateParams.type, finTechInfo:$stateParams.finTechInfo});
			}
		}).catch(function(e){
		});
	};

	vm.appTermsDelete = function(appId) {
		var result = $koscomPopup.confirm("", "해당 동의서를 폐기하시면 연결된 핀테크앱도 함께 해지됩니다. 동의서를 폐기하시겠습니까?");
		result.then(function(res){
			if(res==true){
				$koscomBridge.req("APP_01_140", {appId:appId}).then(function(res){
					if($rootScope.isAppToApp) {
						//핀테크 APP 결과 리턴
						$koscomBridge.fintechError(4000, "FAIL").then(function () {});
						//로그아웃 호출
						$rootScope.appToAppLogout();
					} else {
						$scope.$emit('appRefresh', appId); //동의서 폐기시 이벤트
						$koscomState.go("main");
					}
				}).catch(function(e){
					console.log("error");
				});
			}
		});
	};

	vm.setBtnClickType = function(type) {
		vm.btnType = type;
	};

	vm.tooltipsConfirmClick = function(){
		if(vm.btnType === "sign") {
			vm.signKorea();
		} else if(vm.btnType === "ars") {
			vm.arsPopUp();
		}
	};

});