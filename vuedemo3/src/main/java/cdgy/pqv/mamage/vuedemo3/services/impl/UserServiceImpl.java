package cdgy.pqv.mamage.vuedemo3.services.impl;

import cdgy.pqv.mamage.vuedemo3.bean.User;
import cdgy.pqv.mamage.vuedemo3.mapper.UserMapper;
import cdgy.pqv.mamage.vuedemo3.services.UserService;
import cdgy.pqv.mamage.vuedemo3.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public void addUser(User user) {
        mapper.addUser(user);
    }

    /**
     * 返回的信息不含密码
     * @param sid
     * @param pwd
     * @return
     * @throws NullPointerException
     */
    @Override
    public User queryUserByIdAndPwd(String sid, String pwd) throws NullPointerException {
        if(mapper.queryUserByIdAndPwd(sid, pwd)==null){
            return null;
        }
        User user=mapper.queryUserByIdAndPwd(sid,pwd);
        user.setPassword("你看不到我");
        return user;
    }

    @Override
    public String queryUserId() {
        boolean flag=true;
        String uid="";
        while (flag){
            uid= ModelUtil.ctrateId();
            if(mapper.queryUserId(uid)){
                flag=true;
            }else {
                flag=false;
            }
        }
        return uid;
    }

    public void upload(String url,String uid){
        mapper.upload(url, uid);
    }

    @Override
    public User queryUserById(String userid) {
        User user=mapper.queryUserById(userid);
        user.setPassword("你看不到我");
        return user;
    }

    @Override
    public void updateUserNameById(String username, String userid) {
        mapper.updateUserNameById(username, userid);
    }

    @Override
    public void updateUserTelById(String usertel, String userid) {
        mapper.updateUserTelById(usertel, userid);
    }

    @Override
    public void updateUserAddressById(String address, String userid) {
        mapper.updateUserAddressById(address, userid);
    }

    @Override
    public void updateUserPasswordById(String password, String userid) {
        mapper.updateUserPasswordById(password, userid);
    }

    @Override
    public void updateUserAgeById(Date userage, String userid) {
        mapper.updateUserAgeById(userage, userid);
    }

    @Override
    public ArrayList<User> queryUserList() {
        return mapper.queryUserList();
    }

    @Override
    public ArrayList<User> queryUserByName(String username) {
        return mapper.queryUserByName(username);
    }

    @Override
    public ArrayList<User> queryUserListById(String userid) {
        return mapper.queryUserListById(userid);
    }

    @Override
    public void updateUserTypeById(int type, String userid) {
        mapper.updateUserTypeById(type, userid);
    }
}
