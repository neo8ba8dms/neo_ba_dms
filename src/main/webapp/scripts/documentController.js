angular.module('dmsApp').controller('documentOverviewController', function($scope, $state, documentService) {

    $scope.documents = [];
    $scope.document = {};

    $scope.loadDocuments = function(){
        documentService.query(function(docs){
            $scope.documents = docs;
            console.log("Did (re-)load all documents");
            console.log($scope.documents);
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

angular.module('dmsApp').controller('documentDetailsUpdateController', function($scope, $stateParams, $location, documentService, eorService, documentUpdateService) {

    $scope.document = {};
    $scope.externalObjects = {};
    $scope.isUpdateMode = true;

    $scope.loadDocument = function(id){
        documentService.get({id: id}, function(doc){
            $scope.document = doc;
        });
    };

    $scope.loadExternalObjects = function(){
        eorService.query(function(eors){
            $scope.externalObjects = eors;
            console.log("Did (re-)load all external objects");
            console.log($scope.externalObjects);
        });
    };

    $scope.updateDocument = function(){
        var uploadUrl = "/api/documents/" + $stateParams.id;
        documentUpdateService.updateDocument(uploadUrl, $scope.document).then(function(response){
            $scope.document = response.data;
            $location.path('documents/' + $scope.document.id);
        });

    };

    //initial
    $scope.loadDocument($stateParams.id);
    $scope.loadExternalObjects();
});

angular.module('dmsApp').controller('documentDetailsCreateController', function($scope, documentService, eorService) {

    $scope.document = {};
    $scope.lastCreatedDocument = {};
    $scope.externalObjects;
    $scope.isCreateMode = true;
    $scope.showNewDocument = false;

    $scope.createNewDocument = function(){
        documentService.save($scope.document, function(response){
            $scope.showNewDocument = true;
            $scope.document = {};
            $scope.lastCreatedDocument = response;
        });
    };

    //initial
    eorService.query(function(eors){
        $scope.externalObjects = eors;
    });
    console.log($scope.externalObjects);

});

