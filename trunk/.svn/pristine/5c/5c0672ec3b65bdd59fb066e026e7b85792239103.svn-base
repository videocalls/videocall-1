/**
 * Created by lee on 2017-05-30.
 */

angular.module("oppf2").register.controller("IntAcnt", function($rootScope, $scope, $koscomState, $stateParams, $koscomBridge, $filter, $ionicSlideBoxDelegate, $koscomPopup) {
    var vm = this;
    vm.currentSlideIndex = 0;
    //관심종목 그룹 select option 모델
    vm.interestGroupOption;
    vm.linkInfo = {};
    vm.acctList = {};
    //잔고현황 div
    vm.balanceShow = false;
    vm.balanceSearchShow = true;
    vm.balanceFailShow = false;
    //거래내역 div
    vm.transactionList = [];
    vm.transactionSearchShow = true;
    vm.transactionFailShow = false;
    //포트폴리오 div
    vm.portfolioShow = false;
    vm.portfolioSearchShow = true;
    vm.portfolioFailShow = false;
    //관심종목 div
    vm.interestShow = false;
    vm.interestSearchShow = true;
    vm.interestFailShow = false;
    
    vm.searchAcctList = [];
    vm.searchType = 'balance';
    vm.searchForm = {
        companyCodeId:'0',
        vtAccountNo:'0',
        searchDateFrom:'',
        searchDateTo:''
    };
    var nowDate = new Date();
    vm.searchDateFrom = new Date(nowDate.setDate(nowDate.getDate() - 7));
    vm.searchDateTo = new Date();
    

    //검색 구분 설정
    vm.searchTab = function(){
        switch(vm.currentSlideIndex){
            case 0: vm.searchType = 'balance';
                break;
            case 1: vm.searchType = 'transaction';
                break;
            case 2: vm.searchType = 'portfolio';
                break;
            case 3: vm.searchType = 'interest';
                break;
        }
    };

    //검색
    vm.search = function(){
        switch(vm.searchType){
            case 'balance': vm.getBalance();
                break;
            case 'transaction':
                vm.searchForm.searchDateFrom = $filter('date')(vm.searchDateFrom, 'yyyy-MM-dd');
                vm.searchForm.searchDateTo = $filter('date')(vm.searchDateTo, 'yyyy-MM-dd');
                vm.getTransaction();
                break;
            case 'portfolio': vm.getPortfolio();
                break;
            case 'interest': vm.getInterest();
                break;
        }
    };

    //잔고조회
    vm.getBalance = function(){
        $koscomBridge.req("INT_02_010", vm.searchForm).then(function(res){
            vm.balanceSearchShow = false;
            vm.balance = res.data;
            vm.balance.summary = vm.balance.totalResult != null ? vm.balance.totalResult.summary : [];
            //잔고조회 검색 영역
            if(vm.balance.successCount > 0){
                vm.balanceShow = true;
            }else{
                vm.balanceShow = false;
            }
            //잔고조회 실패 영역
            if(vm.balance.failCount > 0){
                vm.balanceFailShow = true;
            }else{
                vm.balanceFailShow = false;
            }
            //주식리스트
            vm.balanceEquityList = [];
            angular.forEach(vm.balance.resultList, function(row) {
                angular.forEach(row.balanceList.balance.equityList, function(data) {
                    //잔고조회 주식 리스트
                    vm.balanceEquityList.push(data);
                });
            });
            //펀드리스트
            vm.balanceFundList = [];
            angular.forEach(vm.balance.resultList, function(row) {
                angular.forEach(row.balanceList.balance.fundList, function(data) {
                    //잔고조회 펀드 리스트
                    vm.balanceFundList.push(data);
                });
            });
            //기타리스트
            vm.balanceEtcList = [];
            angular.forEach(vm.balance.resultList, function(row) {
                angular.forEach(row.balanceList.balance.etcList, function(data) {
                    //잔고조회 기타 리스트
                    vm.balanceEtcList.push(data);
                });
            });

        }).catch(function(e){
            console.log(e);
        });
    };

    //거래내역
    vm.getTransaction = function(){
        $koscomBridge.req("INT_02_020", vm.searchForm).then(function(res){
            vm.transactionSearchShow = false;
            vm.transaction = res.data.resultList;
            vm.transactionData = res.data;
            //실패 영역
            if(vm.transactionData.failCount > 0){
                vm.transactionFailShow = true;
            }else{
                vm.transactionFailShow = false;
            }
            //거래내역 리스트
            vm.transactionList = [];
            var pattern = /(\d{4})(\d{2})(\d{2})/;
            angular.forEach(vm.transaction, function(row) {
                angular.forEach(row.transList.transaction, function(data) {
                    vm.transactionList.push(data);
                    var converterDate = data.transDate;
                    data.transDate = new Date(converterDate.replace(pattern, '$1-$2-$3'));
                });
            });
        }).catch(function(e){
            console.log(e);
        });
    };

    //포트폴리오
    vm.getPortfolio = function(){
        $koscomBridge.req("INT_02_030", vm.searchForm).then(function(res){
            vm.portfolioSearchShow = false;
            vm.portfolio = res.data.resultList;
            vm.portfolioData = res.data;
            if(vm.portfolioData.successCount > 0){
                vm.portfolioShow = true;
            }else{
                vm.portfolioShow = false;
            }
            //실패 영역
            if(vm.portfolioData.failCount > 0){
                vm.portfolioFailShow = true;
            }else{
                vm.portfolioFailShow = false;
            }
            //주식리스트
            vm.portfolioEquityList = [];
            angular.forEach(vm.portfolio, function(row) {
                angular.forEach(row.portfolioList.portfolio.equityList, function(data) {
                    //포트폴리오 주식 리스트
                    vm.portfolioEquityList.push(data);
                });
            });
            //펀드리스트
            vm.portfolioFundList = [];
            var pattern = /(\d{4})(\d{2})(\d{2})/;
            angular.forEach(vm.portfolio, function(row) {
                angular.forEach(row.portfolioList.portfolio.fundList, function(data) {
                    //포트폴리오 펀드 리스트
                    vm.portfolioFundList.push(data);
                    var converterDate = data.maturity;
                    data.maturity = new Date(converterDate.replace(pattern, '$1-$2-$3'));
                });
            });
            //기타리스트
            vm.portfolioEtcList = [];
            angular.forEach(vm.portfolio, function(row) {
                angular.forEach(row.portfolioList.portfolio.etcList, function(data) {
                    //포트폴리오 기타 리스트
                    vm.portfolioEtcList.push(data);
                });
            });
        }).catch(function(e){
            console.log(e);
        });
    };

    //관심종목
    vm.getInterest = function(){
        $koscomBridge.req("INT_02_040", vm.searchForm).then(function(res){
            vm.interestSearchShow = false;
            vm.interest = res.data.resultList;
            vm.interestData = res.data;
            if(vm.interestData.successCount > 0){
                vm.interestShow = true;
            }else{
                vm.interestShow = false;
            }
            //실패 영역
            if(vm.interestData.failCount > 0){
                vm.interestFailShow = true;
            }else{
                vm.interestFailShow = false;
            }
            //관심종목 그룹 select 리스트
            vm.interestGroupList = [];
            //관심종목 그룹
            vm.interestGroup = [];
            //관심종목 카운트
            vm.interestGroupCount = 0;
            vm.index = 0;
            angular.forEach(vm.interest, function(row) {
                angular.forEach(row.interestSymbolListResponseBody.groupList.group, function(data) {
                    //관심종목 그룹 리스트
                    vm.interestGroupList.push(data);
                    //첫번째 리스트에서 관심종목 그룹 리스트 매핑
                    if(vm.index === 0){
                        vm.interestGroup = data.isinName;
                        vm.interestGroupCount = vm.interestGroup.length;
                        vm.interestGroupOption = data;
                    }
                    vm.index++;
                });
            });
        }).catch(function(e){
            console.log(e);
        });
    };

    //관심종목 그룹 검색
    vm.searchInterestGroup = function(){
        vm.interestGroup = vm.interestGroupOption.isinName;
        vm.interestGroupCount = vm.interestGroup.length;
    };

    vm.parseJson = function(json){
        return JSON.parse(json);
    };
        
    //공통검색 조건
    vm.searchAccountList = function(){
        //전체 금투사 검색
        if('0' == vm.searchForm.companyCodeId){
            vm.allAccountList();
        }else{
            vm.companyAccountList(vm.searchForm.companyCodeId);
        }
    };

    //가상계좌 전체 리스트
    vm.allAccountList = function(){
        vm.searchAcctList = [];
        vm.searchForm.vtAccountNo = '0';
        angular.forEach(vm.acctList, function(row) {
            angular.forEach(row.accountList, function(data) {
                //전체 계좌리스트
                vm.searchAcctList.push(data);
            });
        });
    };

    //가상계좌 금투사 리스트
    vm.companyAccountList = function(companyId){
        vm.searchAcctList = [];
        vm.searchForm.vtAccountNo = '0';
        angular.forEach(vm.acctList, function(row) {
            if(row.companyCodeId == companyId){
                angular.forEach(row.accountList, function(data) {
                    vm.searchAcctList.push(data);
                });
                return;
            }
        });
    };
    
    //app 신청링크
    vm.linkApp = function(){
        $koscomState.go("appDetail", {appId: vm.linkInfo.appId});
    };

    //탭 클릭 슬라이드
    vm.slideChange = function(index){
        $ionicSlideBoxDelegate.slide(index);
        vm.currentSlideIndex = index;
        vm.searchTab();
    };

    //롤링 슬라이드
    vm.rollingSlideChange = function(){
        vm.currentSlideIndex = $ionicSlideBoxDelegate.currentIndex();
        vm.searchTab();
    }

    //페이지 진입시 통합계좌 연결 정보 조회
    $scope.$on('$ionicView.enter', function(){
        $koscomBridge.req("INT_01_010").then(function(res){
            vm.linkInfo = res.data;
            vm.acctList = res.resultList;
            vm.allAccountList();
        }).catch(function(e){
            if(e.code == '9401'){
                //통합계좌 app 미신청
                $koscomPopup.alert("", "코스콤 통합 계좌 조회 서비스를 신청하셔야 합니다.").then(function(){
                    $koscomState.go("appAccountList", {appId: e.message});
                });
            }else if(e.code == '9402' || e.code == '9403'){
                //token not found || token not use
                //권한허용 페이지 이동
                $koscomState.go("scope");
            }else{
                //기타오류
                console.log(e);
            }
        });
    });
});