<!--VutifyBoard 홈추가 라우터추가
VurifyBoardListForm

VuetifyBoardListPage 홈추가 라우터추가
VuetifyBoardListPageForm-->
<template>
  <div align="center">
    <h2>Vuetify Board List</h2>
    <!-- -v-bind생략한 거 form 뒤에 띄워주는 거 잊지말기
     VuetifyBoardListForm html을 태그화
     list-array로 v-bind하고
     "pageArray"로 데이터를 던져줌
      받는애는 prop로 받음-->
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
