/**
 * Created by sunhaLee on 2017. 5. 24..
 */

angular.module("oppf2").register.controller("MemberCreate", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;

    /* 아이디 */
    vm.customerId = "";

    vm.test = function(){
        console.log(vm.customerId);
    }
    /* 회원 OP 등록 번호 */
    vm.customerRegNo = "";
    /* 비회원 여부 */
    vm.temporaryMemberYn = "N";


    /* 휴대폰 인증 시 파라미터 */
    vm.customerNameKor = $stateParams.customerNameKor;                 //이름
    vm.customerBirthDay = $stateParams.customerBirthDay;               //생년월일
    vm.customerPhone = $stateParams.customerPhone;                     //폰번호
    vm.customerSex = $stateParams.customerSex;                         //성별(공통코드)
    vm.customerVerifyCi = $stateParams.customerVerifyCi;               //CI(변수명 뭐로 보낼지 아직 모름)

    vm.passwordDisplayText = ""      //비밀번호 보안키 입력 *
    vm.passwordConfirmDisplayText = ""      //비밀번호 확인 보안키 입력 *

    vm.passwordError = true;                  //비밀번호 에러메세지 show hide 여부
    vm.passwordConfirmError = true;           //비밀번호 확인 에러메세지 show hide 여부
    vm.passwordErrorMsg = '';               //비밀번호 에러메세지
    vm.passwordConfirmErrorMsg = '';        //비밀번호 확인 에러메세지



    $scope.$on('$ionicView.enter', function(){
        /* 아이디 */
        vm.customerId = "";

        vm.test = function(){
            console.log(vm.customerId);
        }
        /* 회원 OP 등록 번호 */
        vm.customerRegNo = "";
        /* 비회원 여부 */
        vm.temporaryMemberYn = "N";


        /* 휴대폰 인증 시 파라미터 */
        vm.customerNameKor = $stateParams.customerNameKor;                 //이름
        vm.customerBirthDay = $stateParams.customerBirthDay;               //생년월일
        vm.customerPhone = $stateParams.customerPhone;                     //폰번호
        vm.customerSex = $stateParams.customerSex;                         //성별(공통코드)
        vm.customerVerifyCi = $stateParams.customerVerifyCi;               //CI(변수명 뭐로 보낼지 아직 모름)

        vm.passwordDisplayText = ""      //비밀번호 보안키 입력 *
        vm.passwordConfirmDisplayText = ""      //비밀번호 확인 보안키 입력 *

        vm.passwordError = true;                  //비밀번호 에러메세지 show hide 여부
        vm.passwordConfirmError = true;           //비밀번호 확인 에러메세지 show hide 여부
        vm.passwordErrorMsg = '';               //비밀번호 에러메세지
        vm.passwordConfirmErrorMsg = '';        //비밀번호 확인 에러메세지

        /**
         * 본인확인 후 정보에 비회원정보가 유효한지 확인 후 inputBox에 깔아주기
         * */

        $koscomBridge.req("MEM_01_020", {
            customerVerify : vm.customerVerifyCi
        }).then(function(res){
            if(res.memberRes != null){
                window.setTimeout(function() {
                    $koscomPopup.alert("", "회원 가입 시, 비회원 인증을 통해 이용하신 내역이 이전됩니다.");
                    /* 비회원이 일반 회원가입 할 시 regNo, nameKor, email 주소 넘겨주고 정보 update시켜야 함 */
                    vm.temporaryMemberYn = res.memberRes.temporaryMemberYn;
                    vm.customerRegNo = res.memberRes.customerRegNo;
                    var customerEmail = res.memberRes.customerEmail;         // 이메일 @ 로 나눠야함
                    var strArray = customerEmail.split('@');
                    vm.customerEmailId = strArray[0];                        // 비회원 이메일 아이디
                    vm.emailPath = strArray[1];                              // 비회원 이메일 주소
                    vm.customerEmailChange();
                },1000);
            }

        }).catch(function(e){
            console.log("error");
            window.setTimeout(function(){
                if(e.code == '9125'){   //이미존재함 -> login
                    window.setTimeout(function() {
                        $koscomPopup.alert("",e.message).then(function() {
                            $koscomState.back(-3);
                        });
                    },500);
                }else if(e.code == '9126' || e.code == '9150'){ //관리자문의
                    /* 탈퇴회원의 재가입은 3일 이후부터 가능합니다. */
                    window.setTimeout(function() {
                        $koscomPopup.alert("",e.message).then(function(){
                            $koscomState.go("main");
                        });
                    },500);
                } else if(e.code == '9144'){   //9144 : 탈퇴
                    window.setTimeout(function() {
                	$koscomPopup.alert("",e.message).then(function(){
                        $koscomState.go("main");
                    });
                },500);
                }

            }, 500);
        });

    });


    /* 약관동의 시 파라미터 */
    vm.customerTermsList = $stateParams.customerTermsList;             //약관동의 List

    /* password */
    vm.customerPassword = "";
    vm.passwordKeypadOpen = function(){
        $koscomBridge.keypadOpen("비밀번호를 입력하세요.", 20, $koscomBridge.PAD_TYPE_ALPHA_U, $koscomBridge.ENCRYPT_RSA).then(function(res){
        	vm.customerPassword = res.cipherData;
            vm.passwordDisplayText = res.displayText;

            vm.passwordValidation(vm.customerPassword, vm.customerPasswordConfirm);
        }).catch(function(){
            console.log("error");
        });
    };

    vm.customerPasswordConfirm = "";
    vm.passwordConfirmKeypadOpen = function(){
        $koscomBridge.keypadOpen("비밀번호 확인.", 20, $koscomBridge.PAD_TYPE_ALPHA_U, $koscomBridge.ENCRYPT_RSA).then(function(res){
            vm.customerPasswordConfirm = res.cipherData;
            vm.passwordConfirmDisplayText = res.displayText;

            vm.passwordValidation(vm.customerPassword, vm.customerPasswordConfirm);
        }).catch(function(){
            console.log("error");
        });
    }

    /**
     * 비밀번호 유효성 검사 API
     * */
    vm.passwordValidation = function(password, passwordConfirm){
        $koscomBridge.req("MBR_02_182",{
            customerPassword : password,
            customerPasswordConfirm : passwordConfirm
        }).then(function(res){
            vm.passwordError = res.passwordError;               //비밀번호 에러메세지 show hide 여부
            vm.passwordConfirmError = res.passwordConfirmError;        //비밀번호 확인 에러메세지 show hide 여부
            vm.passwordErrorMsg = res.passwordErrorMsg;               //비밀번호 에러메세지
            vm.passwordConfirmErrorMsg = res.passwordConfirmErrorMsg;        //비밀번호 확인 에러메세지
        }).catch(function(e){

        });
    }

    /* email */
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
    vm.customerEmailId = "";
    vm.emailPath = "";
    vm.customerEmail = "";
    vm.customerEmailReceiveYn = "N";

    /* opt model */
    vm.customerSendOtpId = "";
    //vm.customerOtpId = "";


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
    };


    /**
     * OTP ID 확인 API(OTP ID 수 맞춰주기 최대 7자리)
     * */
    /*
    vm.otpConfirm = function(){
        $koscomBridge.req("MBR_02_170", {
            customerSendOtpId : vm.customerSendOtpId
        }).then(function(res){
            $koscomBridge.alert(""," OTP 정상 : " + res);
        }).catch(function(e){
            console.log("error");
        })
    };
    */

    /**
     * 최종 submit
     * */
    vm.submit = function(){
    	$koscomBridge.getDeviceInfo().then(function(deviceInfo){

			vm.osType = deviceInfo.osType;
			vm.osVersion = deviceInfo.osVersion;
			vm.appVersion = deviceInfo.appVersion;
			vm.deviceToken = deviceInfo.deviceToken;

	        /* 일반회원가입 (insert or update) */
	        $koscomBridge.req("MBR_02_180",{
	            "customerRegNo": vm.customerRegNo,
	            "customerBirthDay": vm.customerBirthDay,
	            "customerEmail": vm.customerEmail,
	            "customerId": vm.customerId,
	            "customerNameKor": vm.customerNameKor,
	            "customerPassword": vm.customerPassword,
	            "customerPasswordFailCnt": 0,
	            "customerPhone": vm.customerPhone,
	            "customerSex": vm.customerSex,
	            "customerTermsList": vm.customerTermsList,
	            "customerVerifyCi": vm.customerVerifyCi,
                //"customerOtpId": vm.customerOtpId,
                // "customerOtpYn": vm.otpUseYn ? 'Y' : 'N',
	            "customerEmailReceiveYn": vm.customerEmailReceiveYn,
	            "temporaryMemberYn": vm.temporaryMemberYn,
				"deviceType": vm.osType,
				"deviceUniqueId": vm.deviceToken,
				"osVersion": vm.osVersion,
				"appVersion": vm.appVersion
	        }).then(function(res){
	            /* 회원가입 완료 화면으로 이동시 보낼 파라미터 */
	            $koscomState.go("memberSuccess", {
	                customerNameKor : vm.customerNameKor,        //이름
	                customerId : vm.customerId,                  //아이디
	                customerEmail : vm.customerEmail,            //이메일
                    customerRegNo : res.customerRegNo            //회원번호
	            })
	
	        }).catch(function(e){
	            console.log("error");
	            //비밀번호를 입력하지 않았을 때;
	            if(e.code == '9115'){
	                $koscomPopup.alert("",e.message);
	            }
	        });

		});
    }
});