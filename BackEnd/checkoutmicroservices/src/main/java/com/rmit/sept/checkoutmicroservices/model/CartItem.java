package com.rmit.sept.checkoutmicroservices.model;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class CartItem {
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    @NotNull
    private Long user_id;

    @NotNull
    private Long adId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
