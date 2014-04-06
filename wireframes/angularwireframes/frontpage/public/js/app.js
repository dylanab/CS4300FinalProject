'use strict'
/**
 * Author: Christopher Ghyzel
 * Configures the application with dependency injection and routing using
 * AngularJS module ngRoute
 */
angular.module('frontexample', [ //module dependencies
    'ngRoute'
])
    .config(function ($routeProvider, $locationProvider) {
	$routeProvider
	    .when('/', {
		templateUrl: 'partials/mainview.html',
		controller: HomeCtrl
	    })
    });