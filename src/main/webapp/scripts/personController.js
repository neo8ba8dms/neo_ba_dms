angular.module('dmsApp').controller('personsController', function($scope, $state, personService, organisationService) {

    $scope.persons = [];
    $scope.person = {};
    $scope.tmp_postal_address = {};
    $scope.tmp_electronic_address = {};
    $scope.tmp_physical_address = {};
    $scope.tmpRelationship = {};
    $scope.organisations = [];

    $scope.loadPersons = function(){
        personService.query(function(persons){
            $scope.persons = persons;
        });
    };

    $scope.createNewPerson = function(){
        $scope.person.postal_address = $scope.tmp_postal_address;
        $scope.person.electronic_address = $scope.tmp_electronic_address;
        $scope.person.physical_address = $scope.tmp_physical_address;
        if(!$scope.person.person_person_relationships){
            $scope.person.person_person_relationships = [];
        }
        if(!$scope.person.person_organisation_relationships){
            $scope.person.person_organisation_relationships = [];
        }

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

    $scope.createNewPersonPersonRelationship = function(){
        if(!$scope.person.person_person_relationships){
            $scope.person.person_person_relationships = [];
        }
        if(!$scope.person.person_organisation_relationships){
            $scope.person.person_organisation_relationships = [];
        }

        $scope.addPersonPersonRelationshipWithoutDuplicates($scope.person.person_person_relationships, $scope.tmpRelationship);
        $scope.tmpRelationship = {};
        $('#createNewPersonPersonRelationshipModal').modal('hide');
    };

    $scope.createNewPersonOrganisationRelationship = function(){
        if(!$scope.person.person_person_relationships){
            $scope.person.person_person_relationships = [];
        }
        if(!$scope.person.person_organisation_relationships){
            $scope.person.person_organisation_relationships = [];
        }

        $scope.addPersonOrganisationRelationshipWithoutDuplicates($scope.person.person_organisation_relationships, $scope.tmpRelationship);
        $scope.tmpRelationship = {};
        $('#createNewPersonOrganisationRelationshipModal').modal('hide');
    };

        $scope.showPersonOrganisationRelationsModal = function(){
        organisationService.query(function(organisations){
            $scope.organisations = organisations;
        });
    };


    $scope.hideUpperModal = function(){
        $('#createNewPersonPersonRelationshipModal').modal('hide');
        $('#createNewPersonOrganisationRelationshipModal').modal('hide');

    };

    $scope.addPersonOrganisationRelationshipWithoutDuplicates = function(relationshipArray, tmpRelationship){
        var found = false;
        for(var i = 0; i < relationshipArray.length; i++){
            if(relationshipArray[i].relating_party.name == tmpRelationship.relating_party.name){
                found = true;
                break;
            }
        }
        if(!found){
            relationshipArray.push(tmpRelationship);
        }
    };

    $scope.addPersonPersonRelationshipWithoutDuplicates = function(relationshipArray, tmpRelationship){
        var found = false;
        for(var i = 0; i < relationshipArray.length; i++){
            if(relationshipArray[i].relating_party.first_name == tmpRelationship.relating_party.first_name
                && relationshipArray[i].relating_party.last_name == tmpRelationship.relating_party.last_name){
                found = true;
                break;
            }
        }
        if(!found){
            relationshipArray.push(tmpRelationship);
        }
    };


    //initial
    $scope.loadPersons();
});

/*********************************************************************************************************/

angular.module('dmsApp').controller('personDetailsController', function($scope, $stateParams, personService, organisationService) {

    $scope.person = {};
    $scope.organisations = [];
    $scope.persons = {};
    $scope.tmpRelationship = {};


    $scope.loadPerson = function(id){
        personService.get({id: id}, function(person){
            $scope.person = person;
        });
    };

    $scope.update = function(){
        if(!$scope.person.person_person_relationships){
            $scope.person.person_person_relationships = [];
        }
        if(!$scope.person.person_organisation_relationships){
            $scope.person.person_organisation_relationships = [];
        }
        personService.update($scope.person, function(response){
            $scope.person = response;
        });
    };

    $scope.createNewPersonPersonRelationship = function(){
        if(!$scope.person.person_person_relationships){
            $scope.person.person_person_relationships = [];
        }
        if(!$scope.person.person_organisation_relationships){
            $scope.person.person_organisation_relationships = [];
        }
        $scope.addPersonPersonRelationshipWithoutDuplicates($scope.person.person_person_relationships, $scope.tmpRelationship);
        $scope.tmpRelationship = {};
        $('#createNewPersonPersonRelationshipModal').modal('hide');
    };

    $scope.createNewPersonOrganisationRelationship = function(){
        if(!$scope.person.person_person_relationships){
            $scope.person.person_person_relationships = [];
        }
        if(!$scope.person.person_organisation_relationships){
            $scope.person.person_organisation_relationships = [];
        }
        $scope.addPersonOrganisationRelationshipWithoutDuplicates($scope.person.person_organisation_relationships, $scope.tmpRelationship);
        $scope.tmpRelationship = {};
        $('#createNewPersonOrganisationRelationshipModal').modal('hide');
    };

    $scope.showPersonOrganisationRelationsModal = function(){
        organisationService.query(function(organisations){
            $scope.organisations = organisations;
        });
    };

    $scope.showPersonPersonRelationsModal = function(){
        personService.query(function(persons){
            $scope.persons = persons;
        });
    };

    $scope.hideUpperModal = function(){
        $('#createNewPersonPersonRelationshipModal').modal('hide');
        $('#createNewPersonOrganisationRelationshipModal').modal('hide');    };


    $scope.addPersonOrganisationRelationshipWithoutDuplicates = function(relationshipArray, tmpRelationship){
        var found = false;
        for(var i = 0; i < relationshipArray.length; i++){
            if(relationshipArray[i].relating_party.name == tmpRelationship.relating_party.name){
                found = true;
                break;
            }
        }
        if(!found){
            relationshipArray.push(tmpRelationship);
        }
    };

    $scope.addPersonPersonRelationshipWithoutDuplicates = function(relationshipArray, tmpRelationship){
        var found = false;
        for(var i = 0; i < relationshipArray.length; i++){
            if(relationshipArray[i].relating_party.first_name == tmpRelationship.relating_party.first_name
            && relationshipArray[i].relating_party.last_name == tmpRelationship.relating_party.last_name){
                found = true;
                break;
            }
        }
        if(!found){
            relationshipArray.push(tmpRelationship);
        }
    };

    //initial
    $scope.loadPerson($stateParams.id);
});
