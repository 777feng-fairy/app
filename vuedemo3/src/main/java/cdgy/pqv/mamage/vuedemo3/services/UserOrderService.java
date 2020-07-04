package cdgy.pqv.mamage.vuedemo3.services;

import cdgy.pqv.mamage.vuedemo3.bean.UserOrder;

import java.util.ArrayList;

public interface UserOrderService {

    void addUserOrder(UserOrder userOrder);

    String isOrderId();

    ArrayList<UserOrder> queryUserOrderById(String id);

    void updateUserOrderStatus(int status,String orderid);

    UserOrder queryOneOrderById(String orderid);

    ArrayList<UserOrder> queryUserOrderAll();

    ArrayList<UserOrder> queryOrderOneOrMore(String oid);

    ArrayList<UserOrder> queryOrderByStatus(int status);

    void deleteOrderByStatus(int status);

    void deleteOrderByOrderId(String orderid);

}
