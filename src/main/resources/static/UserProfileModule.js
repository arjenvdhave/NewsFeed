'use strict'
;(function() {
    angular.module('userProfile', [])
        .constant('USER_PROFILE', angular.copy(window.userProfile) );
})();