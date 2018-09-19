/*
 * author : PK
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("AccountAddStep01Pop", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;
    
    var appParams=$stateParams.appParams||{}; //앱신청,신청정보 수정으로 들어왔을 경우
    
    vm.companyProfileRegNo="";
    vm.companyCodeId="";
	$koscomBridge.req("ACC_01_060").then(function(res){
		
		vm.comCompanyProfileResList = res.comCompanyProfileResList;
		
	}).catch(function(e){
		
		console.log("error");
		
	});

    vm.close = function(){
        $koscomPopup.close();

    };

    vm.makeAccount = function(companyProfileRegNo,companyCodeId){

        $koscomBridge.req("APP_01_080", {pubCompanyCodeId: companyCodeId}).then(function(res){
        	$koscomState.go("accountAddStep021Pop",{companyProfileRegNo: companyProfileRegNo, companyCodeId: companyCodeId, appParams:appParams});
        }).catch(function(e){
            var isNeedAll = "N";
            if(e.code === "9503") {
               // 인증서 등록 및 정보제공동의가 필요한 경우
               isNeedAll = "Y";
            } else if(e.code === "9505") {
               // 동의서 유효기간이 지난 경우
               isNeedAll = "N";
            }
            $koscomState.go("commonTerms", {appId: "", data:{"isNeedAll":isNeedAll, "appName":"","companyName":"","retUrl":"accountAddStep021Pop","companyProfileRegNo": companyProfileRegNo, "companyCodeId": companyCodeId}});

           // $koscomPopup.modalFromTemplateUrl("/resources/views/terms/commonTermsPopUp.html", {
           //     controllerUrl : "/resources/controllers/terms/commonTerms.js",
           //     scope : $scope
           // }).then(function(modal) {
           //     modal.show();
           // });
        });
    	
				
	};
    
    vm.makeAccount2 = function(companyProfileRegNo,companyCodeId){
    	

        $koscomBridge.req("APP_01_080", {pubCompanyCodeId : " "}).then(function(res){
        	$koscomState.go("accountAddStep022Pop",{companyProfileRegNo: companyProfileRegNo, companyCodeId: companyCodeId, appParams:appParams});
        }).catch(function(e){
            var isNeedAll = "N";
            if(e.code === "9503") {
               // 인증서 등록 및 정보제공동의가 필요한 경우
               isNeedAll = "Y";
            } else if(e.code === "9505") {
               // 동의서 유효기간이 지난 경우
               isNeedAll = "N";
            }
            $koscomState.go("commonTerms", {appId: "", data:{"isNeedAll":isNeedAll, "appName":"","companyName":"","retUrl":"accountAddStep022Pop","companyProfileRegNo": companyProfileRegNo, "companyCodeId": companyCodeId}});

           // $koscomPopup.modalFromTemplateUrl("/resources/views/terms/commonTermsPopUp.html", {
           //     controllerUrl : "/resources/controllers/terms/commonTerms.js",
           //     scope : $scope
           // }).then(function(modal) {
           //     modal.show();
           // });
        });
    	
				
	};
	

    vm.cancel = function(){
    	$koscomState.back(-1);
    };
    
	
});