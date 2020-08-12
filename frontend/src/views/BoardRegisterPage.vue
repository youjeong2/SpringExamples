<template>
  <div align="center">
    <h2>Board Register</h2>
    <!-- 게시글 폼 ->method에 추가  component에 "onSubmit methods 추가하기" -->
    <board-register-form @submit="onSubmit"/>
  </div>
</template>

<script>
import BoardRegisterForm from '@/components/BoardRegisterForm'
// 컨트롤러랑 연결하기 위해 axios 추가하기
import axios from 'axios'

export default {
  name: 'BoardRegisterPage',
  components: {
    BoardRegisterForm
  },
  methods: {
    onSubmit (payload) {
      console.log('BoardRegisterPage onSubmit()')
      const { title, writer, content } = payload
      // 스프링포트번호 넣어서 연결하기 {Requester Mapping} 연결하기
      axios.post('http://localhost:7777/boards', { title, writer, content })
        .then(res => {
          console.log(res)
          alert('Register Success')
          // router 이용해서 강제로 BoardReadPage로 가게 경로 바꾸기
          this.$router.push({
            name: 'BoardReadPage',
            params: { boardNo: res.data.boardNo.toString() }
          })
        })
        // 요청을 처리하지 못했으면 에러메세지 뿌려주는 것
        .catch(err => {
          alert(err.response.data.message)
        })
    }
  }
}
</script>
