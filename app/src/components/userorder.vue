<template>
  <div class="userorder">
   <div class="userorder-content clearfix">
     <div style="width: 50%;float: left;" v-for="index in orderArr.length" :key="index"  >
       <order-model :orderArr="orderArr[index-1]" v-on:removeorder="removeorder"></order-model>
     </div>

   </div>
  </div>
</template>

<script>
  import OrderModel from './order.vue'

  export default{
    components:{
      OrderModel
    },
    data(){
      return{
        orderArr:[],
      }
    },
    mounted:function(){
      this.getOrderList()
    },
    methods:{

      removeorder(row){
        
        for(let i=0;i<this.orderArr.length;i++){
          if(this.orderArr[i]===row){
            this.orderArr.splice(i,1)
          }
        }
      },

      isLogin(){
        if(sessionStorage.getItem("userinfo")==null){
          return false
        }else{
          return true
        }
      },
      getOrderList(){
        if(this.isLogin()){
          let user = JSON.parse(sessionStorage.getItem("userinfo"))
          this.axios({
            url:"http://localhost:8080/userorder/showorderlist",
            method:'post',
            params:{
              userid:user.userId
            }
          }).then(resp=>{
            if(resp.data.status==200){
              let list = resp.data.data
              for(let i =0;i<list.length;i++){
                list[i].shop.shopUrl='data:image/*;base64,'+list[i].shop.shopUrl
              }
              this.orderArr = list
            }
          }).catch(resp=>{
            this.$message.error("哎呀！报错了")
          })
        }else{
          this.$message({
            type:'warning',
            message:'请先登录'
          })
        }
      }
    }
  }
</script>

<style>

  .userorder{
    width: 100%;
  }

  .userorder-content{
    width: 60%;
    margin: 0 auto;
    box-sizing: border-box;
    padding: 20px;
  }
</style>
