/**
 * Created by SH.Lee on 2017. 6. 2..
 */
angular.module("oppf2").register.controller("NonMemberPrepare", function($scope, $koscomState, $stateParams) {
    var vm = this;
    //핀테크 연동 APP 파라메터 정보
    vm.finTechInfo = $stateParams.finTechInfo;
    /**
     * 비회원 약관
     * */
    vm.guestTerms = function(){
        $koscomState.go("nonMemberTerms", {
            finTechInfo: vm.finTechInfo
        });
    };

    // 공인인증서 가져오기
    vm.goParamCert = function(){
        $koscomState.go("signKoreaCert", {signPlainText : "테스트 plain Text",pageType : "importPage", certClick : false} );
    };
    
});