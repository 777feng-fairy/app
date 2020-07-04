package cdgy.pqv.mamage.vuedemo3.services;

import cdgy.pqv.mamage.vuedemo3.bean.Shop;

import java.util.ArrayList;

public interface ShopService {

    /***
     *
     * 查询所有商品
     */
    ArrayList<Shop> queryShopAll();

    /**
     *
     * 根据姓名模糊查询商品
     */
    ArrayList<Shop> queryShopByNam(String name);

    /***
     * 根据id就该商品参数
     * @param shop
     */
    void updateShop(Shop shop);

    /**
     *
     * 添加
     * @param shop
     */
    void addShop(Shop shop);


    /**
     * 创建商品ID
     */
    String getSid();

    /**
     * 根据类型查找
     * @param type
     * @return
     */
    ArrayList<Shop> queryShopByType(String type);

    /**
     * 查询上架商品
     */
    ArrayList<Shop> qyertShopAllWithStatus();

    /**
     * 根据商品ID搜索商品
     * @param name
     * @return
     */
    Shop queryShopWithId(String name);

    /**
     *
     * 更具id减少商品数量
     * @param num
     * @param sid
     */
    void updateShopNumById(int num,String sid);

    void updateShopNumAddById(int num, String sid);

    ArrayList<Shop> queryShopOneOrMore(String shopid);
}
