package com.tax.cache.util;

import com.tax.cache.dao.IAirportDao;
import com.tax.cache.model.Itinerary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

import static com.tax.cache.model.Constants.C_POS;
import static com.tax.cache.model.Constants.N_POS;

@Service
public class ItineraryService implements IItineraryService {

    private static final Logger logger = LoggerFactory.getLogger(ItineraryService.class);

    private IAirportDao airportDao;

    @Autowired
    public void setAirportDao(IAirportDao airportDao) {
        this.airportDao = airportDao;
    }

    @Override
    public void analyzeItinerary(Itinerary itinerary) {
        String[] sectors = itinerary.route.split(";");
        int len = sectors.length;
        itinerary.airports = new String[len * 2];
        itinerary.airInfo = new String[len * 2][11];
        itinerary.secL = len;
        itinerary.airL = len * 2;
        for(int i = 0; i < len; i++) {
            itinerary.airports[i * 2] = sectors[i].substring(0, 3);
            itinerary.airports[i * 2 + 1] = sectors[i].substring(4, 7);
        }
        Map<String, String> map = airportDao.queryAirportInfo(itinerary.airports);

        String nations = "";
        String intlDom = "";
        String cities = "";
        try {
            for(int i = 0; i < len; i++) {
                String[] air1 = map.get(itinerary.airports[i * 2]).split(";");
                itinerary.airInfo[i * 2] = air1;
                String na1 = ru(air1[N_POS]);
                String[] air2 = map.get(itinerary.airports[i * 2 + 1]).split(";");
                itinerary.airInfo[i * 2 + 1] = air2;
                String na2 = ru(air2[N_POS]);
                boolean isDom = sameNation(na1, na2);
                nations += isDom ? na1 + ";" : na1 + "-" + na2 + ";";
                intlDom += isDom ? "D" : "I";
                cities += air1[C_POS] + "-" + air2[C_POS] + ";";
                if (i== 0) {
                    itinerary.org = air1;
                }
                if (i == len - 1) {
                    itinerary.dest = air2;
                }
                if(i > 0 && (!itinerary.airInfo[i * 2 - 1][C_POS].equals(air1[C_POS]))) {
                    itinerary.hasOJ = true;
                }
            }
            itinerary.cities = cities;
            itinerary.nations = nations;
            itinerary.intlDom = intlDom;
            itinerary.isDom = !itinerary.intlDom.contains("I");
            if(itinerary.isDom) {
                itinerary.isRT = itinerary.org[C_POS].equals(itinerary.dest[C_POS]);
            } else {
                itinerary.isRT = sameNation(itinerary.org[N_POS], itinerary.dest[N_POS]);
            }
            itinerary.valid = true;
        }catch (Exception e) {
            logger.warn("{} is invalid !", itinerary.route);
        }
    }

    @Override
    public Itinerary itineraryRT(Itinerary itinerary) {
        String rt = itinerary.route + ";";
        for(int i = itinerary.airL - 1; i >= 0; i -= 2) {
            rt += itinerary.airports[i] + "-" + itinerary.airports[i - 1] + ";";
        }
        Itinerary newRoute = new Itinerary(rt);
        analyzeItinerary(newRoute);
        return newRoute;
    }

    @Override
    public boolean sameNation(String na1, String na2) {
        String n1 = ru(na1);
        String n2 = ru(na2);
        return n1.equals(n2);
    }

    public String ru(String nation) {
        return "XU".equals(nation) ? "RU" : nation;
    }

}
