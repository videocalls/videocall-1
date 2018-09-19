angular.module("koscomAngular").register.directive("accordion", function($ionicScrollDelegate){
	return {
		restrict: "A",
		link: function($scope, elem, attrs){
			elem.on("click", ".accordian-cont .btn_bar", function(){
				elem.find(".accordian-cont").removeClass( 'active' ); //다른형제 열리면 다른형제 닫힘
				var el = $(this);
				
				if(el.hasClass("not-active")){
					return false;
				}
				if ( el.parent().hasClass( 'active' )) {
					el.parent().removeClass( 'active' );
				}
				else {
					el.parent().removeClass( 'active' );
					el.parent().addClass( 'active' );
				}
				$ionicScrollDelegate.resize();
				return false;
			});
		}
	};
});