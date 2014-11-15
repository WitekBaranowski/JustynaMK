//angular.module("marykayApp.services", ["ngResource"]).
//    factory('Booking', function ($resource) {
//        var Booking = $resource('/booking/:bookingId', {bookingId: '@id'});
//        Booking.prototype.isNew = function(){
//            return (typeof(this.id) === 'undefined');
//        };
//        return Booking;
//    });

//angular.module("marykayApp", ["marykayApp.services"]).
//    config(function ($routeProvider) {
//        $routeProvider
//            .when('/', {templateUrl: 'views/stories/list.html', controller: StoryListController})
//            //.when('/stories/new', {templateUrl: 'views/stories/create.html', controller: StoryCreateController})
//    });

function StoryListController($scope, Booking) {
    $scope.bookings = Booking.query();

}

function StoryCreateController($scope, $routeParams, $location, Story) {

    $scope.story = new Story();

    $scope.save = function () {
        $scope.story.$save(function (story, headers) {
            toastr.success("Submitted New Story");
            $location.path('/');
        });
    };
}
var app = angular.module("marykayApp", []);
app.controller('callendarController', function($http, $scope){
    $scope.bookingsData = {};
    $scope.bookingsData.fetchBookings = function(item, event) {

        var responsePromise = $http.get("/booking");

        responsePromise.success(function(data, status, headers, config) {
            $scope.bookingsData.bookings = data
        });
        responsePromise.error(function(data, status, headers, config) {
            alert("AJAX failed!");
        });
    }
});


