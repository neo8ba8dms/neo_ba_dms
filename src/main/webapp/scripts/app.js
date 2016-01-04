var app = angular.module('dmsApp', ['ui.router','ngResource', 'spring-data-rest']);

//important!!! Nicht diese ganzen fancy Frontend-Frameworks nutzen, sondern selbst implementieren

app.config(function($stateProvider, $urlRouterProvider) {

    $stateProvider

        .state('home', {
            url: '/home',
            templateUrl: 'html/welcome.html'
        })
        .state('documents', {
            url: '/documents',
            templateUrl: 'html/documents.html',
            controller: 'documentController'
        });

    $urlRouterProvider.otherwise('/home');
});

/*
app.factory('documentService', function($resource){
   return $resource('http://localhost:8080/api/documents');
});
*/

app.controller('documentController', function($scope, $http, SpringDataRestAdapter){


/*
    var documents = documentService.query(null, function(){

        //console.log(documents);

    });
    var document = documentService.get({id:2},function(){
        console.log(document);

    });
*/
var httpPromise = $http.get('http://localhost:8080/api/documents').success(function (response) {
    $scope.response = angular.toJson(response, true);
    console.log($scope.response);
});

SpringDataRestAdapter.process(httpPromise).then(function (processedResponse) {
    $scope.categories = processedResponse._embeddedItems;
    $scope.processedResponse = angular.toJson(processedResponse, true);
    console.log("blub"+$scope.categories[0]);
    console.log($scope.processedResponse);
});




});