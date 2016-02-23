angular.module('dmsApp').controller('organisationController', function($scope, $state, organisationService) {

    $scope.organisations = [];
    $scope.organisation = {};

    $scope.loadOrganisations = function(){
        organisationService.query(function(organisations){
            $scope.organisations = organisations;
        });
    };

    $scope.createNewOrganisation = function(){
        organisationService.save($scope.organisation, function(){
            $('#createOrganisationModal').modal('hide');
            $scope.loadOrganisations();
            $scope.organisation = {};
        });
    };
    $scope.delete = function(id){
        organisationService.delete({id: id}, function(){
            $scope.loadOrganisations();
        });
    };

    //initial
    $scope.loadOrganisations();
});

/*********************************************************************************************************/

angular.module('dmsApp').controller('organisationDetailsController', function($scope, $stateParams, organisationService) {

    $scope.organisation = {};

    $scope.loadOrganisation = function(id){
        organisationService.get({id: id}, function(organisation){
            $scope.organisation = organisation;
        });
    };

    $scope.update = function(){
        organisationService.update($scope.organisation);
    };

    //initial
    $scope.loadOrganisation($stateParams.id);
});
