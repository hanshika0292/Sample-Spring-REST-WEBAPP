package org.sample.controller;

import org.sample.model.HotelDeals;
import org.sample.service.HotelDealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hanshika on 2/10/16.
 */

@RestController
public class HotelDealsController {

    @Autowired
    HotelDealsService hotelDealsService;

    @RequestMapping(value = "/list",method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<HotelDeals>> getHotelDeals(){
        List<HotelDeals> hotelDealsList = new ArrayList<>();
        hotelDealsList = hotelDealsService.getAllHotelDeals();
        return new ResponseEntity<>(hotelDealsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/page/{number}",method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<HotelDeals>> getHotelDealsByPage(@PathVariable("number") int number){
        List<HotelDeals> hotelDealsList = new ArrayList<>();
        hotelDealsList = hotelDealsService.getAllHotelDeals();
        List<HotelDeals> refinedList = new ArrayList<>();
        int start = (number-1)*6;
        int end = number*6;
        if(end>hotelDealsList.size()){
            end=hotelDealsList.size();
        }
        for(int i=start;i<end;i++){
            refinedList.add(hotelDealsList.get(i));
        }
        return new ResponseEntity<>(refinedList, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/sortBy/{sortBy}",method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<HotelDeals>> getSortedList(@PathVariable("sortBy") String sort){
        List<HotelDeals> hotelDealsList = new ArrayList<>();
        hotelDealsList = hotelDealsService.getSortedListBy(sort);
        return new ResponseEntity<>(hotelDealsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/search/query/{query}",method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<HotelDeals>> getSearchList(@PathVariable("query") String query){
        List<HotelDeals> hotelDealsList = new ArrayList<>();
        hotelDealsList = hotelDealsService.getSearchListByNameorLocation(query);
        if (hotelDealsList.size() == 0) {
            hotelDealsList = hotelDealsService.getAllHotelDeals();
            return new ResponseEntity<>(hotelDealsList, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(hotelDealsList, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/stats",method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Map<String, Object>> getStats(){
        return new ResponseEntity<>(hotelDealsService.getRequiredStats(), HttpStatus.OK);
    }

}
