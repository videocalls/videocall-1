var koscomApp = angular.module("oppf2", ["koscom"]);
document.addEventListener("deviceready", function(){
	var FINTECHINFO;
	
	//fintech 연동 리스너 등록. 초기 화면을 결정하기 위해 사용
	KOSCOM.fintechListener(function(_fintechinfo){
		FINTECHINFO = _fintechinfo;
	});
	
	require([
		"/resources/routes.js"
	], function(routes){
		"use strict";
		koscomApp.config(function($koscomRouterProvider, $ionicConfigProvider, $stateProvider, $locationProvider, $urlRouterProvider, $controllerProvider, $filterProvider, $compileProvider, $provide){
			//ios swipe back
			$ionicConfigProvider.views.swipeBackEnabled(false);
			
			//android
			if(ionic.Platform.isAndroid()){
				$ionicConfigProvider.scrolling.jsScrolling(false);
				if(ionic.Platform.version() < 6){
					$ionicConfigProvider.views.transition("none");
				}
			}

			$koscomRouterProvider.setViewRoot("/resources/views");
			$koscomRouterProvider.setCtrlRoot("/resources/controllers");
			$koscomRouterProvider.setStateProvider($stateProvider);
	
			koscomApp.register = {
				controller: $controllerProvider.register,
				filter: $filterProvider.register,
				directive: $compileProvider.directive,
				service: $provide.service
			};
			
			//반드시 로그인된 상태에서만 진입 가능하도록 하기 위한 전처리 기능
			function checkLoggedin($q, $timeout, $koscomState, $rootScope, $koscomPopup){
				var deferred = $q.defer();
				if($rootScope.isLogin === false){
					$timeout(function(){
						var confirm = $koscomPopup.confirm("로그인", "로그인이 필요합니다.<br/>로그인 하시겠습니까?").then(function(res){
							if(res){
								$koscomState.go('login', {
									data : true
								});
							}
							else{
								$rootScope.callbackState = "";
								deferred.reject();
							}
						});
					});
				}
				else{
					deferred.resolve();
				}
				return deferred.promise;
			}
			
			//반드시 로그인되지 않은 상태에서만 진입 가능하도록 하기 위한 전처리 기능
			function checkNoLoggedin($q, $timeout, $location, $rootScope, $koscomPopup){
				var deferred = $q.defer();
				if($rootScope.isLogin === false){
					deferred.resolve();
				}
				else{
					deferred.reject();
				}
				return deferred.promise;
			}

			var router = $koscomRouterProvider.router;
			for(var route in routes){
				if(routes[route].isLogin === true){
					routes[route].resolve = {
						checkLoggedin: checkLoggedin
					};
				}
	
				if(routes[route].isLogin === false){
					routes[route].resolve = {
						checkNoLoggedin: checkNoLoggedin
					};
				}
				router(route, routes[route]);
			}
		});
		
		koscomApp.run(function($q, $ionicPlatform, $rootScope, $ionicSideMenuDelegate, $state, $koscomState, $koscomBridge, $koscomPopup){
			$ionicPlatform.registerBackButtonAction(function(event) {
				if($state.current.name === "main" || $state.current.name === "oauth"){
					$koscomBridge.closeApp();
					return;
				}
				$koscomState.back();
			}, 100);

			//로그인 여부를 판단하는 속성
			$rootScope.isLogin = false;
			//app to app 연동인지를 판단하는 속성
			$rootScope.isAppToApp = false;
			//로그인후 redirect 할 페이지 정보
			$rootScope.callbackState = "";
			//로그인후 redirect 할 페이지 넘길 파라미터 정보
			$rootScope.callbackStataeParams = {};
			//ars인증 false-사용불가, true-사용가능
			$rootScope.isArs=false;
			// 메인에서 가동의서 체크 여부
			$rootScope.checkedCommonTerms = false;
			//side menu width
			$rootScope.sideMenuWidth = window.innerWidth - 60;
	
			$rootScope.menuToggle = function(){
				$ionicSideMenuDelegate.toggleRight();
			};
			
			$rootScope.pageMove = function(stateName, stateParam){
				$rootScope.callbackState = "";
				if($rootScope.isLogin === false){
					if(routes[stateName].isLogin){
						 $rootScope.callbackState = stateName;
					}
				}
			    
			    $ionicSideMenuDelegate.toggleRight();
				window.setTimeout(function(){
					$koscomState.go(stateName, stateParam);
				}, 300);
			};
			
			$rootScope.goPageNotMenu = function(stateName){
			    $koscomState.go(stateName);
			};
			
			$rootScope.$on("login", function(event){
				$rootScope.isLogin = true;
				$rootScope.checkedCommonTerms = true;
                $koscomBridge.getAppData("customerId").then(function(data){
					$rootScope.customerId = data;
                });

                $koscomBridge.getAppData("customerNameKor").then(function(data){
					$rootScope.customerNameKor = data;
                });

				$koscomBridge.getAppData("temporaryMemberYn").then(function(data){
					$rootScope.temporaryMemberYn = data;
				});
				
				$koscomBridge.getAppData("integrationAccountYn").then(function(data){
				    $rootScope.integrationAccountYn = data;
				});
			});
			
			$rootScope.$on("logout", function(){
			    console.log("app.modules.js logout!");
				$rootScope.isLogin = false;
				$rootScope.isAppToApp = false;
				$rootScope.checkedCommonTerms = false;
			});
			
			$rootScope.logout = function(){
				var logoutConfirm = $koscomPopup.confirm("로그아웃", "로그아웃 하시겠습니까?");

				logoutConfirm.then(function(res){
					if(res == true){
						$koscomBridge.req("MBR_02_130").then(function(req){
							$rootScope.$emit("logout");
							$koscomState.go("main");
							//아이디 초기화
							$koscomBridge.setAppData("customerId", null);
							$rootScope.customerId = null;
							//이름 초기화
							$koscomBridge.setAppData("customerNameKor", null);
							$rootScope.customerNameKor = null;
							//회원여부 초기화
							$koscomBridge.setAppData("temporaryMemberYn", null);
							$rootScope.temporaryMemberYn = null;
							//통합계좌조회여부 초기화
							$koscomBridge.setAppData("integrationAccountYn", null);
							$rootScope.integrationAccountYn = null;
						}).catch(function(e){
						});
					}
				})
			};

			$rootScope.appToAppLogout = function(){
				$koscomBridge.req("MBR_02_130").then(function(req){
					$rootScope.$emit("logout");
					$koscomState.go("main");
					//아이디 초기화
					$koscomBridge.setAppData("customerId", null);
					$rootScope.customerId = null;
					//이름 초기화
					$koscomBridge.setAppData("customerNameKor", null);
					$rootScope.customerNameKor = null;
					//회원여부 초기화
					$koscomBridge.setAppData("temporaryMemberYn", null);
					$rootScope.temporaryMemberYn = null;
					//통합계좌조회여부 초기화
					$koscomBridge.setAppData("integrationAccountYn", null);
					$rootScope.integrationAccountYn = null;
				}).catch(function(e){
					$rootScope.$emit("logout");
					$koscomState.go("main");
				});
			};
			
			$rootScope.floatingButton = true;
			$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){
				var stateName = toState.name;
				switch(stateName){
					case "login":
					case "termsService":
					case "termsPersonal":
					case "signKoreaInfo":
					case "signKoreaCert":
					case "memberCreate":
					case "memberTerms":
					case "findId":
					case "findPassword":
					case "modifyFindPassword":
					case "resultFindId":
					case "mobileCheckAuth":
					case "memberSuccess":
					case "oauth":
					case "scope":
					case "memberPrepare":
					case "nonMemberPrepare":
					case "nonMemberTerms":
					case "appAccountList":
					case "appTerms":
					case "commonTerms":
					case "appCreateComplete":
					case "accountAddStep01Pop":
					case "accountAddStep021Pop":
					case "accountAddStep022Pop":
						$rootScope.floatingButton = false;
						break;
					default:
						$rootScope.floatingButton = true;
							break;
				}
			});
			
			if(FINTECHINFO){
				if(FINTECHINFO.fn === "oauth"){
					$rootScope.isAppToApp = true;
					$koscomState.go("oauth", { finTechInfo: FINTECHINFO.data });
				}
				else if(FINTECHINFO.fn === "join"){
					$rootScope.callbackState = "memberPrepare";
					$koscomState.go("main");
				}
				else if(FINTECHINFO.fn === "appRequest"){
					$rootScope.callbackState = "appDetail";
					$rootScope.callbackStataeParams = FINTECHINFO.data;
					$koscomState.go("main");
				}
			}
			else{
				$rootScope.isAppToApp = false;
				$koscomState.go("main");
			}
			
			
			function fintech(finTechInfo){
			    $koscomBridge.setAppData("finTechInfo", finTechInfo.data);
				if(finTechInfo.fn === "oauth"){
					$rootScope.isAppToApp = true;
					$koscomState.go("oauth", { finTechInfo: finTechInfo.data });
				}
				else if(finTechInfo.fn === "join"){
					$koscomState.go("memberPrepare");
				}
				else if(finTechInfo.fn === "appRequest"){
					$koscomState.go("appDetail", finTechInfo.data);
				}
				
				$koscomBridge.fintech().then(fintech);
			};
	
			//fintech 연동 리스너 등록(매번 새로 등록)
			$koscomBridge.fintech().then(fintech);

			function notification(res){
				$koscomBridge.req("push", {pushMessageRegNo: res.data}).then(function(res){
					if(res.pushMessage.pushMessageType === "alert"){
						$koscomPopup.alert(res.pushMessage.pushMessageTitle, res.pushMessage.pushContents);
					} 
					else if(res.pushMessage.pushMessageType === "page"){
						//페이지 이동 구현되지 않음
						//$koscomPopup.alert(result.pushMessage.pushMessageTitle, result.pushMessage.pushContents);
					}
			    }).catch(function(e){
			        console.log("error");
			    });
				
				$koscomBridge.push().then(notification);

			};
			//푸시 알람 처리(매번 새로 등록)
			$koscomBridge.push().then(notification);
		});
		
		koscomApp.controller("AppMain", function($scope){
			//floating button
			$scope.$on("floating-menu:open", function(){
				document.getElementById("floatingDimm").className = "dimmed active";
			});
			//floating button
			$scope.$on("floating-menu:close", function(){
				document.getElementById("floatingDimm").className = "dimmed";
			});
		});
		
		koscomApp.filter("trustedHtml", function($sce){
	        return function(html){
	            return $sce.trustAsHtml(html)
	        }
	     });
		
		angular.bootstrap(document, ["oppf2"]);
	});
}, false);