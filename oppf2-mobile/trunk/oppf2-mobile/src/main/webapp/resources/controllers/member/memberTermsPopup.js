/**
 * Created by SH.Lee on 2017. 6. 14..
 */

angular.module("oppf2").register.controller("MemberTermsPopup", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;

    vm.detail = {};

    vm.allTerms = false;
    vm.customerVerifyStep = false;

    //password popup 에서 온 경우 $scope.termsList
    vm.termsList = $scope.$parent.vm.termsResult != null ? $scope.$parent.vm.termsResult : $scope.termsList;

    vm.companyTermsList = $scope.$parent.vm.companyTermsResult != null ? $scope.$parent.vm.companyTermsResult : $scope.companyTermsList;;

    /**
     * 약관동의 모두 체크했는지 valid 이후
     * 약관 Update 이후
     * main
     * */
    vm.termsListAgree = function(){
        $koscomBridge.req("MBR_02_190", {
            customerTermsList: vm.termsList,
            companyTermsList: vm.companyTermsList
        }).then(function (res) {
            $scope.$emit("login");
            $koscomState.go("main");
            window.setTimeout(function(){
                $koscomPopup.close("memberTermsPopup");
            });
        });

    }

    /**
     *  본인인증 체크 valid
     * */
    vm.checkValid = function(){
        vm.customerVerifyStep = true;

        var allCheck = true;
        //약관동의 체크
        if(vm.termsList != null){
            angular.forEach(vm.termsList, function(data) {
                if(!data.checked){
                    vm.customerVerifyStep = false;
                    allCheck = false;
                    return;
                }
            });
        }

        if(vm.companyTermsList != null){
            angular.forEach(vm.companyTermsList, function(data) {
                if(!data.checked){
                    vm.customerVerifyStep = false;
                    allCheck = false;
                    return;
                }
            });
        }

        vm.allTerms = allCheck;
    };


    /**
     * 로그아웃 닫기버튼
     * */
    vm.close = function(){
        var logoutConfirm = $koscomPopup.confirm("","로그아웃되어 서비스를 이용하실 수 없습니다.");
        logoutConfirm.then(function(res){
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
                
                $koscomPopup.close("memberTermsPopup");
            }
        })


    }


});