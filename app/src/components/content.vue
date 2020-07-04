<template>
  <div class="content clearfix">
    <div class="content-main clearfix">
      <div class="content-side clearfix">
        <ul class="clearfix">
          <!-- //电脑，手机，平板，充电器，数据线，上衣，裤子，鞋子，食品，首饰，电视机，耳机，路由器，插排线 -->
          <li><a href="#/shop/shoptype/0">电脑</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/1">手机</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/2">平板</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/3">充电器</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/4">数据线</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/5">上衣</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/6">裤子</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/7">鞋子</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/8">食品</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/9">首饰</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/10">电视机</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/11">耳机</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/12">路由器</a><i class="el-icon-arrow-right"></i></li>
          <li><a href="#/shop/shoptype/13">插排线</a><i class="el-icon-arrow-right"></i></li>
        </ul>
      </div>
      <div class="content-carousel">
        <el-carousel height="518px">
           <el-carousel-item v-for="item in 4" :key="item">
                <img :src="require('../assets/img/'+(item)+'.jpg')" alt="" width="100%" height="100%" >
           </el-carousel-item>
        </el-carousel>
      </div>

    </div>
    <div class="content-adv clearfix">
      <ul  class="clearfix">
        <li v-for="index in 4" :key="index"><img :src="require('../assets/img/view'+(index)+'.jpg')" alt="" width="100%" height="100%"></li>
      </ul>
    </div>
    <div style="width: 60%;margin: 0 auto;">
      <img src="../assets/img/b1.jpg" alt="" width="100%" height="100%">
    </div>
    <div class="content-footer">
      <div class="copyright">
        <p>我们的雪蝶商城|鬼聊|多看书城|雪蝶路由器|视频电话|雪蝶天猫店|雪蝶淘宝直营店|雪蝶网盟|雪蝶移动|隐私政策|Select Region</p>
        <p>©mi.com 京ICP证XXXXXX号 京ICP备88888888号 京公网安备1111111111111号 京网文[2019]0000-0009号</p>
      </div>
    </div>


  </div>
</template>

<script>

  export default{
    data:function(){
      return{
        //imgarr:['../assets/img/1.jpg','../assets/img/2.jpg','../assets/img/3.jpg']
      }
    }
  }

</script>

<style>
  .content{

    width: 100%;
    position: relative;
  }
  .content .content-main{
    width: 60%;
    margin: 0 auto;
    height: 518px;
    position: relative;

  }

  .content .content-main .content-side{
    width: 200px;
    height: 518px;
    position: relative;
    float: left;
    background: #000000;
    opacity: 0.7;
  }
  .content .content-main .content-carousel{
    float: left;
    height: 518px;
    width: calc(100% - 200px);
  }

  .content .content-main .content-side ul li{
    height:37px;
    position: relative;
    list-style: none;
  }

  .content .content-main .content-side ul li:hover{
    background: rgb(255,103,0);
  }

  .content .content-main .content-side ul li a,.content .content-main .content-side ul li i{
    line-height: 37px;
    height: 37px;
  }

  .content .content-main .content-side ul li i{
    text-align: right;
    position:relative;
    left: 180px;
    bottom: 36px;
    color: #FFFFFF;
    cursor: pointer;

  }

  .content .content-main .content-side ul li a{
    text-decoration: none;
    color: #FFFFFF;
    padding-left: 30px;
    display: block;
  }

  .content .content-footer{
    width: 100%;
    height: 140px;
    bottom: 0px;
    background: #333;
  }
  .content .content-adv{
    height: 170px;
    width: 60%;
    margin: 20px auto;
  }

  .content .content-adv ul{
    height: 100%;
  }

  .content .content-adv ul li{
    float: left;
    height: 100%;
    width: 24%;
    margin: 0 5px;
    transition:transform .3s;

  }

  .content .content-adv ul li:hover{
    transform: rotateY(180deg);
  }


  .copyright{
    color: #FFFFFF;
    text-align: center;
    position: relative;
  }

  .copyright p{
    position: relative;
    top:50px;
    margin:7px 0;
  }


</style>
