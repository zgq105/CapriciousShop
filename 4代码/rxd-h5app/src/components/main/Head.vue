<template>
<div class="head-root"><p>{{headTitle}}</p></div>
</template>

<script>
   export default {
     props:{
       headTitle:{
         type:String,
         default:"护肤品"
       }
     }
   }
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "head.styl";
</style>
