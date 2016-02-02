angular.module('dmsApp').factory('versionService', function($resource){

    return $resource('http://localhost:8080/api/version/:id', {id: '@id'});
});