/**
 * Created by SH.Lee on 2017. 6. 2..
 */
angular.module("oppf2").register.controller("FindPassword", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup) {
    var vm = this;

    vm.idFocus = false;
    vm.customerId = "";
    vm.customerNameKor = "";
    
    /**
     * 공인인증서
     * */
    vm.findPasswordCert = function(){
        //ID 로 등록된 공인인증서 여부 체크
        $koscomBridge.req("MBR_02_125",{
            customerId: vm.customerId
        }).then(function(res){
            //공인인증서등록이 유효하면 인증서페이지로 이동
            vm.customerNameKor = res.memberRes.customerNameKor;
            $koscomState.go("signKoreaCert");
        }).catch(function(e){
            //console.log(e);
            var message = '';
            if(e.code == '9128'){
                //인증서 등록이 안된 회원
                message = '등록된 인증서 정보가 없습니다. PC버전 비밀번호 찾기를 이용하시기 바랍니다.';
                $koscomPopup.alert("", message).then(function(){});
            }else if(e.code == '9101'){
                //등록된 아이디가 없는경우
            	message = '사용할 수 없는 아이디입니다.';
                $koscomPopup.alert("", message).then(function(){});
            }
        });
        //
    };


    $scope.$on('$ionicView.enter', function(){
        vm.signKoreaResultData = '';

        $koscomBridge.getAppData("signKoreaCertResultData").then(function(res){
            if(res.result == 1) {
                // 인증서 정상 입력
                if(res.customerDn.split(',')[0].indexOf(vm.customerNameKor) < 0){
                    $koscomPopup.alert("", "등록된 이름과 공인인증서의 이름이 서로 일치하지 않습니다. 다시 확인해 주세요.");
                }else{
                    vm.confirmCert(res);
                }

            }
            vm.signKoreaResultData = res;
            $koscomBridge.setAppData("signKoreaCertResultData", {});

        }).catch(function(){
            if($stateParams.customerId != null) {
                vm.customerId = $stateParams.customerId;
            }
            console.log("공인인증서 결과값 확인 실패!!!!!");
        });
    });

    /**
     * 인증서와 아이디값으로 조회
     * */
    vm.confirmCert = function(resultCert){
        $koscomBridge.req("MBR_02_120",{
            customerId: vm.customerId,
            customerVerifyDn: resultCert.customerDn
        }).then(function(res){
            //비밀번호 수정할 때 필수값(회원 OP 번호) 갖고 다음 화면으로 보냄 -> 비밀번호 재설정 화면
            $koscomState.go("modifyFindPassword",{
               customerRegNo : res.loginRes.customerRegNo
            });

        }).catch(function(e){
            console.log("공인인증서 결과값 확인 실패");
            //없는
            if(e.code == "9118"){
                $koscomPopup.alert("", "공인인증서 값이 올바르지 않습니다.");
            }
        });
    }
});