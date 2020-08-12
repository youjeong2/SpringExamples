<!-- 보드정보가 있어야하고 바인드를 시켜서 특정대상만 갖고 오게하기 -->
<template>
  <div align="center">
    <h2>Board Modification</h2>
    <board-modify-form v-if="board" :board="board" @submit="onSubmit"/>
    <p v-else>Loading ...</p>
  </div>
</template>

<script>
import BoardModifyForm from '@/components/BoardModifyForm'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'

export default {
  name: 'BoardModifyPage',
  components: {
    BoardModifyForm
  },
  // prop로 하위컴포에서 전달되는 것 연결하기
  // router에 파람에 props 걸어놓음
  // 자동으로 boardNo이 mapping 되도록 ?
  props: {
    boardNo: {
      type: String,
      required: true
    }
  },
  // 요 정보는 mapState를 통해서 등록
  computed: {
    ...mapState(['board'])
  },
  // 요 객체가 생성이 될 때 conlsole 찍고 보드 하나를 물어와라
  // 에러 발생하면 경고 메세지를 띄워라 그리고 라우터 백을 해서 원래 페이지로 돌아가게 한다.
  created () {
    console.log('BoardModifyPage created()')
    this.fetchBoard(this.boardNo)
      .catch(err => {
        alert(err.response.data.message)
        this.$router.back()
      })
  },
  methods: {
    // payload로 받기
    onSubmit (payload) {
      const { title, content } = payload
      console.log('BoardModifyPage payload: ' + payload)
      axios.put(`http://localhost:7777/boards/${this.boardNo}`, { title, content })
        .then(res => {
          alert('Modify Success')
          console.log('res: ' + res.data)
          this.$router.push({
            name: 'BoardReadPage',
            params: { boardNo: res.data.boardNo.toString() }
          })
        })
        .catch(err => {
          alert(err.response.data.message)
        })
    },
    ...mapActions([
      'fetchBoard'
    ])
  }
}
// methods
// onSubmit
// payload로 받기
// axios.put('7777')
// then altert 하고 data 출력
// 라우터로 경로를 readpage로 넘겨주기
// 거기서 얻어온 보드 넘버에 toString을 넣어주기
//
// catch 만약 얻어오지 못할 경우 에러altert 띄워주기
//
// mapActions (fetch 게시판 하나를 fetch한다는 것)
</script>
