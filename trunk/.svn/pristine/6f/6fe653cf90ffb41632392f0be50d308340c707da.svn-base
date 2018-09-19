/**
 * Created by SH.Lee on 2017. 6. 2..
 */
angular.module("oppf2").register.controller("NonMemberTerms", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup, $rootScope) {
    var vm = this;
    //핀테크 연동 APP 파라메터 정보
    vm.finTechInfo = $stateParams.finTechInfo;

    vm.customerEmail = "";
    vm.customerBirthDay = "";
    vm.customerSex = "";
    vm.customerSex = "";
    vm.customerNameKor = "";
    vm.customerPhone = "";
    vm.customerVerifyCi = "";     //CI
    vm.allTerms = false;
    vm.emailValidMsg = false;            // 이메일 Valid 메세지 여부
    vm.emailPathSelect = "";
    vm.customerEmailId = "";
    vm.emailPath = "";
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
     * 약관동의 리스트 불러오기
     * */
    $koscomBridge.req("MBR_02_140",{
        customerTermsSystemKind : "G003004"     // 모바일 비회원 약관
    }).then(function(res){
        vm.termsList = res.termsList;
        angular.forEach(vm.termsList, function(data) {
            data.checked  = false;
        });
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
         * 본인 확인 후 회원 정보가 있으면
         * */
        if(vm.customerVerifyCi != null) {
            $koscomBridge.req("MEM_01_020", {
                customerVerify: vm.customerVerifyCi
            }).then(function (res) {
                /*가입된 회원이 없는 경우 비회원가입*/
                vm.memberCreate();
            }).catch(function (e) {
                window.setTimeout(function () {
                    if (e.code == '9125' || e.code == '9126') {   //9125 : 이미존재함, 9126 : 미활성화
                        $koscomPopup.alert("", "이미 회원가입된 정보입니다. 확인을 클릭하시면 로그인 페이지로 이동합니다.").then(function () {
                            $koscomState.go("oauth", {
                                finTechInfo: vm.finTechInfo
                            });
                        });
                    }
                    if(e.code == '9144'){   //9144 : 탈퇴
                        $koscomPopup.alert("", e.message);
                    }
                    if(e.code == '9150'){   //9150 : 탈퇴
                        $koscomPopup.alert("", "회원탈퇴 후 3일간 서비스 이용이 불가능합니다.");
                    }
                }, 500);
            });
        }


    };


    /**
     * 회원 등록
     * */
    vm.memberCreate = function(){
        $koscomBridge.req("MEM_02_010",{
            customerEmail : vm.customerEmail,
            customerNameKor : vm.customerNameKor ,
            customerBirthDay : vm.customerBirthDay ,
            customerPhone : vm.customerPhone ,
            customerSex : vm.customerSex ,
            customerVerifyCi : vm.customerVerifyCi ,
            customerTermsList : vm.termsList,
            temporaryMemberYn : 'Y',                 //비회원 여부 Y,
            customerEmailReceiveYn : 'N'             //Email 수신동의여부 N
        }).then(function(res){
            //비회원 등록 or 비회원 로그인
            //로그인 처리 후 app 페이지이동
            $rootScope.isLogin = true;
            $koscomBridge.getAppData("customerId").then(function(data){
                $rootScope.customerId = data;
            });
            $koscomBridge.getAppData("customerNameKor").then(function(data){
                $rootScope.customerNameKor = data;
            });
            $koscomBridge.getAppData("temporaryMemberYn").then(function(data){
                $rootScope.temporaryMemberYn = data;
            });
            $koscomState.go("appRequestList",{
                finTechInfo: vm.finTechInfo
            })
        }).catch(function(e){
            //ci정보가 없을 경우
            if(e.code == "9112"){
                $koscomPopup.alert("",e.message);
            }
            //이미 가입된 회원일  -> 관리자 문의
            if(e.code == "9111"){
                $koscomPopup.alert("",e.message).then(function(){
                    $rootScope.isAppToApp = false;
                    //핀테크 APP 결과 리턴
                    $koscomBridge.fintechError(4400, "FAIL").then(function(){});
                    $koscomState.go("main");
                });
            }
        });

        // /**
        //  * [필요 파라미터]다음화면 이동(-> 회원정보 입력)
        //  * */
        // $koscomState.go("memberCreate",{
        //     customerNameKor : vm.customerNameKor ,
        //     customerBirthDay : vm.customerBirthDay ,
        //     customerPhone : vm.customerPhone ,
        //     customerSex : vm.customerSex ,
        //     customerVerifyCi : vm.customerVerifyCi ,
        //     customerTermsList : vm.termsList
        // })
    }


    /**
     * 이메일 입력시 Valid
     * */
    vm.emailChange = function(){
        /*
         * Email 유효성검사
         * */
        vm.emailPath = vm.emailPathSelect;
        //vm.customerEmail = vm.customerEmailId + '@' + vm.emailPath;
        vm.customerEmailChange();
    }

    vm.customerEmailChange = function(){
        // vm.checkValid();
        vm.customerEmail = vm.customerEmailId + '@' + vm.emailPath;
    };
    
    /**
     * 약관동의 모두 체크했는지 valid 이후 다음화면에 가져감
     * */
    vm.termsListAgree = function(){
        /**
         * 확인후 휴대폰인증 화면 (popup)
         * */
        $koscomPopup.modalFromTemplateUrl("/resources/views/member/mobileCheckAuth.html",{
            name: "phoneAuth",
            controllerUrl: "/resources/controllers/member/mobileCheckAuth.js",
            scope: $scope
        }).then(function(modal) {
            modal.show();

        });
    }

    /**
     * 모두 체크 valid
     * */
    vm.allCheck = function(){
        angular.forEach(vm.termsList, function(data) {
            data.checked = vm.allTerms;
        });
        // vm.checkValid();
    };

    /**
     *  단일 체크 valid
     * */
    vm.checkValid = function(){
        var allCheck = true;
        //약관동의 체크
        angular.forEach(vm.termsList, function(data) {
            if(!data.checked){
                vm.allTerms = false;
                allCheck = false;
                return;
            }
        });
        vm.allTerms = allCheck;
    };
});