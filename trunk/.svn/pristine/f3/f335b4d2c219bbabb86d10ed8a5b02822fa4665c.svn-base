/**
 * Created by lee on 2017-05-12.
 */

angular.module("oppf2").register.controller("SignKoreaInfo", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;

    
    /* page event */
    $scope.$on('$ionicView.loaded', function(){
        console.log('SignKoreaInfo page Loaded');
    });

    // 최초 로딩
    vm.customerCertDn = false;

    $scope.$on('$ionicView.enter', function(){
        // 로그인 사용자 공인인증서 등록 여부 조회
        vm.selectUserCertInfo();
        
        $koscomBridge.getAppData("signKoreaCertResultData").then(function(res){
            var updateUserCertInfoData = {};
            if(res.result == 1) {
              // 처리내용 구현
              updateUserCertInfoData.customerDn = res.customerDn
              // 공인인증서 정보 업데이트
              vm.updateUserCertInfo(updateUserCertInfoData);
            }

            $koscomBridge.setAppData("signKoreaCertResultData", {});
            
        }).catch(function(){
            console.log("공인인증서 결과값 확인 실패!!!!!");
        });
    });
    
    vm.selectUserCertInfo = function(data){
        // 로그인 사용자 공인인증서 등록 여부 조회
        $koscomBridge.req("SKC_01_040").then(function(res){
            console.log(res);
            vm.customerCertDn = res.customerCertDn;
        }).catch(function(e){
            console.log("error");
        });
    };

    vm.modifyUserCertificatie = function(){
        $koscomState.go("signKoreaCert", {signPlainText : "테스트 plain Text"} );
    };


    // 공인인증서 업데이트
    vm.updateUserCertInfo = function(data){
        $koscomBridge.req("SKC_01_050", data).then(function(res){
            console.log(res);
            $koscomPopup.alert("", "인증서 정보가 등록되었습니다.").then(function(){});
            vm.selectUserCertInfo();
        }).catch(function(e){
            console.log("error");
            $koscomPopup.alert("", "인증서 정보가 등록되지 않았습니다.");
        });
    };
    
    
    
    
    
    
    
    
    
    vm.setAppData = function(str){
		// 테스트로 샘플페이지로 이동
		$koscomBridge.setAppData("SKVerifyReq", { data: 99,certInfo : str, returnUrl : 'sample'}).then(function(){
			console.log("setAppData success");
		}).catch(function(){
			console.log("setAppData error");
		});
	};
    

	
	// 인증서 선택
	vm.getUserCertificatie = function(str){
	    var c = $koscomPopup.alert("제목", str + " 인증서 선택!");
	    vm.setAppData(str);
	};
	
	
	// 인증서 선택
	vm.getAppDataTest = function(str){
		$koscomBridge.getAppData("SKVerifyReq").then(function(res){
			console.log("getAppData success", res);
			$koscomPopup.alert("내용 returnUrl", res.returnUrl)
		}).catch(function(){
			console.log("getAppData error");
		});
	};
	
	// 완료 후 반환
	vm.complteUserCertificatie = function(str){

		$koscomBridge.setAppData("SKVerifyRes", { data: 'certInfoData'}).then(function(){
			console.log("setAppData SKVerifyRes success");
		}).catch(function(){
			console.log("setAppData SKVerifyRes error");
		});

		$koscomBridge.getAppData("SKVerifyRes").then(function(res){
			console.log("getAppData SKVerifyRes success", res);
			console.log( res.data)
		}).catch(function(){
			console.log("getAppData SKVerifyRes error");
		});

		$koscomBridge.getAppData("SKVerifyReq").then(function(res){
			console.log("getAppData success", res);
			$koscomState.go(res.returnUrl);

		}).catch(function(){
			console.log("getAppData error");
		});
	};
	
	
});