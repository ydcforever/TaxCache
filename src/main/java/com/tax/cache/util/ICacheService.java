package com.tax.cache.util;

import com.tax.cache.model.Itinerary;

import java.util.List;

/**
 * Created by ydc on 2019/10/24.
 */
public interface ICacheService {

    List<Itinerary> selector(List<String> its);

    boolean selector(Itinerary itinerary);

    boolean sixPortion(Itinerary itinerary);


    List<Itinerary> routeCartesian(Itinerary itinerary);

    String[] psg();

    String[] cabin();
}
