package cdgy.pqv.mamage.vuedemo3.handle;


import cdgy.pqv.mamage.vuedemo3.bean.Shop;
import cdgy.pqv.mamage.vuedemo3.services.ShopService;
import cdgy.pqv.mamage.vuedemo3.util.MessageEnum;
import cdgy.pqv.mamage.vuedemo3.util.MessageUtil;
import cdgy.pqv.mamage.vuedemo3.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ShopService shopService;


    @PostMapping("shopdata")
    public MessageUtil<Shop> queryAll(){
        MessageUtil<Shop> messageUtil= new MessageUtil<>();
        ArrayList<Shop> list =shopService.queryShopAll();
        messageUtil.setData(list);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("shopsearch")
    public MessageUtil<Shop> queryShopByName(@RequestBody Map<String,String> map){
        MessageUtil<Shop> messageUtil =new MessageUtil<>();
        String name=map.get("search");
        ArrayList<Shop> list = shopService.queryShopByNam(name);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setData(list);
        return messageUtil;
    }

    @PostMapping("updateshop")
    public MessageUtil updateShop(@RequestBody Shop shop){
        MessageUtil messageUtil =new MessageUtil();
        shopService.updateShop(shop);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("修改成功");
        return messageUtil;
    }

    @PostMapping("addshop")
    public MessageUtil addShop(@RequestParam("shopUrl")MultipartFile file,@RequestParam("shopName") String shopName,
                               @RequestParam("shopNum") int shopNum,@RequestParam("shopPrice") float shopPrice,
                               @RequestParam("shopInfo") String shopInfo,@RequestParam("type") String type,
                               @RequestParam("shopStatus") String shopStatus) throws IOException {
        MessageUtil messageUtil=new MessageUtil();
        if (file.isEmpty()){
            messageUtil.setMessage("添加失败");
            messageUtil.setStatus(MessageEnum.ERROR.getValue());
            return messageUtil;
        }
        Shop shop =  new Shop();
        shop.setShopName(shopName);
        shop.setType(type);
        shop.setShopInfo(shopInfo);
        shop.setShopPrice(shopPrice);
        shop.setShopStatus(shopStatus);
        shop.setShopNum(shopNum);
        shop.setShopId(shopService.getSid());
        String url ="E:/图片/shop";
        File file1 =new File(url);
        if(!file1.exists()){
            file1.mkdir();
        }
        String imgType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File img = new File(url+"/"+shop.getShopId()+imgType);
        file.transferTo(img);
        shop.setShopUrl(url+"/"+shop.getShopId()+imgType);
        shopService.addShop(shop);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("添加成功");
        return messageUtil;
    }


    @PostMapping("shoplist")
    public MessageUtil<Shop> shopList()throws IOException{
        MessageUtil<Shop> messageUtil =new MessageUtil<>();
        ArrayList<Shop> list = shopService.qyertShopAllWithStatus();
       /* for(int i=0;i<list.size();i++){

            String base64= ModelUtil.getBase64Str(list.get(i).getShopUrl());
            list.get(i).setShopUrl(base64);
        }*/

        messageUtil.setData(ModelUtil.shopUrlToBase64(list));
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("0-0");
        return messageUtil;
    }

    @PostMapping("shopbytype")
    public MessageUtil<Shop> shopTypeList(@RequestParam("type") String type)throws IOException{
        MessageUtil<Shop> messageUtil = new MessageUtil<>();
        ArrayList<Shop> list = shopService.queryShopByType(type);
       /* for(int i = 0;i<list.size();i++){
            String base64 = ModelUtil.getBase64Str(list.get(i).getShopUrl());
            list.get(i).setShopUrl(base64);
        }*/
        messageUtil.setData(ModelUtil.shopUrlToBase64(list));
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("profile")
    public MessageUtil<Shop> shopProfile(@RequestParam("shopid") String shopid) throws IOException {
        MessageUtil<Shop> messageUtil = new MessageUtil<>();
        Shop shop = shopService.queryShopWithId(shopid);
        ArrayList<Shop> list = new ArrayList<>();
        list.add(shop);
        list = ModelUtil.shopUrlToBase64(list);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setData(list);
        return messageUtil;
    }

    //mansearchid
    @PostMapping("mansearchid")
    public MessageUtil<Shop> manShopSearchId(@RequestParam("shopid") String shopid) throws IOException {
        MessageUtil<Shop> messageUtil = new MessageUtil<>();
        ArrayList<Shop> list = shopService.queryShopOneOrMore(shopid);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setData(list);
        return messageUtil;
    }
}
