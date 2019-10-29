package com.tax.cache.model;

/**
 * Created by ydc on 2019/10/24.
 */
public class BlackRegular {

    private char type;

    private String val;

    public BlackRegular(char type, String val) {
        this.type = type;
        this.val = val;
    }

    public char getType() {
        return type;
    }

    public String getVal() {
        return val;
    }
}
