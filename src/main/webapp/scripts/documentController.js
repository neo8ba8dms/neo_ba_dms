angular.module('dmsApp').controller('documentController', function($scope, $state, documentService) {

    $scope.documents = [];
    $scope.document = new Document();

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
        });
    };


    //initial
    $scope.loadDocuments();
});