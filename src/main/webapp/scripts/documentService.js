angular.module('dmsApp').factory('documentService', function($resource){

    return $resource('http://localhost:8080/api/documents/:id', {id: '@id'}, {
        update: {method: 'PUT'}
    });


})

.service('fileUploadService', ['$http', function ($http) {
    this.uploadFileToUrl = function(uploadUrl, data){
        var fd = new FormData();

        //all data, that is going to be send, inclusive the file
        fd.append('file', data['file']);
        fd.append('documentName', data['name']);

        $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
            .success(function(){
            })
            .error(function(){
            });
    }
}]);


