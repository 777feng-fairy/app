<template>
  <div class="register">
    <div class="register-content">
       <div class="register-content-header">
         <img src="../assets/img/logo.png" width="80px" height="80px" alt="logo" style="position: absolute;left: 160px;z-index: 9;">
         <p style="position: relative;top: 19px;">雪蝶商城注册</p>
          <img src="../assets/img/logo.png" width="80px" height="80px" alt="logo" style="position:absolute;right:160px;top:0px;z-index: 9;">
          <div style="height: 1px;position: relative;top:36px">
            <el-divider content-position="right">雪蝶</el-divider>
          </div>
       </div>
       <div class="register-content-form" >
         <div style="width: 60%;margin: 0 auto;">
           <el-form ref="form" :model="form" :rules="rules"  validate-on-rule-change status-icon>
             <el-form-item label="昵称" prop="userName" >
               <el-input v-model="form.userName" placeholder="昵称" prefix-icon="el-icon-user" ></el-input>
             </el-form-item>
            <el-form-item label="手机号" prop="userTel" >
              <el-input v-model="form.userTel" prefix-icon="el-icon-phone" ></el-input>
            </el-form-item>
            <el-form-item label="出生年月" prop="userAge" >
               <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="form.userAge" prefix-icon="el-icon-date" style="width: 100%;"></el-date-picker>
            </el-form-item>
            <el-form-item label="密码" prop="password" >
              <el-input v-model="form.password" prefix-icon="el-icon-key"  show-password></el-input>
            </el-form-item>
            <el-form-item label="重新输入密码" prop="password_ck" >
              <el-input v-model="form.password_ck" prefix-icon="el-icon-key" show-password ></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address" >
              <el-input v-model="form.address" prefix-icon="el-icon-position" ></el-input>
            </el-form-item>
             <el-form-item>
               <el-button type="primary" @click="submitForm()" >立即注册</el-button>
             </el-form-item>
             <el-form-item>
               <router-link to="/manage/login" style="text-align: right;text-decoration: none;color: #40C1F2;">已有账号,前往登录</router-link>
             </el-form-item>
           </el-form>
         </div>

       </div>
    </div>
    <div class="reg-qt">
      <ul>
        <li><a href="#/manage"><i class="el-icon-s-promotion"></i>返回后台</a></li>

        <li><a href="#/"><i class="el-icon-back"></i>回到商城</a></li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default{
    data(){
      let validateNUll=(rule,value,callback)=>{
        if(value==''){
          return callback(new Error("不能为空"))
         }else{
           callback()
         }
      }

      let checkTel=(rule,value,callback)=>{

        if(value==''){
          return callback(new Error("不能为空"))
         }

         if(value.length!=11){
           return callback(new Error("请输入正确电话号码"))
         }

         if(isNaN(value)){
            return callback(new Error("请输入数字"))
         }else{
            callback()
         }

       }

      let checkPassword=(rule,value,callback)=>{
          if(value==''){
            return callback(new Error("不能为空"))
          }
          if(value!=this.form.password){
            return callback(new Error("两次密码不一致"))
          }else{
            callback()
          }

       }

      return{
        form:{
          userName:'',
          password:'',
          password_ck:'',
          userAge:'',
          address:'',
          userTel:'',
          userId:'',
          userImg:''
        },
        rules:{
          userName:[
            {validator:validateNUll,trigger:'blur'},
            {min:0,max:20,message:'最多20个字母或10个汉字',trigger:'blur'}
          ],
          password:[
            {validator:validateNUll,trigger:'blur'},
            {min:6,max:20,message:'最低6位密码',trigger:'blur'}
          ],
          password_ck:[
            {validator:checkPassword,trigger:'blur'}
          ],
          userAge:[
            {validator:validateNUll,trigger:'blur'}
          ],
          userTel:[
            {validator:checkTel,trigger:'blur'}

          ],
          address:[
            {validator:validateNUll,trigger:'blur'}
          ]
        }

      }
    },
    methods:{
      submitForm(){
        let form = this.$refs['form']

       form.validate((pass)=>{
         if(pass){
           this.axios.post(
             'http://localhost:8080/register',
             JSON.stringify(
              {
                userName:form.model.userName,
                password:form.model.password,
                userAge:form.model.userAge,
                address:form.model.address,
                userTel:form.model.userTel,
                userId:'',
                userImg:''
              }
             ),
             {
               headers:{'Content-Type':'application/json'}
             }
           ).then(resp=>{
             if(resp.data.status==200){
                 this.$message({
                   type:"success",
                   message:"注册成功"
                 })
                 sessionStorage.setItem('userid',resp.data.data[0].userId)
                 this.$router.push({
                   path:'/manage/getid'
                 })
             }else{
              this.$message.error('未知错误导致注册失败');
             }
           }).catch(error=>{
             this.$message.error("未知错误导致注册失败")
           })
         }else{
           return false
         }
       })

      }
    }
  }
</script>

<style >
  .register{
    height: 100%;
    width:100%;
    background: #EEEEEE;
    position: relative;
    background-image: linear-gradient(to bottom,#40C1F2,#68CB4F);
  }
  .register .register-content{
    width: 60%;
    height: 100%;
    background: #FFFFFF;
    margin: 0 auto;
    position: relative;
    text-align: center;
  }

  .register .register-content .register-content-form{
      position: relative;
      margin: 50px auto 0;
      width: 60%;
      height: 730px;
      transition: box-shadow .3s;
      text-align: left;
  }

  .register .register-content .register-content-form:hover{
    box-shadow: 0 0 1px;
  }

  .register .register-content .register-content-header{
      position: relative;
      top:30px;
      letter-spacing: 32px;
      font-size: 32px;
      height: 98px;
  }

  .reg-qt{
    width: 82px;
    position: fixed;
    height: 180px;
    right: 0;
    top:300px;
  }

  .reg-qt ul{
   width: 100%;
   height: 100%;
  }
  .reg-qt ul li{
   width: 100%;
   height:50% ;
   box-shadow: 0 0 1px black;
   text-align: center;
   position: relative;
   color:  black;
   cursor: pointer;
   transition: color .3s;
  }

  .reg-qt ul li a:hover{
       color: #ff6700;
   }


  .reg-qt ul li i {
   width: 100%;
   font-size: 40px;
   margin: 0 auto;
   display: block;
   box-sizing: border-box;
   padding: 12px;
  }
  .reg-qt ul a {
    font-size: 16px;
    text-decoration: none;
    color:  black;
  }


</style>
