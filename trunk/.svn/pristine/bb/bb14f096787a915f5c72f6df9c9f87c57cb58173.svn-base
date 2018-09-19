koscomApp.directive("accordion", function($ionicScrollDelegate){
	return {
		restrict: "A",
		link: function($scope, elem, attrs){
			elem.on("click", ".accordian-cont .btn_bar", function(){
				//elem.find(".accordian-cont").removeClass( 'active' );
				var el = $(this);
				
				if(el.hasClass("not-active")){
					return false;
				}
				if ( el.parent().hasClass( 'active' )) {
					el.parent().removeClass( 'active' );
				}
				else {
					el.parent().addClass( 'active' );
				}
				
				$ionicScrollDelegate.resize();
				return false;
			});
		}
	};
});