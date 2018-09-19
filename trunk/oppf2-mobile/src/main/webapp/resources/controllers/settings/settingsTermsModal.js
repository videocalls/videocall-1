/**
 * Created by unionman on 2017-05-25.
 */

angular.module("oppf2").register.controller("SettingsTermsModal", function($scope, $koscomState, $stateParams, $koscomBridge, $koscomPopup){
    var vm = this;
    
    vm.modalPageTitle = $scope.settingsTermsTitle;
    vm.modalPageIndex = $scope.settingsTermsIndex;

    vm.termsContentsText = "";
    
    // 약관 내용 호출
    $koscomBridge.req("MBR_02_140",{
        customerTermsSystemKind : "G003002"
    }).then(function(res){
        vm.termsList = res.termsList;
        
        vm.termsContentsText = vm.termsList[vm.modalPageIndex].customerTermsContent;
    }).catch(function(e){
        console.log("error");
    });

    
    // modal close
	vm.close = function(){
		$koscomPopup.close("settingsSettingsTermsModal");
	};

    vm.alert = function(title, content){
        var c = $koscomPopup.alert(title, content);
    };
	
    
    
});