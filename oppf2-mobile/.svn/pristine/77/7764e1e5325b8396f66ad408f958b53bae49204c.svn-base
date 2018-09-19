/*
 * author : 이한별
 * createDate : 2017-05-01 
 * updateDate : 2017-05-01
 * */

angular.module("oppf2").register.controller("AppAccountList", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup, $rootScope){
	
	var vm = this;
	vm.type=$stateParams.type;
	vm.noList= $stateParams.type ? false : true;      //true일 경우 개설한 가상계좌 목록을 보여준다.
	vm.title="앱 사용 신청";
	if(vm.type && vm.type === "modify"){
		vm.title="신청 정보 수정";
	}

	vm.accountList= [];
	vm.accountListOrg=[]; 				/*최초 DB조회 결과*/
	vm.isAccountListCopy=false; 		/*최초 DB조회 결과 넣기 위한 플래그*/
	vm.appCompanyList = [];

	$scope.$on('$ionicView.enter', function(){
		vm.accountList= [];
		$koscomBridge.req("APP_01_030", {appId:$stateParams.appId, type:$stateParams.type}).then(function(res){
			vm.tmpAccountList = res.accountList;
			/*연결된 계좌 있을경우 아코디언 열림 이벤트*/
			angular.forEach(vm.tmpAccountList, function(account) {  //조회된 계좌 목록
				angular.forEach(account.vtAccountResList, function(vtAccount) {  //가상계좌 목록
					if(vtAccount.accountRegNo) {                 //앱에 연결되어있는 계좌일 경우
						account.divActiveTag="active";						
					}
				});				
			});
			vm.accountList = vm.tmpAccountList;
			vm.appCompanyList = res.appCompanyList;  //연계 서비스
			vm.appName = res.appInfo.appName;
			vm.subCompanyName = res.appInfo.subCompanyName;
			vm.subCompanyCodeId = res.appInfo.subCompanyCodeId;
			vm.disabledCreateButton();
			if(!vm.isAccountListCopy){
				vm.accountListOrg=angular.copy(vm.accountList);
			}
			vm.isCompareObj();
			
		}).catch(function(e){
			console.log("error");
		});		
	});

    /*신청 정보 수정 사항 비교 이벤트*/
    vm.isCompareObj=function(){
        if(angular.equals(vm.accountList,vm.accountListOrg)){//신청 정보 변경 없을때
            vm.noList = true;		//다음 버튼 비활성화 
        } else {//신청 정보 변경 있을때
        	vm.checkCount = 0;   
    		angular.forEach(vm.accountList, function(account) {
    			angular.forEach(account.vtAccountResList, function(vtAccount) {
    				if(vtAccount.checked) {
    					vm.checkCount++;    
    				}
    			});
    		});
    		if(vm.checkCount==0){
    			vm.noList=true;		//선택한 계좌가 없을 경우 ->다음 버튼 비활성화
    		}else{
    			vm.noList=false;	//선택한 계좌가 있을 경우 ->다음 버튼 활성화
    		}
        }
    };
    
    /*다음 버튼 클릭-> 앱 신청, 신청 정보 수정 */
	vm.createApp = function() {
		console.log(vm.accountList);
		var pubCompanyCodeId = "";
		angular.forEach(vm.accountList, function(obj) {
			if(obj.checked) {
				if(pubCompanyCodeId !== "") {
					pubCompanyCodeId += "," + obj.pubCompanyCodeId;
				} else {
					pubCompanyCodeId = obj.pubCompanyCodeId;
				}
			} else {
				var vtCount = 0;
				angular.forEach(obj.vtAccountResList, function(vtAccount) {
					if(vtAccount.checked && vtCount == 0) {
						if(pubCompanyCodeId !== "") {
							pubCompanyCodeId += "," + obj.pubCompanyCodeId;
						} else {
							pubCompanyCodeId = obj.pubCompanyCodeId;
						}
						vtCount++;
					}
				});
			}
		});
		// (나)동의서 체크
		$koscomBridge.req("APP_01_130", {appId:$stateParams.appId, checkedPubCompanyList : pubCompanyCodeId, subCompanyCodeId:vm.subCompanyCodeId }).then(function(res){
			var appCreateReq = {
				"appId" : $stateParams.appId,
				"termsRegNo" : res.termsRegNo,
				"accountList" : vm.accountList,
				"termsCreatedYn" : "Y"
			};

			var serviceId = "APP_01_090";
			if(vm.type === "modify") {
				serviceId = "APP_01_110";
			}

			$koscomBridge.req(serviceId, appCreateReq).then(function(res) {
				$koscomState.go("appCreateComplete", {appId: $stateParams.appId, type: vm.type, finTechInfo:$stateParams.finTechInfo});
			}).catch(function(e){
			});

		}).catch(function(e){
			var params = {
				appId:$stateParams.appId,
				checkedPubCompanyList : pubCompanyCodeId,
				type : vm.type,
				finTechInfo : $stateParams.finTechInfo,
				data: {
					"accountList" : vm.accountList,
					"appName" : vm.appName,
					"companyName" : vm.companyName
				}
			};
			$koscomState.go("appTerms", params, {reload: "appTerms"});
		});
	};
	
	/*취소 버튼 이벤트*/
	vm.cancel = function() {
		$koscomState.back(-1);
		// $koscomState.go("main");
	};

	/*금투사 체크 이벤트*/
	vm.pubCompanyClick = function(index) {
		angular.forEach(vm.accountList[index].vtAccountResList, function(obj) {
			obj.checked = vm.accountList[index].checked;
		});
		vm.disabledCreateButton();
	};

	/*가상 계좌 체크 이벤트*/
	vm.vtAccountClick = function(parentIndex) {
		var allChecked = true;
		angular.forEach(vm.accountList[parentIndex].vtAccountResList, function(obj) {
			if(!obj.checked) {
				allChecked = false;
			}
		});
		vm.accountList[parentIndex].checked = allChecked;
		vm.disabledCreateButton();
		vm.isCompareObj();
	};

	vm.pubCompanyClass = function(vtAccountResList) {
		var checkedCount = 0;
		angular.forEach(vtAccountResList, function(obj) {
			if(obj.checked) {
				checkedCount++;
			}
		});
		if((checkedCount > 0) && checkedCount != vtAccountResList.length) return "all_not_check";
	};

	/*다음 버튼 disable 이벤트*/
	vm.disabledCreateButton = function() {
		vm.isCompareObj();
	};
	
	/*가상 계좌 추가 발급 이벤트*/
	vm.addAccountList=function(){
		$koscomState.go("accountAddStep01Pop",  {appParams:{"appId":$stateParams.appId, "appName":vm.appName, "companyName":vm.subCompanyName, "type":$stateParams.type}} );
	};

	/*앱투앱 삭제 버튼*/
	vm.appDelete = function(appName){
		var result = $koscomPopup.confirm("앱 삭제", appName+"앱 신청 정보를 삭제하시겠습니까? 삭제하시면 서비스를 이용하실 수 없습니다.");
		result.then(function(res){
			if(res==true){
				$koscomBridge.req("APP_01_100", {appId:$stateParams.appId}).then(function(res){
					//핀테크 APP 결과 리턴
					$koscomBridge.fintechError(4000, "FAIL").then(function(){});
					//로그아웃 호출
					$rootScope.appToAppLogout();
				}).catch(function(e){
					console.log("error");
				});
			}
		});
	};
	
	vm.goScope = function() {
		$koscomState.go("scope", {
			finTechInfo: $stateParams.finTechInfo
		});
	};
	
	
});
