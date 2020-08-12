<template>
  <div align="center">
    <h2>Detailed Board List</h2>
    <!-- 읽을게 있어야하니까 board="board"넣어주기 없다면 loading  -->
    <!-- params: {가변인자} -->
    <!-- Edit을 누르면 정보를 수정(BoardModifyPage로 link)할 수 있도록 라우팅 -->
    <board-read v-if="board" :board="board"/>
    <p v-else>Loading ...</p>
    <router-link :to="{ name: 'BoardModifyPage', params: { boardNo } }">
      Edit
    </router-link>
    <!-- 버튼은 @클릭을 onDelte를 동작시키기 -->
    <button @click="onDelete">Delete</button>
    <router-link :to="{ name: 'BoardListPage' }">
      List
    </router-link>
  </div>
</template>

<script>
import BoardRead from '@/components/BoardRead'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'

export default {
  name: 'BoardReadPage',
  // 하위컴포와 연결하기
  props: {
    boardNo: {
      type: String,
      required: true
    }
  },
  computed: {
    ...mapState([
      'board'
    ])
  },
  created () {
    // 초기에 생성할 때 잘 동작을 확인하기 위한 read페이지 걸어놓고 + boardNo을 걸어 놓기
    console.log('BoardReadPage created(): ' + this.boardNo)
    // 페치를 통해 현재 게시판 번호를 전달해주고
    // 게시판 내용을 얻어와라
    // 에러나오면 경고메세지 띄워줘
    this.fetchBoard(this.boardNo)
      .catch(err => {
        alert(err.response.data.message)
        this.$router.push()
      })
  },
  methods: {
    ...mapActions([
      'fetchBoard'
    ]),
    onDelete () {
      // 보드에 보드넘을 보내달라
      const { boardNo } = this.board
      axios.delete(`http://localhost:7777/boards/${boardNo}`)
        // 처리결과를 res로 처리결과 성공이면 success
        .then(res => {
          alert('Delete Success')
          // delete를 누르면 BoardListPage로 강제로 보내기
          this.$router.push({ name: 'BoardListPage' })
        })
        .catch(err => {
          alert(err.response.data.message)
        })
    }
  },
  components: {
    BoardRead
  }
}
</script>
