/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("PasswordChange", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;
    vm.customerPassword = "";               //현재 비밀번호
    vm.newPassword = "";                    //새 비밀번호
    vm.customerPasswordConfirm = "";        //새 비밀번호 확인

    vm.passwordDisplayText = "";            //현재 비밀번호 암호화 Display
    vm.newPasswordDisplayText = "";         //새 비밀번호 암호화 Display
    vm.passwordConfirmDisplayText = "";     //새 비밀번호 확인 암호화 Display

    vm.passwordButtonDisabled = true;       //버튼 비활성화

    vm.customerPasswordCheckMsg = "";       //현재 비밀번호 에러 메세지
	vm.customerPasswordConfirmMsg = "";     //비밀번호 확인 에러 메세지
	vm.newPasswordMsg = "";                 //새비밀번호 에러 메세지

    //하단 버튼

    $scope.$on('$ionicView.enter', function(){
        vm.customerPassword = "";               //현재 비밀번호
        vm.newPassword = "";                    //새 비밀번호
        vm.customerPasswordConfirm = "";        //새 비밀번호 확인

        vm.passwordDisplayText = "";            //현재 비밀번호 암호화 Display
        vm.newPasswordDisplayText = "";         //새 비밀번호 암호화 Display
        vm.passwordConfirmDisplayText = "";     //새 비밀번호 확인 암호화 Display

        vm.passwordButtonDisabled = true;       //버튼 비활성화

        vm.customerPasswordCheckMsg = "";       //현재 비밀번호 에러 메세지
        vm.customerPasswordConfirmMsg = "";     //비밀번호 확인 에러 메세지
        vm.newPasswordMsg = "";                 //새비밀번호 에러 메세지
    });
    

	/**
	 * 현재 비밀번호
	 */
	vm.keypadOpenCust = function(){
		$koscomBridge.keypadOpen("비밀번호를 입력하세요.", 20, $koscomBridge.PAD_TYPE_ALPHA_U, $koscomBridge.ENCRYPT_RSA).then(function(res){
			vm.customerPassword = res.cipherData;
			vm.passwordDisplayText = res.displayText;

			vm.customerpasswordCheck();
		}).catch(function(){
			console.log("error");
		});
	};
	
	/**
	 * 새 비밀번호
	 */
	vm.keypadOpenNew = function(){
		$koscomBridge.keypadOpen("새 비밀번호를 입력하세요.", 20, $koscomBridge.PAD_TYPE_ALPHA_U, $koscomBridge.ENCRYPT_RSA).then(function(res){
			vm.newPassword = res.cipherData;
			vm.newPasswordDisplayText = res.displayText;

            vm.passwordChangeCheck();

		}).catch(function(){
			console.log("error");
		});
	};
	

	/**
	 * 새 비밀번호 확인
	 */
	vm.keypadOpenCon = function(){
		$koscomBridge.keypadOpen("새 비밀번호 확인을 입력하세요.", 20, $koscomBridge.PAD_TYPE_ALPHA_U, $koscomBridge.ENCRYPT_RSA).then(function(res){
			vm.customerPasswordConfirm = res.cipherData;
			vm.passwordConfirmDisplayText = res.displayText;


            vm.passwordChangeCheck();
		}).catch(function(){
			console.log("error");
		});
	};


	/**
     * 현재 비밀번호 확인
     * */
    vm.customerpasswordCheck = function(){


        //비밀번호 확인
        $koscomBridge.req("MBR_02_320",{
            customerPassword: vm.customerPassword
        }).then(function(res){
            if(res.cfPasswordResult == "Y"){
                vm.customerPasswordCheckMsg = "";
            }
            vm.buttonDisabled();
        }).catch(function(e){
            if(e.code == "9108"){
                vm.customerPasswordCheckMsg = "비밀번호를 다시 확인해주세요.";
            }
        });
    };

    /**
     * 새 비밀번호 Valid
     * */
    vm.passwordChangeCheck = function(){
        $koscomBridge.req("MBR_02_340",{
            "customerPassword" : vm.customerPassword,
            "newPassword" : vm.newPassword,
            "customerPasswordConfirm" : vm.customerPasswordConfirm
        }).then(function(res){
            // 새 비밀번호
            if(res.newPassword == "200") {
                vm.newPasswordMsg = '';
            } else {
            // } else if(res.newPassword == "9141") {
                vm.newPasswordMsg = res.newPassword;
            }
            // 새 비밀번호 확인
            if(res.newPasswordConfirm == "200"){
                vm.customerPasswordConfirmMsg = '';
            } else {
                vm.customerPasswordConfirmMsg = res.newPasswordConfirm;
            }

            vm.buttonDisabled();

        }).catch(function(e){
        });
    }

    
    /**
     * 비밀번호 저장
     * */
    vm.passwordChange = function(){
        $koscomBridge.req("MBR_02_310", {
            customerPassword: vm.newPassword
        }).then(function (res) {
            $koscomPopup.alert("","비밀번호 변경이 완료되었습니다.").then(function(){
                window.setTimeout(function(){
                    $koscomState.back(-1);
                })
            });
        })
    }
    
    /**
     * 버튼 활성화
     * */
    vm.buttonDisabled = function(){
        if(    vm.customerPasswordCheckMsg == ""
            && vm.customerPasswordConfirmMsg == ""
            && vm.newPasswordMsg == ""
            && vm.customerPassword != ""
            && vm.newPassword != ""
            && vm.customerPasswordConfirm != ""
        ){
            vm.passwordButtonDisabled = false;       //버튼 활성화
        }else{
            vm.passwordButtonDisabled = true;
        }
    }
	
	
});