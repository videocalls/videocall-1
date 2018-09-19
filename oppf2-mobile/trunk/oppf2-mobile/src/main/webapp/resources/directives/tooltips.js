koscomApp.directive("tooltips", function(){
	return {
		restrict: "A",
		link: function($scope, elem, attrs){
			elem.children( '.icon_tip' ).on( 'click', function (e) {
				$("#tooltipsDim").addClass("active");
				
				var html = elem.children(".detail_tip").html();
				$("#tooltips").show().children(".detail_tip").removeClass("btn_btm_pop").html(html);
				return false;
			});
			
			$("#tooltipsDim").click(function(){
				$(this).removeClass("active");
				$("#tooltips").hide();
				return false;
			});
		}
	};
});


koscomApp.directive("tooltipsConfirm", function(){
	return {
		restrict: "A",
		link: function($scope, elem, attrs){
			elem.on( 'click', function (e) {
				$("#tooltipsDim").addClass("active");
				
				var html = $("#" + attrs.tooltipsConfirm).children(".detail_tip").html();
				$("#tooltips").show().children(".detail_tip").addClass("btn_btm_pop").html(html);
				
				
				$("#tooltips .btn_btm").click(function(){
					$("#tooltips .btn_btm").off("click");
					$("#tooltipsDim").removeClass("active");
					$("#tooltips").hide();

					if($scope.vm.tooltipsConfirmClick){
						$scope.vm.tooltipsConfirmClick();
					}
					return false;
				});
				
				return false;
			});
			
			$("#tooltipsDim").click(function(){
				$("#tooltips .btn_btm").off("click");
				$(this).removeClass("active");
				$("#tooltips").hide();
				return false;
			});
			
			
		}
	};
});
