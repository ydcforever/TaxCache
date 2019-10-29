package com.tax.cache.util;

import com.tax.cache.model.Itinerary;

public interface IItineraryService {

    void analyzeItinerary(Itinerary itinerary);

    Itinerary itineraryRT(Itinerary itinerary);

    boolean sameNation(String na1, String na2);
}
