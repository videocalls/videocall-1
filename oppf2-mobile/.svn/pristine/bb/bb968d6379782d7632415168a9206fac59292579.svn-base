/**
 * Created by unionman on 2017-05-25.
 */

angular.module("oppf2").register.controller("Settings", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;

    vm.displayCurrentVersion = "0.0";
    vm.displayServerVersion = "0.0";
    vm.isShowUpdateButton = false;
    // push 수신 여부
    vm.checkboxPushYn = "N";
    vm.checkboxPushChecked = "false"; 

    vm.appUpdateUrl = "";
    /* ******************
     * page event 
     * ****************** */
    $scope.$on('$ionicView.loaded', function(){
        console.log('SignKoreaListPop page Loaded');
        vm.getDeviceVersion();
    });

    /* ******************
     * 최초 로딩 event 
     * ****************** */
    $scope.$on('$ionicView.enter', function(){
        console.log('SignKoreaListPop page enter');
        vm.selecctMemberInfo();
    });
    
	// 회원 정보 조회
	vm.selecctMemberInfo = function(){
	    if( $rootScope.isLogin) {
	        $koscomBridge.req("MBR_02_300" ).then(function(res){
	            vm.checkboxPushYn = res.memberRes.customerMobilePushYn;
	            vm.checkboxPushChecked = ( vm.checkboxPushYn == 'Y');
	        }).catch(function(e){
	            console.log("error");
	        });
	    }
    };
    
    // 회원 모바일 push 메세지 수신여부 변경
    vm.changeMobilePush = function(){
        $koscomBridge.req("MBR_02_370", {customerMobilePushYn : vm.checkboxPushYn} ).then(function(res){
        }).catch(function(e){
            console.log("error");
        });
    };
    
    // push 수신여부 변경
    vm.updatePushYn = function(){
        if( vm.checkboxPushChecked) {
            vm.checkboxPushYn = "Y";
        } else {
            vm.checkboxPushYn = "N";
        }
        vm.changeMobilePush();
    };
    
    // push 수신여부 변경 로그인 요청
    vm.requestLogin = function(){
        vm.checkboxPushYn = "N";
        vm.checkboxPushChecked = false;
        var c = $koscomPopup.confirm("", "로그인이 필요합니다.<br/>로그인 하시겠습니까?").then(function(res){
            if(res){
                $koscomState.go('login', {
                    data : true
                });
            }
            else{
                deferred.reject();
            }
        });
    };
    
    vm.goUrlParam = function(url, param){
        if( !param ) {
            $koscomState.go(url, param);
        } else {
            $koscomState.go(url);
        }
    };
    
    vm.alert = function(title, contents){
        var c = $koscomPopup.alert(title, contents);
        c.then(function(){
            console.log("click!!");
        });
    };
    
    // api 서버 버전 정보를 조회한다.
    vm.getApiVersion = function(apiVersion) {
        $koscomBridge.req("ITR_01_010",{refrashcookie:'1'}).then(function(res){
//            console.log(res.serviceVersion.version);
            vm.mobileVersion = angular.toJson(res.serviceVersion.version);
            
            vm.displayCurrentVersion = apiVersion.appVersion;
            if( apiVersion.osType == "ios") {
                vm.displayServerVersion = res.serviceVersion.version.ios;
                vm.appUpdateUrl = res.serviceVersion.version.iosInstallUrl;
            } else {
                vm.displayServerVersion = res.serviceVersion.version.android;
                vm.appUpdateUrl = res.serviceVersion.version.androidInstallUrl;
            }
            if( vm.displayCurrentVersion != vm.displayServerVersion) {
                vm.isShowUpdateButton = true;
            }
            
        }).catch(function(e){
            console.log("vm.getApiVertion error");
        });
        
    }
    
    // device 버전 정보를 조회 한다.
    vm.getDeviceVersion = function() {
        $koscomBridge.getDeviceInfo().then(function(res){
            vm.deviceVersion = angular.toJson(res);
//            console.log("deviceInfo", res);
            
            vm.getApiVersion(res);
        });
        
    }
    
    vm.modalSiteTerms = function(str){
        if( str == 1 ) {
            $scope.settingsTermsTitle = "서비스 이용약관";
        } else {
            $scope.settingsTermsTitle = "개인정보취급방침";
        }
        $scope.settingsTermsIndex = str;

        $koscomPopup.modalFromTemplateUrl("/resources/views/settings/settingsTermsModal.html", {
            name: "settingsSettingsTermsModal",
            controllerUrl: "/resources/controllers/settings/settingsTermsModal.js",
            scope: $scope
        }).then(function(modal) {
            modal.show();
        });
    };

    // app Update 
    vm.appUpdate = function() {
        $koscomBridge.openUrl(vm.appUpdateUrl);
    };
    
});