<template>
  <div>
    <tabbar @on-index-change="tabChange">
      <tabbar-item link="/home" v-bind:selected="true">
        <img slot="icon" src="../../../static/images/main_tab_home.png">
        <img slot="icon-active" src="../../../static/images/main_tab_home_f.png">
        <span slot="label">首页</span>
      </tabbar-item>
      <tabbar-item link="/classfy">
        <img slot="icon" src="../../../static/images/main_tab_classfy.png">
        <img slot="icon-active" src="../../../static/images/main_tab_classfy_f.png">
        <span slot="label">分类</span>
      </tabbar-item>
      <tabbar-item link="/shop">
        <img slot="icon" src="../../../static/images/main_tab_shop.png">
        <img slot="icon-active" src="../../../static/images/main_tab_shop_f.png">
        <span slot="label">品牌街</span>
      </tabbar-item>
      <tabbar-item link="/shopcart">
        <img slot="icon" src="../../../static/images/main_tab_shopcart.png">
        <img slot="icon-active" src="../../../static/images/main_tab_shopcart_f.png">
        <span slot="label">购物车</span>
      </tabbar-item>
      <tabbar-item link="/mine">
        <img slot="icon" src="../../../static/images/main_tab_mine.png">
        <img slot="icon-active" src="../../../static/images/main_tab_mine_f.png">
        <span slot="label">我的</span>
      </tabbar-item>
    </tabbar>
  </div>
</template>

<script type="text/javascript">
  import Vue from 'vue'
  import {Tabbar, TabbarItem} from 'vux'

  export default {
    beforeCreate: function () {
      console.log('beforeCreate');
    },
    created: function () {
      console.log('created');
    },
    beforeMount: function () {
      console.log('beforeMount');
    },
    mounted: function () {
      console.log('mounted');
    },
    beforeUpdate: function () {
      console.log('beforeUpdate');
    },
    updated: function () {
      console.log('updated');
    },
    activated: function () {
      console.log('activated');
    },
    deactivated: function () {
      console.log('deactivated');
    },
    beforeDestroy: function () {
      console.log('beforeDestroy');
    },
    destroyed: function () {
      console.log('destroyed');
    },
    errorCaptured: function (err) {
      console.log('errorCaptured');
    },
    methods: {
      init: function () {

      },
      tabChange: function (evt) {
        switch (evt) {
          case 0: {
            this.$parent.$data.headTitle="护肤品商城";
            break;
          }
          case 1: {
            this.$parent.$data.headTitle="分类";
            break;
          }
          case 2: {
            this.$parent.$data.headTitle="品牌列表";
            break;
          }
          case 3: {
            this.$parent.$data.headTitle="购物车";
            break;
          }
          case 4: {
            this.$parent.$data.headTitle="个人中心";
            break;
          }
          default:
            break;
        }
        console.log(evt);
      }
    },
    components: {
      Tabbar,
      TabbarItem,
      Vue
    }
  }

</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "bottomTab.styl";
</style>
