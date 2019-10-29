package com.tax.cache.black;

import com.tax.cache.model.BlackRegular;
import com.tax.cache.model.Itinerary;

import java.util.List;

/**
 * Created by ydc on 2019/10/28.
 */
public abstract class AbstractBlack implements IBlack {

    public boolean isBlank(Itinerary itinerary) {
        List<BlackRegular[]> sets = black();
        for(BlackRegular[] set : sets) {
            if(isMatchAll(itinerary, set)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMatchAll(Itinerary itinerary, BlackRegular... regular) {
        for (BlackRegular reg : regular) {
            if (!isMatchReg(itinerary, reg)) {
                return false;
            }
        }
        return true;
    }
}
