/**
 * Created by sh.lee on 2017. 6. 5..
 */
angular.module("oppf2").register.controller("ModifyFindPassword", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup) {
    var vm = this;

    vm.customerRegNo = $stateParams.customerRegNo;
    vm.customerPassword = '';
    vm.customerPasswordConfirm = '';

    vm.customerPassword = '';
    vm.passwordDisplayText = '';

    vm.customerPasswordConfirm = '';
    vm.passwordConfirmDisplayText = '';

    vm.passwordButtonDisabled = true;         //버튼 비활성화

    vm.passwordError = false;                  //비밀번호 에러메세지 show hide 여부
    vm.passwordConfirmError = false;           //비밀번호 확인 에러메세지 show hide 여부
    vm.passwordErrorMsg = '';               //비밀번호 에러메세지
    vm.passwordConfirmErrorMsg = '';        //비밀번호 확인 에러메세지


    $scope.$on('$ionicView.enter', function(){
        vm.customerRegNo = $stateParams.customerRegNo;
        vm.customerPassword = '';
        vm.customerPasswordConfirm = '';

        vm.customerPassword = '';
        vm.passwordDisplayText = '';

        vm.customerPasswordConfirm = '';
        vm.passwordConfirmDisplayText = '';

        vm.passwordButtonDisabled = true;         //버튼 비활성화

        vm.passwordError = false;                  //비밀번호 에러메세지 show hide 여부
        vm.passwordConfirmError = false;           //비밀번호 확인 에러메세지 show hide 여부
        vm.passwordErrorMsg = '';               //비밀번호 에러메세지
        vm.passwordConfirmErrorMsg = '';        //비밀번호 확인 에러메세지
    });


    vm.passwordKeypadOpen = function(){
        $koscomBridge.keypadOpen("비밀번호를 입력하세요.", 20, $koscomBridge.PAD_TYPE_ALPHA_U, $koscomBridge.ENCRYPT_RSA).then(function(res){
            vm.customerPassword = res.cipherData;
            vm.passwordDisplayText = res.displayText;
            vm.passwordValidation(vm.customerPassword, vm.customerPasswordConfirm);
        }).catch(function(){
            console.log("error");
        });

    }

    vm.passwordConfirmKeypadOpen = function(){
        $koscomBridge.keypadOpen("비밀번호 확인.", 20, $koscomBridge.PAD_TYPE_ALPHA_U, $koscomBridge.ENCRYPT_RSA).then(function(res){
            vm.customerPasswordConfirm = res.cipherData;
            vm.passwordConfirmDisplayText = res.displayText;
            vm.passwordValidation(vm.customerPassword, vm.customerPasswordConfirm);
        }).catch(function(){
            console.log("error");
        });
    }

    /**
     * 비밀번호 유효성 검사 API
     * */
    vm.passwordValidation = function(password, passwordConfirm){
        $koscomBridge.req("MBR_02_182",{
            customerPassword : password,
            customerPasswordConfirm : passwordConfirm
        }).then(function(res){
            vm.passwordError = res.passwordError;               //비밀번호 에러메세지 show hide 여부
            vm.passwordConfirmError = res.passwordConfirmError;        //비밀번호 확인 에러메세지 show hide 여부
            vm.passwordErrorMsg = res.passwordErrorMsg;               //비밀번호 에러메세지
            vm.passwordConfirmErrorMsg = res.passwordConfirmErrorMsg;        //비밀번호 확인 에러메세지
            vm.buttonDisabled();
        }).catch(function(e){
            if(e.code == '9115'){
                $koscomPopup.alert("", e.message);
            }
        });
    }

    /**
     * 버튼 활성화
     * */
    vm.buttonDisabled = function(){
        if(!vm.passwordError && !vm.passwordConfirmError ){
            vm.passwordButtonDisabled = false;       //버튼 활성화
        }else{
            vm.passwordButtonDisabled = true;
        }
    }

    /**
     * 확인버튼 클릭 -> 비밀번호 수정
     * */
    vm.submit = function(){
        $koscomBridge.req("MBR_02_121",{
            customerRegNo : vm.customerRegNo,
            customerPassword : vm.customerPassword
        }).then(function(res){
            //로그인화면으로
            $koscomPopup.alert("","비밀번호 변경이 완료되었습니다.").then(function(){
                $koscomState.go("login");
            });

        }).catch(function(e){
            $koscomPopup.alert("", e.message);
        });
    }
});