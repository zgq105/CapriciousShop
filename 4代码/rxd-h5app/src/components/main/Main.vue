<template>
  <div class="main-container" id="main-container">
    <Head class="main-header" :headTitle="headTitle" ></Head>
    <div class="main-content">
      <router-view></router-view>
    </div>
    <BottomTab class="main-footer"></BottomTab>
  </div>
</template>


<script type="text/javascript">
  import Vue from 'vue'
  import Head from './Head.vue'
  import Home from './Home.vue'
  import ContentPanel from './ContentPanel.vue'
  import BottomTab from './BottomTab.vue'
  import {Swiper} from 'vux'
  import {Grid, GridItem} from 'vux'

  export default {
    data() {
      return {
        headTitle: "护肤品商城",
        swipDatas: [
          {
            url: 'http://www.baidu.com',
            img: 'https://ww1.sinaimg.cn/large/663d3650gy1fq66vvsr72j20p00gogo2.jpg',
            title: ''
          },
          {
            url: 'javascript:',
            img: 'https://ww1.sinaimg.cn/large/663d3650gy1fq66vw1k2wj20p00goq7n.jpg',
            title: ''
          },
          {
            url: 'javascript:',
            img: 'https://ww1.sinaimg.cn/large/663d3650gy1fq66vw50iwj20ff0aaaci.jpg',
            title: ''
          }
        ],
        gridDatas: [
          {
            imgUrl: 'https://ww1.sinaimg.cn/large/663d3650gy1fq66vvsr72j20p00gogo2.jpg',
            name: '拼团直购'
          },
          {
            imgUrl: 'https://ww1.sinaimg.cn/large/663d3650gy1fq66vw1k2wj20p00goq7n.jpg',
            name: '全球代购'
          },
          {
            imgUrl: 'https://ww1.sinaimg.cn/large/663d3650gy1fq66vvsr72j20p00gogo2.jpg',
            name: '优选套餐'
          },
          {
            imgUrl: 'https://ww1.sinaimg.cn/large/663d3650gy1fq66vvsr72j20p00gogo2.jpg',
            name: '限时折扣'
          }
        ]

      }
    },
    components: {
      Head,
      BottomTab,
      Swiper,
      Grid,
      GridItem,
      ContentPanel,
      Home,
      Vue
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "main.styl";
</style>
