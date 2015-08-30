/**
 * Created by havea on 30/08/15.
 */
'use strict'
;(function(app) {
    app.controller('AddRssController', ['RssFeedService','$location',function(RssFeedService,$location){

        var rssCtrl = this;

        rssCtrl.handleCancelClick = function(){
            $location.path('/newsfeed');
        }

        rssCtrl.handleSaveClick = function(){
            var rssFeed = {
                name: rssCtrl.rssFeedName,
                url: rssCtrl.rssFeedUrl
            }
            RssFeedService.create( rssFeed).then(
                function(){
                    $location.path('/newsfeed');
                },
                function(){}
            );
        }

    }]);

})(angular.module('news_feed'));

