package cdgy.pqv.mamage.vuedemo3.util;

import cdgy.pqv.mamage.vuedemo3.bean.Shop;
import cdgy.pqv.mamage.vuedemo3.bean.ShopCart;
import cdgy.pqv.mamage.vuedemo3.bean.UserOrder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

public class ModelUtil<T> {

    public static String ctrateId(){
        Random random =new Random();
        //定义数字长度
        int[] length={6,7,8,9,10,11,12};
        //随机数组长度
        int randLength = random.nextInt(7);
        String id="";
        for(int i=0;i<length[randLength];i++){
            int raandId=random.nextInt(10);
            id=String.valueOf(raandId)+""+id;
        }
        return id;
    }

    public static String getBase64Str(String path) throws IOException {
        byte[] buff = Files.readAllBytes(Paths.get(path));
        return Base64.getEncoder().encodeToString(buff);
    }

    public  static ArrayList<Shop> shopUrlToBase64(ArrayList<Shop> arrayList)throws IOException{
        for (int index=0;index<arrayList.size();index++){
            String base64=getBase64Str(arrayList.get(index).getShopUrl());
            arrayList.get(index).setShopUrl(base64);
        }
        return arrayList;
    }


    public  static  ArrayList<ShopCart> shopCartsToBase64(ArrayList<ShopCart> arrayList) throws IOException{
        for (int i =0;i<arrayList.size();i++){
            String base64=getBase64Str(arrayList.get(i).getShop().getShopUrl());
            arrayList.get(i).getShop().setShopUrl(base64);
        }
        return  arrayList;
    }

    public static  ArrayList<UserOrder> userOrdersToBase64(ArrayList<UserOrder> arrayList) throws IOException{
        for (int i =0;i<arrayList.size();i++){
            String base64="";
            if(arrayList.get(i).getShop().getShopUrl().length()<50){
                base64=getBase64Str(arrayList.get(i).getShop().getShopUrl());
                arrayList.get(i).getShop().setShopUrl(base64);
            }
        }
        return  arrayList;
    }

}
