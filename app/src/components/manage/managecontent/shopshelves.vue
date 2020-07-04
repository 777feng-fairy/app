<template>
  <div>
    <div style="height: 100px;background: #FFFFFF;">
      <div style="box-sizing: border-box;padding: 12px;position: relative;" class="clearfix">
        <div style="float: left;width: 200px;top:18px;position: relative;">
          <el-input placeholder="输入商品名称" prefix-icon="el-icon-search" v-model="search"></el-input>
        </div>
        <div style="float: left;width: 200px;top:18px;position: relative;margin-left: 20px;">
          <el-button type="primary" @click="getShopByName()">搜索</el-button>
        </div>
      </div>
    </div>
    <div>
      <el-table
          :data="tableData"
          style="width: 100%;"
          height="703"
          border
          >
          <el-table-column type="expand" fixed >
               <template slot-scope="props">
                 <el-form label-position="left" inline class="demo-table-expand">
                   <el-form-item label="商品ID" >
                     <span>{{ props.row.shopId }}</span>
                   </el-form-item>
                   <el-form-item label="商品名称" >
                     <span>{{ props.row.shopName }}</span>
                   </el-form-item>
                   <el-form-item label="商品数量" >
                     <span>{{ props.row.shopNum }}</span>
                   </el-form-item>
                   <el-form-item label="店铺单价" >
                     <span>{{ props.row.shopPrice }}</span>
                   </el-form-item>
                   <el-form-item label="商品详情" >
                     <span>{{ props.row.shopInfo }}</span>
                   </el-form-item>
                   <el-form-item label="进货时间">
                     <span>{{ props.row.stime }}</span>
                   </el-form-item>
                   <el-form-item label="商品类型">
                     <span>{{ props.row.type }}</span>
                   </el-form-item>
                   <el-form-item label="商品状态" >
                     <span>{{ props.row.shopStatus }}</span>
                   </el-form-item>
                 </el-form>
               </template>
             </el-table-column>
          <el-table-column
            fixed
            prop="shopId"
            label="商品ID"
            width="120"
            sortable>
          </el-table-column>
          <el-table-column
            fixed
            prop="shopName"
            label="商品名称"
            width="120"
            sortable>
            <template slot-scope="scope">
              <el-input type="text" v-model="scope.row.shopName"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            prop="shopNum"
            label="数量"
            width="120">
            <template slot-scope="scope">
              <el-input type="text" v-model="scope.row.shopNum"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            prop="shopPrice"
            label="单价"
            width="120">
            <template slot-scope="scope">
              <el-input v-model="scope.row.shopPrice"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            prop="shopInfo"
            label="详情"
            >
            <template slot-scope="scope">
              <el-input v-model="scope.row.shopInfo"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            prop="stime"
            label="进货时间"
            width="120"
            sortable>

          </el-table-column>
          <el-table-column
            prop="type"
            label="类型"
            width="120"
          >
          <template slot-scope="scope">

          <el-select v-model="scope.row.type"  placeholder="请选择">
             <el-option
               v-for="item in options"
               :key="item.value"
               :label="item.label"
               :value="item.value">
             </el-option>
           </el-select>
          </template>
          </el-table-column>
          <el-table-column
            prop="shopStatus"
            label="商品状态"
            width="80">
            <template slot-scope="scope">
             <el-select v-model="scope.row.shopStatus"  placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </template>
            </el-table-column>
            <el-table-column
              prop="shopStatus"
              label="操作"
              width="80">
              <template slot-scope="scope">
                <el-button  type="primary" @click="updateShop(scope.$index,tableData)">修改</el-button>
              </template>
              </el-table-column>
        </el-table>
    </div>

  </div>
</template>

<script>
  export default{
    data(){
      return{
        tableData:[
         //{shopId:'',shopName:'dad',shopNum:0,shopUrl:'',shopPrice:0,shopInfo:'',stime:'',type:'电脑',shopStatus:0}
        ],
        search:'',
        table:[],
        options:[
          //电脑，手机，平板，充电器，数据线，上衣，裤子，鞋子，食品，首饰，电视机，耳机，路由器，插排线
          {
            value:'电脑',
            label:'电脑'
          },{
            value:'手机',
            label:'手机'
          },
          {
            value:'平板',
            label:'平板'
          },

          {
            value:'充电器',
            label:'充电器'
          },
          {
            value:'数据线',
            label:'数据线'
          },
          {
            value:'上衣',
            label:'上衣'
          },
          {
            value:'裤子',
            label:'裤子'
          },
          {
            value:'鞋子',
            label:'鞋子'
          },
          {
            value:'食品',
            label:'食品'
          },
          {
            value:'首饰',
            label:'首饰'
          },
          {
            value:'电视机',
            label:'电视机'
          },
          {
            value:'耳机',
            label:'耳机'
          },
          {
            value:'路由器',
            label:'路由器'
          },{
            value:'插板线',
            label:'插板线'
          }
        ],
        status:[
          {
            value:'上架',
            label:'上架'
          },
          {
            value:'下架',
            label:'下架'
            }
        ]
      }
    },
    mounted:function(){

      if(sessionStorage.getItem("userinfo")!=null){
        this.getShopTable()
      }else{
        this.$message({
          type:'warning',
          message:'登录才有数据哦'
        })
      }


    },
    methods:{

      islogin(){
        if(sessionStorage.getItem("userinfo")!=null){
          return true
        }else{
          return false
        }
      },

      getShopTable(){

        this.axios({
          url:'http://localhost:8080/shop/shopdata',
          method:'post',
          responseType: 'json'
        }).then(resp=>{
          if(resp.data.status==200){
            this.tableData=resp.data.data
            this.table=resp.data.data
          }
        })
      },
      getShopByName(){
        if(this.islogin()){
          let search = this.search
          this.axios({
            url:'http://localhost:8080/shop/shopsearch',
            method:'post',
            responseType:'json',
            data:{
              search:search
            }
          }).then(resp=>{
            if(resp.data.status=200){
              if(resp.data.data!=null){
                this.tableData=resp.data.data
              }else{
                this.$message({
                   message: '搜索结果不存在',
                   type: 'warning'
                })
              }
            }
          })
        }else{
          this.$message({
            message:"请前往登录",
            type:'warning'
          })
        }

      },
      updateShop(index,data){

        if(this.islogin()){
          this.axios({
            url:'http://localhost:8080/shop/updateshop',
            method:'post',
            data:data[index]
          }).then(resp=>{
            if(resp.data.status=200){
              this.$message({
                message:resp.data.message,
                type:'success'
              })
            }
          }).catch(resp=>{
                this.$message.error("哎呀！报错了")
                this.tableData=this.table
            })
        }else{
          this.$message({
            message:"请前往登录",
            type:'warning'
          })
        }


      }
    }

  }
</script>

<style>
  .demo-table-expand {
     font-size: 0;
   }
   .demo-table-expand label {
     width: 90px;
     color: #99a9bf;
   }
   .demo-table-expand .el-form-item {
     margin-right: 0;
     margin-bottom: 0;
     width: 100%;
   }
</style>
