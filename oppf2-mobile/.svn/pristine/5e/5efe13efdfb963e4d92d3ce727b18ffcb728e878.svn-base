/**
 * Created by unionman on 2017-05-25.
 */

angular.module("oppf2").register.controller("SignKoreaListPop", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;
    
    // 전자서명 원문 데이터
    if( !$scope.reqSignPlainText ) {
        $scope.reqSignPlainText = "기본 전사서명 plain text";
    } 
    
    vm.skCertList = [];
//    vm.testSkCertList = [
//    	{
//    	    "subjectName":"cn=김재훈-5101640,ou=HTS,ou=한국투자,ou=증권,o=SignKorea,c=KR",
//    	    "expiredImg":1,
//    	    "expiredTime":"만료일 : 2017.06.31",
//    	    "issuerName":"발급자 : SignKorea",
//    	    "policy":"구분 : 증권/보험용"
//            }
//    	,{
//    	    "subjectName":"cn=이선하,ou=HTS,ou=신한금융투자,ou=증권,o=SignKorea,c=KR",
//    	    "expiredImg":1,
//    	    "expiredTime":"만료일 : 2018.06.17",
//    	    "issuerName":"발급자 : SignKorea",
//    	    "policy":"구분 : 증권/보험용"
//            },
//    	{
//    	    "subjectName":"cn=홍길순-14166984,ou=HTS,ou=키움,ou=증권,o=SignKorea,c=KR",
//    	    "expiredImg":1,
//    	    "expiredTime":"만료일 : 2017.04.12",
//    	    "issuerName":"발급자 : SignKorea",
//    	    "policy":"구분 : 증권/보험용"
//            },
//    	{
//    	    "subjectName":"cn=최판광,ou=HTS,ou=키움,ou=증권,o=SignKorea,c=KR",
//    	    "expiredImg":1,
//    	    "expiredTime":"만료일 : 2017.05.31",
//    	    "issuerName":"발급자 : SignKorea",
//    	    "policy":"구분 : 증권/보험용"
//            }
//    ]; // 공인인증서 리스트 정보

    var gUserDn = "";
    var gUserName = "";
    var gSignData = "";
    var gPlainText = "";
    
    
    // 최초 로딩
//    $scope.$on('$ionicView.enter', function(){
		console.log('SignKoreaListPop page enter');
    	// 공인인증서 목록 native 요청
        $koscomBridge.getUserCertList().then(function(data){
            console.log(data);
            vm.skCertList = data;
//            vm.skCertList = vm.testSkCertList;
            // 만료기간 설정
            var currentDate = new Date();
            currentDate.setHours(0, 0, 0, 0);
            for(var i=0, item; item=vm.skCertList[i]; i++) {
                //item 처리
                var cData = item.expiredTime.split(":")[1];
                var certDate = new Date(cData);
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
//                vm.skCertList[i] = item;
            }
            vm.skCertList = vm.skCertList;
                
        });

    	
    	
//    });

	/* page event */
	$scope.$on('$ionicView.loaded', function(){
		console.log('SignKoreaListPop page Loaded');
	});
    	
    
    // modal close
	vm.close = function(str){
	    if(!str) {
	        str = {'result' : 0}; 
	    }
	    console.log(str);
		$koscomPopup.close("signKoreaSignKoreaListPop");
		$scope.singKoreaCertCallBack(str);
	};

    vm.alert = function(title, content){
        var c = $koscomPopup.alert(title, content);
    };
	
    
    // 공인인증서 선택시 보안키패드 오픈
	var cipherData = "";
	vm.signKoreakeypadOpen = function(userName, userDn, expiredType){
	    if( expiredType == -1 ) {
            vm.alert("" ,"만료된 인증서입니다.");
            return;
	    }
	    var plainText = $scope.reqSignPlainText;
	    $koscomBridge.keypadOpenForCertPwd("보안입력하세여.", 20, 
				$koscomBridge.PAD_TYPE_ALPHA_L, $koscomBridge.ENCRYPT_SEED, userDn).then(function(res){
			cipherData = res.cipherData;
			if( cipherData == null ) {
			    return;
			}
			
			console.log('공인인증서 로그인 처리 및 결과값 확인');
			vm.signKoreaSignedData(userName, userDn, cipherData, plainText);
			

		}).catch(function(){
			//debugger;
			console.log('보안 키보드 실패');
//            vm.close({result : 0});
		});
	};

	// 공인인증 전자서명값 유효성 체크
	vm.signKoreaSignedData = function(userName, userDn, cipherData, plainText){
        $koscomBridge.getSignedData(2, userDn, cipherData, plainText).then(function(data){
            // 공인인증서 이름 비교
            vm.signKoreaVerify(userName, userDn, data, plainText);
        }).catch(function(code){
            console.log(code);
            vm.close({result : 0});
        });
	}

    // 로그인 사용자 이름과 공인인증서 이름 비교
    // 로그인이 안되어있을 경우 패스~
//    vm.signKoreaVerifyNameCheck = function(userName, userDn, data, plainText){
//        var returnNum = 1;
//        $koscomBridge.getAppData("customerNameKor").then(function(res){
//            console.log(res);
//            console.log(!res);
//            
//            if(res && userDn.split(',')[0].indexOf(res) < 0 ) {
//                vm.alert("","공인인증서 성명이 일치하지 않습니다.");
//            } else {
//                // 공인인증 전자서명값 유효성 체크
//                vm.signKoreaVerify(userName, userDn, data, plainText);
//            }
//        }).catch(function(){
//            console.log("로그인 사용자 이름과 공인인증서 이름 비교 error");
//        });
//    }

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
              vm.alert("" ,"PC에 등록된 공인인증서가 단말기로 복사되었습니다.");
          } else {
              vm.alert("" ,"인증서 가져오기에 실패하였습니다.");
          }
       }).catch(function(e){
           vm.alert("" ,"인증서 가져오기에 실패하였습니다.\n다시 시도해주세요!");
       });
   };
   

});