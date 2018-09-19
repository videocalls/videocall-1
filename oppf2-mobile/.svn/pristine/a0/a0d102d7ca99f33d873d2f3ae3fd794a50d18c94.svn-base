(function (){
	"use strict";
	var app = angular.module("koscomAngular", ["koscom"]);
	app.config(function($ionicConfigProvider, $koscomRouterProvider, $stateProvider, $urlRouterProvider, $controllerProvider, $filterProvider, $compileProvider, $provide){

		if(ionic.Platform.isAndroid()){
			$ionicConfigProvider.scrolling.jsScrolling(false);
			$ionicConfigProvider.views.transition("none");
		}
		
		$koscomRouterProvider.setViewRoot("/resources/views");
		$koscomRouterProvider.setStateProvider($stateProvider);

		app.register = {
			controller: $controllerProvider.register,
			filter: $filterProvider.register,
			directive: $compileProvider.directive,
			service: $provide.service
		};

		var router = $koscomRouterProvider.router;
		router("main", {
			url: "/",
			path: "/main",
			name: "main",
			views: "app-view"
		});

		router("login", {
			url: "/login",
			path: "/member",
			name: "login",
			views: "app-view"
		});
		router("login_02", {
			url: "/login_02",
			path: "/member",
			name: "login_02",
			views: "app-view"
		});
		router("login_03", {
			url: "/login_03",
			path: "/member",
			name: "login_03",
			views: "app-view"
		});
		router("mem_info", {
			url: "/mem_info",
			path: "/member",
			name: "mem_info",
			views: "app-view"
		});
		router("joining", {
			url: "/joining",
			path: "/member",
			name: "joining",
			views: "app-view"
		});
		router("joining_guest", {
			url: "/joining_guest",
			path: "/member",
			name: "joining_guest",
			views: "app-view"
		});
		router("joining_02", {
			url: "/joining_02",
			path: "/member",
			name: "joining_02",
			views: "app-view"
		});
		router("joining_03", {
			url: "/joining_03",
			path: "/member",
			name: "joining_03",
			views: "app-view"
		});
		router("phone", {
			url: "/phone",
			path: "/member",
			name: "phone",
			views: "app-view"
		});
		router("phone_tip", {
			url: "/phone_tip",
			path: "/member",
			name: "phone_tip",
			views: "app-view"
		});
		router("login_before", {
			url: "/login_before",
			path: "/member",
			name: "login_before",
			views: "app-view"
		});
		router("popup", {
			url: "/popup",
			path: "/common",
			name: "popup",
			views: "app-view"
		});
		router("popup_re", {
			url: "/popup_re",
			path: "/common",
			name: "popup_re",
			views: "app-view"
		});
		router("popup_use", {
			url: "/popup_use",
			path: "/common",
			name: "popup_use",
			views: "app-view"
		});
		router("popup_finance", {
			url: "/popup_finance",
			path: "/common",
			name: "popup_finance",
			views: "app-view"
		});
		router("account_list", {
			url: "/account_list",
			path: "/appuse",
			name: "account_list",
			views: "app-view"
		});
		router("account_list_no", {
			url: "/account_list_no",
			path: "/appuse",
			name: "account_list_no",
			views: "app-view"
		});
		router("appuse_complete", {
			url: "/appuse_complete",
			path: "/appuse",
			name: "appuse_complete",
			views: "app-view"
		});
		router("popup_acc_issue", {
			url: "/popup_acc_issue",
			path: "/appuse",
			name: "popup_acc_issue",
			views: "app-view"
		});
		router("popup_acc_issue_02", {
			url: "/popup_acc_issue_02",
			path: "/appuse",
			name: "popup_acc_issue_02",
			views: "app-view"
		});
		router("all_acc_info", {
			url: "/all_acc_info",
			path: "/appuse",
			name: "all_acc_info",
			views: "app-view"
		});
		router("app_detail", {
			url: "/app_detail",
			path: "/appuse",
			name: "app_detail",
			views: "app-view"
		});
		router("acc_adm", {
			url: "/acc_adm",
			path: "/appuse",
			name: "acc_adm",
			views: "app-view"
		});
		router("acc_adm_list", {
			url: "/acc_adm_list",
			path: "/appuse",
			name: "acc_adm_list",
			views: "app-view"
		});
		router("app_list", {
			url: "/app_list",
			path: "/appuse",
			name: "app_list",
			views: "app-view"
		});
		router("ars", {
			url: "/ars",
			path: "/appuse",
			name: "ars",
			views: "app-view"
		});
		router("id_search", {
			url: "/id_search",
			path: "/member",
			name: "id_search",
			views: "app-view"
		});
		router("id_search_complete", {
			url: "/id_search_complete",
			path: "/member",
			name: "id_search_complete",
			views: "app-view"
		});
		router("pw_search", {
			url: "/pw_search",
			path: "/member",
			name: "pw_search",
			views: "app-view"
		});
		router("pw_input", {
			url: "/pw_input",
			path: "/member",
			name: "pw_input",
			views: "app-view"
		});
		router("oauth", {
			url: "/oauth",
			path: "/member",
			name: "oauth",
			views: "app-view"
		});
		router("mem_info_edit", {
			url: "/mem_info_edit",
			path: "/set",
			name: "mem_info_edit",
			views: "app-view"
		});
		router("mem_info_edit_02", {
			url: "/mem_info_edit_02",
			path: "/set",
			name: "mem_info_edit_02",
			views: "app-view"
		});
		router("set_list", {
			url: "/set_list",
			path: "/set",
			name: "set_list",
			views: "app-view"
		});
		router("pw_change", {
			url: "/pw_change",
			path: "/set",
			name: "pw_change",
			views: "app-view"
		});
		router("pw", {
			url: "/pw",
			path: "/set",
			name: "pw",
			views: "app-view"
		});
		router("leave", {
			url: "/leave",
			path: "/set",
			name: "leave",
			views: "app-view"
		});
		router("signature", {
			url: "/signature",
			path: "/set",
			name: "signature",
			views: "app-view"
		});
		router("first_page", {
			url: "/first_page",
			path: "/etc",
			name: "first_page",
			views: "app-view"
		});
		router("notice", {
			url: "/notice",
			path: "/etc",
			name: "notice",
			views: "app-view"
		});
		router("way", {
			url: "/way",
			path: "/etc",
			name: "way",
			views: "app-view"
		});
		router("biz_agree", {
			url: "/biz_agree",
			path: "/etc",
			name: "biz_agree",
			views: "app-view"
		});
		router("pw_re", {
			url: "/pw_re",
			path: "/etc",
			name: "pw_re",
			views: "app-view"
		});
		router("service", {
			url: "/service",
			path: "/etc",
			name: "service",
			views: "app-view"
		});
		router("notice_sw", {
			url: "/notice_sw",
			path: "/etc",
			name: "notice_sw",
			views: "app-view"
		});
		router("personal", {
			url: "/personal",
			path: "/etc",
			name: "personal",
			views: "app-view"
		});
		router("fin", {
			url: "/fin",
			path: "/main",
			name: "fin",
			views: "app-view"
		});
		router("biz_info", {
			url: "/biz_info",
			path: "/main",
			name: "biz_info",
			views: "app-view"
		});

		router("sample1", {
			url: "/sample1",
			path: "/sample",
			name: "sample1",
			views: "app-view"
		});

		router("sample2", {
			url: "/sample2",
			path: "/sample",
			name: "sample2",
			views: "app-view"
		});
		
		router("sample3", {
			url: "/sample3",
			path: "/sample",
			name: "sample3",
			views: "app-view"
		});
		router("grid", {
			url: "/grid",
			path: "/sample",
			name: "grid",
			views: "app-view"
		});

		$urlRouterProvider.otherwise("/");
	});

	app.run(function($rootScope, $state, $ionicSideMenuDelegate){
		$rootScope.isLogin = false;
		$rootScope.sideMenuWidth = window.innerWidth - 70;
		$rootScope.menuToggle = function(){
			$ionicSideMenuDelegate.toggleRight();
		};
		$rootScope.pageMove = function(stateName){
			$state.go(stateName);
		};

		$rootScope.$on("login", function(){
			$rootScope.isLogin = true;
		});
	});

	angular.bootstrap(document, ["koscomAngular"]);
})();
