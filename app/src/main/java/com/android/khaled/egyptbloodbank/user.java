package com.android.khaled.egyptbloodbank;

import java.io.Serializable;

/**
 * Created by osama saqr on 11/21/2016.
 */
public class user implements Serializable{

    String name;
    String phone;
    String date;
    String city;
    String region;
    String groop;
    String uname;
    String pass;
    String re_pass;

    public int getCity_index() {
        return city_index;
    }

    public void setCity_index(int city_index) {
        this.city_index = city_index;
    }

    public int getRegion_index() {
        return region_index;
    }

    public void setRegion_index(int region_index) {
        this.region_index = region_index;
    }

    int city_index,region_index;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRe_pass() {
        return re_pass;
    }

    public void setRe_pass(String re_pass) {
        this.re_pass = re_pass;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    Long user_id;
    public String getGroop() {
        return groop;
    }

    public void setGroop(String groop) {
        this.groop = groop;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
