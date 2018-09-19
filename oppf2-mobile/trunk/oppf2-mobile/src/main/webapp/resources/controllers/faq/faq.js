/**
 * Created by Yoojin Han on 2017-05-25.
 */

angular.module("oppf2").register.controller("Faq", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;
    
    vm.searchParam = {};
    vm.searchParam.page = 0;
    vm.searchParam.size = "10";
    vm.searchParam.searchType = "G040001"; // 기존 전체
    vm.searchParam.searchKeyword = "";
    /** 검색 히스토리 : 이전 검색 조건과 동일할 경우 조회 안함 **/
    vm.searchTypeHist    = ""; // 기존 전체
    vm.searchKeywordHist = "";
    
    vm.showClearBtn = false;
    vm.searchTotalCount = 0;
    
    vm.commonCodeList = [];
    vm.faqList = [];
    vm.noMoreItemsAvailable = false; //true 면 다음 넥스트 결과가 없음.
    vm.click = true; //true 면 다음 넥스트 결과가 없음.

    /* ******************
     * page event 
     * ****************** */
    $scope.$on('$ionicView.loaded', function(){
        console.log('SignKoreaListPop page Loaded');
        vm.commonCode("G040");
    });
    // 최초 로딩
    $scope.$on('$ionicView.enter', function(){
        console.log('faq page enter');
    });
    
    /** 스크롤 땡겼을때 조회 **/
    vm.searchPulling = function() {
        console.log('vm.searchPulling()');
        vm.search();
    }
    
    /** 조회 **/
    vm.search = function(){
        if( !(vm.searchTypeHist == vm.searchParam.searchType && vm.searchKeywordHist == vm.searchParam.searchKeyword) ) {
            vm.searchParam.page = 0;
            vm.noMoreItemsAvailable = false;
            vm.faqList = [];
            vm.selectFaqList();
        } else {
            $scope.$broadcast('scroll.refreshComplete');
        }
   };
    
    vm.selectFaqList = function(){
        if(!vm.noMoreItemsAvailable){
            vm.searchParam.page ++;
            $koscomBridge.req("APP_05_020", {page:vm.searchParam.page, size:vm.searchParam.size, searchType:vm.searchParam.searchType, searchKeyword:vm.searchParam.searchKeyword}).then(function(res){
                if( vm.searchParam.page == 1) {
                    vm.faqList = res.faqList;
                    vm.searchTotalCount = res.pageInfo.resultCount;
                } else {
                    vm.faqList = vm.faqList.concat(res.faqList);
                }
                
                if(vm.faqList.length === res.pageInfo.totalCount){
                    vm.noMoreItemsAvailable = true;
                }
                vm.searchTypeHist    = vm.searchParam.searchType; 
                vm.searchKeywordHist = vm.searchParam.searchKeyword
                $scope.$broadcast('scroll.infiniteScrollComplete');
            }).catch(function(e){
                console.log("error");
            });
        }
    };
    
    
    vm.commonCode = function(str){
        $koscomBridge.req("COM_01_010",{systemGrpCode : str}).then(function(res){
            vm.commonCodeList = res.commonCodeList;
        }).catch(function(e){
            console.log("error");
        });
    };
        
    
    vm.alert = function(title, content){
        var c = $koscomPopup.alert(title, "상세 조회 구현예정");
    };

    // 검색 초기화
    vm.searchClear = function(){
        console.log("검색 초기화");
        vm.faqList = [];
        vm.searchParam.page = 0;
        vm.searchParam.size = "10";
        vm.searchParam.searchType = "G040001"; // 기존 전체
        vm.searchType = "G040001"; // 기존 전체

        vm.noMoreItemsAvailable = false;

//        if(vm.searchParam.searchKeyword != "" ) {
            vm.searchParam.searchKeyword = ""; // value 변경시 $scope.$watch("vm.searchParam.searchKeyword" 호출됨
//            return;
//        } else {
            vm.selectFaqList();
//        }
    };
    
    // value 변경시 호출
    $scope.$watch("vm.searchParam.searchKeyword", function(newVal, oldVal){
        if(!newVal){
            vm.showClearBtn = false;
        } else {
            vm.showClearBtn = true;
        }
//		vm.faqList = [];
//        vm.searchParam.page = 0;
//        vm.selectFaqList(false, vm.searchParam.page, vm.searchParam.size, vm.searchParam.searchType, newVal);
    });
    
    // key down event
    vm.inputKeyDown = function(event){
      if(event.keyCode == 13) {
          $( ".btn_del" ).focus();
          console.log( $( ".btn_del" ).focus() );
      }
    };
    
});