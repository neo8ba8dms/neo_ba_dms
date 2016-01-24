angular.module('dmsApp').controller('documentController', function($scope, $state, documentService) {

    $scope.documents = [];
    $scope.document = {};

    $scope.loadDocuments = function(){
        documentService.query(function(docs){
            $scope.documents = docs;
            console.log("Did (re-)load all documents");
            console.log($scope.documents);
        });
    };

    $scope.createNewDocument = function(){
        documentService.save($scope.document, function(){
            $('#createDocumentModal').modal('hide');
            $scope.loadDocuments();
            $scope.document = {};
        });
    };
    $scope.delete = function(id){
        documentService.delete({id: id}, function(){
            $scope.loadDocuments();
        });
    };


    //initial
    $scope.loadDocuments();
});

/*********************************************************************************************************/

angular.module('dmsApp').controller('documentDetailController', function($scope, $stateParams, documentService, eorService, fileUploadService) {

    $scope.document = {};
    $scope.externalObjects = {};

    $scope.loadDocument = function(id){
        documentService.get({id: id}, function(doc){
            $scope.document = doc;
        });
    };

    $scope.update = function(){
      documentService.update($scope.document);
    };

    $scope.loadExternalObjects = function(){
        eorService.query(function(eors){
            $scope.externalObjects = eors;
            console.log("Did (re-)load all external objects");
            console.log($scope.externalObjects);
        });
    };

    $scope.uploadFile = function(){
        var uploadUrl = "/documentUpload";
        fileUploadService.uploadFileToUrl(uploadUrl, $scope.document);
    }

    //initial
    $scope.loadDocument($stateParams.id);
    $scope.loadExternalObjects();
});
