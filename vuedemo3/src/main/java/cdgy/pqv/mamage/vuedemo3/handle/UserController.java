package cdgy.pqv.mamage.vuedemo3.handle;

import cdgy.pqv.mamage.vuedemo3.bean.User;
import cdgy.pqv.mamage.vuedemo3.services.UserService;
import cdgy.pqv.mamage.vuedemo3.util.ModelUtil;
import cdgy.pqv.mamage.vuedemo3.util.MessageEnum;
import cdgy.pqv.mamage.vuedemo3.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@RestController
public class UserController {


    @Autowired
    private UserService service;

    @PostMapping("logincheck")
    public MessageUtil<User> loginCheck(@RequestBody Map<String,String> map)throws NullPointerException{
        MessageUtil<User> message =new MessageUtil<>();
        String sid;
        String password;
        if(map.containsKey("userid")){
            sid = map.get("userid");
        }else {
            message.setMessage("账号密码错误");
            message.setStatus(MessageEnum.ERROR.getValue());
            return message;
        }
        if(map.containsKey("password")){
            password = map.get("password");
        }else {
            message.setMessage("账号密码错误");
            message.setStatus(MessageEnum.ERROR.getValue());
            return message;
        }
        if(service.queryUserByIdAndPwd(sid,password)==null){
            message.setMessage("账号密码错误");
            message.setStatus(MessageEnum.ERROR.getValue());
            return message;
        }
        User user = service.queryUserByIdAndPwd(sid,password);
        ArrayList<User> list =new ArrayList<>();
        list.add(user);
        message.setData(list);
        message.setStatus(MessageEnum.SUCC.getValue());
        return message;
    }


    @PostMapping("userimg")
    public MessageUtil userImg(@RequestParam("file") MultipartFile img,@RequestParam("userid") String userid) throws IOException {
        String type=img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf(".")+1);
        MessageUtil message=new MessageUtil();
        if (img.isEmpty()){
            message.setMessage("上传文件失败");
            message.setStatus(MessageEnum.ERROR.getValue());
            return message;
        }else {
            String url = "e:/图片/user";
            File file = new File(url);
            if(!file.exists()){
                file.mkdir();
            }
            File nfile= new File(url+"/"+userid+"."+type);
            img.transferTo(nfile);
            service.upload(url+"/"+userid+"."+type,userid);
            message.setMessage("上传成功");
            message.setStatus(MessageEnum.SUCC.getValue());
            return message;
        }
    }

    @PostMapping("register")
    public MessageUtil<User>  register(@RequestBody User user){
        MessageUtil<User> messageUtil =new MessageUtil<>();
        String uid = ModelUtil.ctrateId();
        user.setUserId(uid);
        service.addUser(user);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("注册成功");
        User user1 =new User();
        user1.setUserId(uid);
        ArrayList<User> list =new ArrayList<>();
        list.add(user1);
        messageUtil.setData(list);
        return  messageUtil;
    }

   @PostMapping("showimg")
    public ResponseEntity<String> showImg(@RequestBody Map<String,String>map) throws IOException {
        ResponseEntity<String> responseEntity;
        String imageUrl="";
        if(map.containsKey("imageUrl")){
            imageUrl=map.get("imageUrl");
        }else {
            responseEntity =new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return responseEntity;
        }
        /*转成字节流二进制
        FileInputStream fileInputStream=new FileInputStream(imageUrl);
        byte[] buffer=new byte[fileInputStream.available()];
        fileInputStream.read(buffer);
        fileInputStream.close();*/
        byte [] buffer = Files.readAllBytes(Paths.get(imageUrl));
        String base64 = Base64.getEncoder().encodeToString(buffer);
        responseEntity =new ResponseEntity<>(base64,HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("user/setname")
    public MessageUtil updateName(@RequestParam("userid") String id,@RequestParam("username") String name){
        MessageUtil messageUtil = new MessageUtil();
        service.updateUserNameById(name,id);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("修改成功");
        return messageUtil;
    }


    @PostMapping("user/setage")
    public MessageUtil updateAge(@RequestParam("userid") String id, @RequestParam("userage") String age) throws ParseException {
        MessageUtil messageUtil = new MessageUtil();
        Date date =new SimpleDateFormat("yyyy-MM-dd").parse(age);
        service.updateUserAgeById(date,id);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("修改成功");
        return messageUtil;
    }

    @PostMapping("user/setaddress")
    public MessageUtil updateAddress(@RequestParam("userid") String id,@RequestParam("address") String address){
        MessageUtil messageUtil = new MessageUtil();
        service.updateUserAddressById(address,id);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("修改成功");
        return messageUtil;
    }

    @PostMapping("user/settel")
    public MessageUtil updateTel(@RequestParam("userid") String id,@RequestParam("usertel") String tel){
        MessageUtil messageUtil = new MessageUtil();
        service.updateUserTelById(tel,id);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("修改成功");
        return messageUtil;
    }

    @PostMapping("user/setpwd")
    public MessageUtil updatePwd(@RequestParam("userid") String id,@RequestParam("password") String pwd){
        MessageUtil messageUtil = new MessageUtil();
        service.updateUserPasswordById(pwd,id);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("修改成功");
        return messageUtil;
    }


    @PostMapping("user/refresh")
    public MessageUtil refresh(@RequestParam("userid") String id){
        MessageUtil messageUtil = new MessageUtil();
        ArrayList<User> list = new ArrayList<>();
        User u=service.queryUserById(id);
        list.add(u);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setData(list);
        return messageUtil;
    }

    @PostMapping("user/manuserlist")
    public MessageUtil<User> manUserList(){
        MessageUtil<User> messageUtil =new MessageUtil<>();
        messageUtil.setData(service.queryUserList());
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("user/manusernamelist")
    public MessageUtil<User> manUserNameList(@RequestParam("username") String name){
        MessageUtil<User> messageUtil =new MessageUtil<>();
        messageUtil.setData(service.queryUserByName(name));
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("user/manuseridlist")
    public MessageUtil<User> manUserIdList(@RequestParam("userid") String id){
        MessageUtil<User> messageUtil =new MessageUtil<>();
        messageUtil.setData(service.queryUserListById(id));
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        return messageUtil;
    }

    @PostMapping("user/updateuptype")
    public MessageUtil manUpLeveType(@RequestParam("userid") String uid){
        MessageUtil messageUtil =new MessageUtil();
        service.updateUserTypeById(1,uid);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("取消成功");
        return messageUtil;
    }

    @PostMapping("user/updatedowntype")
    public MessageUtil manDownLeveType(@RequestParam("userid") String uid){
        MessageUtil messageUtil =new MessageUtil();
        service.updateUserTypeById(0,uid);
        messageUtil.setStatus(MessageEnum.SUCC.getValue());
        messageUtil.setMessage("取消成功");
        return messageUtil;
    }
}
