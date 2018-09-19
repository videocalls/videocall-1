/**
 * Created by Yoojin Han on 2017-05-23.
 */

angular.module("oppf2").register.controller("Notice", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;
    
    vm.searchParam = {};
    vm.searchParam.page = 0;
    vm.searchParam.size = "10";
    vm.searchParam.searchType = "";
    vm.searchParam.searchKeyword = "";
    vm.showClearBtn = false;
    vm.refrashSearch = false;

    vm.searchTotalCount = 0;

    vm.commonCodeList = [];
    vm.noticeList = [];
    vm.noticeFixList = [];
    vm.noMoreItemsAvailable = false; // true 면 다음 넥스트 결과가 없음.

    // 초기화시 자꾸 두번 호출 방지
    vm.isScopeWatchEvent = true; // watch event 적용
    
    /* ******************
     * page event 
     * ****************** */
    $scope.$on('$ionicView.loaded', function(){
        console.log('SignKoreaListPop page Loaded');
        vm.searchParam.page = 0;
        console.log('notice page enter');
        vm.commonCode("G040");

        vm.loadMore();
    });
    // 최초 로딩
    $scope.$on('$ionicView.enter', function(){
    });
    
    // 검색 초기화
    vm.searchClear = function(){
        console.log("검색 초기화");
        vm.noticeList = [];
        vm.noticeFixList =[];
        vm.noMoreItemsAvailable = false;
        vm.searchParam.page = 0;
        vm.searchType = "G040001"; // 기존 전체
        vm.searchParam.searchType = "G040001";
        vm.showClearBtn = false;
        
        vm.isScopeWatchEvent = false;
        vm.searchParam.searchKeyword = ""; // value 변경시 $scope.$watch("vm.searchParam.searchKeyword" 호출됨
        
        vm.loadMore();
    };

    /** 스크롤 땡겼을때 조회 **/
    vm.searchPulling = function() {
        console.log('vm.searchPulling()');
        vm.search();
    }
    
    /** 조회 **/
    vm.search = function(){
        if(vm.refrashSearch) {
            vm.searchParam.page = 0;
            vm.noMoreItemsAvailable = false;
            vm.noticeList = [];
            vm.noticeFixList =[];
            vm.refrashSearch = false;
            
            vm.loadMore();
            
        } else {
            $scope.$broadcast('scroll.refreshComplete');
        }
   };
    
    
    vm.selectNoticeList = function(){
        // noMoreItemsAvailable =fasle 면 request
        if(!vm.noMoreItemsAvailable){
            
            vm.searchParam.page ++;
            $koscomBridge.req("APP_05_010", {'page':vm.searchParam.page, 'size':vm.searchParam.size
                                             ,'searchType':vm.searchParam.searchType, 'searchKeyword':vm.searchParam.searchKeyword}).then(function(res){
                vm.noticeList = vm.noticeList.concat(res.noticeList);
                vm.searchTotalCount = res.pageInfo.totalCount;
                
                if(res.noticeList.length < vm.searchParam.size){
                    vm.noMoreItemsAvailable = true;
                }
                
                $scope.$broadcast('scroll.infiniteScrollComplete');
            }).catch(function(e){
                console.log("error");
            });
        }
    };
    
    
    vm.commonCode = function(str){
        $koscomBridge.req("COM_01_010",{systemGrpCode : str}).then(function(res){
            console.log(res.commonCodeList);
            vm.commonCodeList = res.commonCodeList;
        }).catch(function(e){
            console.log("error");
        });
    };
        
//    vm.selectNoticeFixList = function(page, size, searchType, searchKeyword){
//        $koscomBridge.req("APP_05_010", {page: page, size: size, searchType: searchType, searchKeyword:searchKeyword}).then(function(res){
//            vm.noticeFixList = res.noticeFixList;
//            
//            vm.noticeList       = res.noticeList;
//            vm.searchTotalCount = res.pageInfo.totalCount;
////            vm.searchParam.page ++;
//            
//            $scope.$broadcast('scroll.infiniteScrollComplete');
//        }).catch(function(e){
//            console.log("error");
//        });
//    };
    
    vm.loadMore = function(){
        window.setTimeout(function(){
                vm.searchParam.page ++;
                $koscomBridge.req("APP_05_010", {page:vm.searchParam.page, size:vm.searchParam.size, searchType:vm.searchParam.searchType, searchKeyword:vm.searchParam.searchKeyword}).then(function(res){

                    if(res.pageInfo.page == 1 && !(vm.searchParam.searchKeyword) ){
                        vm.noticeFixList = res.noticeFixList;
                    }
                    
                    if( res.pageInfo.page == 1) {
                        vm.noticeList       = res.noticeList;
                        vm.searchTotalCount = res.pageInfo.totalCount;
                    } else {
                        vm.noticeList = vm.noticeList.concat(res.noticeList);
                    }
        
                    
                    if(res.noticeList.length < vm.searchParam.size){
                        vm.noMoreItemsAvailable = true;
                    }
                    
                    $scope.$broadcast('scroll.infiniteScrollComplete');
                }).catch(function(e){
                    console.log("error");
                });
        }, 0);
    };
    
    // vm.searchParam.searchType value 변경시 호출
    $scope.$watch("vm.searchParam.searchType", function(newVal, oldVal){
        if(newVal.length != oldVal.length){
            vm.refrashSearch = true;
        }
    });
 
    // vm.searchParam.searchKeyword value 변경시 호출
    $scope.$watch("vm.searchParam.searchKeyword", function(newVal, oldVal){
        if(newVal.length != oldVal.length){
            vm.refrashSearch = true;
        }
        
        if(!newVal){
            vm.showClearBtn = false;
        } else {
            vm.showClearBtn = true;
        }
    });
    
    // key down event
    vm.inputKeyDown = function(event){
      if(event.keyCode == 13) {
          $( ".btn_del" ).focus();
          console.log( $( ".btn_del" ).focus() );
      }
    };
    
    
});