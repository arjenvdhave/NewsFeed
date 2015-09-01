'use strict'
;(function(app) {
    app.factory('NewsFeedService',['$http', '$q', 'API','CSRF', function($http, $q, API, CSRF){

        var findAll = function(){
            var deferred = $q.defer();
            $http.get(API.baseUrl+'/newsfeed')
                .success(deferred.resolve)
                .error(deferred.reject);
            return deferred.promise;

        };

        var findAllFromSource = function( sourceId ){
            var deferred = $q.defer();
            $http.get(API.baseUrl+'/newsfeed/fromsource/'+sourceId)
                .success(deferred.resolve)
                .error(deferred.reject);
            return deferred.promise;

        };



        var removeNewsFeed = function ( newsFeedId) {
            var deferred = $q.defer();
            $http.delete(API.baseUrl+"/newsfeed/"+newsFeedId+"?"+CSRF.name + '=' + CSRF.token)
                .success( deferred.resolve )
                .error( deferred.reject );

            return deferred.promise;
        }

        return {
            findAll: findAll,
            findAllFromSource: findAllFromSource,
            remove: removeNewsFeed
        };


    }]);


})(angular.module('news_feed'));


