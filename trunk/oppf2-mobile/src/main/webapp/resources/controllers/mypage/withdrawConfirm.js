/**
 * Created by pk choi on 2017. 6. 9
 */

angular.module("oppf2").register.controller("WithdrawConfirm", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;

    /**
     * 회원탈퇴
     */
    vm.withdrawConfirm = function(){
        $koscomBridge.req("MBR_02_320",{
            customerPassword : vm.customerPassword
        }).then(function(res){
            if(res.cfPasswordResult == "Y"){
                $koscomBridge.req("MBR_02_330",{
                    customerPassword : vm.customerPassword
                }).then(function(res){
    				$scope.$emit("logout", vm.loginRes);
                	$koscomPopup.alert("","회원 탈퇴가 완료되었습니다.").then(function(){
                		$koscomState.go("main");
                    });
                }).catch(function(e){
                    $koscomPopup.alert("",e.message);
                });                 
            }else{
                $koscomPopup.alert("", "비밀번호가 일치하지 않습니다.");
            }
        }).catch(function(e){
            $koscomPopup.alert("",e.message);
        });
    };
    
    
    /**
     * 보안 키패드 호츨
     */
    vm.customerPassword = "";
    vm.keypadOpen = function(){
        $koscomBridge.keypadOpen("비밀번호를 입력하세요.", 20, $koscomBridge.PAD_TYPE_ALPHA_U, $koscomBridge.ENCRYPT_RSA).then(function(res){
            vm.customerPassword = res.cipherData;
            vm.passwordDisplayText = res.displayText;
            // $koscomPopup.alert(JSON.stringify(res));
        }).catch(function(){
            console.log("error");
        });
    };


});