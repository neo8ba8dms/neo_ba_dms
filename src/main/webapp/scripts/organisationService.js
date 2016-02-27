angular.module('dmsApp').factory('organisationService', function($resource){

    return $resource('http://localhost:8080/api/organisation/:id', {id: '@graphId'}, {
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


