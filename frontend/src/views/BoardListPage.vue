<template>
  <div id="board">
    <h2>Board List</h2>
    <router-link :to="{ name: 'BoardRegisterPage' }">
      Create New Board
    </router-link>
    <!-- component BoardList를 보여준다 -->
    <board-list/>
  </div>
</template>

<script>
import BoardList from '@/components/BoardList'

export default {
  name: 'BoardListPage',
  // 실질적인 작업은 여기서 하는 것
  components: {
    BoardList
  }
}
</script>
