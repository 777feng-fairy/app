<template>
  <div class="index clearfix">


    <div style="position: absolute;z-index: 10;font-size: 32px;right: 500px;top:330px;color: #333333;">
      买的东西多了，你自有了人设 | 好物
    </div>

   <!-- <div class="index-date">
      <div style="height: 100%;box-sizing: border-box;padding: 12px;">
        <h1 align="center" style="color: black;margin: 7px 0;"><strong>蜀道难</strong></h1>
        <h2 align="right" style="color: #A69999;margin: 7px 0;">【作者】李白 【朝代】唐</h2>
        <p style="text-indent: 40px;letter-spacing: 4px;margin: 12px 0px;">
          噫吁嚱，危乎高哉！
        蜀道之难，难于上青天！
        蚕丛及鱼凫，开国何茫然！
        尔来四万八千岁，不与秦塞通人烟。
        西当太白有鸟道，可以横绝峨眉巅。
        地崩山摧壮士死，然后天梯石栈相钩连。
        上有六龙回日之高标，下有冲波逆折之回川。
        黄鹤之飞尚不得过，猿猱欲度愁攀援。
        青泥何盘盘，百步九折萦岩峦。
        扪参历井仰胁息，以手抚膺坐长叹。</p>
        <p style="text-indent: 40px;letter-spacing: 4px;margin: 12px 0px;">
        问君西游何时还？畏途巉岩不可攀。
        但见悲鸟号古木，雄飞雌从绕林间。
        又闻子规啼夜月，愁空山。
        蜀道之难，难于上青天，使人听此凋朱颜！
        连峰去天不盈尺，枯松倒挂倚绝壁。
        飞湍瀑流争喧豗，砯崖转石万壑雷。
        其险也如此，嗟尔远道之人胡为乎来哉！</p>
        <p style="text-indent: 40px;letter-spacing: 4px;margin: 12px 0px;">
        剑阁峥嵘而崔嵬，一夫当关，万夫莫开。
        所守或匪亲，化为狼与豺。
        朝避猛虎，夕避长蛇；磨牙吮血，杀人如麻。
        锦城虽云乐，不如早还家。
        蜀道之难，难于上青天，侧身西望长咨嗟！
        </p>
        <h1 align="center" style="color: black;margin: 40px 0 7px;"><strong>鹊桥仙</strong></h1>
        <h2 align="right" style="color: #A69999;margin: 12px 0;">【作者】秦观 【朝代】宋</h2>
         <p style="text-indent: 40px;letter-spacing: 2px;margin: 7px 0px;">
          纤云弄巧，飞星传恨，银汉迢迢暗度。
          </p>
          <p style="text-indent: 40px;letter-spacing: 2px;margin: 7px 0px;">
          金风玉露一相逢，便胜却人间无数。
          </p>
          <p style="text-indent: 40px;letter-spacing: 2px;margin: 7px 0px;">
           柔情似水，佳期如梦，忍顾鹊桥归路。
          </p>
          <p style="text-indent: 40px;letter-spacing: 2px;margin: 7px 0px;">
           两情若是久长时，又岂在朝朝暮暮。
          </p>
          <h1 align="center" style="color: black;margin: 40px 0 7px;"><strong>虞美人·春花秋月何时了</strong></h1>
          <h2 align="right" style="color: #A69999;margin: 12px 0;">【作者】李煜 【朝代】五代</h2>
           <p style="text-indent: 40px;letter-spacing: 4px;margin: 7px 0px;">
           春花秋月何时了，往事知多少？小楼昨夜又东风，故国不堪回首月明中！
           </p>
           <p style="text-indent: 40px;letter-spacing: 4px;margin: 7px 0px;">
           雕栏玉砌应犹在，只是朱颜改。问君能有几多愁？恰似一江春水向东流。
           </p>
      </div>
    </div>

    <div class="index-adv">
      <div style="width: 80%;margin: 0 auto;box-sizing: border-box;padding: 7px;">
         <el-carousel :interval="4000" type="card" height="400px">
            <el-carousel-item v-for="item in 3" :key="item">
              <img :src="require('../../../assets/img/'+(item)+'.jpg')" alt="" width="100%" height="100%">
            </el-carousel-item>
          </el-carousel>
      </div>
      <div style="height:370px;" class="clearfix">
         <el-divider ></el-divider>
        <div class="clearfix">
          <div class="my">
            <i class="el-icon-picture-outline"></i>
            <div style="font-size: 16px;">
              <h1 style="font-size: 27px;text-align: center;margin: 7px 0;">海量用户</h1>
              <p style="font-size: 16px;text-align: center;margin: 15px 0;">大量需求</p>
            </div>
          </div >
          <div class="my">
            <i class="el-icon-orange"></i>
            <div style="font-size: 16px;">
              <h1 style="font-size: 27px;text-align: center;margin: 7px 0;">全球资源</h1>
              <p style="font-size: 16px;text-align: center;margin: 15px 0;">拥有全球供应商提供高品质，多品类，货源丰富，无忧进货</p>
            </div>
          </div>
          <div style="float: left;width: 500px;height: 264px;border-left: solid 1px #DCDFE6;position: relative;">
              <div style="height: 100%;box-sizing: border-box;padding: 12px;margin-top:40px ;" class="clearfix">
                <div style="float: left;">
                  <img src="../../../assets/img/logo.png" alt="" height="200px" width="200px">
                </div>
                <div style="float: left;">
                  <p style="margin: 20px;">雪蝶，打造全新商业模式</p>
                  <p style="margin: 20px;">不一样的体验</p>
                  <p style="margin: 20px;font-size: 27px;">联系我们</p>
                  <p style="margin: 20px;">咨询热线:  <el-link type="primary" href="tel:13264673593">点我咨询</el-link></p>
                  <p style="margin: 7px 20px;">咨询热线（9：00-18：00）</p>
                </div>

              </div>
          </div>
        </div>

        <div>

        </div>
      </div>
    </div>
 -->
  </div>
</template>

<script>
  export default{
    data(){
      return{

      }
    }
  }
</script>

<style>

  .index{
    height: 100%;
    width: 100%;
    background-color: #FFFFFF;
    position:relative;
   background-image: url(../../../assets/img/content.jpg);
    background-size: 100% 100%;
  }
  .index-date{
    width: 400px;
    height: 100%;
    float: left;
    border-right: 1px solid #DCDFE6;
    box-sizing: border-box;
  }
  .index-adv{
    float: left;
    width: calc(100% - 400px);
    height: 100%;
  }

  .my{
    font-size: 180px;
    float: left;
    margin-right:40px ;

    text-align: center;
  }
</style>
