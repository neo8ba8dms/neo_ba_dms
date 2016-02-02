angular.module('dmsApp').factory('versionListService', function($resource){

    return $resource('http://localhost:8080/api/versionHistory/:id', {id: '@id'});
});

angular.module('dmsApp').factory('versionDetailService', function($resource){

    return $resource('http://localhost:8080/api/versionDetail/:id', {id: '@id'});
});