angular.module('dmsApp').controller('documentOverviewController', function($scope, $state, documentService) {

    $scope.documents = [];
    $scope.document = {};

    $scope.loadDocuments = function(){
        documentService.query(function(docs){
            $scope.documents = docs;
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
    $scope.external_object_references = {};
    $scope.isUpdateMode = true;
    $scope.listOfRecentDocumentVersions = {};
    $scope.tmpRelationship = {};

    $scope.loadDocument = function(id){
        documentService.get({id: id}, function(doc){
            $scope.document = doc;
        });
    };

    $scope.updateDocument = function(){
        var uploadUrl = "/api/documents/" + $stateParams.id;

        var tmpFile = $scope.document.file; //save to add after angular.copy //probably better solution possible
        var correctDocument = angular.copy($scope.document); //removes $$hashKey which messes up things
        correctDocument.file = tmpFile;

        documentUpdateService.updateDocument(uploadUrl, correctDocument).then(function(response){
            $scope.document = response.data; //useless because following reload, but removing breaks something
            $location.path('documents/' + $scope.document.graphId);
        });

    };

    //todo should be made generic
    $scope.showModal = function(){
        documentService.query(function(docs){
            $scope.listOfRecentDocumentVersions = docs;
        });
    };

    $scope.createNewRealationship = function(){
        //add relationship to list
        if(!$scope.document.documentRelationships){
            $scope.document.documentRelationships = [];
        }
        $scope.document.documentRelationships.push($scope.tmpRelationship);
        //reset the addable relationship
        $scope.tmpRelationship = {};
        $('#createNewRelationshipModal').modal('hide');
    };

    //initial
    $scope.loadDocument($stateParams.id);
    eorService.query(function(eors){
        $scope.external_object_references = eors;
    });
});

angular.module('dmsApp').controller('documentDetailsCreateController', function($scope, documentService, eorService) {

    $scope.document = {};
    $scope.lastCreatedDocument = {};
    $scope.external_object_references;
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
        $scope.external_object_references = eors;
    });
});

