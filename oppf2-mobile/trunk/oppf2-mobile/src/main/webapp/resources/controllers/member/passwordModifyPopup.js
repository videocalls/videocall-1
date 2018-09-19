/**
 * Created by sh.lee on 2017. 6. 9..
 */
angular.module("oppf2").register.controller("PasswordModifyPopup", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup) {
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

    vm.loginRes = $scope.$parent.vm.loginRes;

    vm.termsList = $scope.$parent.vm.termsResult;       //로그인창에서 약관
    vm.companyTermsList = $scope.$parent.vm.companyTermsResult;     //로그인창에서 가져온 기업약관

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
                if(vm.termsList != null){
                    $scope.termsList = vm.termsList;
                    $scope.companyTermsList = vm.companyTermsList;
                    $koscomPopup.modalFromTemplateUrl("/resources/views/member/memberTermsPopup.html",{
                        name: "memberTermsPopup",
                        controllerUrl: "/resources/controllers/member/memberTermsPopup.js",
                        scope: $scope
                    }).then(function(modal) {
                        modal.show();
                    });
                }else{
                    $scope.$emit("login");
                    $koscomState.go("main");
                }
                window.setTimeout(function(){
                    $koscomPopup.close("passwordModify");
                })
            });
        });
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

    /**
     * 팝업창 닫기(다음에 변경)
     * */
    vm.closePopup = function(){
        window.setTimeout(function() {
            if(vm.termsList != null){
                $scope.termsList = vm.termsList;
                $scope.companyTermsList = vm.companyTermsList;
                $koscomPopup.modalFromTemplateUrl("/resources/views/member/memberTermsPopup.html",{
                    name: "memberTermsPopup",
                    controllerUrl: "/resources/controllers/member/memberTermsPopup.js",
                    scope: $scope
                }).then(function(modal) {
                    modal.show();
                });
            }else{
                $scope.$emit("login");
                $koscomState.go("main");
            }
        },100);
        $koscomPopup.close("passwordModify");
    }

    /**
     * 로그아웃 닫기버튼
     * */
    vm.close = function(){
        $koscomPopup.confirm("","로그아웃되어 서비스를 이용하실 수 없습니다.").then(function(res){
            if(res){
                //로그아웃 API 호출(로그인 쿠키 삭제)
                $koscomBridge.req("MBR_02_130");
                //아이디 초기화
                $koscomBridge.setAppData("customerId", null);
                //이름 초기화
                $koscomBridge.setAppData("customerNameKor", null);
                //회원여부 초기화
                $koscomBridge.setAppData("temporaryMemberYn", null);
                //통합계좌조회여부 초기화
                $koscomBridge.setAppData("integrationAccountYn", null);
                $koscomPopup.close("passwordModify");
            }
        })
    }
});