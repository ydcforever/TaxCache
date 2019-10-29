package com.tax.cache.model;

import java.util.Date;

public class Sector {

    public String dept;

    public String arv;

    public Date deptDt;

    private Date arvDt;

    public String mkt;

    private String opt;

    private String mFlt;

    private String oFlt;

    public String cabin;

    public String cls;

    private String eqp;

    private boolean dept2OrgDom = false;

    private boolean arr2OrgDom = false;

    private boolean isDom = true;

    public Sector() {}

    public Sector(String dept, String arv, Date deptDt, Date arvDt) {
        this.dept = dept;
        this.arv = arv;
        this.deptDt = deptDt;
        this.arvDt = arvDt;
    }
}
