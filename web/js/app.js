'use strict'
/**
 * Author: Christopher Ghyzel
 * Configures the application with dependency injection and routing using
 * AngularJS module ngRoute. The ArticleList is a dynamically generated
 * Factory where normally I would prefer to use REST to get the JSON using 
 * RestAngular and AJAX, but due to lack of knowledge and time contraints,
 * this was unfeasible.
 */
angular.module('frontPage', [ //module dependencies
    'ngRoute',
    'ArticleFactory'
])
    .config(function ($routeProvider, $locationProvider) {
	$routeProvider
	    .when('/:articleId?', {
		templateUrl: 'partials/frontPageView.html',
		controller: SearchCtrl
	    })
	    .otherwise( {
		redirectTo: '/'
	    })
    });