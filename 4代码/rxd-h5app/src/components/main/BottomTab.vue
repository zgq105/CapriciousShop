<template>
  <div>
    <tabbar>
      <tabbar-item>
        <img slot="icon" src="../../assets/main_tab_home.png">
        <span slot="label">首页</span>
      </tabbar-item>
      <tabbar-item>
        <img slot="icon" src="../../assets/main_tab_classfy.png">
        <span slot="label">分类</span>
      </tabbar-item>
      <tabbar-item>
        <img slot="icon" src="../../assets/main_tab_shop.png">
        <span slot="label">品牌街</span>
      </tabbar-item>
      <tabbar-item>
        <img slot="icon" src="../../assets/main_tab_shopcart.png">
        <span slot="label">购物车</span>
      </tabbar-item>
      <tabbar-item>
        <img slot="icon" src="../../assets/main_tab_shopcart.png">
        <span slot="label">我的</span>
      </tabbar-item>
    </tabbar>

  </div>
</template>

<script type="text/javascript">
  import {Tabbar, TabbarItem} from 'vux'


  export default {
    components: {
      Tabbar,
      TabbarItem
    }
  }

</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "bottomTab.styl";
</style>
