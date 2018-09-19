/**
 * Created by Yoonjin.Han on 2017-05-22.
 */

angular.module("oppf2").register.controller("Intro", function($scope, $koscomBridge, $koscomPopup){
	var vm = this;
	vm.close = function(){
		$koscomBridge.setLocalStorage("introPopup", "true");
		$koscomPopup.close("introPopup");
		
		$scope.noticePopupEvent();
	};
});