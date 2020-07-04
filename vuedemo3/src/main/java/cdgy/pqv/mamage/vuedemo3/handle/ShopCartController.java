package cdgy.pqv.mamage.vuedemo3.handle;

import cdgy.pqv.mamage.vuedemo3.bean.ShopCart;
import cdgy.pqv.mamage.vuedemo3.services.ShopCartService;
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

@RestController
public class ShopCartController {

    @Autowired
    private ShopCartService service;


    @PostMapping("shopcart/addcart")
    public MessageUtil addCart(@RequestBody ShopCart shopCart){
        MessageUtil messageUtil = new MessageUtil();
        if(service.queryShopCart(shopCart.getShopId(),shopCart.getUserId())){
            ShopCart s = service.queryShopCartById(shopCart.getShopId(),shopCart.getUserId());
            s.setShopNum(s.getShopNum()+shopCart.getShopNum());
            service.updateShopCartNum(s);
        }else {
            service.addShopCart(shopCart);
        }
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("添加成功");
        return messageUtil;
    }

    @PostMapping("shopcart/listcart")
    public MessageUtil<ShopCart> listCart(@RequestParam("userid") String uid) throws IOException {
        MessageUtil<ShopCart> messageUtil = new MessageUtil<>();
        ArrayList<ShopCart> list = service.queryUserShopCart(uid);
        list = ModelUtil.shopCartsToBase64(list);
        messageUtil.setData(list);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("shopcart/removecart")
    public MessageUtil removeCart(@RequestParam("shopid") String sid,@RequestParam("userid") String uid){
        MessageUtil messageUtil  = new MessageUtil();
        service.deleteShopCart(sid, uid);
        messageUtil.setMessage("删除成功");
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }
}
