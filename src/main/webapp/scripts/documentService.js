angular.module('dmsApp').factory('documentService', function($resource){

    return $resource('http://localhost:8080/api/documents/:id', {id: '@id'}, {
        update: {method: 'PUT'}
    });


});


