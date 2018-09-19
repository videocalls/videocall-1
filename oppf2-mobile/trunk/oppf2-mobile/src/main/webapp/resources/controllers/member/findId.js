/**
 * Created by SH.Lee on 2017. 6. 2..
 */
angular.module("oppf2").register.controller("FindId", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup) {
    var vm = this;

    vm.customerNameKor = "";
    vm.customerEmmailId = "";
    vm.customerEmail = "";
    vm.emailPath = "";
    vm.emailPathSelect = "";

    /*
     * 이메일 SelectBox checked Event (직접입력일 때만 email주소 입력 가능하게, 아닌경우 readonly)
     * */
    vm.emailPathSelectList =  [
        {data:'', name:'직접입력'},
        {data:'naver.com', name:'네이버'},
        {data:'gmail.com', name:'구글'},
        {data:'hanmail.net', name:'다음'},
        {data:'nate.com', name:'네이트'}
    ];

    /**
     * 이메일 입력시
     * */
    vm.emailChange = function(){
        vm.emailPath = vm.emailPathSelect;
        vm.customerEmailChange();
    }

    vm.customerEmailChange = function(){
        vm.customerEmail = vm.customerEmailId + '@' + vm.emailPath;
    };

    /**
     * 아이디찾기 API
     * */
    vm.submit = function(){
        $koscomBridge.req("MBR_02_110", {
            customerEmail: vm.customerEmail,
            customerNameKor: vm.customerNameKor
        }).then(function(res){
            $koscomState.go("resultFindId", {
                customerId: res.setHiddenId
            })
        }).catch(function(e){
            $koscomPopup.alert("",e.message);
        })
    };

});