var app = angular.module('dmsApp', ['ui.router','ngResource']);

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

app.factory('documentService', function($resource){
   return $resource('http://localhost:8080/documents/:id')
});

app.controller('documentController', function($scope, documentService){
    var documents = documentService.query();
    var document = documentService.get({id:2},function(){
        console.log(document);

    });

    console.log(documents);


});