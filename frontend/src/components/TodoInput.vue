<template>
  <div>
    <h3>Todo Input</h3>
    <input type="text" v-model="newTodoItem"
       v-on:keypress.enter="addTodo">
    <button v-on:click="addTodo">Add</button>
  </div>
</template>

<script>

export default {
  data () {
    return {
      newTodoItem: ''
    }
  },
  methods: {
    addTodo () {
      if (this.newTodoItem !== '') {
        this.newTodoItem.trim()
        console.log('addTodo')
        this.clearInput()
      }
    },
    clearInput () {
      this.newTodoItem = ''
    }
  }
}

</script>

<style scoped>
  div {
    background-color: #6464F8
  }
</style>
