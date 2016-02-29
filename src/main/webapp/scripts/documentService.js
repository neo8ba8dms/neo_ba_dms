angular.module('dmsApp').factory('documentService', function($resource){

    return $resource('http://localhost:8080/api/documents/:id', {id: '@graphId'}, {
        save: {method: 'POST',
            transformRequest: function(document){
                console.log(document);
                //handle reduce relationship-endnotes to graphId
                for(var i = 0; i < document.document_relationships.length; i++){
                    var newDocumentRelationshipEndNode = {};
                    newDocumentRelationshipEndNode.graphId =
                        document.document_relationships[i].relating_document.graphId;
                    document.document_relationships[i].relating_document =
                        newDocumentRelationshipEndNode;
                }
                //end handle reduce relationship-endnotes to graphId
                console.log(document);
                return angular.toJson(document);
            }},
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
        //handle reduce relationship-endnotes to graphId
        for(var i = 0; i < data.document_relationships.length; i++){
            var newDocumentRelationshipEndNode = {};
            newDocumentRelationshipEndNode.graphId =
                data.document_relationships[i].relating_document.graphId;
            data.document_relationships[i].relating_document =
                newDocumentRelationshipEndNode;
        }
        //end handle reduce relationship-endnotes to graphId
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
