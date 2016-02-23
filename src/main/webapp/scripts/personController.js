angular.module('dmsApp').controller('personsController', function($scope, $state, personService) {

    $scope.persons = [];
    $scope.person = {};
    $scope.tmp_postal_address = {};
    $scope.tmp_electronic_address = {};
    $scope.tmp_physical_address = {};

    $scope.loadPersons = function(){
        personService.query(function(persons){
            $scope.persons = persons;
        });
    };

    $scope.createNewPerson = function(){
        $scope.person.postal_address = $scope.tmp_postal_address;
        $scope.person.electronic_address = $scope.tmp_electronic_address;
        $scope.person.physical_address = $scope.tmp_physical_address;

        personService.save($scope.person, function(){
            $('#createPersonModal').modal('hide');
            $scope.loadPersons();
            $scope.person = {};
            $scope.tmp_postal_address = {};
            $scope.tmp_electronic_address = {};
            $scope.tmp_physical_address = {};
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
