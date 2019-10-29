package com.tax.cache.black;

import com.tax.cache.model.BlackRegular;
import com.tax.cache.model.Itinerary;
import com.tax.cache.util.IItineraryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.tax.cache.model.Constants.N;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/config.xml")
@Transactional
public class BlackFactoryTest {
    IItineraryService itineraryService;

    @Autowired
    public void setRouteService(IItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @Test
    public void testRemove() throws Exception {
        Itinerary itinerary = new Itinerary("JFK-PVG;PVG-FRA");
        itineraryService.analyzeItinerary(itinerary);
        BlackRegular r1 = new BlackRegular(N,"CN");
        BlackRegular r2 = new BlackRegular(N,"ID");
        BlackFactory factory = new BlackFactory();
        factory.add(r1, r2);
        System.out.println(factory.isBlank(itinerary));
    }
}