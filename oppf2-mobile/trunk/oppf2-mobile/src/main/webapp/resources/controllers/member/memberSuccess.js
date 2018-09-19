/**
 * Created by SH.Lee on 2017. 5. 29..
 */

angular.module("oppf2").register.controller("MemberSuccess", function($scope, $koscomState, $stateParams, $koscomBridge, $rootScope){

    var vm = this;

    vm.customerRegNo = '';
    vm.emailSendType = '';        // 이메일 완료
    vm.receiverName = '';   //이름
    vm.receiverEmail = '';    //이메일
    vm.senderKind = '';               //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
    vm.receiverKind     = '';        //수신자 종류 - G018001:일반사용자, G018002:기업사용자
    vm.receiverId = '';          //수신자 ID
    // vm.uriContextPath = ''; //what the?

    $scope.$on('$ionicView.enter', function(){

        /**
         * 회원가입 정보 보여주기
         * */
        if($stateParams.customerNameKor != null){
            vm.customerNameKor = $stateParams.customerNameKor;
            vm.customerId = $stateParams.customerId;
            vm.customerEmail = $stateParams.customerEmail;

            vm.customerRegNo = $stateParams.customerRegNo;
            vm.emailSendType = "G016003";        // 이메일 완료
            vm.receiverName = vm.customerNameKor;   //이름
            vm.receiverEmail = vm.customerEmail;    //이메일
            vm.senderKind = "G017003"               //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
            vm.receiverKind     = "G018001";        //수신자 종류 - G018001:일반사용자, G018002:기업사용자
            vm.receiverId = vm.customerRegNo;          //수신자 ID
            // vm.uriContextPath = httpContextPath; //what the?

            /* 메일보내기 API */
            $koscomBridge.req("MBR_03_100",{
                customerRegNo : vm.customerRegNo,
                emailSendType : vm.emailSendType,
                receiverName : vm.receiverName,
                receiverName2 : vm.receiverName,
                receiverEmail : vm.receiverEmail,
                receiverEmail2 : vm.receiverEmail,
                senderKind : vm.senderKind,
                receiverKind : vm.receiverKind,
                receiverId : vm.receiverId,
                receiverId2 : vm.customerId
            }).then(function(res){

            }).catch(function(e){

            })
        }

    });
    //메인으로
    vm.goMain = function(){
        $rootScope.callbackState = "";
        $koscomState.go("main"); 
    };

    //앱사용신청 페이지
    vm.goApps = function(){
        //로그인후 리다이렉트 처리
        $rootScope.callbackState = "appRequestList";
        $koscomState.go("appRequestList");
    };




});