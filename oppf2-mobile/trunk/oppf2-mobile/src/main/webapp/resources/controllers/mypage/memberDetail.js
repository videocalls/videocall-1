/**
 * Created by sunhaLee on 2017. 5. 01..
 */

angular.module("oppf2").register.controller("MemberDetail", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;

	vm.detail = {};

	// app data 용 변수 선언
	vm.customerNameKor = "";
	vm.customerEmailReceiveYn = "";
	vm.customerPhone = "";
	vm.customerBirthDay = "";
	vm.customerId = "";
	

    $scope.$on('$ionicView.enter', function(){
		$koscomBridge.req("MBR_02_300" ).then(function(res){
			vm.detail = res.memberRes;
			vm.customerEmail = res.memberRes.customerEmail;
	
			vm.customerId = res.memberRes.customerId;
			vm.customerNameKor = res.memberRes.customerNameKor;
			vm.customerBirthDay = res.memberRes.customerBirthDay;
			vm.customerPhone = res.memberRes.customerPhone;
			vm.customerEmailReceiveYn = res.memberRes.customerEmailReceiveYn;

			vm.customerOtpYn = res.memberRes.customerOtpYn;
			
			//vm.otpUseYn = res.memberRes.customerOtpId != null && res.memberRes.customerOtpId != ""? true : false;
			
			vm.otpUseYn = res.memberRes.customerOtpYn =="N" ? false : true;
			
			vm.customerOtpId = res.memberRes.customerOtpId;
		}).catch(function(e){
			console.log("error");
		});

    });
	
	vm.withDraw = function(){
		$koscomState.go("withdrawMember");
    };
	
});