<template>
  <div class="header clearfix">
      <div style="float: left;height: 100%;width: 1300px;">
        <p style="line-height: 120px;font-size: 42px;letter-spacing: 12px;text-align: center;margin-left:300px;color:#000000;">
          雪蝶商城后台管理系统
        </p>
      </div>
      <div class="header-user clearfix">
        <div class="userimg clearfix">
          <div>
             <el-tooltip  effect="dark" content="上传头像" placement="top-start">
                  <el-upload
                    :data="user"
                    class="avatar-uploader"
                    action="http://localhost:8080/userimg"
                    :show-file-list="false"
                    accept="image/png,imgage/jpg,image/jpeg"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    <input type="text" name="userId" style="display: none;">
                  </el-upload>
             </el-tooltip>
          </div>
        </div>
        <div style="float: left;margin-left:20px ;">
          <div style="height:60px;position: relative;">
            <span style="color:#409EFF;position: relative;top:12px">欢迎!</span>
            <p style="color:#409EFF;position: relative;top:28px;">{{username}}</p>
          </div>
          <div style="height: 60px;">
            <ul class="clearfix">
              <li v-show="islogin"><a href="#/manage/login" >登录</a></li>
              <li v-show="islogin"><a href="#/manage/register">注册</a></li>
              <li><button @click="loginOut">退出</button></li>
            </ul>
          </div>

        </div>
      </div>
  </div>
</template>

<script>

  export default{
    computed:{
      user:function(){
        if(sessionStorage.getItem("userinfo")){
          let obj = JSON.parse(sessionStorage.getItem("userinfo"))
          return {userid:obj.userId}
        }else{
          return {userid:'xx0xx'}
        }
      }
    },
    props:{
      username:{
        default:'欢迎!'
      },
      islogin:{
        type:Boolean
      }

    },
    data(){
      return{
        imageUrl:''
      }
    },
    mounted:function(){
      this.getUserShow()
    },
    methods:{
      getUserInfo(){
        let user =JSON.parse(sessionStorage.getItem("userinfo"))
      },
       handleAvatarSuccess(res, file) {
              this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isLt2M = file.size / 1024 / 1024 < 1;
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 1MB!');
        }
       return  isLt2M;
       },
       loginOut(){
         sessionStorage.removeItem("userinfo")
         this.username=''
         this.islogin=true
         
       },

      getUserShow(){
        if(sessionStorage.getItem('userinfo')!=null){
           let user =JSON.parse(sessionStorage.getItem('userinfo'))
           if(user.userImg!=''){
             this.axios({
               url:'http://localhost:8080/showimg',
               method:'post',
               data:{imageUrl:user.userImg}
             }).then(res=>{
               this.imageUrl='data:image/png;image/jpg;image/jpeg;base64,'+res.data
             })
           }
        }

      }

    }

  }
</script>

<style scoped>
  .header{
    height:120px;
    width: 100%;
    position: relative;

  }
  .header .header-user{
    float: right;
    width: 300px;
    height: 100%;
    position: relative;

  }

  .header .header-user ul{
    height: 100%;
    margin: auto 0;
    display: block;
    position: relative;

  }

  .header .header-user ul li{
    float: left;
    line-height: 24px;
    margin:auto 9px 0 0 ;
    text-align: center;
    height: 100%;
  }

  .header .header-user ul li a{

    line-height:60px;
    text-decoration: none;
    color:rgb(51,51,51);
    overflow: hidden;
  }

  .header .header-user ul li button{
    background-color: #FFFFFF;
    outline: none;
    box-shadow: noen;
    border: none;
    line-height:60px;
    text-decoration: none;
    color:rgb(51,51,51);
    font-size: 16px;
    height: 60px;
    padding: 0;
  }


  .header .header-user .userimg{
    float: left;
    height: 100%;
    position: relative;
  }

  .header .header-user .userimg div{
    height: 100%;
    line-height: 100px;
    width:100px;
    height: 100px;
    position: relative;
    top:5px;

  }

   .avatar-uploader {
      border: 2px dashed #d9d9d9;
      border-radius: 100px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      height: 120px;
      width: 100px;
    }

    .el-upload .el-upload--text{
      height: 120px;
      width: 100px;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 100px;
        height: 120px;
        line-height: 120px;
        text-align: center;
        position: relative;
        bottom: 6px;
     }

    .avatar {
        width: 100px;
        height: 120px;
        display: block;
      }

</style>
