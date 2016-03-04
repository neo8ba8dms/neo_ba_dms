angular.module('dmsApp').controller('organisationRolesController', function($scope, $state, organisationRoleService, organisationService, documentService) {

    $scope.organisationRoles = [];
    $scope.organisationRole = {};
    $scope.documents = [];
    $scope.organisations = [];
    $scope.tmp = {};

    $scope.loadOrganisationRoles = function(){
        organisationRoleService.query(function(organisationRoles){
            $scope.organisationRoles = organisationRoles;
        });
    };

    $scope.createNewOrganisationRole = function(){
        if(!$scope.organisationRole.referes_to){
            $scope.organisationRole.referes_to = [];
        }
        if(!$scope.organisationRole.is_role_of){
            $scope.organisationRole.is_role_of = {};
        }
        organisationRoleService.save($scope.organisationRole, function(){
            $('#createOrganisationRoleModal').modal('hide');
            $scope.loadOrganisationRoles();
            $scope.organisationRole = {};
        });
    };

    $scope.delete = function(id){
        organisationRoleService.delete({id: id}, function(){
            $scope.loadOrganisationRoles();
        })
    };

    $scope.createNewOrganisationRoleDocumentRelationship = function(){
        if(!$scope.organisationRole.referes_to){
            $scope.organisationRole.referes_to = [];
        }
        if(!$scope.organisationRole.is_role_of){
            $scope.organisationRole.is_role_of = {};
        }
        $scope.organisationRole.referes_to.push($scope.tmp.document);
        $scope.tmp.document = {};
        $('#createNewOrganisationRoleDocumentRelationshipModal').modal('hide');
    };

    $scope.createNewOrganisationRoleOrganisationRelationship = function(){
        if(!$scope.organisationRole.referes_to){
            $scope.organisationRole.referes_to = [];
        }
        if(!$scope.organisationRole.is_role_of){
            $scope.organisationRole.is_role_of = {};
        }
        $scope.organisationRole.is_role_of = $scope.tmp.organisation;
        $scope.tmp = {};
        $('#createNewOrganisationRoleOrganisationRelationshipModal').modal('hide');
    };

    $scope.showOrganisationRoleDocumentRelationsModal = function(){
        documentService.query(function(documents){
            $scope.documents = documents;
        });
    };

    $scope.showOrganisationRoleOrganisationRelationsModal = function(){
        organisationService.query(function(organisations){
            $scope.organisations = organisations;
        });
    };

    $scope.hideUpperModal = function(){
        $('#createNewOrganisationRoleDocumentRelationshipModal').modal('hide');
        $('#createNewOrganisationRoleOrganisationRelationshipModal').modal('hide');
    };

    //initial
    $scope.loadOrganisationRoles();
});

angular.module('dmsApp').controller('organisationRoleDetailsController', function($scope, $stateParams, organisationRoleService, organisationService, documentService) {

    $scope.organisationRole = {};
    $scope.documents = [];
    $scope.organisations = [];
    $scope.tmp = {};

    $scope.loadOrganisationRole = function(id){
        organisationRoleService.get({id: id}, function(organisationRole){
            $scope.organisationRole = organisationRole;
        });
    };

    $scope.update = function(){
        if(!$scope.organisationRole.referes_to){
            $scope.organisationRole.referes_to = [];
        }
        if(!$scope.organisationRole.is_role_of){
            $scope.organisationRole.is_role_of = {};
        }
        organisationRoleService.update($scope.organisationRole, function(response){
            $scope.organisationRole = response;
        });
    };

    $scope.createNewOrganisationRoleDocumentRelationship = function(){
        if(!$scope.organisationRole.referes_to){
            $scope.organisationRole.referes_to = [];
        }
        if(!$scope.organisationRole.is_role_of){
            $scope.organisationRole.is_role_of = {};
        }
        $scope.organisationRole.referes_to.push($scope.tmp.document);
        $scope.tmp.document = {};
        $('#createNewOrganisationRoleDocumentRelationshipModal').modal('hide');
    };

    $scope.createNewOrganisationRoleOrganisationRelationship = function(){
        if(!$scope.organisationRole.referes_to){
            $scope.organisationRole.referes_to = [];
        }
        if(!$scope.organisationRole.is_role_of){
            $scope.organisationRole.is_role_of = {};
        }
        $scope.organisationRole.is_role_of = $scope.tmp.organisation;
        $scope.tmp = {};
        $('#createNewOrganisationRoleOrganisationRelationshipModal').modal('hide');
    };

    $scope.showOrganisationRoleDocumentRelationsModal = function(){
        documentService.query(function(documents){
            $scope.documents = documents;
        });
    };

    $scope.showOrganisationRoleOrganisationRelationsModal = function(){
        organisationService.query(function(organisations){
            $scope.organisations = organisations;
        });
    };

    //initial
    $scope.loadOrganisationRole($stateParams.id);
});
