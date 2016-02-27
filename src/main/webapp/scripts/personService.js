angular.module('dmsApp').factory('personService', function($resource){

    return $resource('http://localhost:8080/api/person/:id', {id: '@graphId'}, {
        update: {method: 'PUT'},
        get: {method: 'GET',
        transformResponse: function(data){
            var shouldBeDecoded = JSOG.parse(data);
            console.log(shouldBeDecoded);
            return shouldBeDecoded;
        }},
        query: {transformResponse: function(data){
            var shouldBeDecoded = JSOG.parse(data);
            console.log(shouldBeDecoded);
            return shouldBeDecoded;
        },isArray:true}

    });


});


