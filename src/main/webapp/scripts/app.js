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
            controller: 'documentController',
            onEnter: function(){
                console.log('did enter documents');
            }
        })
        .state('documentDetails', {
            url: '/documents/{id}',
            templateUrl: 'html/documentDetail.html',
            controller: 'documentDetailController'
        });

    $urlRouterProvider.otherwise('/home');
});




