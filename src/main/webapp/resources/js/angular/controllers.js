'use strict';

/* Controllers */

marykayApp.controller('CalendarController', function($modal, $log, $scope,$compile,uiCalendarConfig, BookingService){
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    /* event source that pulls from google.com */
    $scope.eventSource = {
        //url: "http://www.google.com/calendar/feeds/usa__en%40holiday.calendar.google.com/public/basic",
        //className: 'gcal-event',           // an option!
        //currentTimezone: 'America/Chicago' // an option!
    };
    /* event source that contains custom events on the scope */
    $scope.events = [
        {
            start: '2014-11-24',
            end: '2014-11-28',
            overlap: false,
            rendering: 'background',
            color: '#ff9f89'
        },
        {
            start: '2014-11-06',
            end: '2014-11-08',
            overlap: false,
            rendering: 'background',
            color: '#ff9f89'
        }


    ];
    /* event source that calls a function on every view switch */
    $scope.eventsF = function (start, end, timezone, callback) {


        BookingService.findByDates(start, end).then(function(data){
            var events = [];
            data.map( function(booking) {
                events.push(booking);
            });
            $log.info(events);
            callback(events);

        });



        //var s = new Date(start).getTime() / 1000;
        //var e = new Date(end).getTime() / 1000;
        //var m = new Date(start).getMonth();
        //var events = [{title: 'Feed Me ' + m,start: s + (50000),end: s + (100000),allDay: false, className: ['customFeed']}];
        //callback(events);
    };

    $scope.calEventsExt = {
        color: '#f00',
        textColor: 'yellow',
        events: [
            //{type:'party',title: 'Lunch',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
            //{type:'party',title: 'Lunch 2',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
            //{type:'party',title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
        ]
    };
    /* alert on eventClick */
    $scope.alertOnEventClick = function( date, jsEvent, view){
        $scope.alertMessage = (date.title + ' was clicked ');
    };
    /* alert on Drop */
    $scope.alertOnDrop = function(event, delta, revertFunc, jsEvent, ui, view){
        $scope.alertMessage = ('Event Droped to make dayDelta ' + delta);
    };
    /* alert on Resize */
    $scope.alertOnResize = function(event, delta, revertFunc, jsEvent, ui, view ){
        $scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
    };
    /* add and removes an event source of choice */
    $scope.addRemoveEventSource = function(sources,source) {
        var canAdd = 0;
        $log.info("removing "+sources);
        angular.forEach(sources,function(value, key){
            if(sources[key] === source){
                sources.splice(key,1);
                canAdd = 1;
            }
        });
        if(canAdd === 0){
            sources.push(source);
        }
    };
    /* add custom event*/
    $scope.addEvent = function(booking) {
        $scope.events.push(booking);
    };
    /* remove event */
    $scope.remove = function(index) {
        $log.info(index+"removing");
        $scope.events.splice(index,1);
    };


    /* Change View */
    $scope.changeView = function(view,calendar) {
        $log.info($scope.bookings);
        uiCalendarConfig.calendars[calendar].fullCalendar('changeView',view);
    };
    /* Change View */
    $scope.renderCalender = function(calendar) {
        if(uiCalendarConfig.calendars[calendar]){
            $log.info($scope.bookings);
            uiCalendarConfig.calendars[calendar].fullCalendar('render');
        }
    };

    $scope.select = function(start, end) {
        $scope.start = start;
        $scope.end = end;
        var modalInstance = $modal.open({
            templateUrl: 'rezerwationFormTemplate.html',
            controller: 'ModalAddBooking',
            //size: size,
            resolve: {
                start:  function () {
                    return $scope.start;
                },
                end: function () {
                    return $scope.end;
                }


            }
        });

        modalInstance.result.then(function (booking) {
            $log.info('newBooking ' + JSON.stringify(booking));
            $scope.addEvent(booking);
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });


    };

    /* Render Tooltip */
    $scope.eventRender = function( event, element, view ) {
        element.attr({'tooltip': event.title,
            'tooltip-append-to-body': true});
        $compile(element)($scope);
    };
    /* config object */
    $scope.uiConfig = {
        calendar:{
            defaultView: 'agendaWeek',
            lang: 'pl',
            height: 'auto',
            editable: true,
            selectable: true,
            selectHelper: true,
            header:{
                left: 'title',
                center: '',
                right: 'today prev,next'
            },
            eventClick: $scope.alertOnEventClick,
            eventDrop: $scope.alertOnDrop,
            eventResize: $scope.alertOnResize,
            eventRender: $scope.eventRender,
            select: $scope.select,
//            businessHours:{
//                start: '8:00', // a start time (10am in this example)
//                end: '22:00', // an end time (6pm in this example)
//
//                dow: [ 1, 2, 3, 4 ]
//                // days of week. an array of zero-based day of week integers (0=Sunday)
//                // (Monday-Thursday in this example)
//            } ,
            minTime: "8:00",
            maxTime: "22:00",
            allDaySlot: false


        }
    };


    //$scope.bookings = [];
    //
    $scope.refresh = function() {

            uiCalendarConfig.calendars['myCalendar'].fullCalendar('refetchEvents');


    };
    //$scope.refresh();
    /* event sources array*/
    $scope.eventSources = [$scope.events, $scope.eventsF];




});


// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

marykayApp.controller('ModalAddBooking', function ($log, $scope, $modalInstance, BookingService,  start, end) {
    $scope.userBooking = {
        phone: 'test',
        email: 'email',
        start: start,
        end: end

    };




    $scope.ok = function () {
        BookingService.book($scope.userBooking).then(function(result){
            $modalInstance.close(result);
        });

    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});



