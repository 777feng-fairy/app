<template>
  <div class="mysetting">
    <div class="mysetting-content">
      <div>
        <el-divider content-position="left">用户设置</el-divider>
      </div>
      <div>
        <el-collapse >
          <el-collapse-item title="修改昵称" name="1">
            <div>
              <el-input prefix-icon="el-icon-user" style="width: 300px;" v-model="user.userName" minlength="1" maxlength="10" show-word-limit></el-input>
              <el-button type="primary" @click="updateName()">修改</el-button>
             </div>
          </el-collapse-item>
          <el-collapse-item title="修改年龄" name="2">
            <div>
               <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="user.userAge">
               </el-date-picker>
               <el-button type="primary" @click="updateAge()">修改</el-button>
            </div>
          </el-collapse-item>
          <el-collapse-item title="修改地址" name="3">
            <div>
              <el-input prefix-icon="el-icon-location" style="width: 300px;" v-model="user.address" minlength="6" maxlength="50" show-word-limit></el-input>
              <el-button type="primary" @click="updateAddress()">修改</el-button>
             </div>
          </el-collapse-item>
          <el-collapse-item title="修改电话" name="4">
            <div>
              <el-input prefix-icon="el-icon-phone" style="width: 300px;" v-model="user.userTel" maxlength="11" show-word-limit></el-input>
              <el-button type="primary" @click="updateTel()">修改</el-button>
            </div>
          </el-collapse-item>
          <el-collapse-item title="修改密码" name="5">
            <div>
              <el-input prefix-icon="el-icon-key" style="width: 300px;" type="password" v-model="user.password" minlength="6" maxlength="14" show-word-limit></el-input>
              <el-button type="primary" @click="updatePwd()">修改</el-button>
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>
    </div>
  </div>
</template>

<script>

  export default{
    inject: ['reload'],
    mounted:function(){
      this.getUser()
    },
    methods:{
      isLogin(){
        if(sessionStorage.getItem("userinfo")==null){
          return false
        }else{
          return true
        }
      },
      getUser(){
        if(this.isLogin()){
          let user =JSON.parse(sessionStorage.getItem("userinfo"))
          user.password = ''
          this.user=user
        }else{
          this.$message({
            type:'warning',
            message:'请先登录'
          })
        }
      },
      updateUser(){
        let id = this.user.userId
        this.axios({
          url:'http://localhost:8080/user/refresh',
          method:'post',
          params:{
            userid:id,
          }
        }).then(resp=>{
          if(resp.data.status==200){
            let user = resp.data.data[0]
            this.user = user
            sessionStorage.setItem("userinfo",JSON.stringify(user))
            /* 使用中转
            this.$router.replace({
              path:'/refresh'
            }) */
            this.reload()
          }
        })
      }
      ,
      updateName(){
        if(this.isLogin()){
          let name=this.user.userName
          let id = this.user.userId
          this.axios({
            url:'http://localhost:8080/user/setname',
            method:'post',
            params:{
              userid:id,
              username:name
            }
          }).then(resp=>{
            if(resp.data.status==200){
              this.updateUser()
              this.$message({
                type:'success',
                message:'修改成功'
              })

            }
          }).catch(resp=>{
            this.$message.error("哎呀！报错了0.0")
          })
        }else{
          this.$message({
            type:'warning',
            message:'请先登录'
          })
        }
      },
      updateAge(){
        if(this.isLogin()){
          let age=this.user.userAge
          let id = this.user.userId
          this.axios({
            url:'http://localhost:8080/user/setage',
            method:'post',
            params:{
              userid:id,
              userage:age
            }
          }).then(resp=>{
            if(resp.data.status==200){
              this.updateUser()
              this.$message({
                type:'success',
                message:'修改成功'
              })
            }
          }).catch(resp=>{
            this.$message.error("哎呀！报错了0.0")
          })
        }else{
          this.$message({
            type:'warning',
            message:'请先登录'
          })
        }
      },
      updateAddress(){
        if(this.isLogin()){
          let address=this.user.address
          let id = this.user.userId
          this.axios({
            url:'http://localhost:8080/user/setaddress',
            method:'post',
            params:{
              userid:id,
              address:address
            }
          }).then(resp=>{
            if(resp.data.status==200){
              this.updateUser()
              this.$message({
                type:'success',
                message:'修改成功'
              })
            }
          }).catch(resp=>{
            this.$message.error("哎呀！报错了0.0")
          })
        }else{
          this.$message({
            type:'warning',
            message:'请先登录'
          })
        }
      },
      updateTel(){
        if(this.isLogin()){
          let tel=this.user.userTel
          let id = this.user.userId
          this.axios({
            url:'http://localhost:8080/user/settel',
            method:'post',
            params:{
              userid:id,
              usertel:tel
            }
          }).then(resp=>{
            if(resp.data.status==200){
              this.updateUser()
              this.$message({
                type:'success',
                message:'修改成功'
              })
            }
          }).catch(resp=>{
            this.$message.error("哎呀！报错了0.0")
          })
        }else{
          this.$message({
            type:'warning',
            message:'请先登录'
          })
        }
      },
      updatePwd(){
        if(this.isLogin()){
          let pwd=this.user.password
          let id = this.user.userId
          this.axios({
            url:'http://localhost:8080/user/setpwd',
            method:'post',
            params:{
              userid:id,
              password:pwd
            }
          }).then(resp=>{
            if(resp.data.status==200){

              this.$message({
                type:'success',
                message:'修改成功,请重新登录'
              })
              sessionStorage.removeItem("userinfo")
              this.$router.push({
                path:"/manage/login"
              })
            }
          }).catch(resp=>{
            this.$message.error("哎呀！报错了0.0")
          })
        }else{
          this.$message({
            type:'warning',
            message:'请先登录'
          })
        }
      }

    },
    data(){
      return{
        user:{
          userName:'',
          password:'',
          userAge:'',
          address:'',
          userTel:'',
          userId:'',
          userImg:'',
          type:''
         }

      }
    }
  }

</script>

<style>
  .mysetting{
    width: 100%;
  }
  .mysetting-content{
    width: 60%;
    margin: 0 auto;
  }
</style>
