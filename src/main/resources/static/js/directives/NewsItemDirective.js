;(function(app){
    app.directive('nfNewsitem', ['moment', function(moment) {
        return {
            restrict: 'E',
            scope: {
                newsitem:'=',
                itemIdx: '&',
                onRemoveClick: '&'
            },
            templateUrl: 'partials/NewsItemDirectiveTemplate.html',
            link: function(scope, el, attr) {
                scope.removeNewsItem = function( itemIdx, newsItem ){
                    scope.onRemoveClick({
                        newsItemIdx: itemIdx,
                        newsItem: newsItem
                    });
                }

                scope.formatPublishDate = function( date ){
                    return moment( date).locale('nl').calendar();
                }
            }
        }
    }])
})(angular.module('news_feed'));