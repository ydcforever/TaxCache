package com.tax.cache.dao.impl;

import com.tax.cache.dao.IAirportDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/config.xml")
@Transactional
public class AirportDaoTest {

    private IAirportDao airportDao;

    @Autowired
    public void setNationDao(IAirportDao nationDao) {
        this.airportDao = nationDao;
    }

    @Test
    public void testGetCity() throws Exception {
        System.out.println(airportDao.getCity("JFK"));
        System.out.println(airportDao.getCity("FRA"));
    }

    @Test
    public void testGetNation() throws Exception {
        System.out.println(airportDao.getNation("JFK"));
    }

    @Test
    public void testGetArea() throws Exception {
        System.out.println(airportDao.getArea("JFK", "FRA"));
    }

    @Test
    public void testGetAreas() throws Exception {
        System.out.println(airportDao.getAreas("JFK", "FRA"));
    }

    @Test
    public void testQueryInfo() throws Exception {
        Map<String, String> map = airportDao.queryAirportInfo("HND", "FRA");
        System.out.println(map);
    }
}