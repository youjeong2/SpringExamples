import {
  successGenRandNum,
  failGenRandNum,
  ADD_TODO,
  REMOVE_TODO,
  CLEAR_ALL,
  RESTORE,
  EDIT_TODO,
  TOGGLE_TODO_STATUS,
  FETCH_BOARD_LIST,
  FETCH_BOARD
} from './mutation-types'

import axios from 'axios'

export default {
  fetchBoardList ({ commit }) {
    return axios.get('http://localhost:7777/boards')
      .then(res => {
        commit(FETCH_BOARD_LIST, res.data)
      })
  },
  // 낱개만 구해오는 fetchboard 추가
  fetchBoard ({ commit }, boardNo) {
    console.log('fetchBoard ' + commit + ', boardNo = ' + boardNo)
    // axiou 보드s밑에 보드넘을 가지고 요청을 넣는 것
    return axios.get(`http://localhost:7777/boards/${boardNo}`)
      .then(res => {
        console.log('fetchBoard - res: ' + res.data)
        // commit 특정 페치만 가져올 수 있도록
        commit(FETCH_BOARD, res.data)
      })
  },
  editTodo ({ commit }, payload) {
    commit(EDIT_TODO, payload)
  },
  toggleTodoStatus ({ commit }, id) {
    commit(TOGGLE_TODO_STATUS, id)
  },
  save ({ state }) {
    const data = {
      todoItems: state.todoItems,
      nextTodoId: state.nextTodoId
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
