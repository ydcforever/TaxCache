package com.tax.cache.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by ydc on 2019/10/24.
 */
public interface IAirportDao {

    Map<String, String> queryAirportInfo(String... airport);

    String getCity(String airport);

    String getNation(String airport);

    List getArea(String... airport);

    Map getAreas(String... airport);
}
