/**
 * Created by Yoonjin.Han on 2017-05-22.
 */

angular.module("oppf2").register.controller("IntroPartners", function($scope, $koscomBridge, $ionicSlideBoxDelegate){
    var vm = this;
    vm.currentSlideIndex = 0;
    vm.slideChange = function(index){
        $ionicSlideBoxDelegate.slide(index);
        vm.currentSlideIndex = index;
    };

    vm.slideChange2 = function(){
        vm.currentSlideIndex = $ionicSlideBoxDelegate.currentIndex();
    }
    vm.openUrl = function(url){
    	$koscomBridge.openUrl(url);
    };

    $scope.$on('$ionicView.enter', function(){
        $('.fin_info_banner').each(function(){
            // get current ul
            var $ul = $(this);
            // get array of list items in current ul
            var $liArr = $ul.children('li');
            // sort array of list items in current ul randomly
            $liArr.sort(function(a,b){
                // Get a random number between 0 and 10
                var temp = parseInt( Math.random()*10 );
                // Get 1 or 0, whether temp is odd or even
                var isOddOrEven = temp%2;
                // Get +1 or -1, whether temp greater or smaller than 5
                var isPosOrNeg = temp>5 ? 1 : -1;
                // Return -1, 0, or +1
                return( isOddOrEven*isPosOrNeg );
            })
            // append list items to ul
                .appendTo($ul);
        });
    });
});