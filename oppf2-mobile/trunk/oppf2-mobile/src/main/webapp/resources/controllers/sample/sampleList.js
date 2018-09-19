/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("SampleList", function($scope, $koscomState, $stateParams, $koscomBridge){
	var vm = this;

	vm.items = [];
	vm.noMoreItemsAvailable = false; //true 면 다음 넥스트 결과가 없음.
	vm.loadMore = function(){
		$koscomBridge.req("sampleFaq").then(function(res){
			vm.items = vm.items.concat(res.faqResultList);
			
			//임의로 true로 변경함.
			if (vm.items.length > 50 ) {
				vm.noMoreItemsAvailable = true;
			}
			
			$scope.$broadcast('scroll.infiniteScrollComplete');
		}).catch(function(e){
			console.log("error");
		});
	};
	
	vm.test = function(seq){
		$koscomState.go("sampleDetail", {id: seq});
	};
});