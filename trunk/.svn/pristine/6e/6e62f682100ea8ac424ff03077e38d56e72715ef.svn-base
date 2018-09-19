/**
 * Created by sh.lee on 2017. 6. 7..
 */

angular.module("oppf2").register.controller("PasswordConfirm", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;

    /**
     * 보안 키패드 호츨
     */

    vm.customerPassword = "";
    vm.passwordDisplayText = "";

    $scope.$on('$ionicView.enter', function(){
        vm.customerPassword = "";
        vm.passwordDisplayText = "";
    });
    vm.keypadOpen = function(){
        $koscomBridge.keypadOpen("비밀번호를 입력하세요.", 20, $koscomBridge.PAD_TYPE_ALPHA_U, $koscomBridge.ENCRYPT_RSA).then(function(res){
            vm.customerPassword = res.cipherData;
            vm.passwordDisplayText = res.displayText;
            // $koscomPopup.alert(JSON.stringify(res));
        }).catch(function(){
            console.log("error");
        });
    };

    vm.passwordConfirmCheck = function(){
        $koscomBridge.req("MBR_02_320",{
            customerPassword : vm.customerPassword
        }).then(function(res){
            if(res.cfPasswordResult == "Y"){
                $koscomState.go("memberModify");
            }else{
                $koscomPopup.alert("","비밀번호를 확인해주세요");
            }
        }).catch(function(e){
            if(e.code == "9108" || e.code == "9142"){
                $koscomPopup.alert("", e.message);
            }
        });
    }

});