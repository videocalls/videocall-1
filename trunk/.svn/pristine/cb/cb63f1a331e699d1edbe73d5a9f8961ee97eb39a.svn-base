/**
 * Created by pk choi on 2017. 6. 9
 */
angular.module("oppf2").register.controller("WithdrawMember", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;


	vm.appList= [];
	vm.appListTotalCount=0;	
	
	vm.commonCodeList = [];
	vm.searchAppCategory="all";
	vm.searchKeyword=" ";
	vm.resultAccountList = [];
	vm.customerId = ""
		

		
	$scope.$on('$ionicView.enter', function(){
		vm.appList= [];
		vm.appListTotalCount=0;	
		
		vm.commonCodeList = [];
	
		$koscomBridge.req("APP_01_010", {type:"list", searchAppCategory:vm.searchAppCategory, searchKeyword:vm.searchKeyword}).then(function(res){
	    	vm.appListTotalCount = res.appListTotalCount;
	    	vm.appList = res.appList;
	    	
	    	vm.resultAccountList = [];
	    	
	    	$koscomBridge.req("ACC_01_010").then(function(res){
	    		
	    		vm.resultAccountList = res.resultAccountList;
	    		
	    	}).catch(function(e){
	    		console.log("error");
	    	});
		}).catch(function(e){
			console.log("error");
		});

				
	});
	
	$koscomBridge.getAppData("customerId").then(function (res){
		vm.cutomerId = res;
		//debugger;
	});
	
	vm.withDrawMember = function(){
			$koscomState.go("withdrawConfirm");
    };

	
});