angular.module("koscomAngular").register.controller("Sample1", function($scope, $ionicPopup){
	var vm = this;
	vm.alert = function(){
		$ionicPopup.alert({
			title: "타이틀",
			template: "내용입니다.. 내용입니다.. 내용입니다.. 내용입니다.. 내용입니다.."
		}).show();
	};
	
	vm.confirm = function(){
		$ionicPopup.confirm({
			title: "타이틀",
			template: "내용입니다.. 내용입니다.. 내용입니다.. 내용입니다.. 내용입니다.."
		}).show();
	};
});