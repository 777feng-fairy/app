package cdgy.pqv.mamage.vuedemo3.mapper;

import cdgy.pqv.mamage.vuedemo3.bean.ShopCart;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface ShopCartMapper {

    @Insert("insert into cart values(#{shopId},#{userId},#{shopNum},#{shopPrice},#{address})")
    void addShopCart(ShopCart shopCart);

    @Select("select * from cart")
    ArrayList<ShopCartMapper> queryAll();

    @Select("select count(*) from cart where shopid=#{shopid} and userid=#{userid}")
    boolean queryShopCart(String shopid,String userid);

    @Update("update cart set shopnum =#{shopNum} where shopid=#{shopId} and userid = #{userId}")
    void updateShopCartNum(ShopCart shopCart);

    @Delete("delete  from cart where shopid=#{shopid} and userid=#{userid}")
    void deleteShopCart(String shopid,String userid);

    @Select("select * from cart where shopid=#{shopid} and userid=#{userid} ")
    ShopCart queryShopCartById(String shopid,String userid);

    @Select("select * from cart where userid=#{userid}")
    @Results(value = {
            @Result(column = "shopid",id = true,property ="shopId"),
            @Result(column = "userid",id = true,property = "userId"),
            @Result(column = "shopnum",property = "shopNum"),
            @Result(column = "shopprice",property = "shopPrice"),
            @Result(column = "address",property = "address"),
            @Result(property = "user",column = "userid",one = @One(select = "cdgy.pqv.mamage.vuedemo3.mapper.UserMapper.queryUserById",fetchType= FetchType.EAGER)),
            @Result(property = "shop",column = "shopid",one = @One(select = "cdgy.pqv.mamage.vuedemo3.mapper.ShopMapper.queryShopWithId",fetchType = FetchType.EAGER))
    })
    ArrayList<ShopCart> queryUserShopCart(String uid);
}
