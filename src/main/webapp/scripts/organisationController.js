angular.module('dmsApp').controller('organisationController', function($scope, $state, organisationService, personService) {

    $scope.organisations = [];
    $scope.organisation = {};
    $scope.tmp_postal_address = {};
    $scope.tmp_electronic_address = {};
    $scope.tmp_physical_address = {};
    $scope.tmpRelationship = {};
    $scope.persons = [];

    $scope.loadOrganisations = function(){
        organisationService.query(function(organisations){
            $scope.organisations = organisations;
        });
    };

    $scope.createNewOrganisation = function(){
        $scope.organisation.postal_address = $scope.tmp_postal_address;
        $scope.organisation.electronic_address = $scope.tmp_electronic_address;
        $scope.organisation.physical_address = $scope.tmp_physical_address;
        if(!$scope.organisation.organisation_person_relationships){
            $scope.organisation.organisation_person_relationships = [];
        }
        if(!$scope.organisation.organisation_organisation_relationships){
            $scope.organisation.organisation_organisation_relationships = [];
        }

        organisationService.save($scope.organisation, function(){
            $('#createOrganisationModal').modal('hide');
            $scope.loadOrganisations();
            $scope.organisation = {};
            $scope.tmp_postal_address = {};
            $scope.tmp_electronic_address = {};
            $scope.tmp_physical_address = {};
        });
    };
    $scope.delete = function(id){
        organisationService.delete({id: id}, function(){
            $scope.loadOrganisations();
        });
    };

    $scope.createNewOrganisationOrganisationRelationship = function(){
        if(!$scope.organisation.organisation_organisation_relationships){
            $scope.organisation.organisation_organisation_relationships = [];
        }
        if(!$scope.organisation.organisation_person_relationships){
            $scope.organisation.organisation_person_relationships = [];
        }

        $scope.addOrganisationOrganisationRelationshipWithoutDuplicates($scope.organisation.organisation_organisation_relationships, $scope.tmpRelationship);
        $scope.tmpRelationship = {};
        $('#createNewOrganisationOrganisationRelationshipModal').modal('hide');
    };

    $scope.createNewOrganisationPersonRelationship = function(){
        if(!$scope.organisation.organisation_organisation_relationships){
            $scope.organisation.organisation_organisation_relationships = [];
        }
        if(!$scope.organisation.organisation_person_relationships){
            $scope.organisation.organisation_person_relationships = [];
        }

        $scope.addOrganisationPersonRelationshipWithoutDuplicates($scope.organisation.organisation_person_relationships, $scope.tmpRelationship);
        $scope.tmpRelationship = {};
        $('#createNewOrganisationPersonRelationshipModal').modal('hide');
    };

    $scope.showOrganisationPersonRelationsModal = function(){
        personService.query(function(persons){
            $scope.persons = persons;
        });
    };


    $scope.hideUpperModal = function(){
        $('#createNewOrganisationOrganisationRelationshipModal').modal('hide');
        $('#createNewOrganisationPersonRelationshipModal').modal('hide');

    };

    $scope.addOrganisationPersonRelationshipWithoutDuplicates = function(relationshipArray, tmpRelationship){
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

    $scope.addOrganisationOrganisationRelationshipWithoutDuplicates = function(relationshipArray, tmpRelationship){
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

    //initial
    $scope.loadOrganisations();
});

/*********************************************************************************************************/

angular.module('dmsApp').controller('organisationDetailsController', function($scope, $stateParams, organisationService, personService) {

    $scope.organisation = {};
    $scope.organisations = [];
    $scope.persons = [];
    $scope.tmpRelationship = {};

    $scope.loadOrganisation = function(id){
        organisationService.get({id: id}, function(organisation){
            $scope.organisation = organisation;
        });
    };

    $scope.update = function(){
        if(!$scope.organisation.organisation_organisation_relationships){
            $scope.organisation.organisation_organisation_relationships = [];
        }
        if(!$scope.organisation.organisation_person_relationships){
            $scope.organisation.organisation_person_relationships = [];
        }
        organisationService.update($scope.organisation);
    };

    $scope.createNewOrganisationOrganisationRelationship = function(){
        if(!$scope.organisation.organisation_organisation_relationships){
            $scope.organisation.organisation_organisation_relationships = [];
        }
        if(!$scope.organisation.organisation_person_relationships){
            $scope.organisation.organisation_person_relationships = [];
        }

        $scope.addOrganisationOrganisationRelationshipWithoutDuplicates($scope.organisation.organisation_organisation_relationships, $scope.tmpRelationship);
        $scope.tmpRelationship = {};
        $('#createNewOrganisationOrganisationRelationshipModal').modal('hide');
    };

    $scope.createNewOrganisationPersonRelationship = function(){
        if(!$scope.organisation.organisation_organisation_relationships){
            $scope.organisation.organisation_organisation_relationships = [];
        }
        if(!$scope.organisation.organisation_person_relationships){
            $scope.organisation.organisation_person_relationships = [];
        }

        $scope.addOrganisationPersonRelationshipWithoutDuplicates($scope.organisation.organisation_person_relationships, $scope.tmpRelationship);
        $scope.tmpRelationship = {};
        $('#createNewOrganisationPersonRelationshipModal').modal('hide');
    };

    $scope.showOrganisationPersonRelationsModal = function(){
        personService.query(function(persons){
            $scope.persons = persons;
        });
    };

    $scope.showOrganisationOrganisationRelationsModal = function(){
        organisationService.query(function(organisations){
            $scope.organisations = organisations;
        });
    };

    $scope.hideUpperModal = function(){
        $('#createNewOrganisationOrganisationRelationshipModal').modal('hide');
        $('#createNewOrganisationPersonRelationshipModal').modal('hide');

    };

    $scope.addOrganisationPersonRelationshipWithoutDuplicates = function(relationshipArray, tmpRelationship){
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

    $scope.addOrganisationOrganisationRelationshipWithoutDuplicates = function(relationshipArray, tmpRelationship){
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

    //initial
    $scope.loadOrganisation($stateParams.id);
});
