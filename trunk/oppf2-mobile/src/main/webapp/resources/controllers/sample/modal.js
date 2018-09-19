/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("Modal", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
	var vm = this;
	vm.close = function(){
		$koscomPopup.close("aa");
	};
	
	vm.open = function(){
		$koscomPopup.modalFromTemplateUrl("/resources/views/sample/modal2.html", {
			name: "bb",
			controllerUrl: "/resources/controllers/sample/modal2.js"
		}).then(function(modal) {
			modal.show();
		});
	};
});