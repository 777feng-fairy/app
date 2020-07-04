<template>
  <div class="pository clearfix">
    <div style="height: 100%;width: 50%;margin: 0 auto;">
       <el-card shadow="hover" style="height: 100%;">
            <el-form :model="form" ref="form" validate-on-rule-change status-icon label-position="right" :rules="rules">
              <el-form-item prop="shopName" label="商品名称" label-width="100px">
                <el-input v-model="form.shopName"  maxlength="10"  show-word-limit ></el-input>
              </el-form-item>
              <el-form-item prop="shopNum" label="商品数量" label-width="100px">
                <el-input v-model="form.shopNum"></el-input>
              </el-form-item>
              <el-form-item prop="shopPrice" label="商品单价" label-width="100px">
                <el-input v-model="form.shopPrice"></el-input>
              </el-form-item>
              <el-form-item prop="shopInfo" label="商品详情" label-width="100px">
                <el-input v-model="form.shopInfo" type="textarea" :autosize="true"  maxlength="120"  show-word-limit ></el-input>
              </el-form-item>
              <el-form-item prop="shopUrl" label="商品图片" label-width="100px">
                <el-upload
                  class="avatar-uploader-shop"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  :auto-upload="false"
                  :show-file-list="false"
                  accept="image/png,imgage/jpg,image/jpeg"
                  :on-change="changeFile"
                  :before-upload="beforeAvatarUpload">
                  <img v-if="form.shopUrl" :src="form.shopUrl" class="shop-avatar">
                  <i v-else class="el-icon-plus shop-icon"></i>
                </el-upload>
              </el-form-item>
              <el-form-item prop="type" label="商品类型" label-width="100px">
                <el-select v-model="form.type"  placeholder="请选择">
                   <el-option
                     v-for="item in options"
                     :key="item.value"
                     :label="item.label"
                     :value="item.value">
                   </el-option>
                 </el-select>
              </el-form-item>
              <el-form-item prop="shopStatus" label="商品状态" label-width="100px">
                <el-select v-model="form.shopStatus"  placeholder="请选择">
                   <el-option
                     v-for="item in status"
                     :key="item.value"
                     :label="item.label"
                     :value="item.value">
                   </el-option>
                 </el-select>
              </el-form-item>
              <el-form-item label-width="100px">
                <el-button type="primary" @click="submitForm()">提交</el-button>
              </el-form-item>
            </el-form>
       </el-card>
    </div>
    <div>

    </div>
  </div>
</template>

<script>
  export default{
    data(){
      let isNumber= (rule, value, callback) => {
          if(isNaN(value)){
            return callback(new Error('请输入数字'))
          }else{
            callback()
          }
       }
       let isNull=(rule, value, callback) => {
          if(value==''|| value==null){
            return callback(new Error('输入不能为空'))
          }else{
            callback()
          }
       }
      return{
        // {shopId:'',shopName:'dad',shopNum:0,shopUrl:'',shopPrice:0,shopInfo:'',stime:'',type:'',shopStatus:0}
        form:{
          shopName:'',
          shopId:'',
          shopNum:'',
          shopUrl:'',
          shopPrice:'',
          shopInfo:'',
          stime:'',
          type:'',
          shopStatus:''
      },
      rules:{
        shopName:[
          {max:10,trigger:'blur',message:'最大输入10个'},
          {validator:isNull,trigger:'blur'}
        ],
        shopNum:[
          {validator:isNumber,trigger:'blur'},
          {validator:isNull,trigger:'blur'}
        ],
        shopUrl:[
          {required: true,message:'请选择一个图片',trigger:'change'}
        ],
        shopPrice:[
          {validator:isNumber,trigger:'blur'},
          {validator:isNull,trigger:'blur'}
        ],
        shopInfo:[
          {validator:isNull,trigger:'blur'}
        ],
        type:[
          {required: true,message:'请选择一个',trigger:'change'}
        ],
        shopStatus:[
           {required: true,message:'请选择一个',trigger:'change'}
        ]
      },
      options:[
        //电脑，手机，平板，充电器，数据线，上衣，裤子，鞋子，食品，首饰，电视机，耳机，路由器，插排线
        {
          value:'0',
          label:'电脑'
        },{
          value:'1',
          label:'手机'
        },
        {
          value:'2',
          label:'平板'
        },
        {
          value:'3',
          label:'充电器'
        },
        {
          value:'4',
          label:'数据线'
        },
        {
          value:'5',
          label:'上衣'
        },
        {
          value:'6',
          label:'裤子'
        },
        {
          value:'7',
          label:'鞋子'
        },
        {
          value:'8',
          label:'食品'
        },
        {
          value:'9',
          label:'首饰'
        },
        {
          value:'10',
          label:'电视机'
        },
        {
          value:'11',
          label:'耳机'
        },
        {
          value:'12',
          label:'路由器'
        },{
          value:'13',
          label:'插板线'
        }
      ],
      status:[
        {
          value:'1',
          label:'上架'
        },
        {
          value:'0',
          label:'下架'
          }
      ],
      file:''

    }
    },
     methods: {
          changeFile(file,fileList){
            this.file=file
            this.form.shopUrl = URL.createObjectURL(file.raw)
          },
          beforeAvatarUpload(file) {
            const isLt1M = file.size / 1024 / 1024 < 1;
            if (!isLt2M) {
              this.$message.error('上传头像图片大小不能超过 1MB!');
            }
            return isLt2M;
          },

          isLogin(){
            if(sessionStorage.getItem("userinfo")==null){
              return false
            }else{
              return true
            }
          },
          submitForm(){
            let formData=new FormData()
            let form = this.$refs["form"]
            if(this.isLogin()){
              formData.append('shopName',form.model.shopName)
              formData.append('shopNum',form.model.shopNum)
              formData.append('shopPrice',form.model.shopPrice),
              formData.append('shopInfo',form.model.shopInfo)
              formData.append('type',form.model.type)
              formData.append('shopStatus',form.model.shopStatus)
              formData.append('shopUrl',this.file.raw)
              form.validate(pass=>{
                if(pass){
                  this.axios({
                    url:'http://localhost:8080/shop/addshop',
                    method:'post',
                    data:formData,
                    headers:{
                      'Content-Type':'multipart/form-data'
                    }
                  }).then(resp=>{
                    if(resp.data.status=200){
                      this.$message({
                        type:'success',
                        message:resp.data.message
                      })
                      form.resetFields()
                    }else{
                      this.$message.error("哎呀！报错了")
                      form.resetFields()
                    }

                  }).catch(resp=>{
                    this.$message.error("哎呀！报错了")
                    form.resetFields()
                  })
                }
              })
            }else{
              this.$message({
                type:'warning',
                message:'请先登录！'
              })
               form.resetFields()
            }

          }
        }
 }
</script>

<style >
  .pository{
    height: 100%;
    width: 100%;
    background-color: #FFFFFF;
  }

   .avatar-uploader-shop .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;

    }
    .avatar-uploader-shop .el-upload:hover {
      border-color: #409EFF;
    }
    .shop-icon {
      width: 200px;
      height: 200px;
      font-size: 36px;
      color: #99A9BF;
      position: relative;
      top:82px;
    }
    .shop-avatar {
      width: 178px;
      height: 178px;
      display: block;
    }

</style>
