/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("SamplePush", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
	var vm = this;
	vm.message = "";
	vm.device = "1";
	
	vm.submit = function(){
		var deviceType = "";
		var deviceToken = "";
		if(vm.device === "1"){
			deviceType = "ios";
			deviceToken = "4132e9a2c3d7922530d4b5ef634fba143e8e11ebc47c66b75b575825e81fe5f0";
		}
		else{
			deviceType = "android";
			deviceToken = "ckRlB3QanKU:APA91bG47FQhO4CUUn_GrJfYaYRce0WITAO6UQwWgXjW3KE7O9wz-E_iu5eUYpMYFUC7gSjBpBsMmwF7BaYkiuSFwIkHzQ3rAvuUkr-_1t-PhWpIOCAixhKv8Vq7Z7Zd4x_LyWvGFbwO";
		}
		
		$koscomBridge.req("push", {
			message: vm.message, deviceToken: deviceToken, deviceType: deviceType
		}).then(function(res){
			debugger;
		}).catch(function(e){
			debugger;
		});
	};
});