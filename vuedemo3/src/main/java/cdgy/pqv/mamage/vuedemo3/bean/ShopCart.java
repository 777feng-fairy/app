package cdgy.pqv.mamage.vuedemo3.bean;

import lombok.Data;

@Data
public class ShopCart {

    private String shopId;

    private String userId;

    private int shopNum;

    private float shopPrice;

    private String address;

    private User user;

    private Shop shop;



}
