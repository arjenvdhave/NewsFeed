'use strict'
;(function(app) {
    app.factory('RssFeedService',['$http', '$q', 'API','CSRF', function($http, $q, API,CSRF){

        var findAll = function(){
            var deferred = $q.defer();
            $http.get(API.baseUrl+'/rssfeed')
                .success(deferred.resolve)
                .error(deferred.reject);
            return deferred.promise;

        };

        var createRssFeed = function(rssFeed){
            var deferred = $q.defer();
            $http.post(API.baseUrl+'/rssfeed'+"?"+CSRF.name + '=' + CSRF.token, rssFeed)
                .success(deferred.resolve)
                .error(deferred.reject);
            return deferred.promise;

        };

        return {
            findAll: findAll,
            create: createRssFeed
        };


    }]);


})(angular.module('news_feed'));


