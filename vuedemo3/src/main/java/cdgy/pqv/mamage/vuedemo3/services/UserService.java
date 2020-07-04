package cdgy.pqv.mamage.vuedemo3.services;

import cdgy.pqv.mamage.vuedemo3.bean.User;

import java.util.ArrayList;
import java.util.Date;

public interface UserService {

    /**
     * 添加
     */
    void addUser(User user);

    /**
     * 根据用户名和密码匹配用户是否存在
     */
    User queryUserByIdAndPwd(String sid,String pwd);

    String queryUserId();

    /**
     * 上传图片
     */
    void upload(String url,String sid);

    User queryUserById(String userid);


    void updateUserNameById(String username,String userid);


    void updateUserTelById(String usertel,String userid);


    void updateUserAddressById(String address,String userid);


    void updateUserPasswordById(String password,String userid);


    void updateUserAgeById(Date userage, String userid);

    ArrayList<User> queryUserList();

    ArrayList<User> queryUserByName(String username);

    ArrayList<User> queryUserListById(String userid);

    void updateUserTypeById(int type,String userid);
}
