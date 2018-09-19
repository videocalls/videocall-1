/**
 * Created by SH.Lee on 2017. 5. 01..
 */

angular.module("oppf2").register.controller("MemberTerms", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;

    vm.allTerms = false;
    vm.customerVerifyStep = false;

    $scope.$on('$ionicView.enter', function(){
	    vm.allTerms = false;
	    vm.customerVerifyStep = false;
        vm.allCheck();
    });

	/**
     * 약관동의 리스트 불러오기
     * */
	$koscomBridge.req("MBR_02_140",{
        customerTermsSystemKind : "G003002"
    }).then(function(res){
		vm.termsList = res.termsList;
		
		window.setTimeout(function(){
            vm.allCheck();
        },3000);
	}).catch(function(e){
		console.log("error");
	});


    /**
     * 모바일 팝업창에서 받은 response 정보들
     * */
    $scope.mobileCheckAuth = function(popRes){
        vm.customerBirthDay = popRes.niceReq.birthDay;
        if(popRes.niceReq.sex == "F"){              //여자
            vm.customerSex = "G012002";
        }else if(popRes.niceReq.sex == "M"){        //남자
            vm.customerSex = "G012001";
        }
        vm.customerNameKor = popRes.niceReq.sname;
        vm.customerPhone = popRes.niceReq.smobileNo;

        vm.customerVerifyCi = popRes.niceRes.responseCI;     //CI

        /**
         * [필요 파라미터]다음화면 이동(-> 회원정보 입력)
         * */
        $koscomState.go("memberCreate",{
            customerNameKor : vm.customerNameKor ,
            customerBirthDay : vm.customerBirthDay ,
            customerPhone : vm.customerPhone ,
            customerSex : vm.customerSex ,
            customerVerifyCi : vm.customerVerifyCi ,
            customerTermsList : vm.termsList
        })
    };


    /**
     * 전체 체크 Valid
    * */
    vm.allCheck = function(){
        angular.forEach(vm.termsList, function(data) {
            data.checked = vm.allTerms;
        });
        vm.checkValid();
    };


	/**
	 * 약관동의 모두 체크했는지 valid 이후 휴대폰인증 Popup
	 * */
	vm.termsListAgree = function(){

        /**
         * 확인후 휴대폰인증 화면 (popup)
         * */
        if(vm.customerVerifyStep){
            /**
             * 확인후 휴대폰인증 화면 (popup)
             * */
            // $koscomState.go("mobileCheckAuth", {customerTermsList: vm.termsList}); //팝업 아닐경우
            $koscomPopup.modalFromTemplateUrl("/resources/views/member/mobileCheckAuth.html",{
                name: "phoneAuth",
                controllerUrl: "/resources/controllers/member/mobileCheckAuth.js",
                scope: $scope
            }).then(function(modal) {
                modal.show();

            });
        }


	}

    /**
     *  본인인증 체크 valid
     * */
    vm.checkValid = function(){
        vm.customerVerifyStep = true;

        var allCheck = true;
        //약관동의 체크
        angular.forEach(vm.termsList, function(data) {
            if(!data.checked){
                vm.customerVerifyStep = false;
                allCheck = false;
                return;
            }
        });
        vm.allTerms = allCheck;
    };

	
});