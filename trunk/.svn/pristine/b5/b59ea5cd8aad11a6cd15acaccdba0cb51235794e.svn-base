/**
 * Created by unionman on 2017-05-25.
 */

angular.module("oppf2").register.controller("TermsService", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;

    vm.servicesComponyTermsCodeList = [];
    vm.selectedTermsCodeData = {};
    vm.TermsContentsText = "";
    
    /* page event */
    $scope.$on('$ionicView.loaded', function(){
        console.log('termsService page Loaded');
        // 
        vm.selectServiceTermsCodeList();//서비스 이용약관 등록정보 코드 조회
    });
    
    // 페이지 접근시(뒤로가기 허용) 호출
    $scope.$on('$ionicView.enter', function(){
        console.log('termsService page enter');
    });

    //서비스 이용약관 등록정보 코드 조회
    vm.selectServiceTermsCodeList = function() {
        $koscomBridge.req("MBR_02_280").then(function(res){
            vm.servicesComponyTermsCodeList = res.termsComponyList; 
            vm.selectedTermsCodeData = vm.servicesComponyTermsCodeList[0];
            vm.selectBoxChange();
        }).catch(function(e){
            console.log("vm.selectServiceTermsCodeList error");
        });
    };
    
    //서비스 이용약관 내용 조회
    vm.selectServiceTermsContents = function() {
        $koscomBridge.req("MBR_02_285", vm.selectedTermsCodeData).then(function(res){
            vm.TermsContentsText = res.termsContents.termsContents;
        }).catch(function(e){
            console.log("vm.selectServiceTermsContents error");
        });
    };
    
    // change select box
    vm.selectBoxChange = function(){
        vm.selectServiceTermsContents();
    };
    
    
});