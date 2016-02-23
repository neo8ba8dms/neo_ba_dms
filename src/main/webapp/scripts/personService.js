angular.module('dmsApp').factory('personService', function($resource){

    return $resource('http://localhost:8080/api/person/:id', {id: '@graphId'}, {
        update: {method: 'PUT'}
    });


});


