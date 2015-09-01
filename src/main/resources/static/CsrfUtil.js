/**
 * Created by havea on 01/09/15.
 */
'use strict'
;(function() {
    angular.module('csrfUtil', [])
            .constant('CSRF', angular.copy(window.csrf) );
})();