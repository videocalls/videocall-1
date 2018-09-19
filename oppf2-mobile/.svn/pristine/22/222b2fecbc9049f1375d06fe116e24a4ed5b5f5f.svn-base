/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("SampleOauth", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
	var vm = this;
	vm.message = "";
	vm.device = "1";
	vm.finTechInfo = {
		"clientId": "l7xx296eb0af278542b38a6ebd5073749ce0",
		"callbackUrl": "https://10.10.10.103:8440/spt/myp/intAcnt/callback",
		"scope": "account",
		"responseType": "code",
		"state": "12345"
	}
	
	vm.submit = function(){
		$rootScope.isAppToApp = true;
		$koscomState.go("oauth", {
			finTechInfo: vm.finTechInfo
		});
	};
	
	$scope.$on('$ionicView.enter', function(){
		$rootScope.isAppToApp = false;
	});
});