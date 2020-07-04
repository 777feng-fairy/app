package cdgy.pqv.mamage.vuedemo3.services.impl;

import cdgy.pqv.mamage.vuedemo3.bean.ShopCart;
import cdgy.pqv.mamage.vuedemo3.mapper.ShopCartMapper;
import cdgy.pqv.mamage.vuedemo3.services.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ShopCartMapper mapper;


    @Override
    public void addShopCart(ShopCart shopCart) {
        mapper.addShopCart(shopCart);
    }

    @Override
    public ArrayList<ShopCartMapper> queryAll() {
        return mapper.queryAll();
    }

    @Override
    public ShopCart queryShopCartById(String shopid, String userid) {
        return mapper.queryShopCartById(shopid, userid);
    }


    @Override
    public void deleteShopCart(String shopid, String userid) {
        mapper.deleteShopCart(shopid, userid);
    }

    @Override
    public boolean queryShopCart(String shopid, String userid) {
        return mapper.queryShopCart(shopid,userid);
    }

    @Override
    public void updateShopCartNum(ShopCart shopCart) {
        mapper.updateShopCartNum(shopCart);
    }

    @Override
    public ArrayList<ShopCart> queryUserShopCart(String uid) {
        return mapper.queryUserShopCart(uid);
    }
}
