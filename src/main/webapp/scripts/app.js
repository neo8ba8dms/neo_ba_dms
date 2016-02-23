var app = angular.module('dmsApp', ['ui.router','ngResource', 'checklist-model']);


app.config(function($stateProvider, $urlRouterProvider) {

    $stateProvider

        .state('home', {
            url: '/home',
            templateUrl: 'html/welcome.html'
        })
        .state('documents', {
            url: '/documents',
            templateUrl: 'html/documents.html',
            controller: 'documentOverviewController'
        })
        .state('documentDetailsUpdate', {
            url: '/documents/{id}',
            templateUrl: 'html/documentDetail.html',
            controller: 'documentDetailsUpdateController'
        })
        .state('documentDetailsCreate', {
            url: 'documents',
            templateUrl: 'html/documentDetail.html',
            controller: 'documentDetailsCreateController'
        })
        .state('externalObjects', {
            url: '/eor',
            templateUrl: 'html/externalObjects.html',
            controller: 'externalObjectsController'
        })
        .state('externalObjectDetails', {
            url: '/eor/{id}',
            templateUrl: 'html/externalObjectDetails.html',
            controller: 'externalObjectDetailsController'
        })
        .state('versionHistory', {
            url: '/versionHistory/{idOfNewestDocument}',
            templateUrl: 'html/versionHistory.html',
            controller: 'versionHistoryController'

        })
        .state('versionHistoryDetails', {
            url: '/versionHistoryDetails/{documentVersionId}',
            templateUrl: 'html/documentDetail.html',
            controller: 'versionHistoryDetailsController'
        })
        .state('persons', {
            url: '/person',
            templateUrl: 'html/persons.html',
            controller: 'personsController'
        })
        .state('personDetails', {
            url: '/person/{id}',
            templateUrl: 'html/personDetails.html',
            controller: 'personDetailsController'
        });

    $urlRouterProvider.otherwise('home');
});




