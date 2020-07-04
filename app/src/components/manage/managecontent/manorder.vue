<template>
  <div class="manorder">
    <div class="manorder-content">
      <div class="manorder-content-search clearfix">
        <div class="fl clearfix" style="margin-right: 200px;">
          <div class="fl" style="width: auto;margin-right: 200px;">
            <el-button @click="getOrderList()">查看全部数据</el-button>
          </div>
          <div style="width: 300px;margin-right: 50px;" class="fl">
            <el-input placeholder="请输入订单编号" prefix-icon="el-icon-search" v-model="searchId"></el-input>
          </div>
          <div class="fl" style="width: auto;">
            <el-button type="primary" @click="getOrderOneOrMore()">搜索</el-button>
          </div>
        </div>
        <div class="fl" style="width: 200px;margin: 0 50px 0 0;">
          <el-select v-model="value" placeholder="请选择订单类型">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </div>
        <div class="fl" style="width: auto;">
          <el-button type="primary" @click="getOrderByType()">搜索</el-button>
        </div>

      </div>
       <el-divider></el-divider>
       <div style="overflow: auto;height:670px;" class="hid-scoll">
           <el-table
             :data="tableData"
             style="width: 100%">
             <el-table-column type="expand">
               <template slot-scope="props">
                 <el-form label-position="left" inline class="tabledata-algin">
                   <el-form-item label="订单 ID">
                     <span>{{ props.row.orderId }}</span>
                   </el-form-item>
                   <el-form-item label="用户 ID">
                     <span>{{ props.row.userId }}</span>
                   </el-form-item>
                   <el-form-item label="商品 ID">
                     <span>{{ props.row.shopId }}</span>
                   </el-form-item>
                   <el-form-item label="购买数量">
                     <span>{{ props.row.shopNum }}</span>
                   </el-form-item>
                   <el-form-item label="实际付款">
                     <span>{{ props.row.total }}</span>
                   </el-form-item>
                   <el-form-item label="收件人">
                     <span>{{ props.row.userName }}</span>
                   </el-form-item>
                   <el-form-item label="收件人电话">
                     <span>{{ props.row.userTel }}</span>
                   </el-form-item>
                   <el-form-item label="收货地址">
                     <span>{{ props.row.address }}</span>
                   </el-form-item>
                   <el-form-item label="下单时间">
                     <span>{{ props.row.orderTime }}</span>
                   </el-form-item>
                   <el-form-item label="订单状态">
                     <span>{{ props.row.orderStatus }}</span>
                   </el-form-item>
                 </el-form>
               </template>
             </el-table-column>
             <el-table-column
               label="订单 ID"
               prop="orderId">
             </el-table-column>
             <el-table-column
               label="用户 ID"
               prop="userId">
             </el-table-column>
             <el-table-column
               label="商品 ID"
               prop="shopId">
             </el-table-column>
             <el-table-column
               label="购买数量"
               prop="shopNum">
             </el-table-column>
             <el-table-column
               label="实际付款"
               prop="total">
             </el-table-column>
             <el-table-column
               label="收件人"
               prop="userName">
             </el-table-column>
             <el-table-column
               label="收件人电话"
               prop="userTel">
             </el-table-column>
             <el-table-column
               label="收货地址"
               prop="address">
             </el-table-column>
             <el-table-column
               sortable
               label="下单时间"
               prop="orderTime">
             </el-table-column>
             <el-table-column
               label="订单状态"
               prop="orderStatus">
             </el-table-column>
           </el-table>
       </div>
    </div>
  </div>
</template>

