<template>
  <div>
    <h3>Board List View</h3>
    <table border="1">
      <tr>
        <th align="center" width="80">No</th>
        <th align="center" width="320">Title</th>
        <th align="center" width="100">Writer</th>
        <th align="center" width="180">Registration Date</th>
      </tr>
      //
      <tr v-for="page in paginatedData" :key="page.boardNo">
        <td>{{ page.boardNo }}</td>
        <td>{{ page.title }}</td>
        <td>{{ page.writer }}</td>
        <td>{{ page.regDate }}</td>
      </tr>
    </table>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">이전</button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">다음</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'VuetifyListPageForm',
  data () {
    return {
      pageNum: 0
    }
  },
  // 아래쪽VutifyBoardListPage에서 만든listArray를 넣주기
  // pageArray는 views에서 list-array는 compo에서 바인드로 전달받음 (data, props)
  props: {
    listArray: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: true,
      default: 5
    }
  },
  methods: {
    nextPage () {
      this.pageNum += 1
    },
    prevPage () {
      this.pageNum -= 1
    }
  },
  // 여기의 계산식에 의해 위의 페이지가 Count됨
  computed: {
    pageCount () {
      const listLen = this.listArray.length
      const listSize = this.pageSize
      // 디비에 들어온 데이터의 개수 전체길이에서 5로 나누기
      let page = Math.floor(listLen / listSize)
      if (listLen % listSize > 0) {
        page += 1
      }

      return page
    },
    // pagenatedData를 listArray로 return해서 list채워짐
    // 5개의 데이터
    paginatedData () {
      const start = this.pageNum * this.pageSize
      const end = start + this.pageSize
      return this.listArray.slice(start, end)
    }
  }
}
</script>
