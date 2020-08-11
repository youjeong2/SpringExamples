// 하위 컴포넌트들을 작성해주면됨
<template>
  <div>
    <h3>Compo Board List View</h3>
    <router-link :to="{ name: 'BoardReadPage', params: { boardNo: 1 } }">
      Detailed Board View
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'BoardList'
}
</script>
