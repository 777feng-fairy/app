<template>
  <div class="myshop">
    <div class="myshop-content clearfix">
      <div> <el-divider></el-divider></div>
      <div v-for="index in data.length" :key='index' class="clearfix shoplist">
          <shop-model :imgurl="data[index-1].shopUrl" :shopname="data[index-1].shopName" :price="data[index-1].shopPrice" :shopid="data[index-1].shopId"></shop-model>
      </div>
   <!-- <div class="shoplist" v-for="index in data" :key="index">
       <shop-model :imgurl="data[index-1].shopUrl" :shopname="data[index-1].shopName" :price="data[index-1].shopPrice"></shop-model>
     </div> -->
     <!-- <div class="shoplist">
        <shop-model  :imgurl="imgurl"   shopname='dd' price="25"></shop-model>
      </div> -->
    </div>
  </div>
</template>

<script>
  import ShopModel from './shop.vue'


  export default{
    components:{
      ShopModel
    },
    data(){
      return{
        data:[],
      }
    },
    methods:{
      getShopType(){
        let type = this.$route.params.type

        this.axios({
          url:"http://localhost:8080/shop/shopbytype",
          method:'post',
          params:{
            type:type
          }
        }).then(resp=>{
          if(resp.data.status==200){
           
            if(resp.data.data.length!=0){
              let list=resp.data.data
              for(let i = 0;i<list.length;i++){
                list[i].shopUrl="data:image/png;image/jpg;image/jpeg;base64,"+list[i].shopUrl
              }
              this.data=list
            }else{
              this.$message({
                type:"warning",
                message:'抱歉！该类型的产品库存不足了'
              })
            }

          }else{
            this.$message.error("哎呀！报错了")
          }
        }).catch(resp=>{
          this.$message.error("哎呀！报错了")
        })
      }
    },
    mounted:function(){
      this.getShopType()
    }
  }
</script>

<style>

  .myshop{
    width: 100%;

  }
  .myshop-content{
    width: 60%;
    margin: 0 auto;
  }

  .shoplist{
    float: left;
    width: 240px;
    margin: 16px 22px;
  }
</style>
