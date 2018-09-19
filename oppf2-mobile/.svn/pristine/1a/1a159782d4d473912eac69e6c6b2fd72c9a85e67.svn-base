/*
 * author : 이한별
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("Apps", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup, $rootScope, $ionicScrollDelegate){
	//스플래시 화면 닫기
	$koscomBridge.closeSplash();
	
	var vm = this;

	vm.appList= [];
	vm.appListTotalCount=0;	
	
	vm.commonCodeList = [];
	vm.searchAppCategory="all";
	vm.searchKeyword="";
	
	if($stateParams.loginTimeout){
		$koscomPopup.alert("알림", "장시간 미사용 또는 다른 이유로 서버와의 접속이 끊어졌습니다.서비스를 다시 이용해 주십시오.");
	}
	
	//공통코드 셋팅 -> 앱 카테고리
	window.setTimeout(function(){
		vm.commonCode("G025");
	}, 0); 
	
	$scope.$on('$ionicView.enter', function(){
		$koscomState.clearHistory();
        console.log('apps page enter');

		// 가동의서 체크
		if($rootScope.isLogin && $rootScope.checkedCommonTerms) {
			window.setTimeout(function(){
				$koscomBridge.req("APP_01_160").then(function(res){
					vm.checkCallBackState();
				}).catch(function(e){
					$koscomState.go("commonTerms", {appId: null, data:{"isNeedAll":"N", "appName":"","companyName":"", "companyCodeId":"", "retUrl" : "main"}});
				});
			}, 100);
		} else {
			vm.checkCallBackState();
		}

	});

	vm.checkCallBackState = function() {
		//리다이렉트가 존재할 경우 해당 페이지로 이동한다.
		if($rootScope.callbackState){
			if($rootScope.callbackState === "appAccountList") {
				window.setTimeout(function(){
					$koscomBridge.req("APP_01_020", {appId: $rootScope.callbackStataeParams.appId}).then(function (res) {
						if (res.appDtl) {
							$rootScope.callbackStataeParams.type = "modify";
						}
						$koscomBridge.req("APP_01_080", {pubCompanyCodeId : ""}).then(function(res){
							$koscomState.go("appAccountList", $rootScope.callbackStataeParams);
							$rootScope.callbackState = "";
							$rootScope.callbackStataeParams = {};
						}).catch(function(e){
							var isNeedAll = "N";
							if(e.code === "9503") {
								// 인증서 등록 및 정보제공동의가 필요한 경우
								isNeedAll = "Y";
							} else if(e.code === "9505") {
								// 동의서 유효기간이 지난 경우
								isNeedAll = "N";
							}
							window.setTimeout(function(){
								$koscomState.go("commonTerms", {appId: $rootScope.callbackStataeParams.appId, data:{"isNeedAll":isNeedAll}, type: $rootScope.callbackStataeParams.type});
							}, 1000);

							$rootScope.callbackState = "";
							$rootScope.callbackStataeParams = {};
						});
					});
				}, 1000);
			} else {
				window.setTimeout(function(){
					$koscomState.go($rootScope.callbackState, $rootScope.callbackStataeParams);
					$rootScope.callbackState = "";
					$rootScope.callbackStataeParams = {};
				}, 1000);
			}

			return;
		}
	};
		
		// 공지사항 팝업 이벤트 local storage 정보 삭제
		// 테스트시 주석해제
		//$koscomState.removeLocalStorage("displayDateNotice");
		// 공지사항 팝업 이벤트
		

	/*$rootScope.$on('application_list', function(evt, data, data2){

	});

	$scope.$emit('application_list', 1, 2);*/

    // 공지사항 팝업 이벤트
	vm.noticePopupEvent = function() {
	    var displayDate = new Date();
	    var str = $koscomBridge.getLocalStorage("displayDateNotice");
//        console.log("displayDateNoticeOpen :: " + str);
        // 로컬 스토리지에 날짜 값이 들어간게 있다면 밀리세컨로 값이 들어가 있다.
        // 없을경우 null
        // 날짜 객체를 만들고 비교할 날짜를 입력한다.
	    var compareDate = new Date(str);
	    //조건이 중요한다 저장된 str 값이 없거나, 비교할 날짜보다 현자 날짜가 큰경우만 
        //div.MDpopFrame 을 show 한다.
        if(str == undefined || str == null || compareDate.getTime()  <= displayDate.getTime()){
            $koscomPopup.modalFromTemplateUrl("/resources/views/notice/noticePopup.html", {
                name: "noticeNoticePopup",
                controllerUrl: "/resources/controllers/notice/noticePopup.js",
            }).then(function(modal) {
                modal.show();
            });
        }
    };
    

    vm.search = function(){
	    $koscomBridge.req("APP_01_010", {type:"list", searchAppCategory:vm.searchAppCategory, searchKeyword:vm.searchKeyword}).then(function(res){
	    	vm.appListTotalCount = res.appListTotalCount;
	    	vm.appList = res.appList;
	    	
	    	window.setTimeout(function(){
	    		$ionicScrollDelegate.resize();
	    	}, 100);

		   }).catch(function(e){
		       console.log("error");
		   });
   };

   var introPopup = $koscomBridge.getLocalStorage("introPopup");
	if(introPopup !== "true"){
		$scope.noticePopupEvent = function(){
			vm.noticePopupEvent();
		};
		$koscomPopup.modalFromTemplateUrl("/resources/views/intro/intro.html", {
			scope: $scope,
	        name: "introPopup",
	        controllerUrl: "/resources/controllers/intro/intro.js",
	    }).then(function(modal) {
	        modal.show();
	    });
	}
	else{
		vm.noticePopupEvent();
	}

   
    vm.commonCode = function(str){
        $koscomBridge.req("COM_01_010",{systemGrpCode : str}).then(function(res){
        	var CommonCodeRes={codeNameKor:"전체", searchResCode:"all"};
        	vm.commonCodeList.push(CommonCodeRes);
        	vm.commonCodeList = vm.commonCodeList.concat(res.commonCodeList);
        }).catch(function(e){
            console.log("error");
        });
    };
    
	vm.cancel = function() {
		$koscomState.go("main");
	};
	
	vm.goAppRequest = function(){
		$koscomState.go("appRequestList");
	};

	vm.goAppDetail = function(appId){
		$koscomState.go("appDetail", {appId: appId});
	};
	
	
	$rootScope.$on("logout", function(){
		vm.appList=[];
        console.log('apps page logout evnet recall');

        window.setTimeout(function(){
        		 $koscomBridge.req("APP_01_010", {type:"list", searchAppCategory:vm.searchAppCategory, searchKeyword:vm.searchKeyword}).then(function(res){
        		    	vm.appListTotalCount = res.appListTotalCount;
        		    	vm.appList = res.appList;

        			   }).catch(function(e){
        			       console.log("error " + e);
        			   });
        },0);
	});
	
	$rootScope.$on("login", function(){
   		vm.appList=[];
    	vm.searchAppCategory="all";
    	vm.searchKeyword = ""; 
    	
    	vm.search();
	});
	
	//앱 신청,삭제 시 이벤트
	$rootScope.$on("appRefresh", function(evt, data){
		vm.appList=[];
    	vm.searchAppCategory="all";
    	vm.searchKeyword = ""; 
    	
    	vm.search();
	});
		
	
    // 검색 초기화
    vm.searchClear = function(){
    	vm.searchAppCategory="all";
    	vm.searchKeyword = ""; 
    	
    	vm.search();
    };
    
    vm.doRefresh = function() {
/*    	vm.searchAppCategory="all";
    	vm.searchKeyword = ""; 
*/
    	vm.search();
    	$scope.$broadcast('scroll.refreshComplete');
	};
	
	
	$scope.$watch("vm.searchAppCategory", function(newVal, oldVal){
		vm.search();
	});
	
	$scope.$watch("vm.searchKeyword", function(newVal, oldVal){
		vm.search();
	});

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
