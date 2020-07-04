<template>
  <div class="header">
    <div class="header-content clearfix">
      <div style="height: 100%;width: 200px;float: left;" class="clearfix">
        <div style="float: left;height: 100%;width:60px;">
          <el-tooltip content="点击更换头像" placement="top" v-if="isshowimg">
            <el-upload class="header-img" action="http://localhost:8080/userimg"  :show-file-list="false"
              accept="image/png,imgage/jpg,image/jpeg"  :data="userid"
              :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" class="imageavatar">
              <i v-else class="el-icon-plus icon-pos"></i>
            </el-upload>
          </el-tooltip>
        </div>
        <div style="float: left;height: 100%;width: 140px;box-sizing: border-box;padding-left: 12px;">
          <p style="line-height: 60px;color: #FFFFFF;height: 100%;">{{ username }}</p>
        </div>

      </div>
      <div class="shopcart"><i class="el-icon-shopping-cart-2"></i><span>购物车</span></div>
      <ul class="header-user">
        <li><a href="#/manage/login">登录</a></li>
        <li><span class="sep">|</span></li>
        <li><a href="#/manage/register">注册</a></li>
        <li><span class="sep">|</span></li>
        <li><a href="#/manage">开发者管理</a></li>
        <li><span class="sep">|</span></li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    methods: {
      isLogin() {
        if (sessionStorage.getItem("userinfo") == null) {
          return false;
        } else {
          return true;
        }
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
      getUserImg(){
        let user = JSON.parse(sessionStorage.getItem('userinfo'))
        this.axios({
          url:'http://localhost:8080/showimg',
          method:'post',
          data:{imageUrl:user.userImg}
        }).then(res=>{
          this.imageUrl='data:image/png;image/jpg;image/jpeg;base64,'+res.data
        })
      }

    },
    mounted: function() {
      if (this.isLogin()) {
        let m = JSON.parse(sessionStorage.getItem('userinfo'))
        this.username = m.userName
        this.userid ={userid:m.userId}
        this.isshowimg=true
        if(m.userImg!=''){
          this.getUserImg()
        }

      } else {
        this.username = ''
        this.isshowimg=false
        this.userid=''
      }
    },
    data() {
      return {
        username: '',
        isshowimg:false,
        imageUrl:'',
        userid:''
      }
    }
  }
</script>

<style scoped="scoped">
  .header {
    height: 60px;
    background-color: #333333;
    font-size: 12px;
    color: #b0b0b0;
    position: relative;
  }

  .header>.header-content {
    margin: 0 auto;
    width: 60%;
    height: 100%;
    position: relative;
  }

  .header-content .header-img{
    height: 60px;
    background-color:#FFFFFF ;
    border-radius: 100px;
    position: relative;
    overflow: hidden;
  }
  .imageavatar{
    background-size:100% 100% ;
    height: 60px;
    width: 60px;
  }
  .icon-pos{
    position: relative;
    left: 17px;
    font-size: 25px;
    line-height: 60px;
  }

  .header .header-content .header-user {
    float: right;
  }

  .header .header-content ul {
    float: right;
  }

  .header .header-content ul li {
    float: left;
    line-height: 60px;
    margin: 0px 4px;

  }

  .header .header-content ul li a {
    text-decoration: none;
    color: #b0b0b0;
    line-height: 60px;
    height: 60px;

  }

  .header .header-content ul li>a:hover {
    color: #eeeeee;
  }

  .header .header-content .shopcart {
    float: right;
    height: 100%;
    text-align: center;
    cursor: pointer;
    width: 80px;
  }

  .header .header .shopcart span,
  i {
    line-height: 60px;
    margin: 0 1px;
  }

  .header .header-content .shopcart:hover {
    background: #FF6700;
    color: #FFFFFF;
  }


  .sep {
    margin: 0 3.6px;
    color: #EEEEEE;
  }

  .small-shopcart {
    width: 200px;
    transition: height .3s;
    background-color: #FFFFFF;
    position: absolute;
    right: 0px;
    top: 60px;
    z-index: 45;
    box-shadow: 0 2px 10px rgba(0, 0, 0, .15);
  }
</style>
