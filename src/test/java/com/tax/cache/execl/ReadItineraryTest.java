package com.tax.cache.execl;

import org.junit.Test;

import java.util.List;

public class ReadItineraryTest {

    @Test
    public void testBegin() throws Exception {
        ReadItinerary rr = new ReadItinerary("C:\\Users\\T440\\Desktop\\经典航线.xlsx", "Sheet1");
        List ls = rr.begin();
        System.out.println(ls.toString());
    }
}