package com.tax.cache.util;
import com.tax.cache.model.Itinerary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/config.xml")
public class ItineraryServiceTest {
    IItineraryService itineraryService;

    @Autowired
    public void setRouteService(IItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @Test
    public void testAnalyzeRoute() throws Exception {
        Itinerary route = new Itinerary("JFK-PVG;PVG-FRA");
        itineraryService.analyzeItinerary(route);
    }
}