package cdgy.pqv.mamage.vuedemo3.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserOrder {


    private String orderId;

    private String orderStatus;

    private String shopId;

    private String userId;

    private String userName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date orderTime;

    private float total;

    private String address;

    private String userTel;

    private int shopNum;

    private Shop shop;


}
