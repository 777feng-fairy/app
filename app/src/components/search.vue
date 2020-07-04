<template>
  <div class="search">
    <div class="search-content clearfix">
      <div class="search-content-main clearfix">
        <div class="fr" style="width: 100px;height: 97px;">
          <div style="height: 97px;line-height: 97px;font-size: 24px;">
            <el-button @click="back()"><i class="el-icon-back" style="font-size: 20px;">返回</i></el-button>
          </div>
        </div>
        <div class="fl">
          <div class="logo">
                     <img src="../../static/img/logo.png" height="97px" width="97px" alt="雪蝶商城">
                 </div>

        </div>
        <div class="fl">
          <div class="search-title">
            <ul>
              <li><a href="#/shop/myshop" :class="{pathcolor:this.$route.path=='/shop/myshop'}">我的商城</a></li>
              <li><a href="#/shop/shopcart" :class="{pathcolor:this.$route.path=='/shop/shopcart'}">购物车</a></li>
              <li><a href="#/userorder/ordermanage" :class="{pathcolor:this.$route.path=='/userorder/ordermanage'}">订单中心</a></li>
              <li><a href="#/user/mysetting" :class="{pathcolor:this.$route.path=='/user/mysetting'}">个人中心</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

  export default{
    data(){
      return{
        isCur:false
      }
    },
    methods:{
      back(){
        this.$router.go(-1)
      }
    }
  }
</script>

<style scoped="scoped">
  .search{
    height: 121px;
    background: #ffffff;
    margin:0 0 10px 0;
  }

  .search .search-content{
    padding-top: 18px;
    height: 97px;
  }

  .search .search-content .search-content-main{
    position: relative;
    width: 60%;
    text-align: center;
    vertical-align: middle;

    margin: 0 auto;
  }

  .logo{
    height: 97px;
    width: 200px;
    float: left;
  }
  .logo img{
    margin: 0 auto;
    vertical-align:middle ;
  }

  .search .search-content .search-content-main .search-button{
    width: 80%;
    float: left;
    height: 97px;
    text-align: center;
    vertical-align: middle;
    position: relative;
  }

  .search .search-content .search-content-main .search-button{
    margin: auto 0;
    position: relative;
  }

   .search .search-content .search-content-main .search-button div{
     position: absolute;
     width: 100%;
     top: 29px;
     text-align: center;
     vertical-align: middle;
   }


  .search .search-content .search-content-main .search-button input{
      border:solid 1px #b7d971;
      width: 472px;
      height: 36px;
      display: block;
      vertical-align: middle;
      margin: auto auto;
      line-height: 38px;
  }

  .search .search-content .search-content-main .search-button>div>i{
    color: #b7d971;
    position: relative;
    bottom: 25px;
    left: 220px;
  }

  .search .search-content .search-title{
    float: left;
    height: 97px;
    line-height: 97px;

  }
  .search .search-content .search-title ul {
    float: left;
  }
  .search .search-content .search-title ul li{
    float: left;
    box-sizing: border-box;
    padding: 7px;
    margin: auto 12px;

    text-align: center;
  }
  .search .search-content .search-title ul li a{
    outline: none;
    text-decoration: none;
    font-size: 20px;
    color: #000000;
    transition: color .3s;
  }

  .search .search-content .search-title ul li a:hover{
    color: #FF6700;
  }

  .fl{
    float: left;
  }

  .fr{
    float: right;
  }

  .pathcolor{
    border-bottom: #409EFF 2px solid;
    padding: 0 0 7px;
  }
</style>
