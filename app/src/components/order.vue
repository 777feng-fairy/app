<template>
  <div class="order">
    <div class="order-content clearfix">
      <div class="order-content-card clearfix">
        <div style="height: 15%;width: 100%;">
          <div style="height: 100%;line-height:57px;text-align: right;margin:0 20px;color: red;">{{orderArr.orderStatus}}</div>
        </div>
        <div class="order-content-img">
          <el-image lazy style="width: 200px;height: 200px;" :src="orderArr.shop.shopUrl"></el-image>
        </div>
        <div class="order-content-shopinfo">
          <div style="box-sizing: border-box;padding: 10px;width: 100%;height: 100%;" class="clearfix">
            <div style="width: 60%;height: 80%;float: left;">
              <p>{{orderArr.shop.shopName}}</p>
            </div>
            <div style="width: 40%;height: 80%;float: left;text-align: right;">
              <p>￥{{orderArr.shop.shopPrice}}</p>
              <p style="margin: 12px 0;">x{{orderArr.shopNum}}</p>
            </div>
          </div>
        </div>
        <div class="order-content-btn">
          <div class="order-content-btn--table">
            <div align="right">
              <span>总价：{{orderArr.total}}</span>
              <span></span>
            </div>
          </div>
          <div class="order-content-btn--table">
            <div align="right" style="height: 100%;">
              <el-button type="primary" @click="updateOrderStatus()">取消订单</el-button>
              <el-button v-show="deliveryShow" type="success"  @click="confirmReceipt()">确认收货</el-button>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>

  export default{
    computed:{
      deliveryShow:function(){
        let status = this.orderArr.orderStatus
        if(status==='已付款'){
          return false
        }else{
          return true
        }
      }
    },
    props:{
      orderArr:{
        type:Object,
        required:true
      },

    },
    methods:{
      updateOrderStatus(){
        let orderid = this.orderArr.orderId
        this.axios({
          url:"http://localhost:8080/userorder/updatestatus",
          method:'post',
          params:{
            orderid:orderid
          }
        }).then(resp=>{
          if(resp.data.status==200){
            let row = this.orderArr
            this.$emit("removeorder",row)
            this.$message({
              type:"success",
              message:'订单取消'
            })
          }
        })
      },

      confirmReceipt(){
        let orderid = this.orderArr.orderId
        this.axios({
          url:"http://localhost:8080/userorder/confirmreceipt",
          method:'post',
          params:{
            orderid:orderid
          }
        }).then(resp=>{
          if(resp.data.status==200){
            let row = this.orderArr
            this.$emit("removeorder",row)
            this.$message({
              type:"success",
              message:'确认收货'
            })
          }
        })
      }
    }
  }
</script>

<style>

  .order{
    width: 100%;
  }

  .order-content{
    box-sizing: border-box;
    padding: 20px;
    height: 420px;
  }

  .order-content-card{
    background: #FFFFFF;
    border: 1px solid #ebeef5;
    width: 100%;
    height: 100%;
    border-radius:12px ;
  }

  .order-content-card:hover{
     box-shadow: 0 2px 12px rgba(0,0,0,.1);
  }

  .order-content-img{
    height: 200px;
    width: 230px;
    padding:0 15px;
    box-sizing: border-box;
    float: left;
  }

  .order-content-shopinfo{
    height: 200px;
    width: calc(100% - 230px);
    padding:0 15px;
    box-sizing: border-box;
    float: left;
  }
  .order-content-btn{
    width: 100%;
    height: calc(100% - 200px - 15%);
    float: left;
  }

  .order-content-btn--table{
    height: 50%;
    box-sizing: border-box;
    line-height: 55px;
    margin: 0 20px;
  }
</style>
