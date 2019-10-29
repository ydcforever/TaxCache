package com.tax.cache.util;

import com.tax.cache.execl.ReadItinerary;
import com.tax.cache.model.Itinerary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/config.xml")
@Transactional
public class CacheServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(CacheServiceTest.class);

    ICacheService cacheService;

    IItineraryService itineraryService;

    @Autowired
    public void setCacheService(ICacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Autowired
    public void setRouteService(IItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @Test
    public void testItineraryRT() throws Exception {
        Itinerary route = new Itinerary("SHA-KMG;KMG-NRT");
        itineraryService.analyzeItinerary(route);
        Itinerary rt = itineraryService.itineraryRT(route);
        logger.info("Round trip [{}] is {}", rt.route, rt.isRT);
    }

    @Test
    public void testSelector() throws Exception {
        ReadItinerary rr = new ReadItinerary("C:\\Users\\T440\\Desktop\\经典航线筛选.xlsx", "Sheet1");
        List ls = rr.begin();
        List<Itinerary> list = cacheService.selector(ls);
        logger.info("select {} route", list.size());
    }
}