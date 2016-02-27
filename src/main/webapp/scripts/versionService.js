angular.module('dmsApp').factory('versionListService', function($resource){

    return $resource('http://localhost:8080/api/versionHistory/:id', {id: '@id'}, {
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

angular.module('dmsApp').factory('versionDetailService', function($resource){

    return $resource('http://localhost:8080/api/versionDetail/:id', {id: '@id'}, {
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

        }
    );
});