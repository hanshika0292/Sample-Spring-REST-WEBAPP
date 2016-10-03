package org.sample.service;

import com.sun.org.apache.regexp.internal.RE;
import org.sample.dao.HotelDealsDao;
import org.sample.model.HotelDeals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by hanshika on 2/10/16.
 */
@Service
public class HotelDealsServiceImpl implements HotelDealsService {

    @Autowired
    HotelDealsDao hotelDealsDao;
    private int apiHits = 0;

    @Override
    public List<HotelDeals> getAllHotelDeals() {
        apiHits++;
        return hotelDealsDao.getAllHotelDeals();
    }

    @Override
    public List<HotelDeals> getSortedListBy(String sort) {
        apiHits++;
        List<HotelDeals> hotelDealsList = hotelDealsDao.getAllHotelDeals();
        Collections.sort(hotelDealsList, new Comparator<HotelDeals>() {
            @Override
            public int compare(HotelDeals hd1, HotelDeals hd2) {
                try {
                    String output = sort.substring(0, 1).toUpperCase() + sort.substring(1);
                    Method m = hd1.getClass().getMethod("get" + output);
                    // Assume String type. If different, you must handle each type
                    String s1 = (String) (m.invoke(hd1)+"");
                    String s2 = (String) (m.invoke(hd2)+"");
                    return s1.compareTo(s2);
                    // simply re-throw checked exceptions wrapped in an unchecked exception
                } catch (SecurityException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return hotelDealsList;
    }

    @Override
    public List<HotelDeals> getSearchListByNameorLocation(String query) {
        apiHits++;
        List<HotelDeals> hotelDealsList = hotelDealsDao.getAllHotelDeals();
        List<HotelDeals> refinedList = new ArrayList<>();
        for(HotelDeals hotelDeals : hotelDealsList){
            if(hotelDeals.getName().contains(query) || hotelDeals.getLocation().contains(query)){
                refinedList.add(hotelDeals);
            }
        }
        return refinedList;
    }

    @Override
    public Map<String, Object> getRequiredStats() {
        apiHits++;
        List<HotelDeals> hotelDealsList = hotelDealsDao.getAllHotelDeals();
        Map<String, Object> result = new HashMap<>();
        result.put("averageRating", getAverageRating(hotelDealsList));
        result.put("apiHits", apiHits);
        result.put("price", getMinMaxPrice(hotelDealsList));
        result.put("areaWiseHotelDistribution", getAreaWiseHotelDistribution(hotelDealsList));
        return result;
    }

    private float getAverageRating(List<HotelDeals> hotelDealsList) {
        float avg = 0;
        for(HotelDeals hotelDeals : hotelDealsList){
            avg+=hotelDeals.getRating();
        }
        return (avg/hotelDealsList.size());
    }

    private List getMinMaxPrice(List<HotelDeals> hotelDealsList) {
        float minPrice=hotelDealsList.get(0).getActualPrice(),maxPrice=0;
        for(HotelDeals hotelDeals:hotelDealsList){
            if(hotelDeals.getActualPrice()<minPrice){
                minPrice=hotelDeals.getActualPrice();
            }
            if(hotelDeals.getActualPrice()>maxPrice){
                maxPrice=hotelDeals.getActualPrice();
            }
        }
        List<String> result = new ArrayList<>();
        result.add("Maximum : "+maxPrice);
        result.add("Minimum : "+minPrice);
        return result;
    }

    public Map getAreaWiseHotelDistribution(List<HotelDeals> hotelDealsList) {
        Map<String,Integer> result = new HashMap<>();
        for(HotelDeals hotelDeals:hotelDealsList){
            String loc = hotelDeals.getLocation();
            if(loc.contains("Bengaluru")){
                int count = result.containsKey("BLR") ? result.get("BLR") : 0;
                result.put("BLR", count + 1);
            }else if(loc.contains("New Delhi")){
                int count = result.containsKey("DEL") ? result.get("DEL") : 0;
                result.put("DEL", count + 1);
            }else if(loc.contains("Mumbai")){
                int count = result.containsKey("MUM") ? result.get("MUM") : 0;
                result.put("MUM", count + 1);
            }else if(loc.contains("Chennai")){
                int count = result.containsKey("CHE") ? result.get("CHE") : 0;
                result.put("CHE", count + 1);
            }else if(loc.contains("Hyderabad")){
                int count = result.containsKey("HYD") ? result.get("HYD") : 0;
                result.put("HYD", count + 1);
            }
        }
        return result;
    }
}
