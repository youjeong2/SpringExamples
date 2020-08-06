// mutation은 자동으로 데이터에 무결성을 보장 (thread safe)
// state : 데이터 자체
import {
  successGenRandNum,
  failGenRandNum,
  ADD_TODO,
  REMOVE_TODO,
  CLEAR_ALL,
  RESTORE
} from './mutation-types'

export default {
  [RESTORE] (state, { todoItems }) {
    state.todoItems = todoItems
  },
  increment (state) {
    state.count++
  },
  decrement (state) {
    state.count--
  },
  [successGenRandNum] (state, payload) {
    state.random = payload
  },
  [failGenRandNum] () {
    alert('망함')
  },
  [ADD_TODO] (state, todoItems) {
    state.todoItems.push(todoItems)
  },
  [REMOVE_TODO] (state, idx) {
    state.todoItems.splice(idx, 1)
  },
  [CLEAR_ALL] (state) {
    state.todoItems = []
  }
}
