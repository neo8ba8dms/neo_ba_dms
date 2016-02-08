angular.module('dmsApp').controller('externalObjectsController', function($scope, $state, eorService) {

    $scope.external_object_references = [];
    $scope.external_object_reference = {};

    $scope.loadExternalObjects = function(){
        eorService.query(function(eors){
            $scope.external_object_references = eors;
            console.log("Did (re-)load all eors");
            console.log($scope.external_object_references);
        });
    };

    $scope.createNewExternalObject = function(){
        eorService.save($scope.external_object_reference, function(){
            $('#createExternalObjectModal').modal('hide');
            $scope.loadExternalObjects();
            $scope.external_object_reference = {};
        });
    };
    $scope.delete = function(id){
        eorService.delete({id: id}, function(){
            $scope.loadExternalObjects();
        });
    };


    //initial
    $scope.loadExternalObjects();
});

/*********************************************************************************************************/

angular.module('dmsApp').controller('externalObjectDetailsController', function($scope, $stateParams, eorService) {

    $scope.external_object_reference = {};

    $scope.loadExternalObject = function(id){
        eorService.get({id: id}, function(eor){
            $scope.external_object_reference = eor;
        });
    };

    $scope.update = function(){
        eorService.update($scope.external_object_reference);
    };

    //initial
    $scope.loadExternalObject($stateParams.id);
});
