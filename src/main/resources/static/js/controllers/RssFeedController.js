/**
 * Created by havea on 30/08/15.
 */
'use strict'
;(function(app) {
    app.controller('RssFeedController', ['RssFeedService','$rootScope','RSS_EVENTS','$location',function(RssFeedService, $rootScope, RSS_EVENTS,$location){

        var rssCtrl = this;

        RssFeedService.findAll().then(
            function( result ){
                rssCtrl.feeds = result;
            },
            function(){
                //error handling
            }
        );

        rssCtrl.feeds = [];

        rssCtrl.handleRssItemClick = function ( rssFeed ) {
            rssCtrl.activeIndex = rssCtrl.feeds.indexOf( rssFeed ) +1 ;
            $rootScope.$broadcast(RSS_EVENTS.FIND_BY_SOURCE, rssFeed.id);
        }
        rssCtrl.handleRssShowAllClick = function ( ) {
            rssCtrl.activeIndex = 0 ;
            $rootScope.$broadcast(RSS_EVENTS.FIND_ALL);
        }

        rssCtrl.handleAddRssClick = function(){
            $location.path('/addRss');
        }

        rssCtrl.activeIndex = 0;

    }]);

})(angular.module('news_feed'));

