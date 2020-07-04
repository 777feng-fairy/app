package cdgy.pqv.mamage.vuedemo3.util;

public enum ShopTypeEnum {

    //电脑，手机，平板，充电器，数据线，上衣，裤子，鞋子，食品，首饰，电视机，耳机，路由器，插排线
    COMPUTER(0),
    PHONE(1),
    FLAT(2),
    CHARGER(3),
    DATA_LINE(4),
    JACKET(5),
    TROUSERS(6),
    SHOES(7),
    FOOD(8),
    JEWELRY(9),
    TELEVISION(10),
    HEADSET(11),
    ROUTER(12),
    PATCH_CORD(13);

    private final int value;

    ShopTypeEnum(int value){
        this.value=value;
    }

    public int Value(){
        return this.value;
    }


}
