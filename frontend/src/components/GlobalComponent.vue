// 전역 컴포넌트
// click하면 addcounter 함수가 호출됨
// props 컴포넌트간의 공유하는 것 그게 counter로 공유됨
// addcounter를 누르면 method addcounter가 동작하고 서로 mapping이 되어 있어서 동작
// 여러개의 component 만들고 싶으면 v-bind로 갖다쓸 수 있음
// props: ['initialCounter'],
// this.initialCounter
// v-bind:initial-counter -> counter를 test로 바꿔서쓸 수 있음
<template>
  <button @click="addCounter">{{ counter }}</button>
</template>

<script>
export default {
  name: 'global-component',
  props: ['initialCounter'],
  template: '<button @click="addCounter">{{ counter }}</button>',
  data: function () {
    return {
      counter: this.initialCounter
    }
  },
  methods: {
    addCounter: function () {
      this.counter += 1
    }
  }
}
</script>
