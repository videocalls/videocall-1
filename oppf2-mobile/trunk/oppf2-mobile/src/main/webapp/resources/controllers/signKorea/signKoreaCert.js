/**
 * Created by unionman on 2017-05-25.
 */

angular.module("oppf2").register.controller("SignKoreaCert", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup, $koscomLoading){
    var vm = this;
    var plainText = null;
    vm.showImportButton = false;
    
    vm.skCertList = [];
//    vm.testSkCertList = [
//    	{
//    	    "subjectName":"cn=김재훈-5101640,ou=HTS,ou=한국투자,ou=증권,o=SignKorea,c=KR",
//    	    "expiredImg":1,
//    	    "expiredTime":"2017.06.31",
//    	    "issuerName":"SignKorea",
//    	    "policy":"증권/보험용"
//            }
//    	,{
//    	    "subjectName":"cn=이선하,ou=HTS,ou=신한금융투자,ou=증권,o=SignKorea,c=KR",
//    	    "expiredImg":1,
//    	    "expiredTime":"2018.06.17",
//    	    "issuerName":"SignKorea",
//    	    "policy":"증권/보험용"
//            },
//    	{
//    	    "subjectName":"cn=홍길순-14166984,ou=HTS,ou=키움,ou=증권,o=SignKorea,c=KR",
//    	    "expiredImg":1,
//    	    "expiredTime":"2017.04.12",
//    	    "issuerName":"SignKorea",
//    	    "policy":"증권/보험용"
//            },
//    	{
//    	    "subjectName":"cn=최판광,ou=HTS,ou=키움,ou=증권,o=SignKorea,c=KR",
//    	    "expiredImg":1,
//    	    "expiredTime":"2017.05.31",
//    	    "issuerName":"SignKorea",
//    	    "policy":"증권/보험용"
//            }
//    ]; // 공인인증서 리스트 정보

    var gUserDn = "";
    var gUserName = "";
    var gSignData = "";
    var gPlainText = "";
    
    
    // 최초 로딩
    $scope.$on('$ionicView.enter', function(){
        console.log('SignKoreaListPop page enter');
        console.log($stateParams);
        console.log($stateParams.signPlainText);
        
        // 전자서명 원문 데이터
        if( !$stateParams.signPlainText ) {
            plainText = "기본 전사서명 plain text";
        } else {
            plainText = $stateParams.signPlainText;
        }

        $koscomBridge.setAppData("signKoreaCertResultData", {}).then(function(){
//          $koscomBridge.removeAppData("signKoreaCertResultData").then(function(){
            console.log('clear signKoreaCertResultData');
        }).catch(function(){
            console.log('clear signKoreaCertResultData 실패!!!');
        });

        // 가져오기로 바로 진입
//        if( $stateParams.pageType == 'importPage' ) {
//            vm.importPage();
//            angular.element(document.querySelector("#tab-a")).removeClass("active");
//            angular.element(document.querySelector("#tab_body-a")).hide();
//            angular.element(document.querySelector("#tab-b")).addClass("active");
//            angular.element(document.querySelector("#tab_body-b")).show();
//        } else {
            vm.getMobileCertList();
//        }
    	
    });

    vm.getMobileCertList = function(){
        vm.showImportButton = false;
        console.log("vm.getMobileCertList");
        // 공인인증서 목록 native 요청
        $koscomLoading.show();
        $koscomBridge.getUserCertList().then(function(data){
            vm.skCertList = data;
//            vm.skCertList = vm.testSkCertList;
            // 만료기간 설정
            var currentDate = new Date();
            currentDate.setHours(0, 0, 0, 0);
            for(var i=0, item; item=vm.skCertList[i]; i++) {
                //item 처리
                var cData = item.expiredTime.split('.');
                var certDate = new Date(cData[0], (cData[1]-1), cData[2]);
                var diff = parseInt((certDate-currentDate)/(24*60*60*1000));
                if( diff > 30 ) {
                    item.expiredType = 1; // 정상
                    item.expiredClass = 'authentication_view'; // 정상
                } else if( diff >= 0 ) {
                    item.expiredType = 0; // 정상(만기일 30일전)
                    item.expiredClass = 'authentication_view red_alarm'; // 정상
                } else if( diff < 0 ) {
                    item.expiredType = -1; // 비정상(만료)
                    item.expiredClass = 'authentication_view disabled'; // 정상
                }
    //            vm.skCertList[i] = item;
            }
            vm.skCertList = vm.skCertList;
            $koscomLoading.hide();
        }).catch(function(e){
        	$koscomLoading.hide();
        });
    };

    
    
	/* page event */
	$scope.$on('$ionicView.loaded', function(){
		console.log('SignKoreaListPop page Loaded');
	});
    	
    
    // modal close
	vm.close = function(str){
	    if(!str) {
	        str = {'result' : 0}; 
	    }
        $koscomBridge.setAppData("signKoreaCertResultData", str).then(function(){
            console.log("setAppData success");
            console.log(str);
            $koscomState.back(-1);
        }).catch(function(){
            console.log("setAppData error");
        });

	};

    vm.alert = function(title, content){
        var c = $koscomPopup.alert(title, content);
    };
	
    
    // 공인인증서 선택시 보안키패드 오픈
	var cipherData = "";
	vm.signKoreakeypadOpen = function(userName, userDn, expiredType){
	    
	    if( !$stateParams.certClick ) {
	        return;
	    }
	    
	    if( expiredType == -1 ) {
            vm.alert("" ,"만료된 인증서입니다.");
            return;
	    }

	    var currentPasswordFailCnt = 0;

	    // 로그인 시도일 경우 계정잠금 카운트 활성화
	    if( $stateParams.isfailUpCount ) {
        	    // 비밀번호 실패횟수 조회
                $koscomBridge.req("MBR_02_360",{customerVerifyDn: userDn, passwordFail: false }).then(function(res){
                    currentPasswordFailCnt = res.passwordFailCnt;
                    if( res.lockFailCount == res.passwordFailCnt ){
                        var c = $koscomPopup.alert("", "비밀번호 입력 10회 오류로 인해 계정이 정지되며 로그아웃됩니다.");
                        c.then(function(){
                            $rootScope.logout();
                        });
                    } else {
                        vm.callKeypadOpenForCertPwd(userName, userDn, cipherData, plainText, currentPasswordFailCnt, parseInt(res.lockFailCount));
                    }
                }).catch(function(e){
                    vm.callKeypadOpenForCertPwd(userName, userDn, cipherData, plainText, currentPasswordFailCnt, parseInt(res.lockFailCount));
                });
	    } else { // 로그인 시도 외 경우
            vm.callKeypadOpenForCertPwd(userName, userDn, cipherData, plainText, 0, 0);
	    }
	    
	    
	};

	// 공인인증서 삭제
   vm.removeCert = function(index){
       vm.delCertInfo = vm.skCertList[index];
       console.log(vm.delCertInfo.subjectName);
        $koscomBridge.keypadOpenForCertPwd("비밀번호를 입력하세요.", 20, 
                $koscomBridge.PAD_TYPE_ALPHA_U,  vm.delCertInfo.subjectName, 10, 0).then(function(res){
            cipherData = res.cipherData;
            // 인증서 삭제
            $koscomBridge.deleteCert(vm.delCertInfo.subjectName, cipherData).then(function(res){
                vm.alert('','공인인증서가 삭제되었습니다.');
                vm.getMobileCertList();
            }).catch(function(){
                console.log('공인인증서 삭제 실패');
                vm.alert('','공인인증서 삭제 실패');
            });
            
        }).catch(function(){
            console.log('보안 키보드 실패');
            vm.alert('','보안 키보드 실패');
        });
   };

	// 키패드 종료 후 패스워드 실패 횟수 업데이트 성공시 0 
	vm.resultKeypadOpneEvent = function(userDn, isPasswordFail, currentTryCount) {
        // ** 실패 카운트 업데이트
        $koscomBridge.req("MBR_02_360",{customerVerifyDn: userDn, passwordFail: isPasswordFail, currentTryCount : currentTryCount }).then(function(res){
        }).catch(function(e){
        });
	};
	
    // 보안 키패드 오픈
    vm.callKeypadOpenForCertPwd = function(userName, userDn, cipherData, plainText, currentPasswordFailCnt, lockFailCount){
        // 보안 키패드 오픈
        $koscomBridge.keypadOpenForCertPwd("비밀번호를 입력하세요.", 20, 
                $koscomBridge.PAD_TYPE_ALPHA_U,  userDn, lockFailCount, currentPasswordFailCnt).then(function(res){
            cipherData = res.cipherData;
//            alert(angular.toJson(res));
                    if( !cipherData ) {
                        if( $stateParams.isfailUpCount ) { // 로그인 시도일 경우 계정잠금 카운트 활성화
                                if( res.currentTryCount > 0 ) { // 실패횟수가 0회 이상일 경우 노출
                                    vm.resultKeypadOpneEvent(userDn, true, res.currentTryCount);
                                    vm.alert("" ,"입력하신 비밀번호가 올바르지 않습니다. 비밀번호 입력 "+lockFailCount+"회 오류 시 계정이 정지됩니다.(현재 "+res.currentTryCount+"회 오류).");
                                }
                        } // 로그인 시도일 경우 계정잠금 카운트 활성화
                    } else { // 성공시 0
                        vm.resultKeypadOpneEvent(userDn, true, 0);
                        vm.signKoreaSignedData(userName, userDn, cipherData, plainText);
                    }
            
            console.log('공인인증서 로그인 처리 및 결과값 확인');
        }).catch(function(){
            console.log('보안 키보드 실패');
        });
    };

        
	// 공인인증 전자서명값 유효성 체크
	vm.signKoreaSignedData = function(userName, userDn, cipherData, plainText){
        $koscomBridge.getSignedData(2, userDn, cipherData, plainText).then(function(data){
            // 공인인증서 이름 비교
            vm.signKoreaVerify(userName, userDn, data, plainText);
        }).catch(function(code){
            vm.alert("" ,"공인인증 전자서명값 유효성 체크 실패.");
            console.log(code);
            vm.close({result : 0});
        });
	}

    // 공인인증 전자서명값 유효성 체크
	vm.signKoreaVerify = function(userName, userDn, signedData, plainText){

	    var customerId = "";
	    $koscomBridge.req("SKC_01_030",{signData : signedData, customerDn : userDn}).then(function(res){

            if ( res.result == 0 ) {
                if(res.loginCustomerName && userDn.split(',')[0].indexOf(res.loginCustomerName) < 0 ) {
                    vm.alert("","공인인증서 성명이 일치하지 않습니다.");
                } else {
                    console.log("유효성 체크 성공");
                    customerId = res.loginCustomerId;
                    vm.close(
                            {'result' : 1,'signData' : signedData,'customerDn' : userDn, 
                                'customerId' : customerId, 'isEqualCookieCertDn' : res.isEqualCookieCertDn
                            }
                
                    );
                }
            } else {
                vm.alert("" ,"전자서명이 유효하지 않습니다.");
            }
        }).catch(function(e){
            console.log("유효성 체크 error");
            vm.alert("" ,"전자서명이 유효하지 않습니다.");
        });

	};
	
	
	// 가져오기 화면
	// 가져오기 화면
	// 가져오기 화면
	vm.certImportCode = {}; // {certImportCode1: "1122", certImportCode2: "3344", certImportCode3: "5566"}
	
	// 인증서 가져오기 코드값(12자리) 가져오기
	vm.importPage = function () {
        vm.showImportButton = true;
        console.log("가져오기 화면");
        $koscomBridge.simpleCertImport1().then(function(res){
           console.log(res); 
           if(res.code == 2000) {
               console.log("가져오기 연결 성공!!"); 
               vm.certImportCode.certImportCode1 = res.num1;
               vm.certImportCode.certImportCode2 = res.num2;
               vm.certImportCode.certImportCode3 = res.num3;
               console.log(vm.certImportCode);
           } else {
               vm.alert("" ,"인증서 서버와 연결되지 않았습니다.\n다시 시도해주세요!");
           }
        }).catch(function(e){
            console.log("유효성 체크 error");
            vm.alert("" ,"인증서 서버와 연결되지 않았습니다.\n다시 시도해주세요!");
        });
	};
	
   vm.importCertMobile = function () {
       console.log("공인인증서 가져오기 진행!");
//       if(vm.certImportCode.certImportCode1.length == 0 ) {
       if(Object.keys(vm.certImportCode).length == 0 ) {
           vm.alert("" ,"인증서 서버와 연결되지 않았습니다.\n다시 시도해주세요!");
       } else {
           // 인증서 다운로드 호출
           vm.importCertDown();
       }
   };

   // 인증서 가져오기 다운로드
   vm.importCertDown = function () {
       $koscomBridge.simpleCertImport2().then(function(res){
          console.log(res); 
          if(res.code == 2000) {
              $koscomPopup.alert("", "PC에 등록된 공인인증서가 단말기로 복사되었습니다.").then(function(){
                  $("#tab-a").addClass("active");
                  $("#tab-b").removeClass("active");
                  $("#tab_body-a").show();
                  $("#tab_body-b").hide();
                  vm.getMobileCertList();
               });
          } else {
              vm.alert("" ,"인증서 가져오기에 실패하였습니다.");
          }
       }).catch(function(e){
    	   if(e == 5004) {
         	  vm.alert("" ,"증권 전용 또는 범용 인증서만 사용 가능 합니다.");              
           } else {
        	  vm.alert("" ,"인증서 가져오기에 실패하였습니다.\n다시 시도해주세요!");
           }
       });
   };
   

});