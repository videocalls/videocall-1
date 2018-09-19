/**
 * Created by lee on 2017-05-29.
 */

angular.module("oppf2").register.controller("Scope", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup) {
    var vm = this;
    //핀테크 연동 APP 파라메터 정보
    vm.finTechInfo = $stateParams.finTechInfo;
    vm.scopeList = [];
    vm.scopeGrpCode = 'G036';

    //권한 허용
    vm.grant = function(){
        //통합계좌조회 APP 권한허용
        if(!$rootScope.isAppToApp && vm.finTechInfo.sessionId == null){
            vm.createAccessToken();
        }else{
            //APP TO APP 권한허용
            var postData = {
                clientId: vm.finTechInfo.clientId,
                scope: vm.finTechInfo.scope,
                sessionId: vm.finTechInfo.sessionId
            };
            //OAuth 인증 이력 등록
            $koscomBridge.req("OAU_01_030", postData).then(function(res){
                //code 요청
                vm.authorization();
            }).catch(function(e){
                //인증 저장 실패
                console.log(e);
            });
        }
    };

    //권한 거부
    vm.revoke = function(){
        //통합계좌조회 진입시 거부일 경우 메인페이지로
        if(!$rootScope.isAppToApp && vm.finTechInfo.sessionId == null){
            $koscomState.go("main");
        }else{
            //핀테크 APP 결과 리턴
            $koscomBridge.fintechError(4300, "FAIL").then(function(){});
            if($rootScope.temporaryMemberYn != null && $rootScope.temporaryMemberYn == 'Y'){
                //로그아웃 호출
                $rootScope.appToAppLogout();
            }else{
                //회원 기존 로그인 상태
                if(vm.finTechInfo.isLogin){
                    $rootScope.isAppToApp = false;
                    $koscomState.go("main");
                }else{
                    //로그아웃 호출
                    $rootScope.appToAppLogout();
                }
            }
        }
    };

    //핀테크 APP Authorization code 요청
    vm.authorization = function(){
        $koscomBridge.req("OAU_01_040", {
            sessionId: vm.finTechInfo.sessionId
        }).then(function(res){
            //핀테크 APP 결과 리턴
            $koscomBridge.fintechSuccess(2000, "SUCCESS", null).then(function(){});

/**
 *  2017-07-18 이준형k 메세지 알림 삭제 요청            
 *            var message = vm.finTechInfo.appName + '에 대한 인증이 완료되었습니다. 해당 앱으로 이동하여 서비스를 이용하시기 바랍니다.';
 *            $koscomPopup.alert("", message).then(function(){
**/

                if($rootScope.temporaryMemberYn != null && $rootScope.temporaryMemberYn == 'Y'){
                    //로그아웃 호출
                    $rootScope.appToAppLogout();
                }else{
                    //회원 기존 로그인 상태
                    if(vm.finTechInfo.isLogin){
                        $rootScope.isAppToApp = false;
                        $koscomState.go("main");
                    }else{
                        //로그아웃 호출
                        $rootScope.appToAppLogout();
                    }
                }

/**            });   2017-07-18 이준형k 메세지 알림 삭제 요청 **/

            //로그아웃 호출
        }).catch(function(e){
            console.log(e);
            //핀테크 APP 결과 리턴
            $koscomBridge.fintechError(4200, "FAIL").then(function(){});
            if($rootScope.temporaryMemberYn != null && $rootScope.temporaryMemberYn == 'Y'){
                //로그아웃 호출
                $rootScope.appToAppLogout();
            }else{
                //회원 기존 로그인 상태
                if(vm.finTechInfo.isLogin){
                    $rootScope.isAppToApp = false;
                    $koscomState.go("main");
                }else{
                    //로그아웃 호출
                    $rootScope.appToAppLogout();
                }
            }
        });
    };

    //통합계좌 Oauth token 발급
    vm.createAccessToken  = function(){
        $koscomBridge.req("OAU_01_050", vm.finTechInfo).then(function(res){
            //console.log(res);
            //통합계좌페이지
            $koscomState.go("intAcnt");
        }).catch(function(e){
            console.log(e);
        });
    };
    
    //페이지 진입시 허용 권한 목록 조회
    $scope.$on('$ionicView.enter', function(){
        $koscomBridge.getAppData("finTechInfo").then(function(res){
            vm.finTechInfo = res;
            vm.enterAfter();
        }).catch(function(){
            console.log("getAppData error");
            vm.enterAfter();
        });
    });

    vm.enterAfter = function(){
//      alert(angular.toJson(vm.finTechInfo));
        //APP to APP 이 아닌 통합계좌조회 권한 페이지 적용
        if(vm.finTechInfo == null && !$rootScope.isAppToApp){
            //통합계좌 app 정보 조회
            $koscomBridge.req("INT_01_020").then(function(res){
                vm.finTechInfo = res.data;
            }).catch(function(e){
                console.log(e);
            });
        }
        $koscomBridge.req("COM_01_010", {
            systemGrpCode: vm.scopeGrpCode
        }).then(function(res){
            vm.scopeList = res.commonCodeList;
        }).catch(function(e){
            console.log(e);
        });
    }
    
});