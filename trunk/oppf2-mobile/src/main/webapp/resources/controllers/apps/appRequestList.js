/*
 * author : 이한별
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("AppRequestList", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup, $rootScope){

	var vm = this;
	$rootScope.callbackState="";
	
	vm.appList= [];
	vm.appListTotalCount=0;	
	
	vm.commonCodeList = [];
	vm.searchAppCategory="all";
	vm.searchKeyword="";
	
	window.setTimeout(function(){
		vm.commonCode("G025");
	}, 0); 
	
	$koscomBridge.req("APP_01_010", {type:"request", searchAppCategory:vm.searchAppCategory, searchKeyword:vm.searchKeyword}).then(function(res){
		vm.appListTotalCount = res.appListTotalCount;
		vm.appList = res.appList;

	}).catch(function(e){
		console.log("error");
	});
	
	$scope.$on('$ionicView.enter', function(){
		console.log($rootScope.isLogin);
		// 비회원 AppToApp 인 경우 체크
		if($stateParams.finTechInfo) {
			window.setTimeout(function(){
				$koscomBridge.req("APP_01_020", {appId: $stateParams.finTechInfo.appId}).then(function(res){
					var type = "";
					if(res.appDtl){
						type="modify";
					}
					$koscomBridge.req("APP_01_080", {pubCompanyCodeId : ""}).then(function(res){
						$koscomState.go("appAccountList", {appId:$stateParams.finTechInfo.appId, finTechInfo:$stateParams.finTechInfo, type: type});
					}).catch(function(e){
						var isNeedAll = "N";
						if(e.code === "9503") {
							// 인증서 등록 및 정보제공동의가 필요한 경우
							isNeedAll = "Y";
						} else if(e.code === "9505") {
							// 동의서 유효기간이 지난 경우
							isNeedAll = "N";
						}
						$koscomState.go("commonTerms", {appId: $stateParams.finTechInfo.appId, data:{"isNeedAll":isNeedAll}, finTechInfo:$stateParams.finTechInfo, type: type});
					});
				}).catch(function(e){
				});
			}, 0);
		}
		
		
	});
	
    vm.search = function(){
		$koscomBridge.req("APP_01_010", {type:"request", searchAppCategory:vm.searchAppCategory, searchKeyword:vm.searchKeyword}).then(function(res){
			vm.appListTotalCount = res.appListTotalCount;
			vm.appList = res.appList;

		}).catch(function(e){
			console.log("error");
		});
    };

	//여기에서 신청하려는 앱의 인증서 등록 및 코스콤 범용 동의 여부체크하여 미등록 또는 미동의, 유효기간 만료 시 해당 팝업
	vm.checkCommonTerms = function(appId, appName, companyName, companyCodeId){
        $scope.appId = appId;
        $scope.appName = appName;
        $scope.companyName = companyName;
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
            $koscomState.go("commonTerms", {appId: appId, data:{"isNeedAll":isNeedAll, "appName":appName,"companyName":companyName, "companyCodeId":companyCodeId}});
        });
	};

    vm.commonTermsCallBack = function(appId) {
        $koscomState.go('appAccountList', {appId: appId});
    };

    vm.commonCode = function(str){
        $koscomBridge.req("COM_01_010",{systemGrpCode : str}).then(function(res){
        	var CommonCodeRes={codeNameKor:"전체", searchResCode:"all"};
        	vm.commonCodeList.push(CommonCodeRes);
        	angular.forEach(res.commonCodeList, function(value,key){
        		vm.commonCodeList.push(value);
        	});
        }).catch(function(e){
            console.log("error");
        });
    };
	
    // key down event
    vm.inputKeyDown = function(event){
      if(event.keyCode == 13) {
          $( ".btn_del" ).focus();
          console.log( $( ".btn_del" ).focus() );
      }
    };
    
    // 검색 초기화
    vm.searchClear = function(){
    	vm.appList= [];
    	vm.searchAppCategory="all";
    	vm.searchKeyword = ""; 
    	
    	vm.search();
    };
    
	//앱 신청,삭제 시 이벤트
	$rootScope.$on("appRefresh", function(evt, data){
		vm.appList=[];
    	vm.searchAppCategory="all";
    	vm.searchKeyword = ""; 
    	
    	vm.search();
	});
		
    
    vm.doRefresh = function() {
/*    	vm.searchAppCategory="all";
    	vm.searchKeyword = ""; */

    	vm.search();
    	$scope.$broadcast('scroll.refreshComplete');
	};

	vm.cutByteLength = function(s, len) {

		if (s == null || s.length == 0) {
			return 0;
		}
		var size = 0;
		var rIndex = s.length;

		for ( var i = 0; i < s.length; i++) {
			size += this.charByteSize(s.charAt(i));
			if( size == len ) {
				rIndex = i + 1;
				break;
			} else if( size > len ) {
				rIndex = i;
				break;
			}
		}

		return s.substring(0, rIndex);
	};

	vm.charByteSize = function(ch) {

		if (ch == null || ch.length == 0) {
			return 0;
		}

		var charCode = ch.charCodeAt(0);

		if (charCode <= 0x00007F) {
			return 1;
		} else {
			return 2;
		}
	};

});
