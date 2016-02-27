angular.module('dmsApp').factory('documentService', function($resource){

    return $resource('http://localhost:8080/api/documents/:id', {id: '@graphId'}, {
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
}])

.service('documentUpdateService', ['$http', function ($http) {
    this.updateDocument = function(uploadUrl, data){
        var fd = new FormData();

        //all data, that is going to be send, inclusive the file
        fd.append('file', data['file']);

        tmpData = JSON.parse(JSON.stringify(data)); //clone object
        delete tmpData.file; //otherwise there would be an """file: {}""" in the String
        fd.append('documentString', JSON.stringify(tmpData));



        return $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
            .success(function(response){
                return response;
            })
            .error(function(){
            });
    }
}]);
