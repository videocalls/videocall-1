/**
 * Created by SH.Lee on 2017. 5. 25..
 */
angular.module("oppf2").register.controller("MobileCheckAuth", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup, $filter){
    var vm = this;

    /* 사용변수 */
    vm.sresSeq = null;
    vm.birthDay = '';   //생년월일(날짜
    vm.nationality = "Y";       //내국인여부
    vm.customerSex = "M";       //성별
    vm.sname = "";                   //이름
    vm.smobileCo = "";               //통신사코드(SKT, KT, LG)
    vm.smobileNo = "";               //핸드폰번호
    vm.sresSeq = "";                 //인증코드 전송 seq
    vm.sauthNo = "";                 //인증번호
    vm.mobileTermsList = "";         //약관동의 리스트
    vm.mobileSelectTermsList = "";         //통신사 코드 선택 시 약관동의 리스트

    vm.authCodeReqButtonName = "인증번호 전송";   //인증코드전송버튼 이름
    vm.authCodeReqButtonCode = true;   //인증코드전송버튼 이름 코드(true : 인증번호 전송, false : 인증번호 재전송)
    vm.authCodeReqBoolean = true;             //인증코드 전송여부(전송 시 : false) => 전송정상처리되면 인증코드 값 보임
    vm.codeBtnDisabledYn = true;              //버튼 disabled 값



    $scope.$on('$ionicView.enter', function(){
        /* 사용변수 */
        vm.sresSeq = null;
        vm.birthDay = '';   //생년월일(날짜
        vm.nationality = "Y";       //내국인여부
        vm.customerSex = "M";       //성별
        vm.sname = "";                   //이름
        vm.smobileCo = "";               //통신사코드(SKT, KT, LG)
        vm.smobileNo = "";               //핸드폰번호
        vm.sresSeq = "";                 //인증코드 전송 seq
        vm.sauthNo = "";                 //인증번호
        vm.mobileTermsList = "";         //약관동의 리스트
        vm.mobileSelectTermsList = "";         //통신사 코드 선택 시 약관동의 리스트

        vm.authCodeReqButtonName = "인증번호 전송";   //인증코드전송버튼 이름
        vm.authCodeReqButtonCode = true;   //인증코드전송버튼 이름 코드(true : 인증번호 전송, false : 인증번호 재전송)
        vm.authCodeReqBoolean = true;             //인증코드 전송여부(전송 시 : false) => 전송정상처리되면 인증코드 값 보임
        vm.codeBtnDisabledYn = true;              //버튼 disabled 값

    });
    
    /*
    function setTimer(){
    	vm.limitTime = new Date();
        vm.limitTime.setHours(0);
        vm.limitTime.setMinutes(3);
        vm.limitTime.setSeconds(0);
    }
    setTimer();
     */

    vm.allTerms = false;





    /* 에러메세지 */
    // vm.nameErrorMessage;        //이름 에러메세지
    // vm.birthDayErrorMessge;     //생년월일 에러메세지
    // vm.companyErrorMessage;     //통신사 선택 에러메세지
    // vm.phoneErrorMessage;       //핸드폰번호 에러메세지
    // vm.authCodeErrorMessage;    //인증코드 에러메세지
    // vm.agreeErrorMessage;       //전체동의 체크 에러 메세지
    //


    /**
     * 통신사 공통코드 가져오기
     * */
    vm.smobileCoList = [
        {name:'선택', data:''},
        {name:'SKT', data:'1'},
        {name:'KT', data:'2'},
        {name:'LGU+', data:'3'},
        {name:'SK 알뜰폰', data:'5'},
        {name:'KT 알뜰폰', data:'6'},
        {name:'LG 알뜰폰', data:'7'}
    ];

    /**
     * 인증코드 버튼 이름 변경 & 전송버튼 disabled
     * */
    vm.authCodeBtnNameChange = function(){
        if(!vm.authCodeReqButtonCode){          //전송을 이미 했을때 (= 인증코드재전송 일 때) 정보 수정시
            vm.authCodeReqBoolean = true;      //입력칸 비활성화
            vm.sauthNo = '';                    //입력값 지우기
            vm.authCodeReqButtonName = "인증번호 전송"; //버튼값 바꾸기
        }

        if( vm.birthDay != null && vm.birthDay != ''
            && vm.nationality != null && vm.nationality != ''
            && vm.customerSex != null && vm.customerSex != ''
            && vm.smobileCo != null && vm.smobileCo != ''
            && vm.smobileNo != null && vm.smobileNo != ''
            && vm.sname != null && vm.sname != '') {
            vm.codeBtnDisabledYn = false;
        }else{
            vm.codeBtnDisabledYn = true;
        }
    }



    /**
     * 인증코드 전송 ( => 인증코드 재 전송 )
     * */
    vm.authCodeReq = function(){

        vm.authCodeReqButtonName = "인증번호 재전송";
        vm.authCodeReqButtonCode = false;
        vm.authCodeReqBoolean = false;

        $koscomBridge.req("MBR_02_230",{
            birthDay: vm.birthDay,
            nationality: vm.nationality,
            sex: vm.customerSex,
            smobileCo: vm.smobileCo,
            smobileNo: vm.smobileNo,
            sname: vm.sname
        }).then(function(res){
            console.log("SUCCESS");
            vm.sresSeq = res.niceRes.sresSeq;
            if(res.niceRes.sresSeq == "" || res.niceRes.sresSeq == null){
                vm.authCodeReqButtonName = "인증번호 전송";
                vm.authCodeReqButtonCode = true;
                vm.authCodeReqBoolean = true;
                $koscomPopup.alert("", "입력하신 정보가 일치하지 않습니다. 다시 확인해주세요.");
            }else{
                $koscomPopup.alert("", "인증번호가 전송되었습니다.");
                vm.authCodeReqBoolean = false;
                vm.authCodeReqButtonCode = false;
            }

            //setTimer();
/*
            var timer = window.setInterval(function(){
            	vm.limitTime.setSeconds(vm.limitTime.getSeconds() - 1);
            	if(vm.limitTime.getMinutes() === 59){
            		//3분 타임아웃
            		window.clearInterval(timer);
                    vm.authCodeReqBoolean = true;      //입력칸 비활성화
                    vm.sauthNo = '';                    //입력값 지우기
                    vm.authCodeReqButtonName = "인증번호 전송"; //버튼값 바꾸기
                    $scope.$apply();
            	}
            	else{
            		$scope.$apply();
            	}
            }, 1000);
*/
        }).catch(function(e){
            vm.authCodeReqButtonName = "인증번호 전송";
            vm.authCodeReqButtonCode = true;
            vm.authCodeReqBoolean = true;
            //유효하지 않은 정보
            if(e.code == "9130"){
                $koscomPopup.alert("", e.message);
            }//14세 미만 정보
            else if(e.code == "9127"){
                $koscomPopup.alert("", e.message);
            }
            //유효하지않은 인증정보
        })
    }

    /**
     * 통신사 약관 가져오기
     * */
    vm.companyTerms = function(){
        $koscomBridge.req("MEM_01_050").then(function(res){
            console.log("SUCCESS");
            vm.mobileTermsList = res;
        }).catch(function(e){
            console.log("error");
        });
    }

    /**
     * 통신사 SelectBox 선택 시 약관동의 가져오기(통신사별) event
     * */
    vm.companySelect = function(){
        vm.mobileCodeTouch = true;

        if(vm.smobileCo == ""){
        	/* vm.mobileTermsList => null로 만들기 */
            vm.mobileSelectTermsList = [];
        }else if(vm.smobileCo == "1" || vm.smobileCo == "5"){           //sk
            vm.mobileSelectTermsList = vm.mobileTermsList.skTermsList;
        }else if(vm.smobileCo == "2" || vm.smobileCo == "6"){           //kt
            vm.mobileSelectTermsList = vm.mobileTermsList.ktTermsList;
        }else if(vm.smobileCo == "3" || vm.smobileCo == "7"){           //lg
            vm.mobileSelectTermsList = vm.mobileTermsList.lgTermsList;
        }
    }


    /**
     * 인증하기 완료 -> 회원정보 입력화면
     * */
    vm.submit = function(){
        $koscomBridge.req("MBR_02_240",{
            // birthDay: $filter("date")(vm.birthDay, "yyyyMMdd"),
            birthDay: vm.birthDay,
            nationality: vm.nationality,
            sex: vm.customerSex,
            smobileCo: vm.smobileCo,
            smobileNo: vm.smobileNo,
            sname: vm.sname,
            sauthNo: vm.sauthNo,        //인증번호
            sresSeq: vm.sresSeq         //코드전송시 생긴 seq
        }).then(function(res){
            console.log("SUCCESS");
            //실패
            if(res.niceRes.responseCI == "") {
                $koscomPopup.alert("", "본인인증이 완료되지 않았습니다. 다시 시도해주세요.");

            }else{  //성공
                // $koscomPopup.close();
                /* 인증 완료 후 부모 페이지에 완료된(Ci, phone, ...) 값 보내기 */
                $koscomPopup.close("phoneAuth");
                $scope.mobileCheckAuth(res);
            }


        }).catch(function(e){
            console.log("error");
        	$koscomPopup.alert("", "본인인증이 완료되지 않았습니다. 다시 시도해주세요.");
        });
    }

    vm.close = function(){
        $koscomPopup.close("phoneAuth");

    }
    
    /**
     * 전체 체크 Valid
    * */
    vm.allCheck = function(){
        if(vm.mobileSelectTermsList){
	        angular.forEach(vm.mobileSelectTermsList, function(data) {
	            data.checked = vm.allTerms;
	        });
        } else {
        	$koscomPopup.alert("", "통신사를 선택하세요.");
        	vm.allTerms ="";
        }
    };
    
    /**
     *  본인인증 체크 valid
     * */
    vm.checkValid = function(){
        var allCheck = true;
        vm.allCheckTouch = true;
        //약관동의 체크
        angular.forEach(vm.mobileSelectTermsList, function(data) {
            if(!data.checked){
                allCheck = false;
                return;
            }
        });
        vm.allTerms = allCheck;
    };


    vm.companyTerms();



});