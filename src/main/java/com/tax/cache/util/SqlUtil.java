package com.tax.cache.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ydc on 2019/10/28.
 */
public class SqlUtil {

    public static String paramIn(String sql, int len){
        StringBuilder param = new StringBuilder(sql);
        param.append(" (");
        for(int i = 0; i< len; i++) {
            param.append("?");
            if(i < len - 1) {
                param.append(",");
            }
        }
        param.append(")");
        return param.toString();
    }

    public static String getSeries(ResultSet rs, int len) throws SQLException {
        return getSeries(rs, ";", len);
    }

    public static String getSeries(ResultSet rs, String split, int len) throws SQLException {
        StringBuilder obj = new StringBuilder();
        for(int i = 1; i <= len; i++) {
            obj.append(rs.getString(i))
                    .append(split);
        }
        return obj.toString();
    }

    public static String getIntermittently(ResultSet rs, int... index) throws SQLException {
        return getIntermittently(rs, ";", index);
    }

    public static String getIntermittently(ResultSet rs, String split, int... index) throws SQLException {
        StringBuilder obj = new StringBuilder();
        for(int i : index) {
            obj.append(rs.getString(i))
                    .append(split);
        }
        return obj.toString();
    }
}
