package cdgy.pqv.mamage.vuedemo3.mapper;


import cdgy.pqv.mamage.vuedemo3.bean.UserOrder;
import cdgy.pqv.mamage.vuedemo3.convert.UserOrderStatusHandel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface UserOrderMapper {

    /**
     * 添加订单
     * @param userOrder
     */
    @Insert("insert into uorder(orderid,username,shopid,address,total,orderstatus,usertel,userid,shopnum) values(" +
            "#{orderId},#{userName},#{shopId},#{address},#{total},#{orderStatus},#{userTel},#{userId},#{shopNum}) ")
    void addUserOrder(UserOrder userOrder);

    /**
     * 判断订单id是否存在
     * @param orderid
     * @return
     */
    @Select("select count(*) from uorder where orderid=#{orderid}")
    boolean isOrderId(String orderid);

    /**
     * 根据userid查询存在的订单
     *
     */
    @Select("select * from uorder where userid=#{userid} and orderstatus<3")
    @Results(value = {
            @Result(property = "orderId",column = "orderid",id = true),
            @Result(property = "userId",column = "userid"),
            @Result(property = "shopId",column = "shopid"),
            @Result(property = "orderTime",column = "ordertime"),
            @Result(property = "total",column = "total"),
            @Result(property = "address",column = "address"),
            @Result(property = "userName",column = "username"),
            @Result(property = "userTel",column = "usertel"),
            @Result(property = "orderStatus",column = "orderstatus",javaType = String.class,jdbcType = JdbcType.INTEGER,typeHandler = UserOrderStatusHandel.class),
            @Result(property = "shopNum",column = "shopnum"),
            @Result(property = "shop",column = "shopid",one = @One(select = "cdgy.pqv.mamage.vuedemo3.mapper.ShopMapper.queryShopWithId",fetchType = FetchType.EAGER))
    })
    ArrayList<UserOrder> queryUserOrderById(String userid);

    /**
     * 根据订单id修改订单状态
     */
    @Update("update uorder set orderstatus = #{status} where orderid = #{orderid}")
    void updateUserOrderStatus(int status,String orderid);

    /**
     * 查询单个订单
     */
    @Select("select * from uorder where orderid=#{orderid}")
    UserOrder queryOneOrderById(String orderid);

    /**
     * 查询所有 ，非详细信息
     * @return
     */
    @Select("select * from uorder ")
    @Results(id = "order",value = {
            @Result(property = "orderId",column = "orderid",id = true),
            @Result(property = "userId",column = "userid"),
            @Result(property = "shopId",column = "shopid"),
            @Result(property = "orderTime",column = "ordertime"),
            @Result(property = "total",column = "total"),
            @Result(property = "address",column = "address"),
            @Result(property = "userName",column = "username"),
            @Result(property = "userTel",column = "usertel"),
            @Result(property = "orderStatus",column = "orderstatus",javaType = String.class,jdbcType = JdbcType.INTEGER,typeHandler = UserOrderStatusHandel.class),
            @Result(property = "shopNum",column = "shopnum")
    })
    ArrayList<UserOrder> queryUserOrderAll();

    /**
     * 根据订单id模糊搜索
     */
    @Select("select * from uorder where orderid like concat('%',#{oid},'%')")
    @ResultMap("order")
    ArrayList<UserOrder> queryOrderOneOrMore(String oid);

    /**
     * 根据状态搜索全部
     */
    @Select("select * from uorder where orderstatus=#{status}")
    @ResultMap("order")
    ArrayList<UserOrder> queryOrderByStatus(int status);

    /**
     * 根据状态删除
     */
    @Delete("delete from uorder where orderstatus=#{status}")
    void deleteOrderByStatus(int status);

    /**
     * 根据oid删除
     */
    @Delete("delete from uorder where orderid=#{orderid}")
    void deleteOrderByOrderId(String orderid);
}
