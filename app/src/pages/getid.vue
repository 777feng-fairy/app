<template>
  <div class="getid">
    <div class="getid-content">
      <div class="getid-content-main clearfix">
          <el-card  shadow="hover" class="getid-content-card clearfix" :body-style="{height:'760px'}">
            <div class="getid-content-card-list clearfix" >
               <div class="biaoqian clearfix">
                  <div class="biaoqian-content">
                    <ul>
                      <li> <i class="el-icon-chat-line-round"><span>会员账号</span></i></li>
                      <li><span style="font-size: 12px;letter-spacing: 4px;">由数字组成·经典通行账号</span></li>
                    </ul>
                  </div>
                  <div class="jt">
                  </div>
               </div>
            </div>
            <div class="getid-content-card-main clearfix">
              <div class="code clearfix">
                <div style="position: relative;height: 80px;top:80px;line-height: 80px;font-size: 80px;color: rgb(73,172,48);">
                  <div style="width: 80px;float: left;">
                    <i class="el-icon-aim"></i>
                  </div>
                   <div style="width: 300px;float: left;height:41px;color: #000000;font-size:24px;line-height: 41px;margin-left: 20px;" class="clearfix">
                      申请成功，您获得的号码：
                   </div>
                   <div style="width: 300px;float: left;height:41px;color: #000000;font-size:24px;line-height: 41px;margin-left: 20px;" class="clearfix">
                      {{userid}}
                   </div>
                </div>
                <div style="width: 60%;position:relative;top:300px; center;text-align: center;vertical-align: middle;" align="left" class="clearfix">
                  <el-button type="primary" @click="toLogin()">立即登录</el-button>
                </div>
              </div>

            </div>
          </el-card>
      </div>

    </div>
  </div>
</template>

<script>

  export default{
    computed:{
      userid:function(){
        return sessionStorage.getItem("userid")
      }
    },
    methods:{
      toLogin(){
        sessionStorage.removeItem('userid')
        this.$router.push({
          path:'/manage/login'
        })
      }
    }
  }

</script>

<style >

  .getid{
    width: 100%;
    height: 100%;
    background-image: linear-gradient(to bottom,#79bbff,#FFFFFF);
    position: relative;
  }

  .getid .getid-content{
    width: 80%;
    height: 100%;
    margin: auto auto;
    position: relative;

  }

  .getid .getid-content .getid-content-main{
    width: 80%;
    height:800px;
    margin: 0 auto;
    vertical-align: middle;
    position: relative;
    top:73px;
    text-align: center;
    vertical-align: middle;
  }

  .getid-content-card{
    width: 80%;
    margin: 0 auto;
  }

  .getid .getid-content  .getid-content-main .getid-content-card-list{
    height: 760px;
    width: 30%;
    float: left;
    background-image: linear-gradient(to bottom,#a0cfff,#c6e2ff);
    position: relative;
  }

  .getid-content-card-main{
    width: 70%;
    height: 760px;
    float: left;
    background-color: #e5e9eb;
  }

  .getid-content-card-list .biaoqian{

    width:380px;
    height: 100px;
    margin: 20px 0;
    position: absolute;
  }
  .getid-content-card-list .biaoqian .biaoqian-content{
    width:280px;
    height: 100px;
    float: left;
    background: #409EFF;
  }

  .biaoqian-content ul {
    height: 100%;
    width: 100%;
  }
  .biaoqian-content ul li {
    height: 50%;
    width: 100%;
    line-height: 50px;
    color: #FFFFFF;
    font-size: 27px;
    letter-spacing: 7px;
  }

  .biaoqian-content ul li {
    margin: 0 9px;

   }

  .jt{
    float: left;
    border: 50px solid #e5e9eb;
    border-left-color: #409EFF;
  }
  .code{
    width: 500px;
    height: 100%;
    float: right;
    position: relative;
  }


</style>
