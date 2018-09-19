/**
 * Created by sh.lee on 2017. 6. 8..
 */

angular.module("oppf2").register.controller("MemberModify", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;

    vm.detail = {};
    vm.customerEmail = '';

    vm.customerPhone = '';

    /* email */
    vm.emailPathSelect = "";

    vm.verifyProcessType = ''; // 인증서 등록 : insertVerify, otp삭제 : deleteOtp, otp등록 : insertOtp
    vm.otpUseYn = false;
    vm.dnHaveYn = false;
    vm.otpValueYn = false;
    vm.otpCheckedYn = false;
    vm.signKoreaResultData = '';
    vm.reUpdateOtp = false;
    vm.otpCheckBtnName = '확인';
    vm.saveBtn=true;

    vm.emailValidMsg = '';
    vm.bindingEmailPath = '';
    vm.bindingCustomerEmailReceiveYn = '';
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
    vm.customerEmailId = "";
    vm.emailPath = "";
    vm.customerEmail = "";
    vm.customerEmailReceiveYn = "N";


    /* opt model */
    vm.customerSendOtpId = "";
    vm.customerOtpId = "";

    /* 기본 정보 */
    vm.customerId = "";
    vm.customerBirthDay = "";
    vm.customerNameKor = "";
    vm.customerPhone = "";


    vm.customerOtpYn = '';




    /**
     * 이메일 입력시 Valid
     * */
    vm.emailChange = function(){
        /*
         * Email 유효성검사
         * */
        vm.emailPath = vm.emailPathSelect;
        vm.customerEmailChange();
    }

    vm.customerEmailChange = function(){
        vm.customerEmail = vm.customerEmailId + '@' + vm.emailPath;
        vm.emailValid();
    };

    /**
     * Email 정보 수정 시 EmailPath Valid
     * */
    vm.emailValid = function(){
        var reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

        if(vm.customerEmailId == null || vm.emailPath == null){
            vm.emailValidMsg = '이메일을 입력하세요.';
            vm.saveBtn = true;
        }else if(!reg.test(vm.customerEmail)){
            vm.emailValidMsg = '이메일 형식이 일치 하지 않습니다.';
            vm.saveBtn = true;
        }else{
            vm.emailValidMsg = '';
            vm.emailDoubleCheck();
        }
    }

    /**
     * Email Path 이전과 비교
     * */
    vm.emailDoubleCheck = function(){
        if(vm.bindingEmailPath != vm.customerEmail || vm.bindingCustomerEmailReceiveYn != vm.customerEmailReceiveYn){
            $koscomBridge.req("MBR_03_110",{
                customerEmail : vm.customerEmail
            }, null, false).then(function(res){
                vm.emailValidmsg = '';
                vm.saveBtnEnabled();
            }).catch(function(e){
                if(e.code == '9117'){
                    vm.emailValidMsg = '이미 존재하는 이메일 입니다.';
                } else if (e.code == '9118'){
                    vm.emailValidMsg = '이메일을 입력하세요.';
                }
            })
        }
    }

    /**
     * 저장버튼 활성화
     * */
    vm.saveBtnEnabled = function(){
        if(vm.emailValidMsg == ''){
            vm.saveBtn=false;
        }
    }

    /**
    * 공인인증서 인증 & 상세정보
    * */
    $scope.$on('$ionicView.enter', function(){
        //otp관련
        vm.customerSendOtpId = "";
        vm.otpValueYn = false;
        vm.reUpdateOtp = false;
        vm.emailValidMsg = '';
        /**
         * 공인인증서 화면을 다녀왔을 경우
         * */
        if(vm.verifyProcessType != ''){
            $koscomBridge.getAppData("signKoreaCertResultData").then(function(res){

                if(res.result == 1) {
                    /*
                     * 인증서 등록 : insertVerify, otp삭제 : deleteOtp, otp등록 : insertOtp
                     * */
                    // 인증서 등록
                    if(vm.verifyProcessType == 'insertVerify'){
                        vm.updateVerify(res);
                    }
                    // 인증서 삭제
                    if(vm.verifyProcessType == 'deleteOtp'){
                        if(res.customerDn == vm.customerVerifyDn){
                            vm.deleteOtp();
                            vm.otpCheckBtnName = "확인";
                        }else{
                            $koscomPopup.alert("인증서가 일치하지 않습니다.");
                        }
                    }
                    // OTP 등록
                    if(vm.verifyProcessType== 'insertOtp'){
                        if(res.customerDn == vm.customerVerifyDn){
                            vm.confirmCertDnCheck();
                        }else{
                            $koscomPopup.alert("인증서가 일치하지 않습니다.");
                        }
                    }
                }
                vm.verifyProcessType = '';
                vm.signKoreaResultData = res;
                $koscomBridge.setAppData("signKoreaCertResultData", {});

                window.setTimeout(function(){
                    vm.selectMemberDetail();
                },100);
            });
        }else{
            vm.selectMemberDetail();
        }



    });
    vm.selectMemberDetail = function(){
        $koscomBridge.req("MBR_02_300").then(function(res){
            vm.detail = res.memberRes;

            //이메일 주소
            vm.customerEmail = res.memberRes.customerEmail;
            var customerEmail = res.memberRes.customerEmail;         // 이메일 @ 로 나눠야함
            var strArray = customerEmail.split('@');
            vm.customerEmailId = strArray[0];                        // 이메일 아이디
            vm.emailPath = strArray[1];                              // 이메일 주소
            vm.customerEmailReceiveYn = res.memberRes.customerEmailReceiveYn;

            //이전이메일 정보 변경 여부 확인을 위한 binding
            vm.bindingEmailPath = res.memberRes.customerEmail;
            vm.bindingCustomerEmailReceiveYn = res.memberRes.customerEmailReceiveYn;

            //이메일 수신여부

            //이름, 아이디, 생년월일, 휴대폰 번호
            vm.customerId = res.memberRes.customerId;
            vm.customerNameKor = res.memberRes.customerNameKor;
            vm.customerBirthDay = res.memberRes.customerBirthDay;
            vm.customerPhone = res.memberRes.customerPhoneReplace;

            //CI
            vm.customerVerifyCi = res.memberRes.customerCi;
            vm.customerOtpYn = res.memberRes.customerOtpYn;

            vm.customerVerifyDn = res.memberRes.customerVerifyDn

            //DN 값이 NULL 일 경우 : 스마트 OTP 체크박스 on클릭시 DN 값 가지고있는지 true false.
            if(vm.customerVerifyDn == null){
                vm.dnHaveYn = false;
            }else{
                vm.dnHaveYn = true;
            }
            //vm.otpUseYn = res.memberRes.customerOtpId != null && res.memberRes.customerOtpId != ""? true : false;

            vm.otpUseYn = res.memberRes.customerOtpYn =="N" ? false : true;

            vm.customerOtpYn = res.memberRes.customerOtpYn;

        }).catch(function(e){
            console.log("error");
        });
    }

    /**
     * 인증서 등록
     * */
    vm.updateVerify = function(res){
        var updateUserCertInfoData = {};
        if(res.result == 1) {
            // 처리내용 구현
            updateUserCertInfoData.customerDn = res.customerDn
            // 공인인증서 정보 업데이트
            vm.updateUserCertInfo(updateUserCertInfoData);
        }

        $koscomBridge.setAppData("signKoreaCertResultData", {});
    }

    /**
     * 공인인증서 update
     * */
    vm.updateUserCertInfo = function(data){
        $koscomBridge.req("SKC_01_050", data).then(function(res){
            console.log(res);
            $koscomPopup.alert("", "인증서 정보가 등록되었습니다.").then(function(){
                vm.otpUseYn = true;
                vm.dnHaveYn = true;
                vm.otpChecked();
            });

        }).catch(function(e){
            console.log("error");
            $koscomPopup.alert("", "인증서 정보가 등록되지 않았습니다.");
        });
    };

    /**
     * OTP 이용 체크시
     * */
    vm.otpChecked = function(){
        /*
        *  OTP 체크가 true 일 때
        *  DN 값이 없는 경우
        *  : 공인인증서 등록 화면 이동
        *
        *  OTP 체크가 true 일때
        *  DN 값이 있는 경우
        *  : OTP 입력칸 SHOW
        *
        *  OTP 체크가 false 일때
        *  OTP 값이 있는 경우
        *  : 공인인증서 화면
        * */
        /*
         * 인증서 등록 : insertVerify, otp삭제 : deleteOtp, otp등록 : insertOtp
         * */
        if(vm.customerOtpYn == 'N' && vm.otpUseYn && !vm.dnHaveYn){
            vm.verifyProcessType = 'insertVerify';
            window.setTimeout( function() {
                var verifyDnUpdate = $koscomPopup.confirm("","공인인증서 정보 등록 후 설정 가능합니다. 인증서 등록 화면으로 이동 하시겠습니까?");
                verifyDnUpdate.then(function(res){
                    if(res){
                        //공인인증서 등록화면
                        $koscomState.go("signKoreaCert", {signPlainText : "테스트 plain Text"});
                    }
                });
            },100);
        }else if(vm.customerOtpYn == 'N' && vm.otpUseYn && vm.dnHaveYn){
            //OTP비밀번호 입력 text 칸 입력
            vm.otpValueYn = true;
            vm.otpCheckedYn = true;        //otp확인 완료되는경우에 다시 false
        }else if(vm.customerOtpYn == 'N' && !vm.otpUseYn && vm.dnHaveYn){
            vm.otpValueYn = false;
        }
        if(vm.customerOtpYn == 'Y' && vm.reUpdateOtp && vm.dnHaveYn){
            vm.otpValueYn = true;
            vm.otpCheckedYn = true;

            vm.otpCheckBtnName = '재등록';
        }
        if(vm.customerOtpYn == 'Y' && !vm.otpUseYn && vm.dnHaveYn){
            vm.verifyProcessType = 'deleteOtp';
            vm.otpValueYn = false;
            $koscomState.go("signKoreaCert");
        }else if(vm.customerOtpYn =='Y' && !vm.reUpdateOtp){
            vm.otpValueYn = false;
            vm.otpCheckedYn = false;
        }
    }


    /**
     * OTP ID 확인 API(OTP ID 수 맞춰주기 최대 7자리)
     * */
    vm.otpConfirm = function(){
        vm.verifyProcessType = 'insertOtp';
        $koscomBridge.req("MBR_02_170", {
            customerSendOtpId : vm.customerSendOtpId
        }).then(function(res){
            if(res.otpRes.result == 'success' && res.otpRes.message == 'ok'){
                vm.customerOtpId = res.otpRes.otpId;
                $koscomState.go("signKoreaCert");
            }else{
                $koscomPopup.alert("", "otp비밀번호가 유효하지 않습니다.");

            }
        }).catch(function(e){
            console.log("error");
            if(e.code('9119')){
                $koscomPopup.alert(e.message);
            }

        })
    }

    /**
     * OTP 입력 or 수정
     * */
    vm.confirmCertDnCheck = function(){
        $koscomBridge.req("MBR_02_171",{
            customerOtpId : vm.customerOtpId
        }).then(function(res){
        })
    }

    /**
     * OTP 삭제
     * */
    vm.deleteOtp = function(){
        $koscomBridge.req("MBR_02_172",{
        }).then(function(res){
            vm.otpCheckedYn = false;
            vm.customerOtpYn = 'N';
            vm.otpUseYn = 'N';
        })
    }


    /**
     * 휴대폰인증 화면 (popup)
     * */
    vm.phoneAuth = function(){
        $koscomPopup.modalFromTemplateUrl("/resources/views/member/mobileCheckAuth.html",{
            name: "phoneAuth",
            controllerUrl: "/resources/controllers/member/mobileCheckAuth.js",
            scope: $scope
        }).then(function(modal) {
            modal.show();
        });

    }

    /**
     * 모바일 팝업창에서 받은 response 정보들
     * */
    $scope.mobileCheckAuth = function(popRes){
        var changeNameKor = popRes.niceReq.sname;
        var changePhoneNumber = popRes.niceReq.smobileNo;
        var Ci = popRes.niceRes.responseCI;     //CI

        if(Ci == vm.customerVerifyCi){

            vm.customerNameKor = changeNameKor;
            vm.customerPhone = changePhoneNumber;

            $koscomBridge.req("MBR_02_310",{
                customerNameKor : changeNameKor,
                customerPhone : changePhoneNumber
            }).then(function(res){
                window.setTimeout(function() {
                    $koscomPopup.alert("", "본인인증이 완료되었습니다.").then(function () {
                        $koscomPopup.close();
                    })
                },1000);
            }).catch(function (e) {
                $koscomPopup.close();
            })

        }else{
            window.setTimeout(function() {
                $koscomPopup.alert("","회원정보와 일치하지 않습니다.");
                return ;
            },1000);

        }
    };


    /**
     * 정보 수정(email)
     * */
    vm.memberModify = function() {
        $koscomBridge.req("MBR_02_310", {
            customerEmail: vm.customerEmail,
            customerEmailReceiveYn : vm.customerEmailReceiveYn,
        }).then(function (res) {
            $koscomPopup.alert("", "정상적으로 저장되었습니다.").then(function(){
                window.setTimeout(function(){
                    $koscomState.back(-2);
                })
            });

        }).catch(function (e) {

        })

    }

});