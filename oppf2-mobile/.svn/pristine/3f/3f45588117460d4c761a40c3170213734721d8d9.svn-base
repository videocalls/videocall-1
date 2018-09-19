/*! 
 * KOSCOM. koscom common
 * Copyright 2017, 2017 
 * 
 * project: KOSCOM
 * version: 0.0.1
 * contact: 
 * homepage: 
 * Date: 2017-04-13 11:08 
 */
(function(factory){
	var Module = factory();
	if ( typeof module === "object" && typeof module.exports === "object" ) {
		module.exports = Module;
	}
	else{
		window.koscom = Module;
	}
})(function(){
var koscom = angular.module("koscom", ["ionic", "ion-floating-menu", "ngMessages"]);
$provider = koscom.provider;
$service = koscom.service;
$controller = koscom.controller;
$filter = koscom.filter;
$directive = koscom.directive;
$factory = koscom.factory;

$provider("$koscomRouter", function(){
	var viewRoot = "", ctrlRoot = "", $stateProvider = null;

	this.$get = function(){
		return this;
	};

	this.setViewRoot = function(vr){
		viewRoot = vr;
	};

	this.getViewRoot = function(){
		return viewRoot;
	};

	this.setCtrlRoot = function(cr){
		ctrlRoot = cr;
	};

	this.getCtrlRoot = function(){
		return ctrlRoot;
	};

	this.setStateProvider = function(sp){
		$stateProvider = sp;
	};

	var resolveDependencies = function ($q, $rootScope, dependencies, $koscomPopup) {
		var defer = $q.defer();
		require(dependencies, function () {
			defer.resolve();
			$rootScope.$apply()
		}, function(){
			$koscomPopup.alert("", "네트워크에 접속할 수 없습니다.네트워크 연결상태를 확인해주세요.");
		});

		return defer.promise;
	};

	this.router = function(stateName, config) {
		var routeDef = {}, baseFileName = "";

		if(config.name){
			baseFileName = config.name.charAt(0).toLowerCase() + config.name.substr(1);
			baseFileName = (config.path || "") + "/" + baseFileName;
		}

		if(config.url){
			routeDef.url = config.url;
			routeDef.cache = true;
		}

		if(config.params){
			routeDef.params = config.params;
		}

		if(config.hasOwnProperty("abstract")){
			routeDef.abstract = config.abstract;
		}

		if(config.views){
			routeDef.views = { };
			if(baseFileName){
				routeDef.views[config.views] = {
					templateUrl: viewRoot + baseFileName + ".html",
					controller: config.name.charAt(0).toUpperCase() + config.name.substr(1),
					controllerAs: "vm"
				};
			}

			if(config.resolve){
				routeDef.views[config.views].resolve = config.resolve;
			}
			else{
				routeDef.views[config.views].resolve = {};
			}
			if(baseFileName){
				routeDef.views[config.views].resolve.move = function($koscomBridge, $koscomPopup){
					$koscomBridge.clearPageData();
					$koscomPopup.close();
				};
				routeDef.views[config.views].resolve.load = function ($q, $rootScope, $koscomPopup) {
					var dependencies = [ctrlRoot + baseFileName + ".js"];
					return resolveDependencies($q, $rootScope, dependencies, $koscomPopup);
				};
			}
		}
		else{
			if(baseFileName){
				routeDef.templateUrl = viewRoot + baseFileName + ".html";
				routeDef.controller = config.name.charAt(0).toUpperCase() + config.name.substr(1);
				routeDef.controllerAs = "vm";
			}

			if(config.resolve){
				routeDef.resolve = config.resolve;
			}
			else{
				routeDef.resolve = {};
			}
			if(baseFileName){
				routeDef.resolve.move = function($koscomBridge, $koscomPopup){
					$koscomBridge.clearPageData();
					$koscomPopup.close();
				};
				routeDef.resolve.load = function ($q, $rootScope, $koscomPopup) {
					var dependencies = [ctrlRoot + baseFileName + ".js"];
					return resolveDependencies($q, $rootScope, dependencies, $koscomPopup);
				}
			}
		}

		$stateProvider.state(stateName, routeDef);
	}
});

$service("$koscomState", function($state, $ionicHistory, $ionicViewService, $koscomBridge){
	this.go = function(stateName, params, options){
		params = params || {};
		options = options || {};
		
		$state.go(stateName, params, options);
	};
	
	this.back = function(index){
		$ionicHistory.goBack(index);
	};
	
	this.href = function(url){
		window.location.href = url;
	};
	
	this.clearHistory = function(){
		$ionicHistory.clearHistory();
		$ionicViewService.clearHistory();
		
		$koscomBridge.clearHistory();
	};
    
});

$service("$koscomBridge", function($rootScope, $http, $q, $koscomPopup, $state, $rootScope, $koscomLoading){
	
    //로컬스토리지에 특정 객체 저장
	this.setLocalStorage = function(key, id){
		window.localStorage.setItem(key, id);
	};
	
    //로컬스토리지에서 특정 객체 가져오기
	this.getLocalStorage = function(key){
		return window.localStorage.getItem(key);
	};
    //로컬스토리지에 특정 객체 삭제
    this.removeLocalStorage = function(name){
        window.localStorage.removeItem(name);
    };
	
	this.setAppData = function(name, value){
		return $q(function(resolve, reject){
			KOSCOM.datacache.setAppData(name, value, function(result){
				if(result[name]){
					resolve();
				}
				else{
					reject();	
				}
			});
		});
	};

	this.getAppData = function(name){
		return $q(function(resolve, reject){
			KOSCOM.datacache.getAppData(name, function(result){
				if(result[name]){
					resolve(result[name]);
				}
				else{
					reject();
				}
			});
		});
	};

	this.setPageData = function(name, value){
		return $q(function(resolve, reject){
			KOSCOM.datacache.setPageData(name, value, function(result){
				if(result[name]){
					resolve();
				}
				else{
					reject();	
				}
			});
		});
	};

	this.getPageData = function(name){
		return $q(function(resolve, reject){
			KOSCOM.datacache.getPageData(name, function(result){
				if(result[name]){
					resolve(result[name]);
				}
				else{
					reject();
				}
			});
		});
	};

	this.removeAppData = function(name){
		return $q(function(resolve, reject){
			KOSCOM.datacache.removeAppData(name, function(result){
				resolve();
			});
		});
	};

	this.removePageData = function(name){
		return $q(function(resolve, reject){
			KOSCOM.datacache.removePageData(name, function(result){
				resolve();
			});
		});
	};

	this.clearPageData = function(){
		return $q(function(resolve, reject){
			KOSCOM.datacache.clearPageData();
			resolve();
		});
	};

	this.req = function(service, data, header, isLoading){
		if(isLoading === undefined || isLoading === true){
			$koscomLoading.show();
		}

		data = data || null;
		header = header || null;
		return $q(function(resolve, reject){
			KOSCOM.api.doRequest(service, header, data, 10000, function(data){
				data = JSON.parse(data);
				var code = parseInt(data.header.code);
				if(code === 2000){
					resolve(data.body);
				}
				else{
					if(code < 9100){
						$koscomPopup.alert("경고", data.header.message);
						reject(data.header);
					}
					else if(code < 9999){
						reject(data.header);
					}
					else if(code === 9999){
						//로그아웃 된 상태
						$koscomPopup.alert("알림", data.header.message).then(function(){
							$rootScope.$emit("logout");
							$state.go("main", {
								loginTimeout: true
							});
						});
					}
				}
				
				window.setTimeout(function(){
					$koscomLoading.hide();
				}, 1000);
			}, function(code, message){
				$koscomLoading.hide();
				
				$koscomPopup.alert("경고", "서버와의 통신에 실패했습니다.");
				reject({
					code: 9000,
					message: "서버와의 통신에 실패했습니다."
				});
			});
		});
	};

	this.longTimeoutReq = function(service, data, header, isLoading){
		if(isLoading === undefined || isLoading === true){
			$koscomLoading.show();
		}
		
		data = data || null;
		header = header || null;
		return $q(function(resolve, reject){
			KOSCOM.api.doRequest(service, header, data, 120000, function(data){
				data = JSON.parse(data);
				var code = parseInt(data.header.code);
				if(code === 2000){
					resolve(data.body);
				}
				else{
					if(code < 9100){
						$koscomPopup.alert("경고", data.header.message);
						reject(data.header);
					}
					else if(code < 9999){
						reject(data.header);
					}
					else if(code === 9999){
						//로그아웃 된 상태
						$koscomPopup.alert("알림", data.header.message).then(function(){
							$rootScope.$emit("logout");
							$state.go("main", {
								loginTimeout: true
							});
						});
					}
				}
				
				window.setTimeout(function(){
					$koscomLoading.hide();
				}, 1000);
			}, function(code, message){
				$koscomLoading.hide();

				$koscomPopup.alert("경고", "서버와의 통신에 실패했습니다.");
				reject({
					code: 9000,
					message: "서버와의 통신에 실패했습니다."
				});
			});
		});
	};

	this.login = function(){
		return $q(function(resolve, reject){
			window.setTimeout(function(){
				resolve(true);
			}, 2000);
		});
	};
	
	
	this.fintech = function(){
		return $q(function(resolve, reject){
			KOSCOM.fintechListener(function(req){
				var finTechInfo;
				if(typeof req === "string"){
					finTechInfo = JSON.parse(req);
				}
				else{
					finTechInfo = req;
				}
				
				resolve(finTechInfo);
			});
		});
	};
	
	this.fintechSuccess = function(code, message, data){
		$rootScope.isAppToApp = false;
		data = data || { };
		return $q(function(resolve, reject){
			KOSCOM.fintechSuccess(code, message, data, function(){
				resolve();
			});
		});
	};
	
	this.fintechError = function(code, message){
		$rootScope.isAppToApp = false;
		return $q(function(resolve, reject){
			KOSCOM.fintechFail(code, message, function(){
				resolve();
			});
		});
	};
	
	this.clearHistory = function(){
		return $q(function(resolve, reject){
			KOSCOM.clearHistory(function(){
				resolve();
			});
		});
	};
	
	//푸시 메시지 정의 후 구현 해야함
	this.push = function(){
		return $q(function(resolve, reject){
			KOSCOM.pushListener(function(res){
				resolve(res);
			}, 500);
		});
	};
	
	this.closeSplash = function(delayMilisecond){
		delayMilisecond = delayMilisecond || 0;
		
		return $q(function(resolve, reject){
			KOSCOM.closeSplash(delayMilisecond);
			resolve();
		});
	};
	
	
	this.PAD_TYPE_ALPHA_U = "alpha_u";   /* 영문 보안 패드 타입 - 영문 */
	this.PAD_TYPE_ALPHA_L = "alpha_l";   /* 영문 보안 패드 타입 - 영문 */
	this.PAD_TYPE_NUMBER = "number";   /* 영문 보안 패드 타입 - 영문 */

	this.ENCRYPT_RSA = "rsa";   /* 보안키패드방식이 RSA, default */
	this.ENCRYPT_SEED = "seed";   /* 보안키패드방식이 SEED */
	
	
	/**
     * 보안 입력 키패드를 연다. 
     * @param title string, 보안 패드 타이틀
     * @param maxLength int, 최대 글자 수
     * @param padType string, 보안 패드 타입
     * <pre>
     *   - "alpha_u" : 영문
     *   - "alpha_l" : 영문
     *   - "number" : 숫자
     * </pre>
     * @param encryptMethod  string, 보안키패드가 RSA인지 SEED 방식인지 구분하기 위함. default는 RSA 방식. RMS계좌에서만 SEED 방식 사용
     * <pre>
     *   - "seed" : 보안키패드방식이 SEED
     *   - "rsa" : 보안키패드방식이 RSA
     * </pre>
     * @param callback function(json-object), 보안 키패드 입력값을 수신하기 위한 callback 
     * <pre>
     * - code : int, CODE_SUCCESS:2000, CODE_CANCEL:-1 취소
     * - encryptMethod : string, "rsa", "seed"
     * - oriInputLength : int, 입력 글자 수
     * - cipherData : string, 입력 문자 암호화 데이터 
     * </pre>
     */
	this.keypadOpen = function(title, maxLength, padType, encryptMethod){
		return $q(function(resolve, reject){
			KOSCOM.securepad.open(title, maxLength, padType, encryptMethod, function(res){
				if(res.oriInputLength < 1){
					reject();
					return;
				}
				if(res.code !== 2000){
					reject();
					return;
				}
				else{
					resolve(res);
				}
			});
		});
	};
	
	/**
     * 인증서 비번 입력용 보안 키패드를 연다.
     * @param title string, 보안 패드 타이틀
     * @param maxLength int, 최대 글자 수
     * @param padType string, 보안 패드 타입
     * <pre>
     *   - "alpha_u" : 영문
     *   - "alpha_l" : 영문
     *   - "number" : 숫자
     * </pre>
     * @param checkCertDn string, 인증서 비빌번호 입력용(SEED)으로 사용할 경우 인증서 Subject 전달하면 입력 완료 시 인증서 비빌번호를 체크한다.
     *                            default null
     * @param maxCheckCount int, 인증서 비번 입력 재시도 가능 횟수
     * @param currentTryCount int, 인증서 비번 입력 시도 시작 횟수 지정, 지정한 값부터 count 시작. default 0
     * @param callback function(json-object), 보안 키패드 입력값을 수신하기 위한 callback
     * <pre>
     * - code : int, CODE_SUCCESS:2000, CODE_FAIL:3000 실패, CODE_CANCEL:-1 취소
     * - encryptMethod : string, "rsa", "seed"
     * - oriInputLength : int, 입력 글자 수
     * - cipherData : string, 입력 문자 암호화 데이터
     * - maxCheckCount: int, 인증서 비번 입력 재시도 가능 횟수
     * - currentTryCount: int, 현재 인증서 비번 입력 재시도 횟수
     * </pre>
     */
	this.keypadOpenForCertPwd = function(title, maxLength, padType, checkCertDn, maxCheckCount, currentTryCount){
		return $q(function(resolve, reject){
			KOSCOM.securepad.openForCertPwd(title, maxLength, padType, checkCertDn, maxCheckCount, currentTryCount, function(res){
				resolve(res);
			});
		});
	};
	
	/**
     * "seed"로 입력 받은 cipher-data에서 원문을 반환한다. 
     * @param cipherData string, seed 입력 문자 암호화 데이터 
     * @param  @param callback function(string)
     */
	this.cipherToText = function(cipherData){
		return $q(function(resolve, reject){
			KOSCOM.securepad.cipherToText(cipherData, function(res){
				resolve(res);
			});
		});
	};
	
	/**
     * 인증서 정보 목록을 조회한다. 
     * @param callback function(json-object)
     * <pre>
     * - subjectName : string
	 * - expiredImg : string
	 * - expiredTime : string
	 * - issuerName : string
	 * - policy : string
	 * </pre>
     */
	this.getUserCertList = function(){
		return $q(function(resolve, reject){
			KOSCOM.certificate.getUserCertList(function(res){
				if(res.code === 2000){
					resolve(res.certList);
				}
				else{
					$koscomPopup.alert("알림", "SDCard 에 접근 권한이 없습니다. 권한 허가 후 다시 시도해주세요.");
					reject();
				}
			});
		});
	};
	
	/**
     * 인증서 비밀번호 확인
     * @param subjectDn String, 선택한 인증서의 subjectDn
     * @param cipherPwd String, 인증서 비밀번호. 보안 키패드(SEED) 입력값  
     * @param callback function(code(int})
     *  - CODE_SUCCESS:2000
     *  - CODE_FAIL:3000
     *  - CODE_FAIL_CERT_NULL:5000, 인증서 없음
     */
	this.checkPwd = function(subjectDn, cipherPwd){
		return $q(function(resolve, reject){
			KOSCOM.certificate.checkPwd(subjectDn, cipherPwd, function(code){
				if(code === 2000){
					resolve();
				}
				else{
					reject(code);
				}
			});
		});
	};
	
	/**
     * 인증서 전자 서명
     * @param certType int, 1: 축약서명, 2: 일반서명
     * @param subjectDn String, 선택한 인증서의 subjectDn
     * @param cipherPwd String, 인증서 비밀번호. 보안 키패드(SEED) 입력값  
     * @param plainText String, 서명 데이터
     * @param callback function(string)
     */
	this.getSignedData = function(certType, subjectDn, certPwd, plainText){
		return $q(function(resolve, reject){
			KOSCOM.certificate.getSignedData(certType, subjectDn, certPwd, plainText, function(res){
				if(res.code === 2000){
					resolve(res.data);
				}
				else{
					reject(res.code);
				}
			});
		});
	};
	
	/**
     * 승인번호 가져오기
     * @param @param callback function(code, message, num1, num2, num3)
     * <pre>
     * - code : int, 성공(CODE_SUCCESS:2000)
     *               실패(CODE_FAIL_CERT_INTERNAL:5001)
	 * - message : string
	 * - num1 : string
	 * - num2 : string
	 * - num3 : string
	 * </pre>
     */
	this.simpleCertImport1 = function(){
		return $q(function(resolve, reject){
			KOSCOM.certificate.simpleCertImport1(function(res){
				if(res.code === 2000){
					resolve(res);
				}
				else{
					reject(res.code);
				}
			});
		});
	};
	
	/**
     * 인증서 가져오기(다운로드)
     * @param callback function(code, message, certDn, certPolicy)
     * <pre>
     * - code : int, 성공(CODE_SUCCESS:2000)
     *               실패(CODE_FAIL_CERT_NULL:5000, CODE_FAIL_CERT_INTERNAL:5001, CODE_FAIL_CERT_REGISTRATION:5002)
	 * - message : string
	 * - certDn : string, 실패 시 null
	 * - certPolicy : string, 실패 시 null
	 * </pre>
     */
	this.simpleCertImport2 = function(){
		return $q(function(resolve, reject){
			KOSCOM.certificate.simpleCertImport2(function(res){
				if(res.code === 2000){
					resolve(res);
				}
				else{
					reject(res.code);
				}
			});
		});
	};
	
	/**
     * 인증서 삭제 요청
     * @param subjectDn String, 선택한 인증서의 subjectDn
     * @param cipherPwd String, 인증서 비밀번호. 보안 키패드(SEED) 입력값
     * @param callback function(json-object)
     * - code : int, CODE_SUCCESS:2000
     *               CODE_FAIL_DELETE_CERT_PASSWD:5200, 인중서 비밀번호 오류
     *               CODE_FAIL_DELETE_CERT_NULL:5201, 인증서 없음
     *               CODE_FAIL_DELETE_CERT_INTERNAL:5202, 인증서 삭제 내부 오류
     * - message String, 결과 메세지
     * - certList : object array. 인증서가 없으면 빈 배열
     *   - subjectName : string
     *   - expiredImg : string
     *   - expiredTime : string
     *   - issuerName : string
     *   - policy : string
     */
	this.deleteCert= function(subjectDn, cipherPwd){
		return $q(function(resolve, reject){
			KOSCOM.certificate.deleteCert(subjectDn, cipherPwd, function(res){
				if(res.code === 2000){
					resolve(res.certList);
				}
				else{
					reject(res);
				}
			});
		});
	};
	
	/**
     * 외부 브라우저에 URL을 전달하여 페이지를 연다
     * @param url string, 웹 URL
     * @param callback function(json-object)
     * <pre>
     * - code : int , 2000 성공, 실패 4001
     * - message : string
     * </pre>
     */
	this.openUrl = function(url){
		return $q(function(resolve, reject){
			KOSCOM.openUrl(url, function(res){
				if(res.code === 2000){
					resolve();
				}
				else{
					reject();
				}
			});
		});
	};
	

	this.getDeviceInfo = function(){
		return $q(function(resolve, reject){
			KOSCOM.datacache.getAppData(["osType", "osVersion", "appVersion", "deviceToken"], function(result){
				resolve(result);
			});
		});
	};
	
	this.closeApp = function(){
		return $q(function(resolve, reject){
			KOSCOM.closeApp();
			resolve();
		});
	};
});


$service("$koscomPopup", function($ionicPopup, $ionicModal, $q){
	var popup = null, modal = { };
	this.alert = function(title, template){
		popup = $ionicPopup.alert({
			title: title,
			template: template,
			okText: "확인"
		});
		return popup;
	};

	this.confirm = function(title, template){
		popup = $ionicPopup.confirm({
			title: title,
			template: template,
			cancelText: "취소",
			okText: "확인"
		});
		return popup;
	};
	
	this.modalFromTemplate = function(template, config){
		config = config || {};
		config.animation = "slide-in-up,";
		
		return $q(function(resolve, reject){
			require([config.controllerUrl], function(){
				modal[config.name] = $ionicModal.fromTemplate(template, config);
				resolve(modal);
			});
		});
		return modal;
	};
	
	this.modalFromTemplateUrl = function(template, config){
		config.animation = "slide-in-up,";
		
		return $q(function(resolve, reject){
			require([config.controllerUrl], function(){
				$ionicModal.fromTemplateUrl(template, config).then(function(_modal){
					modal[config.name] = _modal;
					resolve(_modal);
				});
			});
		});
	};
	
	this.close = function(name){
		if(popup){
			if(popup.close){
				popup.close();
				popup = null;
			}
		}
		
		if(modal){
			if(modal[name]){
				if(modal[name].hide){
					modal[name].hide();
					delete modal[name];
				}
			}
		}
	};
	
	this.popupClose = function(){
		if(popup){
			if(popup.close){
				popup.close();
				popup = null;
			}
		}
	};
	
	this.modalClose = function(name){
		if(modal){
			if(modal[name]){
				if(modal[name].hide){
					modal[name].hide();
					delete modal[name];
				}
			}
		}
	};
});


$service("$koscomLoading", function($ionicLoading){
	this.show = function(){
		$ionicLoading.show({
			template: '<p>Loading...</p><ion-spinner></ion-spinner>'
		});
	};

	this.hide = function(){
		window.setTimeout(function(){
			$ionicLoading.hide();
		}, 0);
	};
});

return koscom;
});