angular.module('dmsApp').factory('eorService', function($resource){

    return $resource('http://localhost:8080/api/eor/:id', {id: '@id'}, {
        update: {method: 'PUT'}
    });


});


