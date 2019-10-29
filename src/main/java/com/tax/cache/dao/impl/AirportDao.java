package com.tax.cache.dao.impl;

import com.tax.cache.dao.IAirportDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tax.cache.util.SqlUtil.getSeries;
import static com.tax.cache.util.SqlUtil.paramIn;

/**
 * Created by ydc on 2019/10/24.
 */
@Repository
public class AirportDao implements IAirportDao {

    private static final Logger logger = LoggerFactory.getLogger(AirportDao.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String QUERY_CITY_SQL = "select city_code from airport_area_partition " +
            "where airport_code = ?";

    private static final String QUERY_NATION_SQL = "select country_code from airport_area_partition " +
            "where airport_code = ?";

    private static final String QUERY_AREA_SQL = "select airport_code, city_code, country_code from airport_area_partition " +
            "where airport_code in (?,?)";

    private static final String QUERY_INFO_SQL = "select * from airport_area_partition " +
            "where airport_code in";

    @Override
    public Map<String, String> queryAirportInfo(final String... airport) {
        String sql = paramIn(QUERY_INFO_SQL, airport.length);
//        final Map<String, String> infoMap = new HashMap<String, String>(airport.length);
        try {
//            jdbcTemplate.query(sql,
//                    new RowCallbackHandler() {
//                        @Override
//                        public void processRow(ResultSet rs) throws SQLException {
//                            infoMap.put(rs.getString(1), getSeries(rs, 11));
//                        }
//                    }, airport);
          return  jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, String>>() {
                @Override
                public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                    Map<String, String> infoMap = new HashMap<String, String>(airport.length);
                    while (rs.next()) {
                        infoMap.put(rs.getString(1), getSeries(rs, 11));
                    }
                    return infoMap;
                }
            }, airport);
        }catch (DataAccessException e) {
            logger.warn("{} have no info", airport);
            logger.debug(e.getMessage(), e);
        }
        return null;
    }


    @Override
    public String getCity(String airport) {
        try{
            return  jdbcTemplate.queryForObject(QUERY_CITY_SQL, String.class, airport);
        } catch (DataAccessException e) {
            logger.error("{} has no city", airport);
            logger.debug(e.getMessage(), e);
        }
        return "";
    }

    @Override
    public String getNation(String airport) {
        try {
            return jdbcTemplate.queryForObject(QUERY_NATION_SQL, String.class, airport);
        } catch (DataAccessException e){
            logger.error("{} has no nation", airport);
            logger.debug(e.getMessage(), e);
        }
        return "";
    }

    @Override
    public List getArea(String... airport) {
        try {
            return jdbcTemplate.queryForList(QUERY_AREA_SQL, airport);
        } catch (DataAccessException e) {
            logger.debug(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Map getAreas(String... airport) {
        final Map<String, Object> airports = new HashMap<String, Object>(airport.length);
        try{
            jdbcTemplate.query(QUERY_AREA_SQL,
                    new RowCallbackHandler() {
                        @Override
                        public void processRow(ResultSet rs) throws SQLException {
                            airports.put(rs.getString("AIRPORT_CODE"), rs.getString("COUNTRY_CODE") + ";" +  rs.getString("CITY_CODE"));
                        }
                    },airport);
        } catch (DataAccessException e) {
            logger.debug(e.getMessage(), e);
        }
        return airports;
    }
}
