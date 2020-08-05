<template>
  <div>
    <h3> Todo Footer </h3>
    <button>Delete All</button>
  </div>
</template>

<script>
export default {
  methods: {
    clearTodo () {
      console.log('clearTodo')
    }
  }
}
</script>

<style scoped>
  div {
    background-color: #7E13B7
  }
</style>
