/*
 * author : 이한별
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("CommonTerms", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup, $rootScope){

	var vm = this;
    vm.commonTermsInfo = {};
    vm.signKoreaResultData = {};
    vm.isNeedAll = $stateParams.data.isNeedAll;
    vm.titleText = vm.isNeedAll == "Y" ? "인증서 등록 및 동의" : "정보제공동의";
    
    vm.retUrl = $stateParams.data.retUrl;
    vm.companyProfileRegNo = $stateParams.data.companyProfileRegNo;
    vm.companyCodeId = $stateParams.data.companyCodeId;
    vm.beforeCommonTermsInfo = {};
    console.log($rootScope.isAppToApp);
    
    vm.cancel = function() {
        if(vm.retUrl != '' && vm.retUrl == 'main') {
            $rootScope.checkedCommonTerms = false;
            var message = "코스콤에 대한 금융거래정보 제공동의가 만료될 예정입니다.\n(만료" + vm.beforeCommonTermsInfo.dateCount + "일전)\n정보 제공에 재동의하셔야 정상적인 서비스 이용이 가능합니다.";
            $koscomPopup.alert("", message).then(function(){
                if($rootScope.isAppToApp && !vm.retUrl) {
                    //핀테크 APP 결과 리턴
                    $koscomBridge.fintechError(4000, "FAIL").then(function(){});
                    //로그아웃 호출
                    $rootScope.appToAppLogout();
                } else {
                    $koscomState.back(-1);
                }
            });
        } else {
            if($rootScope.isAppToApp && !vm.retUrl) {
                //핀테크 APP 결과 리턴
                $koscomBridge.fintechError(4000, "FAIL").then(function(){});
                //로그아웃 호출
                $rootScope.appToAppLogout();
            } else {
                $koscomState.back(-1);
            }
        }

    };

    $koscomBridge.req("APP_01_050").then(function(res){
        vm.commonTermsInfo = res.commonTermsInfo;
        vm.beforeCommonTermsInfo = res.beforeCommonTermsInfo;
    }).catch(function(e){
        console.log("error");
    });

    $scope.$on('$ionicView.enter', function(){
        $koscomBridge.getAppData("signKoreaCertResultData").then(function(res){
            if(res.result == 1) {
                // 처리내용 구현
                var termsHtml = $("#commonTerms").html().replace(/\(/g, "&#40;");
                termsHtml = termsHtml.replace(/\(/g, "&#40;");
                termsHtml = termsHtml.replace(/\)/g, "&#41;");
                var appCreateReq = {
                    "customerSignData" : res.signData,
                    "customerSignDn" : res.customerDn,
                    "reqHtml" : termsHtml
                };
                $koscomBridge.req("APP_01_120", appCreateReq).then(function(res){
                    // $scope.commonTermsCallBack($scope.appId);
                    var alertMessage = "인증서 등록 및 정보제공동의가 완료되었습니다.";
                    if($stateParams.data.isNeedAll === "N") {
                        alertMessage = "정보제공동의가 완료되었습니다.";
                    }
                    $koscomPopup.alert("", alertMessage).then(function() {
                        if("accountAddStep021Pop" == vm.retUrl){
                            $koscomState.go("accountAddStep021Pop",{
                                companyProfileRegNo: vm.companyProfileRegNo,
                                companyCodeId: vm.companyCodeId
                            });
                        }else if("accountAddStep022Pop" == vm.retUrl){

                            $koscomState.go("accountAddStep022Pop",{
                                companyProfileRegNo: vm.companyProfileRegNo,
                                companyCodeId: vm.companyCodeId
                            });
                        } else if("main" == vm.retUrl) {
                            $koscomState.go("main");
                        } else {
                            $koscomState.go('appAccountList',{
                                appId: $stateParams.appId,
                                type: $stateParams.type,
                                finTechInfo:$stateParams.finTechInfo
                            });
                        }
                    });
                }).catch(function(e){
                    if(e.code === "9502") {
                        $koscomPopup.alert("", e.message);
                    }
                });

                /*if($stateParams.data.isNeedAll === "Y") {
                    $koscomPopup.alert("", "인증서 등록 및 정보제공동의가 완료되었습니다.");
                } else {
                    $koscomPopup.alert("", "정보제공동의가 완료되었습니다.");
                }*/

            }

            $koscomBridge.setAppData("signKoreaCertResultData", {});

        }).catch(function(){
            console.log("공인인증서 결과값 확인 실패!!!!!");
        });
    });

    vm.sign = function() {
    };

    vm.signKorea = function(){
        var termsHtml = $("#commonTerms").html().replace(/\(/g, "&#40;");
        termsHtml = termsHtml.replace(/\(/g, "&#40;");
        termsHtml = termsHtml.replace(/\)/g, "&#41;");
        $koscomState.go("signKoreaCert", {signPlainText : termsHtml});
    };

    vm.tooltipsConfirmClick = function(){
        vm.signKorea();
    };

});
