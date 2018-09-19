/**
 * Created by lee on 2017-05-12.
 */

angular.module("oppf2").register.controller("Login", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
	//스플래시 화면 닫기
	$koscomBridge.closeSplash();
	
	var vm = this;
    
    vm.keypadOpen = function(){
        $koscomBridge.keypadOpen("비밀번호를 입력하세요.", 20, $koscomBridge.PAD_TYPE_ALPHA_U, $koscomBridge.ENCRYPT_RSA).then(function(res){
            vm.loginInfo.password = res.cipherData;
            vm.loginInfo.passwordDisplayText = res.displayText;
        }).catch(function(){
            console.log("error");
        });
    };

    vm.loginIdOtpCheck = function(){
        //OAuth 로그인 OTP 사용여부 조회
        $koscomBridge.req("OAU_01_015", {
            userId: vm.loginInfo.userId
        }).then(function(res){
            res.data == 'Y' ? vm.optMessage = true : vm.optMessage = false;
           //console.log(res.data);
        }).catch(function(e){
            console.log(e);
        });
    }

    vm.login = function(){
        if(vm.loginInfo.userId == null || vm.loginInfo.userId == "" || vm.loginInfo.password == null ||vm.loginInfo.password == ""){
            vm.idFocusOut = true;
            vm.passwordFocusOut = true;
        }else{
            vm.authenticate();
        }
    }
    
    //회원 로그인 인증 (인증서버)
    vm.authenticate = function(){
        $koscomBridge.req("OAU_01_020", vm.loginInfo).then(function(res){
            //인증서버 인증 성공 && Session OK
            if(res.data.code == '200' && vm.isAppState){
                //로그인 처리
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
                vm.appCheck();
            }else{
                //인증 실패
                $koscomPopup.alert("", '아이디 또는 비밀번호를 다시 확인하세요');
            }
        }).catch(function(e){
            console.log(e);
            $koscomPopup.alert("", '아이디 또는 비밀번호를 다시 확인하세요');
        });
    };

    //App 신청정보 조회
    vm.appCheck = function(){
        $koscomBridge.setAppData("finTechInfo", vm.finTechInfo);
        //App 신청정보 조회
        $koscomBridge.req("OAU_01_025", {
            clientId: vm.finTechInfo.clientId
        }).then(function(res){
            //권한허용 페이지
            $koscomState.go("scope", {
                finTechInfo: vm.finTechInfo
            });
            //6.27일 앱 사용신청 확인 로직 제외 협의
            /*if(res.data == 'Y'){
                //권한허용 페이지
                $koscomState.go("scope", {
                    finTechInfo: vm.finTechInfo
                });
            }else{
                $rootScope.isAppToApp = false;
                //핀테크 APP 결과 리턴
                $koscomBridge.fintechError(4000, "FAIL").then(function(){});
                //앱 상세 신청 페이지로 이동
                $koscomState.go("appAccountList", {appId: vm.finTechInfo.appId});
            }*/
        }).catch(function(e){
            console.log(e);
        });
    };

    /**
     * 회원가입 클릭
     * */
    vm.memberCreate = function(){
        $rootScope.isAppToApp = false;
        $koscomBridge.fintechError(4000, "FAIL").then(function(){});
        $rootScope.callbackState = "memberPrepare";
        $koscomState.go("main");
    };

    /**
     * ID 찾기 클릭
     * */
    vm.memberFindId = function(){
        $rootScope.isAppToApp = false;
        $koscomBridge.fintechError(4000, "FAIL").then(function(){});
        $rootScope.callbackState = "findId";
        $koscomState.go("main");
    };

    /**
     * 비밀번호 찾기 클릭
     * */
    vm.memberFindPassword = function(){
        $rootScope.isAppToApp = false;
        $koscomBridge.fintechError(4000, "FAIL").then(function(){});
        $rootScope.callbackState = "findPassword";
        $koscomState.go("main");
    };

    /**
     * 비회원 인증
     * */
    vm.guestAuthenticate = function(){
        $koscomState.go("nonMemberPrepare", {
            finTechInfo: vm.finTechInfo
        });
    };

    //페이지 진입시 OAuth Session 정보 조회
    $scope.$on('$ionicView.enter', function(){
        //App to App OAuth 로그인 Session 정보 검증
        vm.isAppState = false;
        
        //핀테크 연동 APP 파라메터 정보
        vm.finTechInfo = $stateParams.finTechInfo;
        $koscomBridge.getAppData("finTechInfo").then(function(res){
            vm.finTechInfo = res;
            vm.finTechInfoLogin();
        }).catch(function(){
            console.log("getAppData error");
            vm.finTechInfoLogin();
        });
        
    });
        
    /**
     * 핀테크 로그인
     * */
    vm.finTechInfoLogin = function(){

        //console.log(vm.finTechInfo);
//        alert(angular.toJson(vm.finTechInfo));
        //로그인 폼 데이터
        vm.loginInfo = {
            "userId" : '',
            "passwordDisplayText" : '',
            "password" : '',
            "clientId" : vm.finTechInfo.clientId
        };

        //아이디 에러 message
        vm.idFocusOut = false;
        //비밀번호 에러 message
        vm.passwordFocusout = false;
        //OTP 안내 message
        vm.optMessage = false;
        
        $koscomBridge.req("OAU_01_010", vm.finTechInfo).then(function(res){
            //인증서버 인증 성공
            vm.finTechInfo.sessionId = res.data.sessionId;
            vm.finTechInfo.appId = res.data.appId;
            vm.finTechInfo.appName = res.data.appName;
            vm.finTechInfo.appImg = res.data.appImg;
            vm.finTechInfo.companyName = res.data.companyName;
            vm.isAppState = true;
            //기존 회원 로그인 상태 체크
            vm.finTechInfo.isLogin = false;
            //기존 로그인 사용자는 인증 패스
            if($rootScope.isLogin){
                vm.finTechInfo.isLogin = true;
                vm.appCheck();
            }
        }).catch(function(e){
            console.log(e);
            $rootScope.isAppToApp = false;
            //핀테크 APP 결과 리턴
            $koscomBridge.fintechError(4100, "FAIL").then(function(){});
            $koscomState.go("main");
        });
    }
});