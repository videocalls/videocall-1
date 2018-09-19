/*
 * author : 이한별
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("AppIntro", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup, $rootScope){

	var vm = this;

	vm.appList= [];
	vm.appListTotalCount=0;	
	
	vm.commonCodeList = [];
	vm.searchAppCategory="all";
	vm.searchKeyword="";
	
	window.setTimeout(function(){
		vm.commonCode("G025");
	}, 0);
	
	$koscomBridge.req("APP_01_010", {type:"intro", searchAppCategory:vm.searchAppCategory, searchKeyword:vm.searchKeyword}).then(function(res){
    	vm.appListTotalCount = res.appListTotalCount;
    	vm.appList = res.appList;

	   }).catch(function(e){
	       console.log("error");
	   });
	

    vm.search = function(){
	    $koscomBridge.req("APP_01_010", {type:"intro", searchAppCategory:vm.searchAppCategory, searchKeyword:vm.searchKeyword}).then(function(res){
	    	vm.appListTotalCount = res.appListTotalCount;
	    	vm.appList = res.appList;

		   }).catch(function(e){
		       console.log("error");
		   });
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
    
	vm.cancel = function() {
		$koscomState.go("main");
	};
	

	vm.goAppDetail = function(appId){
		$koscomState.go("appDetail", {appId:appId, type:"intro"});
	};
	
	
	$rootScope.$on("logout", function(){
		vm.appList=[];
        console.log('apps page logout evnet recall');
        window.setTimeout(function(){
        		 $koscomBridge.req("APP_01_010", {type:"intro", searchAppCategory:vm.searchAppCategory, searchKeyword:vm.searchKeyword}).then(function(res){
        		    	vm.appListTotalCount = res.appListTotalCount;
        		    	vm.appList = res.appList;

        			   }).catch(function(e){
        			       console.log("error " + e);
        			   });
        },0);
	});

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
    
    vm.doRefresh = function() {
/*    	vm.searchAppCategory="all";
    	vm.searchKeyword = ""; */

    	vm.search();
    	$scope.$broadcast('scroll.refreshComplete');
	};
	
	//앱 신청,삭제 시 이벤트
	$rootScope.$on("appRefresh", function(evt, data){
		vm.appList=[];
    	vm.searchAppCategory="all";
    	vm.searchKeyword = ""; 
    	
    	vm.search();
	});

	$rootScope.$on("login", function(){
		vm.appList=[];
		vm.searchAppCategory="all";
		vm.searchKeyword = "";

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
