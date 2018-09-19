koscomApp.directive("emailValid", function ($koscomBridge){
	return {
		require: "ngModel",
		link: function(scope, elem, attr, ngModel){
			var reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

			var eng = /^[A-Za-z0-9]{8,12}$/;

			ngModel.$asyncValidators.send = function (value){
				var email = scope.vm.customerEmail;
				if(reg.test(email)){
					ngModel.$setValidity("syntax", true);
					return value;
				}
				else{
					ngModel.$setValidity("syntax", false);
				}
				return $koscomBridge.req("MEM_01_040", {
	                customerEmail : scope.vm.customerEmail
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
		}
	}
});