<script>
  export default {
    mounted:function() {
      this.getOrderList()
    },
    methods:{
      isLogin(){
        if(sessionStorage.getItem("userinfo")){
          return true
        }else{
          return false
        }
      },
      getOrderList(){
        if(this.isLogin()){
          this.axios({
            url:'http://localhost:8080/userorder/manorderlist',
            method:'post'
          }).then(resp=>{
            if(resp.data.status==200){
              this.tableData = resp.data.data
            }
          }).catch(resp=>{
            this.$message.error("哎呀！服务器报错了")
          })
        }else{
          this.$message({
            type:'warning',
            message:'请登录查看'
          })
        }
      },
      getOrderOneOrMore(){
        if(this.isLogin()){
          let id = this.searchId
          if(id){
            this.axios({
              url:'http://localhost:8080/userorder/manserchid',
              method:'post',
              params:{
                orderid:id
              }
            }).then(resp=>{
              if(resp.data.status==200){
                this.tableData=resp.data.data
                this.searchId=''
              }
            }).catch(resp=>{
              this.$message.error("哎呀！服务器报错了")
            })
          }
        }else{
          this.$message({
            type:'warning',
            message:'请登录,再搜索'
          })
        }
      },
      getOrderByType(){
        if(this.isLogin()){
          let type = this.value
          if(type){
            this.axios({
              url:'http://localhost:8080/userorder/manserchstatus',
              method:'post',
              params:{
                orderstatus:type
              }
            }).then(resp=>{
              if(resp.data.status==200){
                this.tableData=resp.data.data
              }
            }).catch(resp=>{
              this.$message.error("哎呀！服务器报错了")
            })
          }
        }else{
          this.$message({
            type:'warning',
            message:'请登录再查看'
          })
        }
      }
    },
    data() {
      return {
        options: [{
            value: '1',
            label: '已付款'
          },
          {
            value: '2',
            label: '已发货'
          },
          {
            value: '3',
            label: '无效订单'
          },
          {
            value:'4',
            label:'确认收货'
          }
        ],
       value:'',
       tableData:[
        /* {orderId:'11111',shopId:'111111',userId:'1111',shopNum:5,orderTime:new Date('yyyy-MM-dd'),total:2500,address:'湖北天门',userTel:'13264673593',orderStatus:'已上架',userName:'彭奇威'},
         {orderId:'11111',shopId:'111111',userId:'1111',shopNum:5,orderTime:new Date('yyyy-MM-dd'),total:2500,address:'湖北天门',userTel:'13264673593',orderStatus:'已上架',userName:'彭奇威'},
         {orderId:'11111',shopId:'111111',userId:'1111',shopNum:5,orderTime:new Date('yyyy-MM-dd'),total:2500,address:'湖北天门',userTel:'13264673593',orderStatus:'已上架',userName:'彭奇威'},
         {orderId:'11111',shopId:'111111',userId:'1111',shopNum:5,orderTime:new Date('yyyy-MM-dd'),total:2500,address:'湖北天门',userTel:'13264673593',orderStatus:'已上架',userName:'彭奇威'},
         {orderId:'11111',shopId:'111111',userId:'1111',shopNum:5,orderTime:new Date('yyyy-MM-dd'),total:2500,address:'湖北天门',userTel:'13264673593',orderStatus:'已上架',userName:'彭奇威'},
         {orderId:'11111',shopId:'111111',userId:'1111',shopNum:5,orderTime:new Date('yyyy-MM-dd'),total:2500,address:'湖北天门',userTel:'13264673593',orderStatus:'已上架',userName:'彭奇威'},
         {orderId:'11111',shopId:'111111',userId:'1111',shopNum:5,orderTime:new Date('yyyy-MM-dd'),total:2500,address:'湖北天门',userTel:'13264673593',orderStatus:'已上架',userName:'彭奇威'},
         {orderId:'11111',shopId:'111111',userId:'1111',shopNum:5,orderTime:new Date('yyyy-MM-dd'),total:2500,address:'湖北天门',userTel:'13264673593',orderStatus:'已上架',userName:'彭奇威'}, */

       ],
       searchId:''



      }
    }
  }
</script>

<style>
  .manorder {
    width: 100%;
    height: 100%;
    background-color: #FFFFFF;
    position: relative;
  }

  .manorder-content {
    box-sizing: border-box;
    padding: 20px;
    width: 100%;
    position: relative;
  }

  .fl{
    float:left;
  }

  .fr{
    float: right;
  }

  .tabledata-algin{
    font-size: 0;
  }

  .tabledata-algin label{
    width: 300px;
  }

  .hid-scoll::-webkit-scrollbar{
    display: none;
  }

  .tabledata-algin .el-form-item {
      margin-right: 0;
      margin-bottom: 0;
      width: 100%;
    }
</style>
