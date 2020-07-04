<template>
  <div class="shopprofile">
     <div class="shopprofile-content clearfix">
        <div class="shopprofile-content-img">
          <el-image style="width: 400px;height: 400px;" :src="shop.shopUrl"></el-image>
        </div>
        <div class="shopprofile-content-cs">
          <div class="list" style="background-color:#FFF2E8 ;">
            <p style="padding: 12px 0;">商品名称:</p>
            <p style="padding: 12px 0;color: red;">{{shop.shopName}}</p>
          </div>
          <div class="list">
            <p style="padding: 12px 0;color: #000000;">单价:</p>
            <p style="padding: 12px 0;color:red;">{{shop.shopPrice}}</p>
          </div>
          <div class="list">
            <p style="padding: 12px 0;">数量：(库存：{{shop.shopNum}})</p>
            <el-input-number :min="1" v-model="num" :max="shop.shopNum" ></el-input-number>
          </div>
          <div class="list">
            <div style="padding: 27px 0;letter-spacing: 20px;">
            <span><el-button type="primary" @click="buyShop">立即购买</el-button></span>
              <span>
               <el-dialog width="500px" :visible.sync="dd">
                <div style="height: 440px;">
                  <div style="height: 150px;" class="clearfix">
                    <div style="width: 150px;float: left;">
                      <el-image lazy :src="shop.shopUrl" style="width: 150px;height: 150px;"></el-image>
                    </div>
                    <div style="width: calc(100% - 150px);height:100%;float: left;">
                      <div style="box-sizing: border-box;padding: 10px 6px;height: 100%;width: 100%;">
                        <p style="color: red;font-size: 24px;padding:4px 0px;letter-spacing: 4px;">￥{{shop.shopPrice}}</p>
                        <p style="padding: 12px 0;color: #545C64;letter-spacing: 4px;">库存{{shop.shopNum}}件</p>
                        <p style="padding: 12px 0;color: #000000;letter-spacing: 4px;">配送至:{{address}}</p>
                      </div>
                    </div>
                  </div>
                  <div style="height: 50%;" class="clearfix">
                      <div style="float: left;width: 100%;"><el-divider></el-divider></div>
                      <div style="letter-spacing: 4px;width: 50%;float: left;height: 40px;line-height: 40px;">购买数量</div>
                      <div style="letter-spacing: 4px;width: 50%;float: right;" align="right"> <el-input-number :min="1" v-model="num" :max="shop.shopNum"></el-input-number></div>
                      <div style="float: left;width: 100%;"><el-divider></el-divider></div>
                      <div style="height: 120px;float: left;width: 100%;"  class="clearfix">
                        <div style="letter-spacing: 4px;height: 40px;line-height: 40px;width: 40%;float: left;">收货人<i class="el-icon-user"></i></div>
                        <div  style="letter-spacing: 4px;height: 40px;line-height: 40px;width: 60%;float: left;"><el-input prefix-icon="el-icon-user" v-model="username"></el-input></div>
                        <div style="letter-spacing: 4px;height: 40px;line-height: 40px;width: 40%;float: left;">联系电话<i class="el-icon-phone-outline"></i></div>
                        <div  style="letter-spacing: 4px;height: 40px;line-height: 40px;width: 60%;float: left;"><el-input prefix-icon="el-icon-phone-outline" v-model="usertel"></el-input></div>
                        <div style="letter-spacing: 4px;height: 40px;line-height: 40px;width: 40%;float: left;">收货地址<i class="el-icon-location"></i></div>
                        <div  style="letter-spacing: 4px;height: 40px;line-height: 40px;width: 60%;float: left;"><el-input prefix-icon="el-icon-s-home" v-model="address"></el-input></div>
                      </div>
                      <div style="float: left;width: 100%;"><el-divider></el-divider></div>
                  </div>
                </div>
                <div slot="footer">
                  <el-button @click="dd = false">取 消</el-button>
                  <el-button type="primary" @click="addOrder">确 定</el-button>
                </div>
              </el-dialog>
            </span>
            <span><el-button type="success" @click="addShopCart">添加购物车</el-button></span>
            </div>
          </div>
        </div>
        <div class="shopprofile-content-info">
           <p style="padding: 12px 0;color: #000000;">商品详情：</p>
           <p style="padding: 12px 0;color: #000000;"> {{shop.shopInfo}}</p>
        </div>
     </div>
  </div>
