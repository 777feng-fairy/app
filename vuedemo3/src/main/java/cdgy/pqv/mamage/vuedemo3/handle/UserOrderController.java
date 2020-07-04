package cdgy.pqv.mamage.vuedemo3.handle;

import cdgy.pqv.mamage.vuedemo3.bean.UserOrder;
import cdgy.pqv.mamage.vuedemo3.services.ShopCartService;
import cdgy.pqv.mamage.vuedemo3.services.ShopService;
import cdgy.pqv.mamage.vuedemo3.services.UserOrderService;
import cdgy.pqv.mamage.vuedemo3.util.MessageEnum;
import cdgy.pqv.mamage.vuedemo3.util.MessageUtil;
import cdgy.pqv.mamage.vuedemo3.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserOrderController {

    @Autowired
    private UserOrderService service;

    @Autowired
    private ShopCartService shopCartService;

    @Autowired
    private ShopService shopService;

    @PostMapping("userorder/addorder")
    public MessageUtil addOrder(@RequestBody UserOrder order){
        MessageUtil messageUtil =new MessageUtil();
        String orderid= service.isOrderId();
        order.setOrderId(orderid);
        service.addUserOrder(order);
        shopService.updateShopNumById(order.getShopNum(),order.getShopId());
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("购买成功");
        return messageUtil;
    }

    @PostMapping("userorder/listorder")
    public MessageUtil listOrder(@RequestBody Map<String,Object> map){
        MessageUtil messageUtil =new MessageUtil();
        List<String> shopidList = (List<String>)map.get("shopidlist") ;
        List<Integer> shopnumList = (List<Integer>)map.get("shopnumlist") ;
        List shoppriceList = (List)map.get("shoppricelist") ;
        String uid = map.get("userId").toString();
        String utel =map.get("userTel").toString();
        String uname =map.get("username").toString();
        String address = map.get("address").toString();
        for(int i = 0;i<shopidList.size();i++){
            UserOrder userOrder =new UserOrder();
            String oid = service.isOrderId();
            int num = shopnumList.get(i);
            float price =Float.parseFloat(shoppriceList.get(i).toString());
            float total= price*num;
            userOrder.setOrderId(oid);
            userOrder.setUserName(uname);
            userOrder.setTotal(total);
            userOrder.setUserTel(utel);
            userOrder.setShopId(shopidList.get(i));
            userOrder.setAddress(address);
            userOrder.setOrderStatus("1");
            userOrder.setShopNum(num);
            userOrder.setUserId(uid);
            service.addUserOrder(userOrder);
            shopCartService.deleteShopCart(shopidList.get(i),uid);
            shopService.updateShopNumById(num,shopidList.get(i));
        }
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("成功结算");
        return messageUtil;
    }

    @PostMapping("userorder/showorderlist")
    public MessageUtil<UserOrder> showOrderList(@RequestParam("userid") String id)throws IOException {
        MessageUtil<UserOrder> messageUtil =new MessageUtil<>();
        ArrayList<UserOrder> userOrderArrayList = service.queryUserOrderById(id);
        ArrayList<UserOrder> list = ModelUtil.userOrdersToBase64(userOrderArrayList);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setData(list);
        return messageUtil;
    }

    @PostMapping("userorder/updatestatus")
    public MessageUtil cancleUserOrder(@RequestParam("orderid") String oid){
        MessageUtil messageUtil = new MessageUtil();
        service.updateUserOrderStatus(3,oid);
        UserOrder userOrder =service.queryOneOrderById(oid);
        shopService.updateShopNumAddById(userOrder.getShopNum(),userOrder.getShopId());
        messageUtil.setMessage("订单已取消");
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("userorder/confirmreceipt")
    public MessageUtil confirmReceipt(@RequestParam("orderid") String oid){
        MessageUtil messageUtil = new MessageUtil();
        service.updateUserOrderStatus(4,oid);
        messageUtil.setMessage("确认收货");
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("userorder/manorderlist")
    public MessageUtil<UserOrder> manOrderList(){
        MessageUtil<UserOrder> messageUtil = new MessageUtil<>();
        ArrayList<UserOrder> list = service.queryUserOrderAll();
        messageUtil.setData(list);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("userorder/manserchid")
    public MessageUtil<UserOrder> manSeachId(@RequestParam("orderid") String oid){
        MessageUtil<UserOrder> messageUtil = new MessageUtil<>();
        ArrayList<UserOrder> list = service.queryOrderOneOrMore(oid);
        messageUtil.setData(list);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("userorder/manserchstatus")
    public MessageUtil<UserOrder> manSeachId(@RequestParam("orderstatus") int status){
        MessageUtil<UserOrder> messageUtil = new MessageUtil<>();
        ArrayList<UserOrder> list = service.queryOrderByStatus(status);
        messageUtil.setData(list);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("userorder/manremoveone")
    public MessageUtil<UserOrder> manRemoveOne(@RequestParam("orderid") String oid){
        MessageUtil<UserOrder> messageUtil = new MessageUtil<>();
        service.deleteOrderByOrderId(oid);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("userorder/manremoveall")
    public MessageUtil<UserOrder> manRemoveAll(@RequestParam("orderstatus") int status){
        MessageUtil<UserOrder> messageUtil = new MessageUtil<>();
        service.deleteOrderByStatus(status);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("userorder/mandeliverygoods")
    public MessageUtil manDeliveryGoods(@RequestParam("orderid") String oid){
        MessageUtil messageUtil =new MessageUtil();
        service.updateUserOrderStatus(2,oid);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("发货成功");
        return messageUtil;
    }



}
