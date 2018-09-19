/*
 * author : 이한별
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("AppDetail", function($scope, $state, $stateParams, $koscomBridge, $rootScope, $koscomState,$koscomPopup){
	var vm = this;
	
	$rootScope.callbackState="";
	
/*	vm.appDtl = {};
	vm.terms={};
	vm.AppCompanyList=[];
	vm.AppAccountList=[];*/
	vm.type="";
	
	$scope.appId="";
	$scope.reSyncYn="";
	
	vm.style = "accordian-cont cont_wrap";
		
	$koscomBridge.req("APP_01_020", {appId: $stateParams.appId, type:$stateParams.type}).then(function(res){

		if(!res.appDtl) {
			$koscomPopup.alert("", "활성화 된 앱이 아닙니다.").then(function(){
				$koscomState.go("main");
			});
		}

		vm.appDtl = res.appDtl;  //상세 조회
		if(vm.appDtl && vm.appDtl.serviceRegNo){
			vm.type="modify";
		}
		vm.AppCompanyList = res.AppCompanyList;  //연계 서비스
		if(undefined!==res.AppAccountList){      //로그인 되었을때 연결된 가상계좌리스트를 던져줬다면.
			vm.AppAccountList = res.AppAccountList;  //로그인 되었을때 상세화면에 연결된 가상계좌 리스트를 뿌려준다.
		}
    }).catch(function(e){
        console.log("error");
    });
	
	//로그인 후 화면 갱신
	$rootScope.$on("login", function(){
		if(!$stateParams.appId){
			return;
		}
		vm.appDtl={};
		vm.AppAccountList=[];
        window.setTimeout(function(){
        	$koscomBridge.req("APP_01_020", {appId: $stateParams.appId, type:$stateParams.type}).then(function(res){
        		vm.appDtl = res.appDtl;  //상세 조회
        		if(vm.appDtl && vm.appDtl.serviceRegNo){
        			vm.type="modify";
        		}
        		vm.AppCompanyList = res.AppCompanyList;  //연계 서비스
        		if(undefined!==res.AppAccountList){      //로그인 되었을때 연결된 가상계좌리스트를 던져줬다면.
        			vm.AppAccountList = res.AppAccountList;  //로그인 되었을때 상세화면에 연결된 가상계좌 리스트를 뿌려준다.
        		}
            }).catch(function(e){
                console.log("error");
            });
        },0);
	});
	
	//신청 정보 수정시 갱신
	$rootScope.$on('appModify', function(evt, data){
		if($stateParams.appId==data){
			$koscomBridge.req("APP_01_020", {appId:data, type:$stateParams.type}).then(function(res){
				vm.appDtl = res.appDtl;  //상세 조회
				if(vm.appDtl.serviceRegNo){
					vm.type="modify";
				}
				vm.AppCompanyList = res.AppCompanyList;  //연계 서비스
				if(undefined!==res.AppAccountList){      //로그인 되었을때 연결된 가상계좌리스트를 던져줬다면.
					vm.AppAccountList = res.AppAccountList;  //로그인 되었을때 상세화면에 연결된 가상계좌 리스트를 뿌려준다.
				}
		    }).catch(function(e){
		        console.log("error");
		    });				
		}

	});
	
	//앱 신청,삭제 시 이벤트
	$rootScope.$on("appRefresh", function(evt, data){
		if($stateParams.appId==data){
			$koscomBridge.req("APP_01_020", {appId:data, type:$stateParams.type}).then(function(res){
				vm.appDtl = res.appDtl;  //상세 조회
				if(vm.appDtl.serviceRegNo){
					vm.type="modify";
				}
				vm.AppCompanyList = res.AppCompanyList;  //연계 서비스
				if(undefined!==res.AppAccountList){      //로그인 되었을때 연결된 가상계좌리스트를 던져줬다면.
					vm.AppAccountList = res.AppAccountList;  //로그인 되었을때 상세화면에 연결된 가상계좌 리스트를 뿌려준다.
				}
		    }).catch(function(e){
		        console.log("error");
		    });				
		}
	});

	vm.termsInfo = function(appId, type) {
		var params = {
			appId: appId,
			checkedPubCompanyList : "",
			type : type,
			data: {
				"accountList" : vm.AppAccountList,
				"appName" : vm.appDtl.appName,
				"companyName" : vm.appDtl.companyName
			}
		};
		$koscomState.go("appTerms", params, {reload: "appTerms"});
	};
	
	/*로그인 전 신청하기*/
	vm.goapps = function(){
	     //로그인 전 ->상세->앱신청(로그인 필요)
		var params = {appId:vm.appDtl.appId, type:"", appName: vm.appDtl.appName, companyCodeId: vm.appDtl.companyCodeId};
		$rootScope.callbackState="appAccountList";
		$rootScope.callbackStataeParams = params;
		$koscomState.go("appAccountList", params);
	};
	
	vm.goMain = function() {
		$koscomState.back(-1);
	};
	
	vm.appDelete = function(appId, appName){
		var result = $koscomPopup.confirm("앱 삭제", appName+"앱 신청 정보를 삭제하시겠습니까? 삭제하시면 서비스를 이용하실 수 없습니다.");
		result.then(function(res){
			if(res==true){
				$koscomBridge.req("APP_01_100", {appId:appId}).then(function(res){
					$scope.$emit('appRefresh', appId); //앱 삭제시 이벤트
					$koscomState.go("main");
			    }).catch(function(e){
			        console.log("error");
			    });
			}
		});
	};
	/*신청 정보 수정*/
	vm.goAppAccountList=function(appId, type, appName, companyName, companyCodeId){
		$koscomState.go("appAccountList",{"appId":appId, "type":type, "appName":appName, "companyName":companyName, "companyCodeId":companyCodeId});
	};
	
	/*신청 하기*/
	vm.goAppRequestAccountList=function(appId){
		$koscomBridge.req("APP_01_080", {pubCompanyCodeId : ""}).then(function(res){
			$koscomState.go("appAccountList", {appId:appId});
		}).catch(function(e){
			var isNeedAll = "N";
			if(e.code === "9503") {
				// 인증서 등록 및 정보제공동의가 필요한 경우
				isNeedAll = "Y";
			} else if(e.code === "9505") {
				// 동의서 유효기간이 지난 경우
				isNeedAll = "N";
			}
			$koscomState.go("commonTerms", {appId: appId, data:{"isNeedAll":isNeedAll}});
		});		
/*        $koscomBridge.req("APP_01_080", {pubCompanyCodeId : ""}).then(function(res){
            $koscomState.go("appAccountList", {appId:appId});
         }).catch(function(e){
             var isNeedAll = "N";
             if(e.code === "9503") {
                // 인증서 등록 및 정보제공동의가 필요한 경우
                isNeedAll = "Y";
             } else if(e.code === "9505") {
                // 동의서 유효기간이 지난 경우
                isNeedAll = "N";
             }
             $koscomState.go("commonTerms", {appId: appId, data:{"isNeedAll":isNeedAll, "appName":appName,"companyName":companyName, "companyCodeId":companyCodeId}});
         });*/
	};
	
	//앱 소개 ->아이콘 구역 터치시 외부 브라우저 이동
	vm.goDlUrl=function(){
		var url = vm.appDtl.appDlUrl;
		if(!url){
			return false;
		}
//	    $koscomBridge.openUrl("https://"+url);
	    $koscomBridge.openUrl(url);
	};
	
});