angular.module('dmsApp').controller('versionHistoryController', function($scope, $stateParams, versionListService) {

    $scope.versionSet = []; //chose array because orderBy-filter only works with arrays

    $scope.loadVersionHistory = function(){
        versionListService.query({id: $stateParams.idOfNewestDocument}, function(versions){
            $scope.versionSet = versions;
        });
    };

    //initial
    $scope.loadVersionHistory();
});

angular.module('dmsApp').controller('versionHistoryDetailsController', function($scope, $stateParams, versionDetailService, eorService) {

    $scope.document = {}; //name is needed do fit with documentDetails.html-template
    $scope.isVersionHistoryMode = true;
    $scope.external_object_references;


    $scope.loadDocumentVersion = function(){
        versionDetailService.get({id: $stateParams.documentVersionId}, function(data){
          $scope.document = data;
      });
    };

    //initial
    $scope.loadDocumentVersion();
    eorService.query(function(eors){
        $scope.external_object_references = eors;
    });
});
