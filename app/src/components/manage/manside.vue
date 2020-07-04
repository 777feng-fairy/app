<template>
  <div class="side">
    <div class="side-content">
      <el-menu
            default-active="/manage"
            background-color="#FFFFFF"
            :router="true"
            text-color="#303133"

            class="vertical-menu"
            active-text-color="#409EFF">
            <el-menu-item index="/manage">
              <i class="el-icon-house"></i>
              <span slot="title">首页</span>
            </el-menu-item>
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-s-shop"></i>
                <span>商品管理</span>
              </template>
              <el-menu-item-group>
                <template slot="title">商品详情管理</template>
                <el-menu-item index="/manage/shoptable">商品列表</el-menu-item>
                <el-menu-item index="/manage/shopshelves">商品管理</el-menu-item>
                <el-menu-item index="/manage/shoppository">入库管理</el-menu-item>
              </el-menu-item-group>
             </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span >订单管理</span>
              </template>
              <el-menu-item-group>
                <template slot="title">订单详情</template>
                <el-menu-item index="/manage/ordermage">查看全部订单</el-menu-item>
                <el-menu-item index="/manage/deliverygoods">订单操作</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="无效订单管理">
                <el-menu-item index="/manage/manremoveorder">无效订单</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-menu-item index="/manage/manusers">
              <i class="el-icon-picture-outline-round"></i>
              <span slot="title">用户管理</span>
            </el-menu-item>
            <el-menu-item index="/">
              <i class="el-icon-s-promotion"></i>
              <span slot="title">前往商城</span>
            </el-menu-item>
          </el-menu>
    </div>
  </div>
</template>

<script>

  export default{
    data(){
      return {

      }
    }
  }
</script>

<style scoped="scoped">
  .side{
    width: 100%;
    height:947px;
    position: relative;
  }
  .side .side-content{
      height: 100%;
      background-color: #545c64;
  }
  .vertical-menu{
    height: 100%;
  }
</style>
