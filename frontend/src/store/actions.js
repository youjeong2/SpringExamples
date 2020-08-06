// 추가한 method들 import하
import {
  successGenRandNum,
  failGenRandNum,
  ADD_TODO,
  REMOVE_TODO,
  CLEAR_ALL,
  RESTORE
} from './mutation-types'

import axios from 'axios'
// 커밋해서 날려주는 애들은 다 필요한 정보니까 import하기
export default {
  save ({ state }) {
    const data = {
      todoItems: state.todoItems
    }
    localStorage.setItem('todo-app-data', JSON.stringify(data))
  },
  restore ({ commit }) {
    const data = localStorage.getItem('todo-app-data')

    if (data) {
      console.log('data: ' + JSON.parse(data))
      commit(RESTORE, JSON.parse(data))
    }
  },
  generateRandomNumber ({ commit }) {
    console.log(commit)

    axios.get('http://localhost:7777/random')
      .then((res) => {
        commit(successGenRandNum,
          parseInt(res.data.randNumber))
      })
      .catch((res) => {
        commit(failGenRandNum, res)
      })
  },
  addTodo (context, payload) {
    context.commit(ADD_TODO, payload)
  },
  removeTodo (context, payload) {
    context.commit(REMOVE_TODO, payload)
  },
  clearAll (context, payload) {
    context.commit(CLEAR_ALL)
  }
}
// actions: {
//  generateRandomNumber ({ commit }) {
//    console.log(commit)
//
//    axios.get('http://localhost:7777/random')
//      .then((res) => {
//        commit('successGenRandNum',
//          parseInt(res.data.randNumber))
//      })
//      .catch((res) => {
//        commit('failGenRandNum', res)
//      })
//  },
//  addTodo (context, payload) {
//    context.commit('ADD_TODO', payload)
//  },
//  removeTodo (context, payload) {
//    context.commit('REMOVE_TODO', payload)
//  },
//  clearAll (context, payload) {
//    context.commit('CLEAR_ALL')
//  }
