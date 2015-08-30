'use strict'
;(function(app) {
    app.controller('NewsFeedController', ['NewsFeedService','$scope','RSS_EVENTS',function(NewsFeedService, $scope, RSS_EVENTS){

        var newsFeedController = this;


        //####--Build up scope--######################################################################//

        newsFeedController.removeNewsItem = function(newsItemIdx, newsItem){
            NewsFeedService.remove( newsItem.id );

            //optimistic view update
            newsFeedController.newsItems.splice( newsItemIdx, 1);
        }


        //####--event handlers--######################################################################//

        $scope.$on(RSS_EVENTS.FIND_BY_SOURCE, function (event, feedId) {
            NewsFeedService.findAllFromSource(feedId).then(
                function(result){
                    newsFeedController.newsItems = result;
                },
                function(){
                    //error
                    console.log("Error in find by source :(");
                }
            )
        });

        $scope.$on( RSS_EVENTS.FIND_ALL, function (event ) {
            handleFindAll();
        });


        //####--private methods--######################################################################//

        function handleFindAll (){
            NewsFeedService.findAll().then(function(result){
                newsFeedController.newsItems = result;
            }, function(){
                console.log("Error :(");
            });
        }




        //####--init code--######################################################################//
        handleFindAll();


    }]);

})(angular.module('news_feed'));
