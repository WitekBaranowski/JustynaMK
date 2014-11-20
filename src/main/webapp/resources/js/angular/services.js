'use strict';

/* Services */

marykayApp.factory('BookingService', function ($log, $http) {
    return {
        book: function (booking) {
            booking.start = new Date(booking.start).getTime();
            booking.end =  new Date(booking.end).getTime();
            return $http.post('booking', booking).then(function (response) {
                return response.data;
            });
        },

        findAll: function () {
            return $http.get('booking').then(function (response) {
                return response.data;
            });
        },
        findByDates: function (fromDate, toDate) {
            return $http.get('booking',
                {
                    params: {fromDate: fromDate, toDate: toDate}
                }).then(function (response) {
                    return response.data;
                });
        }
    }
});



