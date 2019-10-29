package com.tax.cache.model;

/**
 * Created by ydc on 2019/10/24.
 */
public class Itinerary {

    public String route;

    public String[] airports;

    public int airL;

    public int secL;

    public String[][] airInfo;

    public boolean valid = false;

    /**
     * route properties
     */
    public String cities;

    public String states;

    public String nations;

    public String zones;

    public String areas;

    /**
     * optional
     */
    private String deptDts;

    private String arvDts;

    /**
     * analyze
     */
    public String intlDom;

    public boolean isRT = false;

    public boolean isDom = false;

    public boolean hasOJ = false;

    public String[] org;

    public String[] dest;

    public Itinerary(String route) {
       this.route = route;
    }

    public Itinerary FltDt(String deptDts, String arvDts) {
        this.deptDts = deptDts;
        this.arvDts = arvDts;
        return this;
    }

    public String getDeptDts() {
        return deptDts;
    }

    public String getArvDts() {
        return arvDts;
    }
}
