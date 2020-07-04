package cdgy.pqv.mamage.vuedemo3.services;

import cdgy.pqv.mamage.vuedemo3.bean.ShopCart;
import cdgy.pqv.mamage.vuedemo3.mapper.ShopCartMapper;

import java.util.ArrayList;

public interface ShopCartService {

    void addShopCart(ShopCart shopCart);

    ArrayList<ShopCartMapper> queryAll();

    ShopCart queryShopCartById(String shopid,String userid);

    void deleteShopCart(String shopid,String userid);

    boolean queryShopCart(String shopid,String userid);

    void updateShopCartNum(ShopCart shopCart);

    ArrayList<ShopCart> queryUserShopCart(String uid);

}
