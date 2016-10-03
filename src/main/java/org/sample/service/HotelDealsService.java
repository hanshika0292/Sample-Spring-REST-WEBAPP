package org.sample.service;

import org.sample.model.HotelDeals;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 2/10/16.
 */
public interface HotelDealsService {
    List<HotelDeals> getAllHotelDeals();

    List<HotelDeals> getSortedListBy(String sort);

    List<HotelDeals> getSearchListByNameorLocation(String query);

    Map<String,Object> getRequiredStats();
}
