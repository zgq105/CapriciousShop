<template>
  <div class="contentpanel-root">
    <div v-for="item in contentItemDatas">
      <h3>{{item.title}}</h3>
      <img :src="item.img" class="contentpanel-bigimg">
      <ul class="contentpanel-goods">
        <li v-for="item2 in item.goods">
          <span>
            <img  :src="item2.img">
            <span class="contentpanel-name">{{item2.name}}</span>
            <span class="contentpanel-price">{{item2.price}}</span>
          </span>
        </li>
      </ul>
      <x-button class="contentpanel-btn-more" type="warn">查看更多</x-button>

    </div>
  </div>
</template>

<script type="text/javascript">
  import { XButton } from 'vux'

  export default {
    data: function () {
      return {
        contentItemDatas: [
          {
            title: "全球代购",
            img: "../../../static/images/swiper01.jpg",
            goods: [{
              img: "../../../static/images/swiper01.jpg",
              name: "洗面奶1",
              price: 75.00
            },{
              img: "../../../static/images/swiper02.jpg",
              name: "洗面奶2",
              price: 73.00
            },{
              img: "../../../static/images/swiper03.jpg",
              name: "洗面奶3",
              price: 55.00
            },{
              img: "../../../static/images/swiper01.jpg",
              name: "洗面奶4",
              price: 55.00
            }]
          },
          {
            title: "全球代购2",
            img: "../../../static/images/swiper02.jpg",
            goods: []
          },
          {
            title: "全球代购3",
            img: "../../../static/images/swiper03.jpg",
            goods: []
          }
        ]
      }
    },
    created: function () {
      console.log('created');
    },
    methods: {},
    components: {
      XButton
    }
  }

</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "contentPanel.styl";
</style>
