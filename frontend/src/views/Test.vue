<template>
  <div class="home">
    <div id="header">
      <router-link :to="{ name: 'Home' }" class="nav-link" active-class="active">
        Home
      </router-link>
      <router-link :to="{ name: 'About' }" class="nav-link" active-class="active">
        About Us
      </router-link>
      <router-link :to="{ name: 'Test' }" class="nav-link" active-class="active">
        Test
      </router-link>
    </div>
    <h2>This is an TEST Page</h2>
    <div id="app">
      <p v-if="seen"> You can see it!</p>
      <p v-bind:key=todo v-for="todo in todos">
        {{ todo. text }}
        </p>
        <p>{{ message }}</p>
        <button v-on:click="reverseMsg"> Reverse Message</button>
        <p>{{ message }}</p>
        <input v-model="message"><br>
        <button @click="intCnt">{{ cnt }}</button><br>
        <!-- border-collapse는 테두리와 셀 사이 간격 삭제 -->
        <p>count() 기반 순회</p>
        <table border="1" style="border-collapse:collapse;">
           <tr v-bind:key=idx v-for="idx in count">
             <td>{{ msg }}</td>
             <td>{{ idx }}</td>
          </tr>
        </table>
        <p>list 기반 순회</p>
        <table border="1" style="border-collapse:collapse;">
           <tr v-bind:key=idx v-for="idx in list()">
             <td>{{ message }}</td>
             <td>{{ idx }}</td>
           </tr>
        </table>
        <p>(key ,value) 기반 순회</p>
        <table border="1" style="border-collapse:collapse;">
         <tr v-bind:key="(key ,value)" v-for="(key ,value) in data">
           <td>{{ key }}</td>
           <td>{{ value }}</td>
         </tr>
      </table>
      <!-- this.$store이 vuex를 사용하겠단 의미 -->
      <b>count: {{ this.$store.state.count }}</b><br>
      <b>count^2: {{ this.$store.getters.count }}</b><br>
      <b>weight: {{ this.$store.getters.weight }}</b><br>
      <input type="button" @click="increment()" value="inc"/>
      <input type="button" @click="decrement()" value="dec"/><br>
      <b>random: {{ this.$store.getters.random }}</b><br>
      <input type="button" @click="randomNumber()" value="random"/><br>
     </div>
    </div>
</template>

<script>
import Vue from 'vue'
/* eslint-disable no-unused-vars */
import store from '../store'
import cookies from 'vue-cookies'

Vue.use(cookies)

export default {
  data: function () {
    return {
      msg: 'Test',
      count: 7,
      list: function () {
        var list = []
        for (var i = 1; i < this.count; i += 2) {
          list.push(i)
        }
        return list
      },
      message: 'Test Page',
      seen: true,
      todos: [
        { text: 'JavaScript' },
        { text: 'Golang' },
        { text: 'C++' }
      ],
      cnt: 0,
      data: {
        bird: 'raven',
        animal: 'tiger',
        fish: 'tuna',
        proj: 'cnn',
        major: 'EE'
      }
    }
  },
  methods: {
    reverseMsg: function () {
      this.message = this.message.split('').reverse().join('')
    },
    intCnt: function () {
      this.cnt++
    },
    increment: function () {
      this.$store.commit('increment')
      this.$cookies.set('value', this.$store.state.count, '1h')
    },
    decrement: function () {
      this.$store.commit('decrement')
      this.$cookies.set('value', this.$store.state.count, 60 * 60 * 24)
    },
    randomNumber: function () {
      this.$store.dispatch('generateRandomNumber')
      // dispatch는 무조건 action으로간다.
      // commit은 비동기 처리가 가능하다.
      // 동기가 된다는 건 해당내용의 실행을 보장/ 비동기는 나오든 말든 그냥 뿌리고 간다.
      // 비동기에서 작업이 오래걸리는 것은 바로 반영이 안될 수 있음
      // 동기처리할거에 비동기처리 넣으면 렉걸림
      // dispatch는 action 서버에 랜덤 넘 달라 -> 인덱스 스토어 파싱한 랜덤넘버 값을 랜덤넘에 넣어 주세요 데이터 요청이 끝날 깨가지 보장해주세요
      // commit은 내부에 있는 것에 보낼 때는 커밋
    }
  },
  created: function () {
    this.$store.state.count = this.$cookies.get('value')
  }
}
</script>
