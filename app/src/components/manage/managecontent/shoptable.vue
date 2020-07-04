<template>
  <div class="shop-table">
    <div class="shop-table-content clearfix">
      <div class="clearfix">
        <div style="float: left;width: auto;margin-right: 20px;">
          <el-button @click="getShopTable()">查看全部</el-button>
        </div>
        <div style="float: left;" class="clearfix">
          <div style="float: left;width: auto;margin-right: 20px;">
            <el-input placeholder="根据id搜索" prefix-icon="el-icon-search" v-model="serchid"></el-input>
          </div>
          <div style="float: left;width: auto;margin-right: 20px;">
            <el-button @click='search()' type="primary">搜索</el-button>
          </div>
        </div>
        <div style="float: left;" class="clearfix">
          <div style="float: left;width: auto;margin-right: 20px;">
            <el-input placeholder="根据名称搜索" prefix-icon="el-icon-search" v-model="serchname"></el-input>
          </div>
          <div style="float: left;width: auto;margin-right: 20px;">
            <el-button @click='getShopByName()' type="primary">搜索</el-button>
          </div>
        </div>
      </div>

      <el-divider></el-divider>
      <el-table :data="tableData" style="width: 100%;" height="670" border>
        <el-table-column type="expand" fixed>
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="商品ID">
                <span>{{ props.row.shopId }}</span>
              </el-form-item>
              <el-form-item label="商品名称">
                <span>{{ props.row.shopName }}</span>
              </el-form-item>
              <el-form-item label="商品数量">
                <span>{{ props.row.shopNum }}</span>
              </el-form-item>
              <el-form-item label="店铺单价">
                <span>{{ props.row.shopPrice }}</span>
              </el-form-item>
              <el-form-item label="商品详情">
                <span>{{ props.row.shopInfo }}</span>
              </el-form-item>
              <el-form-item label="进货时间">
                <span>{{ props.row.stime }}</span>
              </el-form-item>
              <el-form-item label="商品类型">
                <span>{{ props.row.type }}</span>
              </el-form-item>
              <el-form-item label="商品状态">
                <span>{{ props.row.shopStatus }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column fixed prop="shopId" label="商品ID" width="200" sortable>
        </el-table-column>
        <el-table-column fixed prop="shopName" label="商品名称" width="120" sortable>
        </el-table-column>
        <el-table-column prop="shopNum" label="数量" width="80">
        </el-table-column>
        <el-table-column prop="shopPrice" label="单价" width="80">
        </el-table-column>
        <el-table-column prop="shopInfo" label="详情">
        </el-table-column>
        <el-table-column prop="stime" label="进货时间" width="200" sortable>
        </el-table-column>
        <el-table-column prop="type" label="类型" width="80">
        </el-table-column>
        <el-table-column prop="shopStatus" label="商品状态" width="80"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [
          // {shopId:'',shopName:'',shopNum:0,shopUrl:'',shopPrice:0,shopInfo:'',stime:'',type:'',shopStatus:0}
        ],
        serchid: '',
        serchname: ''
      }
    },
    mounted: function() {

      this.getShopTable()

    },
    methods: {
      islogin() {
        if (sessionStorage.getItem("userinfo")) {
          return true
        } else {
          return false
        }
      },
      getShopTable() {
        if (this.islogin()) {
          this.axios({
            url: 'http://localhost:8080/shop/shopdata',
            method: 'post',
            responseType: 'json'
          }).then(resp => {

            if (resp.data.status == 200) {
              this.tableData = resp.data.data
            }

          })
        } else {
          this.$message({
            type: 'warning',
            message: '请登录再搜索'
          })
        }

      },
      search() {
        if (this.islogin()) {
          let id = this.serchid
          if (id) {
            this.axios({
              url: 'http://localhost:8080/shop/mansearchid',
              method: 'post',
              params: {
                shopid: id
              }
            }).then(resp => {
              if (resp.data.status == 200) {
                this.tableData = resp.data.data
                this.serchid = ''
              }
            }).catch(resp => {
              this.$message.error("哎呀！报错了0.0")
            })
          }

        } else {
          this.$message({
            type: 'warning',
            message: '请登录再搜索'
          })
        }
      },
      getShopByName() {
        if (this.islogin()) {
          let search = this.serchname
          if (search) {
            this.axios({
              url: 'http://localhost:8080/shop/shopsearch',
              method: 'post',
              responseType: 'json',
              data: {
                search: search
              }
            }).then(resp => {
              if (resp.data.status = 200) {
                if (resp.data.data != null) {
                  this.tableData = resp.data.data
                  this.serchname = ''
                } else {
                  this.$message({
                    message: '搜索结果不存在',
                    type: 'warning'
                  })
                }
              }
            })
          }

        } else {
          this.$message({
            message: "请前往登录",
            type: 'warning'
          })
        }

      },
    }
  }
</script>

<style>
  .shop-table {
    width: 100%;
    height: 100%;
    background-color: #FFFFFF;
    position: relative;
  }

  .shop-table-content {
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    padding: 20px;
    position: relative;
  }

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
