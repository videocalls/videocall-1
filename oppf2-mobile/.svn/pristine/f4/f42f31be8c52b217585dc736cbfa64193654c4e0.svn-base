/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("SampleSlide", function($ionicSlideBoxDelegate, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
	var vm = this;
	vm.currentSlideIndex = 0;
	vm.slideChange = function(index){
		$ionicSlideBoxDelegate.slide(index);
		vm.currentSlideIndex = index;
	};
	
	vm.slideChange2 = function(){
		vm.currentSlideIndex = $ionicSlideBoxDelegate.currentIndex();
	}
});