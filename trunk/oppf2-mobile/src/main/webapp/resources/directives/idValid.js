koscomApp.directive("idValid", function ($koscomBridge){
	return {
		require: "ngModel",
		link: function(scope, elem, attr, ngModel){
			ngModel.$asyncValidators.send = function (value){
				return $koscomBridge.req("MEM_01_030", {
	                customerId : value
	            }, null, false).then(function(res){
	            	ngModel.$setValidity("already", true);
	            	ngModel.$setValidity("err", true);
	            }).catch(function(e){
	                if(e.code == '9117'){       //이미 존재하는 값 of 파라미터 전달이 안됨
	                	ngModel.$setValidity("already", false);
	                }
	                else{
	                	ngModel.$setValidity("err", false);
	                }
	            });
			};


			var start_eng_req = /^[a-zA-Z]/;
			var korean_reg = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힝]/;
			ngModel.$parsers.unshift(function(value) {
				var start_eng = start_eng_req.test(value);
				if(value === ""){
					start_eng = true;
				}
				ngModel.$setValidity("starteng", start_eng);
				if(start_eng === false){
					return value;
				}

				var korean = !korean_reg.test(value);
				ngModel.$setValidity("onlyeng", korean);
				
				if(korean === false){
					return value;
				}

				return value;
			});
		}
	};
});