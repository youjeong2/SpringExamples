<!--VutifyBoard 홈추가 라우터추가
VurifyBoardListForm

VuetifyBoardListPage 홈추가 라우터추가
VuetifyBoardListPageForm-->
<template>
  <div align="center">
    <h2>Vuetify Board List</h2>
    <!-- -v-bind생략한 거 form 뒤에 띄워주는 거 잊지말기 -->
    <vuetify-board-list-form :list-array="pageArray"/>
  </div>
</template>

<script>
import axios from 'axios'
import VuetifyBoardListForm from '@/components/VuetifyBoardListForm.vue'

export default {
  name: 'VuetifyBoard',
  components: {
    VuetifyBoardListForm
  },
  // pageArray는 views에서 list-array는 compo에서 바인드로 전달받음
  data () {
    return {
      pageArray: []
    }
  },
  created () {
    axios.get('http://sample.bmaster.kro.kr/contacts')
      .then(res => {
        console.log(res)
        // pageArray를 res.data로 보내기
        this.pageArray = res.data.contacts
      })
      .catch(err => {
        console.log(err)
      })
  }
}
</script>
