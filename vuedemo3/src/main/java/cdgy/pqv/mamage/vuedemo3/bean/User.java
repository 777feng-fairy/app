package cdgy.pqv.mamage.vuedemo3.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {

    private String userId;

    private String userName;

    private String password;

    private String address;

    @DateTimeFormat( pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date userAge;

    private String userTel;

    private String userImg;

    private String type;

}
