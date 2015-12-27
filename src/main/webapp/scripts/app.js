var app = angular.module('dmsApp', ['ui.router']);

app.config(function($stateProvider, $urlRouterProvider) {

    $stateProvider

        .state('home', {
            url: '/home',
            templateUrl: 'html/welcome.html'
        })
        .state('documents', {
            url: '/documents',
            templateUrl: 'html/documents.html'
        });

    $urlRouterProvider.otherwise('/home');
});