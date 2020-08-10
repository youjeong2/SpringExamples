<template>
  <div class="todo">
    <todo-header></todo-header>
    <todo-input v-on:addTodo="onAddTodo"></todo-input>
    <todo-filter></todo-filter>
    <todo-list v-on:removeTodo="onRemoveTodo"
      v-on:updateTodo="onEditTodo"
      v-on:toggleTodoStatus="onToggleTodoStatus"></todo-list>
    <todo-footer v-on:removeAll="onClearAll"></todo-footer>
    <b>random: {{ this.$store.getters.random }}</b><br>
    <input type="button" @click="randomNumber()" value="random"/><br>
  </div>
</template>

<script>
import TodoHeader from '../components/TodoHeader.vue'
import TodoInput from '../components/TodoInput.vue'
import TodoList from '../components/TodoList.vue'
import TodoFooter from '../components/TodoFooter.vue'
import TodoFilter from '../components/TodoFilter.vue'
// import store from '../store'
// 자동으로 ~ mapping 해와라
import { mapActions } from 'vuex'
export default {
  name: 'Todo',
  components: {
    'todo-header': TodoHeader,
    'todo-input': TodoInput,
    'todo-list': TodoList,
    'todo-footer': TodoFooter,
    'todo-filter': TodoFilter
  },
  methods: {
  // = 전에 것을 추가
    ...mapActions([
      'clearAll',
      'addTodo',
      'removeTodo',
      'generateRandomNumber',
      'save',
      'restore',
      'editTodo',
      'toggleTodoStatus'
    ]),
    // "" 안에 것을 추가
    onClearAll () {
      this.clearAll()
      this.save()
    },
    onAddTodo (content) {
      const todoItem = { content }
      this.addTodo(todoItem)
      this.save()
    },
    onRemoveTodo (id) {
      this.removeTodo(id)
      this.save()
    },
    randomNumber () {
      // this.$store.dispatch('generateRandomNumber')
      this.generateRandomNumber()
    },
    onEditTodo (content, id) {
      this.editTodo({ id, content })
      this.save()
    },
    onToggleTodoStatus (id) {
      this.toggleTodoStatus(id)
      this.save()
    }
  },
  // create :생성자가 동작하는 시점 정보가 들어갈 때
  // restore:생성된 시점에 보관한다 - 기존정보 불러오고 날린 상태보관
  created () {
    this.restore()
  }
}
</script>

<style>
  body {
    text-align: center;
    background-color: #23582B;
  }
</style>
