koscomApp.directive('watchMenu', function($timeout, $ionicSideMenuDelegate) {
	return {
		restrict: 'A',
		link: function($scope, $element, $attr) {
			$timeout(function() {
				$scope.$watch(function() { 
					return $ionicSideMenuDelegate.getOpenRatio();
				}, function(ratio) {
					if(ratio === -1){
						//open
						document.getElementById("menuDim").className = "dimmed active";
					}
					else{
						//hide
						document.getElementById("menuDim").className = "dimmed";
					}
				});
			});
		}
	};
});