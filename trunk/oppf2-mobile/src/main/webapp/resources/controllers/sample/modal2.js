/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("Modal2", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
	var vm = this;
	vm.close = function(){
		$koscomPopup.close("bb");
	};
});