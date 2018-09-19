/**
 * Created by SH.Lee on 2017. 6. 2..
 */
angular.module("oppf2").register.controller("MemberPrepare", function($scope, $koscomState, $stateParams, $koscomBridge) {
    var vm = this;


    // 공인인증서 가져오기
    vm.goParamCert = function(){
        $koscomState.go("signKoreaCert", {signPlainText : "테스트 plain Text",pageType : "importPage", certClick : false} );
    };


});