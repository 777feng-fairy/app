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
        /* imgurl:require('../assets/img/liebiao_xiaomi5s.jpg'), */
        data:[
         // {shopName:'华为p30',shopPrice:2420,shopUrl:"require('../assets/img/liebiao_xiaomi5s.jpg')",shopId:'5873366'}
        ],
      }
    },
    computed:{
     /* imgurl:function(){
        return require('../assets/img/liebiao_xiaomi5s.jpg')
      } */
    },
    mounted:function(){
      this.getShopList()
    },
    methods:{
      getShopList(){
        this.axios({
          url:'http://localhost:8080/shop/shoplist',
          method:'post'
        }).then(resp=>{
          let list=resp.data.data
          for(let i = 0;i<list.length;i++){
            list[i].shopUrl="data:image/png;image/jpg;image/jpeg;base64,"+list[i].shopUrl
          }
          this.data=list

        }).catch(resp=>{
          this.$message.error("哎呀！报错了")
        })
      }
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
