koscomApp.directive("tabs", function($ionicScrollDelegate){
	return {
		restrict: "A",
		link: function($scope, elem, attrs){
			var head = elem.children(".tab-head");
			var body = elem.children(".tab-body");
			head.find("li a").click(function(){
				head.find("li a").closest('li').removeClass("active");
				$(this).closest('li').addClass("active");
				
				body.children("div").hide();
				
				var index = head.find("li a").index(this);
				body.children("div:eq(" + index + ")").show();
				
				$ionicScrollDelegate.resize();
				return false;
			});
		}
	};
});

koscomApp.directive("tabs2", function($ionicScrollDelegate){
	return {
		restrict: "A",
		link: function($scope, elem, attrs){
			var head = elem.find(".tab-head");
			var body = elem.find(".tab-body");
			head.find("li a").click(function(){
				head.find("li a").closest('li').removeClass("active");
				$(this).closest('li').addClass("active");
				
				body.children("div").hide();
				
				var index = head.find("li a").index(this);
				body.children("div:eq(" + index + ")").show();
				
				$ionicScrollDelegate.resize();
				return false;
			});
		}
	};
});