</template>

<script>

  export default{
    mounted:function(){
      this.getShop()
      this.getUser()
    },
    methods:{
      buyShop(){
        //this.dd=true
       if(this.isLogin()){
          this.dd=true
        }else{
          this.$message({
            type:'warning',
            message:'请先登录'
          })
        }
      },
      accMul(arg1, arg2) {
          var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
          try {
              m += s1.split(".")[1].length;
          }
          catch (e) {
          }
          try {
              m += s2.split(".")[1].length;
          }
          catch (e) {
          }
          return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
      },
      addOrder(){
        let shop=this.shop
        let user = this.user
        let address = this.address
        let total = this.accMul(this.shop.shopPrice,this.num)
        let num = this.num
        let username = this.username
        let usertel = this.usertel
        this.axios({
           url:'http://localhost:8080/userorder/addorder',
           method:'post',
           headers:{
             'Content-Type':'application/json'
           },
           data:JSON.stringify({
              orderId:'',
              userName:username,
              shopId:shop.shopId,
              orderStatus:1,
              orderTime:'',
              total:total,
              address:address,
              userTel:usertel,
              userId:user.userId,
              shopNum:num
           })
         }).then(resp=>{
            if(resp.data.status==200){
              this.shop.shopNum=shop.shopNum-num
              this.dd=false
              this.$message({
                type:'success',
                message:'购买成功'
              })
            }else{
              this.dd=false
               this.$message.error("哎呀！发生未知错误")
            }
         }).catch(resp=>{
           this.$message.error("哎呀！发生未知错误")
         })
      },
      getShop(){
        let id = this.$route.params.id
        this.axios({
          url:'http://localhost:8080/shop/profile',
          method:'post',
          params:{
            shopid:id
          }
        }).then(resp=>{
          if(resp.data.status==200){
            let sp = resp.data.data[0]
            sp.shopUrl = "data:image/png;image/jpg;image/jpeg;base64,"+sp.shopUrl
            this.shop=sp
          }
        }).catch(resp=>{
          this.$message.error("哎呀！报错了")
        })
      },
      isLogin(){
        if(sessionStorage.getItem("userinfo")==null){
          return false
        }else{
          return true
        }

      },
      getUser(){
        if(this.isLogin()){
          let user = JSON.parse(sessionStorage.getItem('userinfo'))
          this.user = user
          this.address = user.address
          this.usertel=user.userTel
          this.username=user.userName
        }
      }
      ,
      addShopCart(){
        if(this.isLogin()){
          let user = JSON.parse(sessionStorage.getItem('userinfo'))
          let shop = this.shop
          let num = this.num
          this.axios({
            url:'http://localhost:8080/shopcart/addcart',
            method:'post',
            data:{
              userId:user.userId,
              shopId:shop.shopId,
              shopNum:num,
              shopPrice:shop.shopPrice,
              address:''
            }
          }).then(resp=>{
            if(resp.data.status==200){
              this.$message({
                type:'success',
                message:'添加成功'
              })
            }else{
               this.$message.error("哎呀！发生了意外的错误")
            }
          }).catch(resp=>{
            this.$message.error("哎呀！发生了意外的错误")
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
        shop:{
          shopId:'',
          shopName:'',
          shopPrice:'',
          shopUrl:'',
          type:'',
          shopStatus:'',
          stime:'',
          shopNum:'',
          shopInfo:''
        },
        num:1,
        dd:false,
        user:{
          userId:'',
          userName:'',
          address:'',
          password:'',
          userAge:'',
          userTel:'',
          userImg:''
        },
        address:'',
        username:'',
        usertel:''
      }
    }
  }
</script>

<style>

  .shopprofile{
    width: 100%;
  }
  .shopprofile-content{
    width: 60%;
    margin: 0 auto;
  }
  .shopprofile-content-img{
    width: 420px;
    height: 420px;
    box-sizing: border-box;
    padding: 20px;
    float: left;
  }
  .shopprofile-content-cs{
    float: left;
    box-sizing: border-box;
    padding: 20px;
    width: calc(100% - 420px);
    height: 420px;
  }
  .shopprofile-content-cs .list{
    height: 25%;
  }
  .shopprofile-content-info{
    float: left;
    width: 100%;
    box-sizing: border-box;
    padding: 20px;
  }
</style>
