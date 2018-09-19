/*
 * author : chungyeol.kim
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("AppArsPopUp", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
	var vm = this;
	vm.step = "1";
	vm.phoneNumber = "";
	vm.appTermsArsRes = {};
	vm.arsSuccessYn = "";

	$koscomBridge.req("APP_01_060").then(function(res){
		vm.phoneNumber = res.customerPhoneNumber;
    }).catch(function(e){
        console.log("error");
    });
	
	vm.close = function(){
		$koscomPopup.close("appArsPopUp");
	};

	vm.requestArs = function() {
		vm.step = "2";
		var appTermsArsReq = {
			"accountList" : $scope.accountList,
			"subCompanyName" : $scope.subCompanyName
		};
		
		$koscomBridge.longTimeoutReq("APP_01_070", appTermsArsReq, null, false).then(function(res){
			vm.appTermsArsRes = res.arsTermsResultInfo;
			vm.step = "3";
			if(vm.appTermsArsRes.arsResultCode === "0000") {
				vm.arsSuccessYn = "Y";
			} else {
				vm.arsSuccessYn = "N";
			}
		}).catch(function(e){
			if(e.code === "9501") {
				$koscomPopup.alert("", e.message).then(function(){
					vm.step = "1";
					vm.arsSuccessYn = "";
				});
			}
			console.log("error");
		});
	};

	vm.arsOk = function() {
		$koscomPopup.close("appArsPopUp");
		$scope.arsResultCallBack(vm.appTermsArsRes);
	};

	vm.reTry = function() {
		vm.step = "1";
		vm.arsSuccessYn = "";
	};

	vm.customTag = function(step) {
		var tag = "stage_0" + step;
		if(vm.step === step) {
			tag += " active";
		}
		return tag;
	};
});

