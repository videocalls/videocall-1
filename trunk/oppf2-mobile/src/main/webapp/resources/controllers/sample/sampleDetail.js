/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("SampleDetail", function($scope, $state, $stateParams, $koscomBridge){
	var vm = this;
	vm.detail = {};
	
	$koscomBridge.req("faqDetail", {faqSeq: $stateParams.id}).then(function(res){
		vm.detail = res.resultDetail;
	}).catch(function(e){
		console.log("error");
	});
});