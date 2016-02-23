angular.module('dmsApp').controller('personsController', function($scope, $state, personService) {

    $scope.persons = [];
    $scope.person = {};

    $scope.loadPersons = function(){
        personService.query(function(persons){
            $scope.persons = persons;
        });
    };

    $scope.createNewPerson = function(){
        personService.save($scope.person, function(){
            $('#createPersonModal').modal('hide');
            $scope.loadPersons();
            $scope.person = {};
        });
    };
    $scope.delete = function(id){
        personService.delete({id: id}, function(){
            $scope.loadPersons();
        });
    };

    //initial
    $scope.loadPersons();
});

/*********************************************************************************************************/

angular.module('dmsApp').controller('personDetailsController', function($scope, $stateParams, personService) {

    $scope.person = {};

    $scope.loadPerson = function(id){
        personService.get({id: id}, function(person){
            $scope.person = person;
        });
    };

    $scope.update = function(){
        personService.update($scope.person);
    };

    //initial
    $scope.loadPerson($stateParams.id);
});
