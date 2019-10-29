package com.tax.cache.black;

import com.tax.cache.model.BlackRegular;
import com.tax.cache.model.Itinerary;

import java.util.List;

/**
 * Created by ydc on 2019/10/28.
 */
public interface IBlack {

    List<BlackRegular[]> black();

    boolean isMatchReg(Itinerary itinerary, BlackRegular regular);
}
