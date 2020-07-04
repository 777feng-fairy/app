package cdgy.pqv.mamage.vuedemo3.util;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MessageUtil<T> {

    //发送的数据包
    private ArrayList<T> data;

    //发送的附带消息
    private String message;

    //状态
    private  int status;
}
