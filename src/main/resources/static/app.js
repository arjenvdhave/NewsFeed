'use strict'
;(function() {
    angular.module('news_feed', ['userProfile','csrfUtil', 'ngRoute'])
        .run(['USER_PROFILE', function(USER_PROFILE){
            if( !USER_PROFILE || !USER_PROFILE.email ){
                //Unauthorized user, redirect here
                //TODO redirect when spring sec is in place
            }

        }])
        .config(['$routeProvider', function($routeProvider){
            $routeProvider.
                when('/newsfeed', {
                    templateUrl: 'partials/NewsFeedTemplate.html',
                    controller: 'NewsFeedController as newsFeedController'
                }).
                when('/addRss', {
                    templateUrl: 'partials/AddRssTemplate.html',
                    controller: 'AddRssController as addRssCtrl'
                }).
                otherwise({
                    redirectTo: '/newsfeed'
                });

        }]);
})();


