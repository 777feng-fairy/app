package cdgy.pqv.mamage.vuedemo3.services.impl;

import cdgy.pqv.mamage.vuedemo3.bean.Shop;
import cdgy.pqv.mamage.vuedemo3.mapper.ShopMapper;
import cdgy.pqv.mamage.vuedemo3.services.ShopService;
import cdgy.pqv.mamage.vuedemo3.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper mapper;

    @Override
    public ArrayList<Shop> queryShopAll() {
        return mapper.queryShopAll();
    }

    @Override
    public ArrayList<Shop> queryShopByNam(String name) {
        return mapper.queryShopByName(name);
    }

    @Override
    public void updateShop(Shop shop) {
        mapper.updateShop(shop);
    }

    @Override
    public void addShop(Shop shop) {
        mapper.addShop(shop);
    }

    @Override
    public String getSid() {
        String sid = "";
        boolean flag = true;
        while (flag){
            sid= ModelUtil.ctrateId();
            if(!mapper.queryShopId(sid)){
                flag=false;
            }
        }
        return sid;
    }

    @Override
    public ArrayList<Shop> queryShopByType(String type) {
        return mapper.queryShopByType(type);
    }

    @Override
    public ArrayList<Shop> qyertShopAllWithStatus() {
        return mapper.qyertShopAllWithStatus();
    }

    @Override
    public Shop queryShopWithId(String id) {
        return mapper.queryShopWithId(id);
    }

    @Override
    public void updateShopNumById(int num, String sid) {
        Shop shop =mapper.queryShopWithId(sid);
        shop.setShopNum(shop.getShopNum()-num);
        mapper.updateShopNumById(shop.getShopNum(),sid);
    }

    public void updateShopNumAddById(int num, String sid){
        Shop shop =mapper.queryShopWithId(sid);
        shop.setShopNum(shop.getShopNum()+num);
        mapper.updateShopNumById(shop.getShopNum(),sid);
    }

    @Override
    public ArrayList<Shop> queryShopOneOrMore(String shopid) {
        return mapper.queryShopOneOrMore(shopid);
    }
}
