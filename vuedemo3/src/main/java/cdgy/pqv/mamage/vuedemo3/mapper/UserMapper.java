package cdgy.pqv.mamage.vuedemo3.mapper;

import cdgy.pqv.mamage.vuedemo3.bean.User;
import cdgy.pqv.mamage.vuedemo3.convert.UserTypeHandle;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 根据用户名查询用户
     */
    @Select("select userid, username, password, address, userage, usertel, userimg,type from user where userid=#{sid} and password=#{pwd}")
    User queryUserByIdAndPwd(String sid,String pwd);

    /**
     * 查询所有
     * @return
     */
    @Select("select * from user")
    ArrayList<User> querAll();

    /**
     * 添加学生
     */
    @Insert("insert into user(userid,username,password,address,usertel,userimg,userage)" +
            " values(#{userId},#{userName},#{password},#{address},#{userTel},#{userImg},#{userAge})")
    void addUser(User user);

    @Select("select count(*) from user where userid=#{userid}")
    boolean queryUserId(String uid);

    @Select("select * from user where userid=#{userid}")
    User queryUserById(String userid);

    /**
     * java 安卓 界面
     * 上传图片
     */
    @Update("update user set userimg=#{url} where userid=#{sid}")
    void upload(String url,String sid);

    /**
     * 根据id修改
     */
    @Update("update user set username=#{username} where userid =#{userid}")
    void updateUserNameById(String username,String userid);

    @Update("update user set usertel=#{usertel} where userid =#{userid}")
    void updateUserTelById(String usertel,String userid);

    @Update("update user set address=#{address} where userid =#{userid}")
    void updateUserAddressById(String address,String userid);

    @Update("update user set password=#{password} where userid =#{userid}")
    void updateUserPasswordById(String password,String userid);

    @Update("update user set userage=#{userage} where userid =#{userid}")
    void updateUserAgeById(Date userage, String userid);

    @Select("select * from user")
    @Results(id = "user",value = {
            @Result(column = "userid",property = "userId",id = true),
            @Result(column = "username",property = "userName"),
            @Result(property = "address",column = "address"),
            @Result(property = "userAge",column = "userage"),
            @Result(property = "userTel",column = "usertel"),
            @Result(property = "userImg",column = "userimg"),
            @Result(property = "password",column = "password"),
            @Result(property = "type",column = "type",jdbcType = JdbcType.INTEGER,javaType = String.class,typeHandler = UserTypeHandle.class)
    })
    ArrayList<User> queryUserList();

    @Select("select * from user where username like concat('%',#{username},'%')")
    @ResultMap("user")
    ArrayList<User> queryUserByName(String username);

    @Select("select * from user where userid like concat('%',#{userid},'%')")
    @ResultMap("user")
    ArrayList<User> queryUserListById(String userid);

    @Update("update user set type = #{type} where userid=#{userid}")
    void updateUserTypeById(int type,String userid);
}
