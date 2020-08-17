<template>
  <div align="center">
    <h2>Vuetify Real Board List</h2>
    <router-link :to="{ name: 'BoardRegisterPage' }">
      Create New Board
    </router-link>
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
