package com.tax.cache.util;

import com.tax.cache.black.BlackFactory;
import com.tax.cache.model.BlackRegular;
import com.tax.cache.model.Itinerary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.tax.cache.model.Constants.N;
/**
 * Created by ydc on 2019/10/24.
 */
@Service
public class CacheService implements ICacheService {

    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    private IItineraryService itineraryService;

    private static BlackFactory black;

    static {
        black = new BlackFactory();
        black.add(new BlackRegular(N, "GB"));
    }

    @Autowired
    public void setItineraryService(IItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @Override
    public List<Itinerary> selector(List<String> its) {
        List<Itinerary> list = new ArrayList<Itinerary>();
        for(String it : its) {
            Itinerary itinerary = new Itinerary(it);
            itineraryService.analyzeItinerary(itinerary);
            if(itinerary.valid && selector(itinerary)) {
                list.add(itinerary);
            }
        }
        return list;
    }

    @Override
    public boolean selector(Itinerary itinerary) {
        if (itinerary.isDom || itinerary.secL == 1) {
            return true;
        }

        if(black.isBlank(itinerary)) {
            return false;
        }

        if (itinerary.hasOJ) {
            return false;
        }

//        if(itinerary.isRT) {
//            return itinerary.secL <= 4;
//        } else {
//            return itinerary.secL <= 2;
//        }
        return true;
    }

    @Override
    public boolean sixPortion(Itinerary itinerary) {
        return false;
    }

    @Override
    public List<Itinerary> routeCartesian(Itinerary itinerary) {
        String[] psgs = psg();
        String[] cabins = cabin();
        for(String psg : psgs) {
            for(String cabin : cabins) {
                break;
            }
        }
        return null;
    }

    @Override
    public String[] psg() {
        return new String[]{"ADT", "CHD", "INF"};
    }

    @Override
    public String[] cabin() {
        return new String[]{"F", "C", "Y"};
    }
}
