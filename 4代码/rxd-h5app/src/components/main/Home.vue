<template>
  <div>
    <swiper v-bind:list="swipDatas" v-bind:auto="true" dots-position="center">
    </swiper>
    <grid :show-lr-borders="false" :show-vertical-dividers="false">
      <grid-item :label="item.name" v-for="item in gridDatas">
        <img slot="icon" :src="item.imgUrl">
      </grid-item>
    </grid>
    <ContentPanel></ContentPanel>
  </div>
</template>

<script type="text/javascript">
  import ContentPanel from './ContentPanel.vue'
  import {Swiper} from 'vux'
  import {Grid, GridItem} from 'vux'

  export default {
    data() {
      return {
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
      Swiper,
      Grid,
      GridItem,
      ContentPanel
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "home.styl";
</style>
