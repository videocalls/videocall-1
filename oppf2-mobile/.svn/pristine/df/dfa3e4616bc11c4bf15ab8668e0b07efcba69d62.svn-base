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
var koscom = angular.module("koscom", ["ionic", "ion-floating-menu"]);
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

	var resolveDependencies = function ($q, $rootScope, dependencies) {
		var defer = $q.defer();
		require(dependencies, function () {
			defer.resolve();
			$rootScope.$apply()
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
					//controller: config.name.charAt(0).toUpperCase() + config.name.substr(1),
					//controllerAs: "vm"
				};
			}

			if(config.resolve){
				routeDef.views[config.views].resolve = config.resolve;
			}
			else{
				routeDef.views[config.views].resolve = {};
			}
			if(baseFileName){
				routeDef.views[config.views].resolve.load = function ($q, $rootScope) {
					//var dependencies = [ctrlRoot + baseFileName + ".js"];
					//return resolveDependencies($q, $rootScope, dependencies);
				};
			}
		}
		else{
			if(baseFileName){
				routeDef.templateUrl = viewRoot + baseFileName + ".html";
				//routeDef.controller = config.name.charAt(0).toUpperCase() + config.name.substr(1);
				//routeDef.controllerAs = "vm";
			}

			if(config.resolve){
				routeDef.resolve = config.resolve;
			}
			else{
				routeDef.resolve = {};
			}
			if(baseFileName){
				routeDef.resolve.load = function ($q, $rootScope) {
					//var dependencies = [ctrlRoot + baseFileName + ".js"];
					//return resolveDependencies($q, $rootScope, dependencies);
				}
			}
		}

		$stateProvider.state(stateName, routeDef);
	}
});


$service("koscomBridge", function($http, $q){
	function SuccessDelegate(){
		
	};
	
	function ErrorDelegate(){
		
	};

	this.setAppData = function(name, value){
		KOSCOM.datacache.setAppData(name, value, function(result){
			if(result[name]){
				deferred.resolve();
			}
			else{
				deferred.reject();	
			}
		});
	};

	this.getAppData = function(name){
		var deferred = $.Deferred();
		KOSCOM.datacache.getAppData(name, function(result){
			if(result[name]){
				deferred.resolve(result[name]);
			}
			else{
				deferred.reject();
			}
		});
		return new deferred.promise();
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

	this.removeAppData = function(){
		return $q(function(resolve, reject){
			KOSCOM.datacache.removeAppData(name, function(result){
				
			});
		});
	};

	this.removePageData = function(name){
		return $q(function(resolve, reject){
			KOSCOM.datacache.removePageData(name, function(result){
				
			});
		});
	};

	this.clearPageData = function(name){
		return $q(function(resolve, reject){
			KOSCOM.datacache.clearPageData(name, function(result){
				
			});
		});
	};

	this.get = function(url){
		return $http.get(url);
	};


	this.login = function(){
		return $q(function(resolve, reject){
			window.setTimeout(function(){
				resolve(true);
			}, 2000);
		});
	};

});


$service("koscomPopup", function($ionicPopup, $ionicModal){
	this.alert = function(title, template){
		return $ionicPopup.alert({
			title: title,
			template: template
		});
	};

	this.confirm = function(title, template){
		return $ionicPopup.confirm({
			title: title,
			template: template
		});
	};

	this.modal = function(template){
		return $ionicModal.fromTemplate(template, {
			animation: 'slide-in-up'
		});
	};
});


return koscom;
});