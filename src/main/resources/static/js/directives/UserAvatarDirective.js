/**
 * Created by havea on 30/08/15.
 */
;(function(app){
    app.directive('nfAvatar', ['USER_PROFILE', function(USER_PROFILE) {
        return {
            restrict: 'E',
            scope: {
                showAsSmall: '='
            },
            templateUrl: 'partials/UserAvatarTemplate.html',
            link: function(scope, el, attr) {
                scope.small = false
                if( scope.showAsSmall && scope.showAsSmall === true )
                    scope.small = true;

                scope.avatarUrl = USER_PROFILE.avatar;
                scope.user = USER_PROFILE.name;
            }
        }
    }])
})(angular.module('news_feed'));
