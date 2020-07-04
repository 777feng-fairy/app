<template>
  <div class="manusers">
    <div class="manusers-content">

      <div class="manusers-content-search clearfix">
        <div class="fl clearfix" style="margin-right: 200px;">
          <div class="fl" style="width: auto;margin-right: 200px;">
            <el-button @click="getUserList()">查看全部数据</el-button>
          </div>
          <div style="width: 300px;margin-right: 50px;" class="fl">
            <el-input placeholder="请输入账号ID" prefix-icon="el-icon-search" v-model="searchid" ></el-input>
          </div>
          <div class="fl" style="width: auto;">
            <el-button type="primary" @click='queryUserById()' >搜索</el-button>
          </div>
        </div>
        <div style="width: 300px;margin-right: 50px;" class="fl">
            <el-input placeholder="请输入昵称" prefix-icon="el-icon-search" v-model="searchname"></el-input>
          </div>
          <div class="fl" style="width: auto;">
            <el-button type="primary" @click='queryUserByName()' >搜索</el-button>
          </div>
        </div>

       <el-divider></el-divider>

       <div style="height: 670px;">
          <el-table :data="tableData" style="width: 100%" height="670">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="tabledata-algin">
                  <el-form-item label="用户 ID">
                    <span>{{ props.row.userId }}</span>
                  </el-form-item>
                  <el-form-item label="用户昵称">
                    <span>{{ props.row.userName }}</span>
                  </el-form-item>
                  <el-form-item label="用户年龄">
                    <span>{{ props.row.userAge }}</span>
                  </el-form-item>
                  <el-form-item label="用户电话">
                    <span>{{ props.row.userTel }}</span>
                  </el-form-item>
                  <el-form-item label="用户密码">
                    <span>{{ props.row.password }}</span>
                  </el-form-item>
                  <el-form-item label="用户地址">
                    <span>{{ props.row.address }}</span>
                  </el-form-item>
                  <el-form-item label="用户身份">
                    <span>{{ props.row.type }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column sortable label="用户 ID" prop="userId">
            </el-table-column>
            <el-table-column label="用户昵称" prop="userName">
            </el-table-column>
            <el-table-column label="用户年龄" prop="userAge">
            </el-table-column>
            <el-table-column label="用户电话" prop="userTel">
            </el-table-column>
            <el-table-column label="用户密码" prop="password">
            </el-table-column>
            <el-table-column label="用户地址" prop="address">
            </el-table-column>
            <el-table-column label="用户身份" prop="type">
            </el-table-column>
            <el-table-column label="操作" width="280px">
              <template slot-scope="scope">
                <el-button type="primary" @click="updateUpUserType(scope.row.userId,scope.$index)">升级为管理员</el-button>
                <el-button type="success" @click="updateDownUserType(scope.row.userId,scope.$index)">取消管理员</el-button>
              </template>
            </el-table-column>

          </el-table>
       </div>

    </div>
  </div>
</template>

<script>
  export default{
    mounted:function(){
      this.getUserList()
    },
    data(){
      return{
        searchid:'',
        searchname:'',
        tableData:[
         /* {userId:'11',userName:'aa',userTel:'aa',address:'aa',password:'aa',type:'aa',userAge:'aa'},
          {userId:'11',userName:'aa',userTel:'aa',address:'aa',password:'aa',type:'aa',userAge:'aa'},
          {userId:'11',userName:'aa',userTel:'aa',address:'aa',password:'aa',type:'aa',userAge:'aa'} */
        ]
      }
    },
    methods:{

      islogin(){
        if(sessionStorage.getItem('userinfo')){
          return true
        }else{
          return false
        }
      },

      getUserList(){
        if(this.islogin()){
            this.axios({
              url:'http://localhost:8080/user/manuserlist',
              method:'post',
            }).then(resp=>{
              if(resp.data.status==200){
                this.tableData = resp.data.data
              }
            }).catch(resp=>{
              this.$message.error("哎呀！报错了0.0！")
            })
        }else{
          this.$message({
            type:'warning',
            message:'请先登录，再搜索'
          })
        }
      },

      queryUserById(){
        if(this.islogin()){
          let id =this.searchid
          if(id){
            this.axios({
              url:'http://localhost:8080/user/manuseridlist',
              method:'post',
              params:{
                userid:id
              }
            }).then(resp=>{
              if(resp.data.status==200){
                this.tableData = resp.data.data
                this.searchid=''
              }
            }).catch(resp=>{
              this.$message.error("哎呀！报错了0.0！")
            })
          }
        }else{
          this.$message({
            type:'warning',
            message:'请先登录，再搜索'
          })
        }
      },

      queryUserByName(){
        if(this.islogin()){
          let searchname =this.searchname
          if(searchname){
            this.axios({
              url:'http://localhost:8080/user/manusernamelist',
              method:'post',
              params:{
                username:searchname
              }
            }).then(resp=>{
              if(resp.data.status==200){
                this.tableData = resp.data.data
                this.searchname=''
              }
            }).catch(resp=>{
              this.$message.error("哎呀！报错了0.0！")
            })
          }
        }else{
          this.$message({
            type:'warning',
            message:'请先登录，再搜索'
          })
        }
      },

      updateUpUserType(id,index){
        this.axios({
          url:'http://localhost:8080/user/updateuptype',
          method:'post',
          params:{
            userid:id
          }
        }).then(resp=>{
          if(resp.data.status==200){
            this.tableData[index].type='管理员'
            this.$message({
              type:'success',
              message:'升级成功'
            })
          }
        }).catch(resp=>{
          this.$message.error("哎呀！报错了")
        })
      },

      updateDownUserType(id,index){
        this.axios({
          url:'http://localhost:8080/user/updatedowntype',
          method:'post',
          params:{
            userid:id
          }
        }).then(resp=>{
          if(resp.data.status==200){
            this.tableData[index].type='一般会员'
            this.$message({
              type:'success',
              message:'取消成功'
            })
          }
        }).catch(resp=>{
          this.$message.error("哎呀！报错了")
        })
      },

    }
  }
</script>

<style>
  .manusers{
    width: 100%;
    height: 100%;
    background-color: #FFFFFF;
    position: relative;
  }

  .manusers-content{
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    padding: 20px;
    position: relative;
  }

  .fl{
    float: left;
  }

  .tabledata-algin {
    font-size: 0;
  }

  .tabledata-algin label {
    width: 300px;
  }

  .tabledata-algin .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 100%;
  }
</style>
