package com.stacksimplify.restservices.dtos;

import com.stacksimplify.restservices.entity.Order;

import java.util.List;

public class UserMsDto {

    private Long userid;
    private String firstname;
    private String username;
    private String lastname;
    private List<Order> orders;

    //Getter&Setter

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    //Field Constructor

    public UserMsDto(Long userid, String firstname, String username, String lastname, List<Order> orders) {
        this.userid = userid;
        this.firstname = firstname;
        this.username = username;
        this.lastname = lastname;
        this.orders = orders;
    }

    //No Argument Constructor

    public UserMsDto() {

    }
}

