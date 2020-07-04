package cdgy.pqv.mamage.vuedemo3.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Shop {

    private String shopId;

    private String shopName;

    private int shopNum;

    private String shopUrl;


    private float shopPrice;

    private String shopInfo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date stime;

    private String type;

    private String shopStatus;

}
