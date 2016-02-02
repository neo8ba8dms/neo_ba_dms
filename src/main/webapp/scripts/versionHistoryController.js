angular.module('dmsApp').controller('versionHistoryController', function($scope, $stateParams, versionService) {

    $scope.versionSet = []; //chose array because orderBy-filter only works with arrays

    $scope.loadVersionHistory = function(){
        versionService.query({id: $stateParams.idOfNewestDocument}, function(versions){
            $scope.versionSet = versions;
        });
    };

    //initial
    $scope.loadVersionHistory();
});
