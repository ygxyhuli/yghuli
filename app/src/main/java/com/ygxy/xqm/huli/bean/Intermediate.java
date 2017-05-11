package com.ygxy.xqm.huli.bean;

/**
 * Created by XQM on 2017/4/28.
 */

public class Intermediate {
    public boolean isSelect0;
    public boolean isSelect1;
    private String number;
    private int url1;
    private int url2;
    private String num0;
    private String num1;

    public Intermediate(boolean isSelect0,boolean isSelect1,String num0,String num1,String number,int url1,int url2){
        this.isSelect0 = isSelect0;
        this.isSelect1 = isSelect0;
        this.number = number;
        this.url1 = url1;
        this.url2 =  url2;
        this.num0 = num0;
        this.num1 = num1;
    }

    public boolean isSelect0() {
        return isSelect0;
    }

    public boolean isSelect1() {
        return isSelect1;
    }

    public String getNum0() {
        return num0;
    }

    public String getNum1() {
        return num1;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getUrl1() {
        return url1;
    }

    public void setUrl1(int url1) {
        this.url1 = url1;
    }

    public int getUrl2() {
        return url2;
    }

    public void setUrl2(int url2) {
        this.url2 = url2;
    }
}
