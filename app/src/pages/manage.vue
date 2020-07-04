<template>
  <div class="manage clearfix">

    <div class="manage-side">
      <man-side></man-side>
    </div>
    <div class="manage-content-is">
      <div class="manage-header">
         <man-header :username="username" :islogin="islogin"></man-header>
      </div>
      <div class="manage-content">
        <man-content>

        </man-content>
      </div>
    </div>


  </div>
</template>

<script>
  import manHeader from '../components/manage/manheader.vue'
  import manSide from '../components/manage/manside.vue'
  import manContent from '../components/manage/mancontent.vue'
  export default{
    components:{
      manHeader,
      manSide,
      manContent
    },
    data(){
      return{
        username:'',
        islogin:true
      }
    },
    mounted() {
      this.getUserName()
    }
    ,
    methods:{
      getUserName(){

        if(sessionStorage.getItem("userinfo")!=null){
          let user = JSON.parse(sessionStorage.getItem("userinfo"))
          this.username=user.userName
          this.islogin=false

        }else{
          this.username=''
          this.islogin=true
        }
      }
    },
    beforeCreate:function(){

      if(sessionStorage.getItem("userinfo")){
        let user = JSON.parse(sessionStorage.getItem("userinfo"))
        if(user.type!=1){
            this.$message({
            type:'warning',
            message:'权限不够'
          })
          this.$router.go(-1)
        }
      }else{
        this.$message({
          type:'warning',
          message:'请登录'
        })
        this.$router.go(-1)
      }
    }


  }

</script>

<style scoped="scoped">
  .manage{
    height: 100%;
    width: 100%;
    position: relative;
  }

  .manage .manage-side{
    width: 15%;
    float: left;
  }
  .manage .manage-header{
    width: 100%;
    float: left;

  }
  .manage .manage-content{
    width: 100%;
    float: left;
    height:calc(100% - 120px);

  }

  .manage .manage-content-is{
    height:100%;
    float: left;
    width: 85%;
  }
</style>
