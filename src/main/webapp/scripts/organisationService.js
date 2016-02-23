angular.module('dmsApp').factory('organisationService', function($resource){

    return $resource('http://localhost:8080/api/organisation/:id', {id: '@graphId'}, {
        update: {method: 'PUT'}
    });


});


