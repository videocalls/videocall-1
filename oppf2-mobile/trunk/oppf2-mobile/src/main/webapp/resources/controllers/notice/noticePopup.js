/**
 * Created by Yoonjin.Han on 2017-05-22.
 */

angular.module("oppf2").register.controller("NoticePopup", function($ionicSlideBoxDelegate, $scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;
    
    vm.noticeFixList = [];
    
    // 종료 이벤트
    vm.noticePopupEventClose = function(){
        console.log("팝업 종료");
        //팝업을 3일동안 보지 않기로 했으니 현재 시간을 구한후
        //3일을 더해서 로컬스토리지에 넣는다.
        var displayDate = new Date(); 
        displayDate.setDate(displayDate.getDate() + 1);
                      //date 객체를 저장할때 자동으로 밀리세컨드 시간으로 저장된다.
        $koscomBridge.setLocalStorage("displayDateNotice",displayDate);
        $koscomPopup.close("noticeNoticePopup");
    }


    // 공지사항 조회
    vm.selectNoticeFixList = function(){
            $koscomBridge.req("APP_05_010", {page: 1, size: 10, searchType: '', searchKeyword:'',noticePopYn:'Y'}).then(function(res){
                vm.noticeFixList = res.noticePopList;
                console.log( vm.noticeFixList);
                
                // ionslide 적용
                window.setTimeout(function(){
                	if($ionicSlideBoxDelegate._instances.length){
                		$ionicSlideBoxDelegate._instances[$ionicSlideBoxDelegate._instances.length-1].update();
                	}
                }, 500);
                if( vm.noticeFixList.length == 0 ) {
                    vm.noticePopupEventClose();
                }
                
            }).catch(function(e){
                console.log("error");
            });
    };


    
    // 최초 로딩
//  $scope.$on('$ionicView.enter', function(){
//      console.log('notice page enter');
      // 공지사항 조회
      vm.selectNoticeFixList();
//  });


});