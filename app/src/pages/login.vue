<template>
  <div class="login">
    <div class="adv">
      <ul>
        <li><a href="#/manage/login/#">意见反馈</a></li>
      </ul>
    </div>
    <div class="kf">
      <div class="kf-one">
         <el-popover
            placement="top-start"
            title="客服"
            width="200"
            trigger="hover"
            content="致电客服。">
            <a href="tel:13264673593" slot="reference"></a>
          </el-popover>
      </div>
      <div class="kf-two">
        <el-popover
           placement="top-start"
           title="客服"
           width="200"
           trigger="hover"
           content="联系QQ:2384520548">
           <img src="../assets/img/qq.jpg" alt="" width="80px" height="80px" slot="reference">
         </el-popover>
      </div>

    </div>
    <div class="login-card">
        <el-card>
          <div style="width: 80%;margin:20px auto;">
            <span style="font-size: 44px;">雪蝶商城</span>
          </div>
          <div style="width: 80%;margin:20px auto;">
           <span style="font-size: 28px;">登录系统</span>
          </div>
          <div style="width: 80%;margin: 0 auto;">
            <el-form ref="form" :model="form" :rules="rules" validate-on-rule-change status-icon >
              <el-form-item label="账号" prop="name" :error="error">
                <el-input v-model="form.name" prefix-icon="el-icon-user" placeholder="账号">
                </el-input>
              </el-form-item>
              <el-form-item label="密码" prop="password" :error="error">
                <el-input v-model="form.password" show-password  prefix-icon="el-icon-key" placeholder="密码"></el-input>
              </el-form-item>
              <el-button type="primary" @click="submitForm()">登录</el-button>
              </el-form-item>
              <el-form-item size="medium">
                <router-link to="/manage/register" style="text-align: right;text-decoration: none;color: #40C1F2;" >没有账号,前往注册</router-link>
              </el-form-item>

            </el-form>
          </div>

        </el-card>
    </div>
    <div class="lo-qt">
      <ul>
        <li><a href="#/manage"><i class="el-icon-s-promotion"></i>返回后台</a></li>

        <li><a href="#/"><i class="el-icon-back"></i>回到商城</a></li>
      </ul>
    </div>
  </div>
</template>

<script>

  export default{
    data () {
      let validateName = (rule, value, callback) => {

              if (value=='') {
                return callback(new Error('账号不能为空'))
              }else{
                callback()
              }
             }
      let validatePassword=(rule,value,callback)=>{
        if (value=='') {
          return callback(new Error('密码不能为空'))
        }else{
          callback()
        }
      }
      return{
        form:{
          name:'',
          password:''
        },
        rules:{
          name:[
            {trigger:'blur',validator:validateName}
          ],
          password:[
            {trigger:'blur',validator:validatePassword}
          ]
        },
        error:'',
      }
    },
    methods:{
      submitForm(){
          let forms = this.$refs['form']
          forms.validate((pass)=>{
            if(pass){
              this.axios.post(
                'http://localhost:8080/logincheck',
                JSON.stringify({
                  userid:forms.model.name,
                  password:forms.model.password
                }),
                {
                  headers:{'Content-Type':'application/json'}
                }
              ).then(resp=>{
                if(resp.data.status==200){
                  let user = resp.data.data[0]
                  sessionStorage.setItem("userinfo",JSON.stringify(user))
                  this.$router.push({
                      path:"/"
                  })
                }else{
                  this.error=resp.data.message
                }
              })
            }else{
              return false
            }
          })

      }
     }

  }

</script>

<style scoped="scoped">
  .login{
    margin: 0 auto;
    position: relative;
    background-color:#EEEEEE ;
    height: 100%;
    width: 100%;
    background-image: url(../assets/img/login.jpg);
    background-size: 100% 100%;
  }

  .login .login-card{
    position: absolute;
    width: 500px;
    right: 35%;
    top: 25%;
  }

  .login .adv{
    position: absolute;
    right:40px;
    top: 20px;
  }

  .login .kf{
    width: 80px;
    position: relative;
    top:300px;
    height: 200px;
  }

  .login .adv ul li{
    font-size: 24px;
    opacity: 0.6;
    transition: opacity .3s;
  }

  .login .adv ul li:hover{
    opacity: 1;
  }

  .login .adv ul li a{
    text-decoration: none;
    color: black;
  }

  .kf .kf-one{
    width: 80px;
    height: 80px;
    background-image: url(../assets/img/phone.jpg);
    background-size: 100% 100%;
    position: absolute;
    z-index: 31;
    border-radius: 15px;
    background-color:#FFFFFF;
    transition: left .8s;
    left: -45px;
  }

  .kf .kf-one:hover{
    left: 12px;
  }

  .kf .kf-one a{
    display: block;
    height: 100%;
    text-align: center;
    border-radius: 15px;
  }

  .kf .kf-two{
    position: absolute;
    top:120px;
    left: -45px;
    border-radius: 15px;
    background-color: #FFFFFF;
    transition: left .8s;
  }

  .kf-two img{
    border-radius: 15px;

  }

  .kf-two:hover{
    left: 12px;
  }

  .lo-qt{
    width: 82px;
    position: fixed;
    height: 180px;
    right: 0;
    top:300px;
  }

  .lo-qt ul{
   width: 100%;
   height: 100%;
  }
  .lo-qt ul li{
   width: 100%;
   height:50% ;
   box-shadow: 0 0 1px #99A9BF;
   text-align: center;
   position: relative;
   color:black;
   cursor: pointer;
  }

  .lo-qt ul li a:hover{
      color: #ff6700;
   }


  .lo-qt ul li i {
   width: 100%;
   font-size: 40px;
   margin: 0 auto;
   display: block;
   box-sizing: border-box;
   padding: 12px;
  }
  .lo-qt ul a {
    font-size: 16px;
    text-decoration: none;
    color:  black;
  }
</style>
