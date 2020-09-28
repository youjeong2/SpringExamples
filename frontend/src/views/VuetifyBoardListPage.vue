<template>
  <div align="center">
    <h2>Vuetify Real Board List</h2>
    <router-link :to="{ name: 'BoardRegisterPage' }">
      Create New Board
    </router-link>
    <!-- 실질적으로 페이지내이션 하는 것-->
    <board-list-page-form :list-array="pageArray"/>
  </div>
</template>

<script>
import axios from 'axios'
import BoardListPageForm from '@/components/BoardListPageForm.vue'

export default {
  name: 'VuetifyBoardListPage',
  components: {
    BoardListPageForm
  },
  // pageArray는 views에서 list-array는 compo에서 바인드로 전달받음 (data, props)
  data () {
    return {
      pageArray: []
    }
  },
  // AXIOS해서 게시판 정보를 받고 pageArray에 넣음
  // 이부분 action으로 빼고 mutaition, state에 페이지내이션할거 다시 만들기
  // 페이지내이션은 create의 데이터 가져오는거랑, computed의 계산이 전부임
  created () {
    axios.get('http://localhost:7777/boards')
      .then(res => {
        console.log(res)
        // pageArray를 res.data로 보내기
        this.pageArray = res.data
      })
      .catch(err => {
        console.log(err)
      })
  }
}
</script>
