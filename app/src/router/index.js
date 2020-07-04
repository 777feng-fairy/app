import Vue from 'vue'
import Router from 'vue-router'


import Manage from '../pages/manage.vue'
import ManageLogin from '../pages/login.vue'
import ManageRegister from '../pages/register.vue'
import ManageGetId from '../pages/getid.vue'
import ShopTable from '../components/manage/managecontent/shoptable.vue'
import ShopShelves from '../components/manage/managecontent/shopshelves.vue'
import ManageIndex from '../components/manage/managecontent/index.vue'
import ShopPository from '../components/manage/managecontent/shoppository.vue'
import ManIndex from '../pages/manindex.vue'
import ManContent from '../components/content.vue'
import MyShop from '../components/myshop.vue'
import MyShopType from '../components/shoptype.vue'
import MyShopProfile from '../components/shopprofile.vue'
import MyShopCart from '../components/shopcart.vue'
import MyUserOrder from '../components/userorder.vue'
import MyUserSetting from '../components/usersetting.vue'
import RefreshCur from '../pages/refresh.vue'
import ManOrderOne from '../components/manage/managecontent/manorder.vue'
import ManDeliveryGoods from '../components/manage/managecontent/deliverygoods.vue'
import ManRemoveOrder from '../components/manage/managecontent/manremoveorder.vue'
import ManUsers from '../components/manage/managecontent/manusers.vue'
Vue.use(Router)

const originalReplace = Router.prototype.replace;
Router.prototype.replace = function replace(location) {
  return originalReplace.call(this, location).catch(err => err);
}

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

export default new Router({
  routes: [
   {
     path:'/manage',
     component:Manage,
     children:[
       {
         path:'shoptable',
         component:ShopTable
       },
       {
         path:'shopshelves',
         component:ShopShelves
       },
       {
         path:'/',
         component:ManageIndex
        },
        {
          path:'shoppository',
          component:ShopPository
        },
        {
          path:'ordermage',
          component:ManOrderOne
        },
        {
          path:'deliverygoods',
          component:ManDeliveryGoods
        },
        {
          path:'manremoveorder',
          component:ManRemoveOrder
        },
        {
          path:'manusers',
          component:ManUsers
        }
     ]
   },
   {
     path:'/manage/login',
     component:ManageLogin
   },
   {
     path:'/manage/register',
     component:ManageRegister
   },
   {
     path:'/manage/getid',
     component:ManageGetId
   },
   {
     path:'/',
     component:ManIndex,
     children:[
       {
         path:'/',
         component:ManContent
       },
       {
         path:'shop/myshop',
         component:MyShop
       },{
         path:'shop/shoptype/:type',
         component:MyShopType
       },
       {
         path:'shop/shopprofile/:id',
         component:MyShopProfile
       },
       {
         path:'shop/shopcart',
         component:MyShopCart
       },
       {
         path:'userorder/ordermanage',
         component:MyUserOrder
       },
       {
         path:'user/mysetting',
         component:MyUserSetting
       }
     ]
   },
   {
     path:'/refresh',
     component:RefreshCur
   }

  ]

})
