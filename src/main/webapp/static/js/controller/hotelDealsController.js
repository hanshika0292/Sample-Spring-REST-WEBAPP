/**
 * Created by hanshika on 2/10/16.
 */
'use strict';

angular.module('hotelDealsApp').controller('hotelDealsController', ['$scope', 'hotelDealsService', function($scope, hotelDealsService) {
    var self = this;
    self.hotelDeal={id:null,name:'',image:'',rating:'',link:'',actualPrice:'',discount:'',location:''};
    self.hotelDeals=[];
    $scope.stats = {averageRating:null,apiHits:null, price:[],areaWiseHotelDistribution:{}};
    $scope.iconSrc = "/images/icon.png";
    $scope.numberOfResults = 0;

    fetchAllHotelDeals();

    function fetchAllHotelDeals(){
        hotelDealsService.fetchAllHotelDeals()
            .then(
                function(d) {
                    self.hotelDeals = d;
                    $scope.numberOfResults = self.hotelDeals.length;
                },
                function(errResponse){
                    console.error('Error while fetching Hotel Deals');
                }
            );
    };

    $scope.SortBy = function(sortby){
        hotelDealsService.sortListBy(sortby)
            .then(
                function(d) {
                    self.hotelDeals = d;
                },
                function(errResponse){
                    console.error('Error while fetching Hotel Deals');
                }
            );
    };

    $scope.searchInList = function(){
        var val = $scope.searchBox;
        if(val=="" || val=="undefined"){
            hotelDealsService.fetchAllHotelDeals()
                .then(
                    function (d) {
                        self.hotelDeals = d;
                        $scope.numberOfResults = self.hotelDeals.length;
                    },
                    function (errResponse) {
                        console.error('Error while fetching Hotel Deals');
                    }
                );
        }else {
            hotelDealsService.searchListBy(val)
                .then(
                    function (d) {
                        self.hotelDeals = d;
                        $scope.numberOfResults = self.hotelDeals.length;
                    },
                    function (errResponse) {
                        console.error('Error while fetching Hotel Deals');
                    }
                );
        }

    };

    $scope.initializeStats = function () {
        hotelDealsService.getStats()
            .then(
                function (d) {
                    $scope.stats.apiHits = d.apiHits;
                    $scope.stats.areaWiseHotelDistribution = d.areaWiseHotelDistribution;
                    $scope.stats.averageRating = d.averageRating;
                    $scope.stats.price = d.price;
                },
                function (errResponse) {
                    console.error('Error while fetching Hotel Deals');
                }
            );
    };

    $scope.getPage = function (pageNo) {
        hotelDealsService.fetchAllHotelDealsOnPage(pageNo)
            .then(
                function(d) {
                    self.hotelDeals = d;
                    $scope.numberOfResults = self.hotelDeals.length;
                },
                function(errResponse){
                    console.error('Error while fetching Hotel Deals');
                }
            );
    }

}]);