package com.tax.cache.black;

import com.tax.cache.model.BlackRegular;
import com.tax.cache.model.Itinerary;

import java.util.LinkedList;
import java.util.List;

import static com.tax.cache.model.Constants.C;
import static com.tax.cache.model.Constants.N;
import static com.tax.cache.model.Constants.P;

/**
 * Created by ydc on 2019/10/28.
 */
public class BlackFactory extends AbstractBlack {

    public List<BlackRegular[]> set = new LinkedList<BlackRegular[]>();

    public BlackFactory() {
        set.add(new BlackRegular[]{new BlackRegular(N, "IN")});
        set.add(new BlackRegular[]{new BlackRegular(P,"SHA"), new BlackRegular(P, "NRT")});
    }

    public BlackFactory add(BlackRegular... reg) {
        set.add(reg);
        return this;
    }

    @Override
    public List<BlackRegular[]> black() {
        return this.set;
    }

    @Override
    public boolean isMatchReg(Itinerary itinerary, BlackRegular regular) {
        switch (regular.getType()) {
            case P:
                return itinerary.route.contains(regular.getVal());
            case C:
                return itinerary.cities.contains(regular.getVal());
            case N:
                return itinerary.nations.contains(regular.getVal());
            default:
                return true;
        }
    }
}
