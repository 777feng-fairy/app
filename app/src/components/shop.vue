<template>
  <div class="shop-model">
    <div class="shop-model-card">
      <div class="shop-model-img">
        <div style="height: 100%;width: 100%;box-sizing: border-box;padding:20px 39px;">
           <el-image
              style="width: 160px; height: 160px"
              :src="imgurl"
              :lazy="true"
              >

            </el-image>
        </div>
      </div>
      <div class="shop-model-name">
        <h3 class="shop-modele-name-title">{{shopname}}</h3>
      </div>
      <div class="shop-model-price">
        <span>价格:{{price}}元</span>
      </div>
      <div align="right" style="margin-right: 12px;margin-top: 12px;">
        <el-button type="primary" @click='lookProfile()'>查看宝贝</el-button>
        <span style="display: none;">{{shopid}}</span>
      </div>
    </div>
  </div>
</template>

<script>
  export default{
     props:['imgurl','shopname','price','shopid'],
     methods:{
       lookProfile(){
         let id=this.shopid
         this.$router.push({
           path:'/shop/shopprofile/'+id,
          
         })
       }
     },
     mounted:function(){

     }

  }
</script>

<style>
  .shop-model{
    height:320px ;
    width: 240px;
    cursor: pointer;

  }
  .shop-model-img{
    height: 200px;
    width: 100%;
  }
  .shop-model-card{
    height: 100%;
    width: 100%;
    border: 1px solid #ebeef5;
    box-sizing: border-box;
    border-radius: 4px;
  }

  .shop-model-card:hover{
   box-shadow: 0 2px 12px rgba(0,0,0,.1);
  }

  .shop-model-name{
    height: 30px;
    margin: 7px 0;
  }

  .shop-modele-name-title{
    text-align: center;
    color: #333333;
  }

  .shop-model-price{
    color: #FF6700;
    text-align: center;
    margin: 7px 0;
  }


</style>
