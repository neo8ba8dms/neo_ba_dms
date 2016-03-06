angular.module('dmsApp').controller('personRolesController', function($scope, $state, personRoleService, personService, documentService) {

    $scope.personRoles = [];
    $scope.personRole = {};
    $scope.documents = [];
    $scope.persons = [];
    $scope.tmp = {};

    $scope.loadPersonRoles = function(){
        personRoleService.query(function(personRoles){
           $scope.personRoles = personRoles;
        });
    };

    $scope.createNewPersonRole = function(){
        if(!$scope.personRole.referes_to){
            $scope.personRole.referes_to = [];
        }
        if(!$scope.personRole.is_role_of){
            $scope.personRole.is_role_of = {};
        }
        personRoleService.save($scope.personRole, function(){
           $('#createPersonRoleModal').modal('hide');
            $scope.loadPersonRoles();
            $scope.personRole = {};
        });
    };

    $scope.delete = function(id){
        personRoleService.delete({id: id}, function(){
            $scope.loadPersonRoles();
        })
    };

    $scope.createNewPersonRoleDocumentRelationship = function(){
        if(!$scope.personRole.referes_to){
            $scope.personRole.referes_to = [];
        }
        if(!$scope.personRole.is_role_of){
            $scope.personRole.is_role_of = {};
        }
        $scope.personRole.referes_to.push($scope.tmp.document);
        $scope.tmp.document = {};
        $('#createNewPersonRoleDocumentRelationshipModal').modal('hide');
    };

    $scope.createNewPersonRolePersonRelationship = function(){
        if(!$scope.personRole.referes_to){
            $scope.personRole.referes_to = [];
        }
        if(!$scope.personRole.is_role_of){
            $scope.personRole.is_role_of = {};
        }
        $scope.personRole.is_role_of = $scope.tmp.person;
        $scope.tmp = {};
        $('#createNewPersonRolePersonRelationshipModal').modal('hide');
    };

    $scope.showPersonRoleDocumentRelationsModal = function(){
        documentService.query(function(documents){
           $scope.documents = documents;
        });
    };

    $scope.showPersonRolePersonRelationsModal = function(){
        personService.query(function(persons){
            $scope.persons = persons;
        });
    };

    $scope.hideUpperModal = function(){
        $('#createNewPersonRoleDocumentRelationshipModal').modal('hide');
        $('#createNewPersonRolePersonRelationshipModal').modal('hide');
    };

    //initial
    $scope.loadPersonRoles();
});

angular.module('dmsApp').controller('personRoleDetailsController', function($scope, $stateParams, personRoleService, personService, documentService) {

    $scope.personRole = {};
    $scope.documents = [];
    $scope.persons = [];
    $scope.tmp = {};

    $scope.loadPersonRole = function(id){
        personRoleService.get({id: id}, function(personRole){
            $scope.personRole = personRole;
        });
    };

    $scope.update = function(){
        if(!$scope.personRole.referes_to){
            $scope.personRole.referes_to = [];
        }
        if(!$scope.personRole.is_role_of){
            $scope.personRole.is_role_of = {};
        }
        personRoleService.update($scope.personRole, function(response){
            $scope.personRole = response;
        });
    };

    $scope.createNewPersonRoleDocumentRelationship = function(){
        if(!$scope.personRole.referes_to){
            $scope.personRole.referes_to = [];
        }
        if(!$scope.personRole.is_role_of){
            $scope.personRole.is_role_of = {};
        }
        $scope.personRole.referes_to.push($scope.tmp.document);
        $scope.tmp.document = {};
        $('#createNewPersonRoleDocumentRelationshipModal').modal('hide');
    };

    $scope.createNewPersonRolePersonRelationship = function(){
        if(!$scope.personRole.referes_to){
            $scope.personRole.referes_to = [];
        }
        if(!$scope.personRole.is_role_of){
            $scope.personRole.is_role_of = {};
        }
        $scope.personRole.is_role_of = $scope.tmp.person;
        $scope.tmp = {};
        $('#createNewPersonRolePersonRelationshipModal').modal('hide');
    };

    $scope.showPersonRoleDocumentRelationsModal = function(){
        documentService.query(function(documents){
            $scope.documents = documents;
        });
    };

    $scope.showPersonRolePersonRelationsModal = function(){
        personService.query(function(persons){
            $scope.persons = persons;
        });
    };

    //initial
    $scope.loadPersonRole($stateParams.id);
});
