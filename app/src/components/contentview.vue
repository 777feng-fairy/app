<template>
  <div class="contentview">
    <div class="content-qt">
      <ul>
        <li><a href="#/user/mysetting"><i class="el-icon-user"></i>个人中心</a></li>
        <li><a href="#/shop/shopcart"><i class="el-icon-s-shop"></i>购物车</a></li>
         <li><a href="#/"><i class="el-icon-back"></i>回到首页</a></li>
      </ul>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
</script>

<style>
  .contentview{
    width: 100%;
  }
  .content-qt{
    width: 82px;
    position: fixed;
    height: 180px;
    right: 0;
    top:300px;
  }

  .content-qt ul{
   width: 100%;
   height: 100%;
  }
  .content-qt ul li{
   width: 100%;
   height:50% ;
   box-shadow: 0 0 1px #99A9BF;
   text-align: center;
   position: relative;
   color: #757575;
   cursor: pointer;
  }

  .content-qt ul li:hover,.content-qt ul a:hover{
        color: #ff6700;
   }


  .content-qt ul li i {
   width: 100%;
   font-size: 40px;
   margin: 0 auto;
   display: block;
   box-sizing: border-box;
   padding: 12px;
  }
  .content-qt ul a {
    font-size: 16px;
    text-decoration: none;
    color: #757575;
  }

</style>
