/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("AccountList", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;
    vm.resultAccountList = [];
    vm.compCnt = 0;
    vm.vtAccountCnt = 0;

    $scope.$on('$ionicView.enter', function(){
		vm.resultAccountList = [];
    	$koscomBridge.req("ACC_01_010").then(function(res){
    		
    		vm.resultAccountList = res.resultAccountList;
    		vm.compCnt = res.compCnt;
    		vm.vtAccountCnt = res.vtAccountCnt;
    	}).catch(function(e){
    		
    		console.log("error");
    		
    	});
    	
    });
    
	vm.AccInfoAdd = function(){
        $koscomState.go("accountAddStep01Pop"); //팝업 아닐경우
	}

	vm.accoutDeatil = function(customerVtaccountNo, companyProfileRegNo){
		$koscomState.go("accounts",{customerVtaccountNo: customerVtaccountNo, companyProfileRegNo: companyProfileRegNo});
	}
	
});