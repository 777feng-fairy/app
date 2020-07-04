<template>
  <div class="removeorder">
      <div class="removeorder-content">
          <div style="height: 60px;">
            <div style="height: 60px;line-height: 60px;margin-left: 60px;">
              <el-button type="warning" @click="removeAll()">一键清空</el-button>
            </div>
          </div>
           <el-divider></el-divider>
          <div style="height: calc(100% - 110px);overflow: scroll;width: 100%;" class="hid-scoll">
                  <el-table
                    :data="tableData"
                    style="width: 100%">
                    <el-table-column type="expand" >
                      <template slot-scope="props">
                        <el-form label-position="left" inline class="tabledata-algin">
                          <el-form-item label="下单时间">
                            <span>{{ props.row.orderTime }}</span>
                          </el-form-item>
                          <el-form-item label="订单 ID">
                            <span>{{ props.row.orderId }}</span>
                          </el-form-item>
          <!--                <el-form-item label="用户 ID">
                            <span>{{ props.row.userId }}</span>
                          </el-form-item>
                          <el-form-item label="商品 ID">
                            <span>{{ props.row.shopId }}</span>
                          </el-form-item> -->
                          <el-form-item label="购买数量">
                            <span>{{ props.row.shopNum }}</span>
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
                          <el-form-item label="实际付款">
                            <span>{{ props.row.total }}</span>
                          </el-form-item>
                          <el-form-item label="订单状态">
                            <span>{{ props.row.orderStatus }}</span>
                          </el-form-item>
                        </el-form>
                      </template>
                    </el-table-column>
                    <el-table-column
                      sortable
                      label="下单时间"
                      prop="orderTime">
                    </el-table-column>
                    <el-table-column
                      label="订单 ID"
                      prop="orderId">
                    </el-table-column>
                   <!-- <el-table-column
                      label="用户 ID"
                      prop="userId">
                    </el-table-column>
                    <el-table-column
                      label="商品 ID"
                      prop="shopId">
                    </el-table-column> -->
                    <el-table-column
                      label="购买数量"
                      prop="shopNum">
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
                      label="实际付款"
                      prop="total">
                    </el-table-column>
                    <el-table-column
                      label="订单状态"
                      prop="orderStatus">
                    </el-table-column>
                    <el-table-column
                      label="操作"
                     >
                     <template slot-scope="scope">
                       <el-button type="primary" @click="removeOne(scope.row.orderId,scope.$index)">删除订单</el-button>
                     </template>
                    </el-table-column>

                  </el-table>
                </div>
      </div>
  </div>
</template>

<script>

  export default{
    mounted:function(){
      this.getOrderByType()
    },
    data(){
      return{
        tableData:[
          // {orderId:'11111',shopId:'111111',userId:'1111',shopNum:5,orderTime:new Date('yyyy-MM-dd'),total:2500,address:'湖北天门',userTel:'13264673593',orderStatus:'已上架',userName:'彭奇威'},
        ]
      }
    },
    methods:{
      isLogin(){
        if(sessionStorage.getItem("userinfo")){
          return true
        }else{
          return false
        }
      },
      getOrderByType(){
        if(this.isLogin()){

            this.axios({
              url:'http://localhost:8080/userorder/manserchstatus',
              method:'post',
              params:{
                orderstatus:3
              }
            }).then(resp=>{
              if(resp.data.status==200){
                this.tableData=resp.data.data
              }
            }).catch(resp=>{
              this.$message.error("哎呀！服务器报错了")
            })

        }else{
          this.$message({
            type:'warning',
            message:'请登录再查看'
          })
        }
      },
      removeAll(){
        if(this.isLogin()){

            this.axios({
              url:'http://localhost:8080/userorder/manremoveall',
              method:'post',
              params:{
                orderstatus:3
              }
            }).then(resp=>{
              if(resp.data.status==200){
                this.tableData=[]
                this.$message({
                  type:'success',
                  message:'删除成功'
                })
              }
            }).catch(resp=>{
              this.$message.error("哎呀！服务器报错了")
            })

        }else{
          this.$message({
            type:'warning',
            message:'请登录再查看'
          })
        }
      },
      removeOne(id,index){
        this.axios({
          url:'http://localhost:8080/userorder/manremoveone',
          method:'post',
          params:{
            orderid:id
          }
        }).then(resp=>{
          if(resp.data.status==200){
            this.tableData.splice(index,1)
            this.$message({
              type:'success',
              message:'删除成功'
            })
          }
        }).catch(resp=>{
          this.$message.error("哎呀！服务器报错了")
        })
      },
    }
  }

</script>

<style>
 .removeorder{
   width: 100%;
   height: 100%;
   position: relative;
   background-color: #FFFFFF;
 }

 .removeorder-content{
   width: 100%;
   height: 100%;
   box-sizing: border-box;
   padding: 20px;
 }

 .hid-scoll::-webkit-scrollbar{
   display: none;
 }

 .tabledata-algin{
   font-size: 0;
 }

 .tabledata-algin label{
   width: 300px;
 }

 .tabledata-algin .el-form-item {
     margin-right: 0;
     margin-bottom: 0;
     width: 100%;
 }
</style>
