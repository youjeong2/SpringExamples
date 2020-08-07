<template>
  <div>
    <h3> Todo List</h3>
      <ul>
        <h3>Todo List</h3>
        // bind는 데이터, on은 mehthod
        // editingId를 "editingId" 로 mapping?
        <todo-item v-for="todoItem in todoItems"
          v-bind:key="todoItem.id"
          v-bind:todoItem="todoItem"
          v-bind:editingId="editingId"
          v-on:removeTodo="onRemoveTodo"
          v-on:updateTodo="onUpdateTodo"
          v-on:setEditingId="SET_EDITING_ID"
          v-on:resetEditingId="RESET_EDITING_ID"
          v-on:toggleTodoStatus="onToggleTodoStatus"/>
      </ul>
  </div>
</template>

<script>
import TodoItem from './TodoItem.vue'
import { mapState, mapMutations, mapGetters } from 'vuex'
import { RESET_EDITING_ID, SET_EDITING_ID } from '../store/mutation-types'
export default {
  // 컴포넌트 TodoItem추가
  components: {
    'todo-item': TodoItem
  },
  // 단순계산
  computed: {
    todoItems () {
      return this.filteredTodoItems
    },
    ...mapState([
      'editingId'
    ]),
    ...mapGetters([
      'filteredTodoItems'
    ])
  },
  // 상위에 있는 removeTodo에서 "onRemoveTodo"가 호출되니까
  // onRemove를 methods에 추가시켜줌
  methods: {
  ...mapMutations([
    SET_EDITING_ID,
    RESET_EDITING_ID
  ]),
  onRemoveTodo (id) {
    this.$emit('removeTodo', id)
  },
  onUpdateTodo (id) {
    this.$emit('updateTodo', id)
  },
  onToggleTodoStatus (id) {
    this.$emit('toggleTodoStatus')
    }
  }
}
</script>
<style scoped>
  div {
    background-color: #7CA1E1

  }
</style>
