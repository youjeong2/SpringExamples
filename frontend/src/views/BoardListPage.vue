<template>
  <div id="board">
    <h2>Board List</h2>
    <router-link :to="{ name: 'BoardRegisterPage' }">
      Create New Board
    </router-link>
    <!-- component BoardList를 보여준다 -->
    <!--보드리스트(compo)를 통해서 받아오니까 바인드를 해서 내용 전달하기-->
    <!-- component BoardList는 카멜케이스로-> 소문자로 바꿀 수 있음 -->
    <board-list :boards="boards"/>
  </div>
</template>

<script>
import BoardList from '@/components/BoardList'
import { mapState, mapActions } from 'vuex'

export default {
  name: 'BoardListPage',
  // 실질적인 작업은 여기서 하는 것
  components: {
    BoardList
  },
  computed: {
    ...mapState(['boards'])
  },
  // 아래를 실행시키고 위를 실행시키게함
  mounted () {
    this.fetchBoardList()
  },
  //
  methods: {
    ...mapActions(['fetchBoardList'])
  }
}

</script>
