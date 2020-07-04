<template>
  <div class="shopcart">
     <div class="shopcart-content">
       <div style="width: 100%;margin: 0 0 20px;" class="clearfix">
         <div style="width: 154px;float: left;">
           <el-button @click="isShow()">点击编辑收货信息</el-button>
         </div>
         <div v-show="showaddr" style="float: right;height:40px ;width: 675px;" class="clearfix">
           <div class="fl" style="margin-left: 7px;">
             <el-input v-model="user.userName" prefix-icon="el-icon-user"></el-input>
           </div>
           <div class="fl" style="margin-left: 7px;">
             <el-input v-model="user.userTel" prefix-icon="el-icon-phone"></el-input>
           </div>
           <div class="fl" style="margin-left: 7px;">
             <el-input v-model="user.address" prefix-icon="el-icon-location-outline"></el-input>
           </div>
         </div>

       </div>
       <el-table
          ref="shopcart"
          :data="tabel"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange"
          @select="selectIndex"
          >
          <el-table-column
            type="selection"
            width="50">
          </el-table-column>
          <el-table-column
            label=""
            prop="shop.shopUrl"
            width="110">
            <template slot-scope="scope">
              <el-image :src="scope.row.shop.shopUrl" style="width: 80px;height: 80px;" lazy></el-image>
            </template>
          </el-table-column>
          <el-table-column
            label="商品名称"
            prop="shop.shopName"
            width="100">
          </el-table-column>
          <el-table-column
            label="商品信息"
            prop="shop.shopInfo"
            width="100">
          </el-table-column>
          <el-table-column
            label="单价"
            prop="shopPrice"
            width="120"
            >
          </el-table-column>
          <el-table-column
            label="数量"
            prop="shopNum"
            width="220"
            >
            <template slot-scope="scope">
              <el-input-number v-model="scope.row.shopNum" :min="1"></el-input-number>
            </template>
          </el-table-column>
          <!-- <el-table-column
            label="配送地址"
            prop="address"
            show-overflow-tooltip>
            <template slot-scope="scope">
              <el-input v-model="scope.row.address"></el-input>
            </template>
          </el-table-column> -->
          <el-table-column
            label="总价"
            width="120"
            >
            <template slot-scope="scope">
              <span style="color: red;">{{ accMul(scope.row.shopNum,scope.row.shopPrice) }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            width="150"
            >
            <template slot-scope="scope">
               <el-button type="primary" @click.native.prevent="removeShopCart(scope.$index,tabel)">移出购物车</el-button>
            </template>

          </el-table-column>
        </el-table>
        <div style="margin-top: 20px" class="clearfix">
          <div style="float: left;width: 30%;">
            <el-button @click="toggleSelection()" type="success">取消选择</el-button>
          </div>
          <div style="float: right;width: 50%;text-align: right;">
            <span>已选商品 <span style="color: red;">{{selectNum}}</span>  件</span>
             <el-divider direction="vertical"></el-divider>
            <span>共计：<span style="color: red;">{{allTotal}}</span>元</span>
            <el-divider direction="vertical"></el-divider>
            <el-button type="primary" @click="jieSuan()">结算</el-button>
            <el-divider direction="vertical"></el-divider>
          </div>
        </div>

     </div>
  </div>
</template>

<script>

  export default{
    data(){
      return{
        tabel:[
        // {shop:{shopName:'小米5s'},shopNum:12,shopPrice:120}

        ],
        multipleSelection:[],
        selectNum:0,
        showaddr:false,
        user:{
          userId:'',
          password:'',
          userName:'',
          address:'',
          userAge:'',
          userTel:'',
          userImg:''
        }
      }
    },
    methods:{
      isShow(){
        this.showaddr =this.showaddr? false:true
      },
      jieSuan(){
        if(this.isLogin()){
          let shopidlist=[],shopnumlist=[],shoppricelist=[]
          for(let i =0;i<this.multipleSelection.length;i++){
            shopidlist[i]=this.multipleSelection[i].shopId
            shopnumlist[i]=this.multipleSelection[i].shopNum
            shoppricelist[i]=this.multipleSelection[i].shopPrice
          }
          let userTel = this.user.userTel
          let address =this.user.address
          let userName = this.user.userName
          let userId = this.user.userId
          if(this.multipleSelection.length==0){
              this.$message({
                type:'warning',
                message:'请选择商品'
              })
          }else{
            this.axios({
              url:'http://localhost:8080/userorder/listorder',
              method:'post',
              data:{
                shopidlist:shopidlist,
                shopnumlist:shopnumlist,
                shoppricelist:shoppricelist,
                username:userName,
                address:address,
                userTel:userTel,
                userId:userId
              }
            }).then(resp=>{
              if(resp.data.status==200){
                let table = this.tabel
                let selectable=this.multipleSelection
                for(let i=0;i<selectable.length;i++){
                  for(let j =0;j<table.length;j++){
                    if(table[j]===selectable[i]){
                      this.tabel.splice(j,1)
                    }
                  }
                }
                this.$message({
                  type:'success',
                  message:'结算成功'
                })
              }
            }).catch(resp=>{
              this.$message.error("呀哎！服务器报错了0.0")
            })
          }

        }else{
          this.$message({
            type:'warning',
            message:'请先登录'
          })
        }
      },
      removeShopCart(index,table){
        if(this.isLogin()){
          let row  = table[index]

          this.axios({
            url:"http://localhost:8080/shopcart/removecart",
            method:'post',
            params:{
              shopid:row.shopId,
              userid:row.userId
            }
          }).then(resp=>{
            if(resp.data.status==200){
              table.splice(index,1)
              this.$message({
                type:"success",
                message:'移除成功'
              })
            }else{
               this.$message.error("哎呀！不小心报错了0.0")
            }

          }).catch(resp=>{
            this.$message.error("哎呀！不小心报错了0.0")
          })
        }else{
          this.$message({
            type:'warning',
            message:'请先登录'
          })
        }


      },

       toggleSelection(rows) {

              if (rows) {
                rows.forEach(row => {
                  this.$refs.shopcart.toggleRowSelection(row);
                });
              } else {
                this.$refs.shopcart.clearSelection();
                this.selectNum=0;

              }
            },
            handleSelectionChange(val) {
             this.multipleSelection = val;
             this.selectNum=val.length

            },
            selectIndex(rows,row){
              this.selectNum=rows.length

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
            isLogin(){
              if(sessionStorage.getItem("userinfo")==null){
                return false
              }else{
                return true
              }
            },
            getUserShopCart(){
              if(this.isLogin()){
                let user = JSON.parse(sessionStorage.getItem("userinfo"))
                this.user = user
                this.axios({
                  url:'http://localhost:8080/shopcart/listcart',
                  method:'post',
                  params:{
                    userid:user.userId
                  }
                }).then(resp=>{
                  if(resp.data.status==200){
                    let dd=resp.data.data
                    for(let i=0;i<dd.length;i++){
                      dd[i].shop.shopUrl="data:image/png;image/jpg;image/jpeg;base64,"+dd[i].shop.shopUrl
                    }
                    this.tabel=dd
                  }
                }).catch(resp=>{
                  this.$message.error("哎呀！未知原因的错误")
                })
              }else{
                this.$message({
                  type:'warning',
                  message:'请先登录'
                })
              }
            }

     },
     computed:{
       allTotal:function(){
         if(this.multipleSelection.length==0){
           return 0
         }else{
           let sum = 0
           for(let i =0;i<this.multipleSelection.length;i++){
             let a = this.accMul(this.multipleSelection[i].shopNum,this.multipleSelection[i].shopPrice)
            sum+=a

           }
           return sum
         }

       }
     },
     mounted:function(){
       this.getUserShopCart()
     }

  }
</script>

<style>
  .shopcart{
    width: 100%;
  }
  .shopcart-content{
    width: 60%;
    margin: 20px auto;
  }
  .fl{
    float: left;
  }
</style>
