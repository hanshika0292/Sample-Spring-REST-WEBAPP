/**
 * Created by hanshika on 2/10/16.
 */
'use strict';

angular.module('hotelDealsApp').factory('hotelDealsService', ['$http', '$q', function($http, $q){

    // var baseURL = 'http://hanshika.cloud.cms500.com/';
    var baseURL = 'http://localhost:8080/';

    var factory = {
        fetchAllHotelDeals: fetchAllHotelDeals,
        sortListBy : sortListBy,
        searchListBy : searchListBy,
        getStats : getStats
    };

    return factory;

    function fetchAllHotelDeals() {
        var deferred = $q.defer();
        $http.get(baseURL+'list')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Hotel Deals');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function sortListBy(sortby) {
        var deferred = $q.defer();
        $http.get(baseURL+'list/sortBy/'+sortby)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Hotel Deals');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function searchListBy(val) {
        var deferred = $q.defer();
        $http.get(baseURL+'search/query/'+val)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Hotel Deals');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getStats() {
        var deferred = $q.defer();
        $http.get(baseURL+'stats')
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Hotel Deals');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


}]);