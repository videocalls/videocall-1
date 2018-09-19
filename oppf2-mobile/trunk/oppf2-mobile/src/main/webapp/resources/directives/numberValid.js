koscomApp.directive('numberValid', function(){
	return {
		require: "ngModel",
		link: function(scope, element, attr, ngModel){
			ngModel.$parsers.unshift(function(value) {
				
				var r = /^[0-9]*$/.test(value);
				
				ngModel.$setValidity("numberolny", r);

				return value;
			});
		}, 
	}
});
