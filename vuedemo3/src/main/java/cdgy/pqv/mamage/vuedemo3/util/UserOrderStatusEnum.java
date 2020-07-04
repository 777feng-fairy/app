package cdgy.pqv.mamage.vuedemo3.util;

public enum UserOrderStatusEnum {

    PAY(1),   //用户付款
    MAN_YES(2),//商家确认订单
    USER_NO(3),//用户退款/取消订单
    USER_YES(4);//确认收货

    private int value;

    UserOrderStatusEnum(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
