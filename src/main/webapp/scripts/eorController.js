angular.module('dmsApp').controller('externalObjectsController', function($scope, $state, eorService) {

    $scope.externalObjects = [];
    $scope.externalObject = {};

    $scope.loadExternalObjects = function(){
        eorService.query(function(eors){
            $scope.externalObjects = eors;
            console.log("Did (re-)load all eors");
            console.log($scope.externalObjects);
        });
    };

    $scope.createNewExternalObject = function(){
        eorService.save($scope.externalObject, function(){
            $('#createExternalObjectModal').modal('hide');
            $scope.loadExternalObjects();
            $scope.externalObject = {};
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

    $scope.externalObject = {};

    $scope.loadExternalObject = function(id){
        eorService.get({id: id}, function(eor){
            $scope.externalObject = eor;
        });
    };

    $scope.update = function(){
        eorService.update($scope.externalObject);
    };

    //initial
    $scope.loadExternalObject($stateParams.id);
});
