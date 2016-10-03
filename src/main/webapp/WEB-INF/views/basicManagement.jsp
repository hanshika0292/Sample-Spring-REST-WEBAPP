<%--
  Created by IntelliJ IDEA.
  User: hanshika
  Date: 2/10/16
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AngularJS Spring RESTful APP</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/styles.css' />" rel="stylesheet"/>
</head>
<body ng-app="hotelDealsApp" class="ng-cloak">
<div class="generic-container" ng-controller="hotelDealsController as ctrl">
    <header>
        <img  src="https://images.treebohotels.com/images/favicon-3.ico?212" style="width: 50px;height: 50px">
        <span class="blue">D</span>eals<span class="blue">H</span>ub
        <span class="light-blue">-- finding hotel deals made easy!</span>
    </header>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">
            <span class="lead">List of Hotel Deals <span style="font-size: 10px">(Sorting implemented on the header names of the table)</span></span>
            <div class="search-holder" style="width: 400px;margin-top: -10px;margin-right: -12px">
                <span style="font-size: 20px;color: #1b6d85">Stats:</span>
                <div ng-init="initializeStats();">
                    <p> Average Rating : <span>{{stats.averageRating}}</span>
                    </p>
                    <p> Price : <span>{{stats.price}}</span> | Api Hits : <span>{{stats.apiHits}}</span>
                    </p>
                    <p> Area Wise Hotel Distribution :
                        Bangalore : <span>{{stats.areaWiseHotelDistribution.BLR}}</span>
                        Delhi : <span>{{stats.areaWiseHotelDistribution.DEL}}</span>
                        Hyderabad : <span>{{stats.areaWiseHotelDistribution.HYD}}</span>
                        Chennai : <span>{{stats.areaWiseHotelDistribution.CHE}}</span>
                        Mumbai : <span>{{stats.areaWiseHotelDistribution.MUM}}</span>
                    </p>
                </div>
            </div>
            <div class="search-holder" style="margin-right: -10px;margin-top: -10px">
                <input list="tagList" ng-model="searchBox" name="search" class="search-box" placeholder=" Search by Name or Location">
                <datalist id="tagList"></datalist>
                <button ng-click="searchInList();">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
            </span>
            </div>
        </div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>
                        <a ng-click="SortBy('id');">S.No</a>
                    </th>
                    <th>
                        <a ng-click="SortBy('name');">Name</a>
                    </th>
                    <th colspan="2">
                        Image
                    </th>
                    <th>
                        <a ng-click="SortBy('rating');">Rating</a>
                    </th>
                    <th>
                        <a ng-click="SortBy('actualPrice');">Actual Price</a>
                    </th>
                    <th>
                        <a ng-click="SortBy('discount');">Discount</a>
                    </th>
                    <th>
                        <a ng-click="SortBy('location');">Location</a>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="hotelDeal in ctrl.hotelDeals">
                    <td colspan="1">
                        <span ng-bind="hotelDeal.id"></span>
                    </td>
                    <td colspan="1">
                        <a ng-href="{{hotelDeal.link}}" target="_blank"><span ng-bind="hotelDeal.name"></span></a>
                    </td>
                    <td colspan="2">
                        <img ng-src="{{hotelDeal.image}}">
                    </td>
                    <td colspan="1">
                        <span ng-bind="hotelDeal.rating"></span>
                    </td>
                    <td colspan="1">
                        <span ng-bind="hotelDeal.actualPrice"></span>Rs.
                    </td>
                    <td colspan="1">
                        <span ng-bind="hotelDeal.discount"></span>%
                    </td>
                    <td colspan="1">
                        <span ng-bind="hotelDeal.location"></span>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/hotelDealsService.js' />"></script>
<script src="<c:url value='/static/js/controller/hotelDealsController.js' />"></script>
</body>
</html>