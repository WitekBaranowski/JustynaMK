'use strict';

/* Services */

marykayApp.factory('BookingService', function ($log, $http) {
    return {
        book: function (booking) {
            booking.start = booking.start.utc().valueOf();
            booking.end =  booking.end.utc().valueOf();
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
            return $http.get('booking/byDates',
                {
                    params: {fromDate: fromDate.utc().format(), toDate: toDate.utc().format()}
                }).then(function (response) {
                    return response.data;
                });
        }
    }
});



