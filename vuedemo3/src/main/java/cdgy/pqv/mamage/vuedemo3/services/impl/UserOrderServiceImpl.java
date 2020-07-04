package cdgy.pqv.mamage.vuedemo3.services.impl;

import cdgy.pqv.mamage.vuedemo3.bean.UserOrder;
import cdgy.pqv.mamage.vuedemo3.mapper.UserOrderMapper;
import cdgy.pqv.mamage.vuedemo3.services.UserOrderService;
import cdgy.pqv.mamage.vuedemo3.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderMapper mapper;

    @Override
    public void addUserOrder(UserOrder userOrder) {
        mapper.addUserOrder(userOrder);
    }

    @Override
    public String isOrderId() {
        boolean flag=true;
        String orderid="";
        while (flag){
            orderid = ModelUtil.ctrateId();
            flag = mapper.isOrderId(orderid);
        }
        return  orderid;
    }

    @Override
    public ArrayList<UserOrder> queryUserOrderById(String id) {
        return mapper.queryUserOrderById(id);
    }

    @Override
    public void updateUserOrderStatus(int status, String orderid) {
        mapper.updateUserOrderStatus(status, orderid);
    }

    @Override
    public UserOrder queryOneOrderById(String orderid) {
        return mapper.queryOneOrderById(orderid);
    }

    @Override
    public ArrayList<UserOrder> queryUserOrderAll() {
        return mapper.queryUserOrderAll();
    }

    @Override
    public ArrayList<UserOrder> queryOrderOneOrMore(String oid) {
        return mapper.queryOrderOneOrMore(oid);
    }

    @Override
    public ArrayList<UserOrder> queryOrderByStatus(int status) {
        return mapper.queryOrderByStatus(status);
    }

    @Override
    public void deleteOrderByStatus(int status) {
        mapper.deleteOrderByStatus(status);
    }

    @Override
    public void deleteOrderByOrderId(String orderid) {
        mapper.deleteOrderByOrderId(orderid);
    }


}
