/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("AppCreateComplete", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup, $rootScope){
	var vm = this;
	vm.appDtl = {};
	vm.AppCompanyList = [];
	vm.AppAccountList = [];
	vm.type = "";
	vm.title = "앱 사용 신청";

	$scope.$on('$ionicView.enter', function(){
		$scope.$emit('appModify',$stateParams.appId); //앱 신청 정보 수정 시 이벤트
		$scope.$emit('appRefresh',$stateParams.appId); //앱 신청시 이벤트

		vm.appDtl = {};
		vm.AppCompanyList = [];
		vm.AppAccountList = [];
		vm.type = "";

		$koscomBridge.req("APP_01_020", {appId: $stateParams.appId}).then(function(res){
			vm.appDtl = res.appDtl;  //상세 조회
			vm.AppCompanyList = res.AppCompanyList;  //연계 서비스
			vm.AppAccountList = res.AppAccountList;  //로그인 되었을때 상세화면에 연결된 가상계좌 리스트를 뿌려준다.
		}).catch(function(e){
			console.log("error");
		});

		vm.type = $stateParams.type;

		if(vm.type == "modify"){
			vm.title = "신청 정보 수정";
		}
	});

	vm.goMain = function() {
		$koscomState.go("main");
	};

	vm.termsInfo = function(appId, type) {
		var params = {
			appId: appId,
			checkedPubCompanyList : "",
			type : type,
			data: {
				"accountList" : vm.AppAccountList,
				"appName" : vm.appName,
				"companyName" : vm.companyName
			}
		};
		$koscomState.go("appTerms", params, {reload: "appTerms"});
	};

	vm.goScope = function() {
		$koscomState.go("scope", {
			finTechInfo: $stateParams.finTechInfo
		});
	};

});