<template>
  <div align="center">
    <h2>Detailed Board List</h2>
    <!-- Edit을 누르면 정보를 수정(BoardModifyPage로 link)할 수 있도록 라우팅
     가변인자 안쓰고 boardNo:1 -->
    <board-read/>
    <router-link :to="{ name: 'BoardModifyPage', params: { boardNo: 1 } }">
      Edit
    </router-link>
    <button>Delete</button>
    <router-link :to="{ name: 'BoardListPage' }">
      List
    </router-link>
  </div>
</template>

<script>
import BoardRead from '@/components/BoardRead'

export default {
  name: 'BoardReadPage',
  components: {
    BoardRead
  }
}
</script>
