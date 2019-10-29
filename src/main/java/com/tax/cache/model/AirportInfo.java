package com.tax.cache.model;

/**
 * Created by T440 on 2019/2/8.
 */
public class AirportInfo {
    private String airport;
    
    private String city;

    private String state;
    
    private String nation;
    
    private String zone;

    private String area;

    private String city2;

    private String state2;

    private String zone2;

    private String area2;

    private boolean us50States = false;

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getZone2() {
        return zone2;
    }

    public void setZone2(String zone2) {
        this.zone2 = zone2;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea2() {
        return area2;
    }

    public void setArea2(String area2) {
        this.area2 = area2;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public boolean isUs50States() {
        return us50States;
    }

    public void setUs50States(boolean us50States) {
        this.us50States = us50States;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    public AirportInfo() {
    }

    public AirportInfo(String airport) {
        this.airport = airport;
    }

    public AirportInfo(String airport, String city, String state, String nation, String zone, String area, String city2, String state2, String zone2, String area2, boolean us50States) {
        this.airport = airport;
        this.city = city;
        this.state = state;
        this.nation = nation;
        this.zone = zone;
        this.area = area;
        this.city2 = city2;
        this.state2 = state2;
        this.zone2 = zone2;
        this.area2 = area2;
        this.us50States = us50States;
    }
}
