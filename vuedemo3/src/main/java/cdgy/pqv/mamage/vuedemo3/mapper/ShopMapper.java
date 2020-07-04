package cdgy.pqv.mamage.vuedemo3.mapper;

import cdgy.pqv.mamage.vuedemo3.bean.Shop;
import cdgy.pqv.mamage.vuedemo3.convert.ShopStatusHandle;
import cdgy.pqv.mamage.vuedemo3.convert.ShopTypeHandle;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface ShopMapper {

    /**
     *
     * 查询所有的商品
     * @return
     */
    @Select("select * from shop")
    @Results(value = {
          @Result(column = "type",property = "type",javaType = String.class,jdbcType = JdbcType.INTEGER,typeHandler = ShopTypeHandle.class),
          @Result(column = "shopstatus",property = "shopStatus",javaType = String.class,jdbcType = JdbcType.INTEGER,typeHandler = ShopStatusHandle.class)
    })
    ArrayList<Shop> queryShopAll();

    /**
     * 查询所有上架商品
     */
    @Select("select * from shop where shopstatus = 1")
    ArrayList<Shop> qyertShopAllWithStatus();

    /**
     * 根据商品名称搜索
     * #{}
     */
    @Select("select * from shop where shopname like concat('%',#{name},'%')")
    ArrayList<Shop> queryShopByName(String name);

    /**
     * 修改商品参数
     */
    @Update("update shop set shopname=#{shopName},shopnum=#{shopNum},shopprice=#{shopPrice}," +
            "shopstatus=#{shopStatus,javaType=String,jdbcType=INTEGER,typeHandler=cdgy.pqv.mamage.vuedemo3.convert.ShopStatusHandle}," +
            "type=#{type,javaType=String,jdbcType=INTEGER,typeHandler=cdgy.pqv.mamage.vuedemo3.convert.ShopTypeHandle}," +
            "shopinfo=#{shopInfo} where shopid=#{shopId}")
    void updateShop(Shop shop);

    /**
     *
     * 添加商品
     * @param shop
     */
    @Insert("insert into shop (shopid,shopname,shopnum,shopurl,shopprice,shopinfo,type,shopstatus)" +
            "values(#{shopId}," +
            "#{shopName}," +
            "#{shopNum}" +
            ",#{shopUrl}," +
            "#{shopPrice}," +
            "#{shopInfo}," +
            "#{type}" +
            ",#{shopStatus})")
    void addShop(Shop shop);

    /**
     * 是否存在指定商品ID
     */
    @Select("select count(*) from shop where shopid = #{sid}")
    boolean queryShopId(String sid);

    /**
     * 根据类型查询商品
     * @param type
     * @return
     */
    @Select("select * from shop where type = #{type} and shopstatus = 1")
    ArrayList<Shop> queryShopByType(String type);

    /**
     * 根据商品名称搜索商品
     */
    @Select("select * from shop where shopid = #{shopid}")
    Shop queryShopWithId(String id);

    @Update("update shop set shopnum=#{shopnum} where shopid=#{shopid}")
    void updateShopNumById(int shopnum,String shopid);

    @Select("select * from shop where shopid like concat('%',#{shopid},'%')")
    ArrayList<Shop> queryShopOneOrMore(String shopid);
}
