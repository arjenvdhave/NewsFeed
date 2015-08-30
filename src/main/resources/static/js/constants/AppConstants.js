'use strict'
;(function(app, m) {

    app.constant('API',{
       baseUrl: '/api'
    });

    app.constant('moment', m );

})(angular.module('news_feed'), moment);
