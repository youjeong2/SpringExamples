<template>
  <td @click="onClickTd">{{ cellData }}</td>
</template>

<script>
export default {
  data () {
    return {
      game: {
        propTurn: this.turn,
        propWin: this.winner
      }
    }
  },
  props: {
    // GlobalComponent ===> global-component
    cellData: String,
    rowIndex: Number,
    cellIndex: Number,
    tableData: Array,
    turn: String,
    winner: String
  },
  methods: {
    onClickTd () {
      if (this.cellData) return
      this.$set(this.tableData[this.rowIndex], this.cellIndex, this.turn)
      let win = false
      if (
        this.tableData[this.rowIndex][0] === this.turn &&
        this.tableData[this.rowIndex][1] === this.turn &&
        this.tableData[this.rowIndex][2] === this.turn
      ) {
        win = true
      }
      if (
        this.tableData[0][this.cellIndex] === this.turn &&
        this.tableData[1][this.cellIndex] === this.turn &&
        this.tableData[2][this.cellIndex] === this.turn
      ) {
        win = true
      }
      if (
        this.tableData[0][0] === this.turn &&
        this.tableData[1][1] === this.turn &&
        this.tableData[2][2] === this.turn
      ) {
        win = true
      }
      if (
        this.tableData[0][2] === this.turn &&
        this.tableData[1][1] === this.turn &&
        this.tableData[2][0] === this.turn
      ) {
        win = true
      }
      if (win) {
        this.game.propWin = this.turn
        // 하위에 데이터 전달
        this.$emit('updateWinner', this.game.propWin)
        this.$emit('updateTurn', 'O')
        this.$emit('updateTableData')
      } else {
        // 모든 셀들이 체크가 되었는가?
        let all = true
        this.tableData.forEach(row => {
          row.forEach(cell => {
            if (!cell) {
              all = false
            }
          })
        })
        if (all) {
          this.propWin = ''
          this.$emit('updateTurn', 'O')
          this.$emit('updateTableData')
        } else {
          this.game.propTurn = this.turn === 'O' ? 'X' : 'O'
          this.$emit('updateTurn', this.game.propTurn)
        }
      }
    }
  },
  mounted: function () {
    console.log('tableData: ' + this.tableData +
                ', turn: ' + this.turn +
                ', winner: ' + this.winner)
  }
}
</script>

<style></style>
