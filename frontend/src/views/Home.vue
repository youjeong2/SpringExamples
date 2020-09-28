// home을 idex에서 보여줌, router에 있는 home을 찾아서 보여줌
<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <!-- -isAuthorized 권한이 있으면 체크를 해서 접근을 할 수 있게 해줌
         -권한이 부여된거니까 로그아웃이 되게하고 @클릭하면 로그아웃이 되게 -->
    <div id="header" v-if="isAuthorized">
      <button id="login" @click="onClickLogout">Logout</button>
      <router-link :to="{ name: 'Home' }"
          class="nav-link"
          active-class="active">
        Home
      </router-link>
      <router-link :to="{ name: 'About' }"
          class="nav-link"
          active-class="active">
        About Us
      </router-link>
      <router-link :to="{ name: 'Test' }"
          class="nav-link"
          active-class="active">
        Test
      </router-link>
      <router-link :to="{ name: 'Todo' }"
          class="nav-link"
          active-class="active">
        Todo
      </router-link>
      <router-link :to="{ name: 'Concave' }"
          class="nav-link"
          active-class="active">
        Concave
      </router-link>
      <router-link :to="{ name: 'BoardListPage' }"
          class="nav-link"
          active-class="active">
        Board
      </router-link>
      <router-link :to="{ name: 'VuetifyBoard' }"
          class="nav-link"
          active-class="active">
        VuetifyBoard
      </router-link>
      <router-link :to="{ name: 'VuetifyBoardListPage' }"
          class="nav-link"
          active-class="active">
        VuetifyBoardListPage
      </router-link>
      <router-link :to="{ name: 'CrawlCategory' }"
          class="nav-link"
          active-class="active">
        CrawlCategory
      </router-link>

      <h2>This is an Home Page</h2>
      <div id="app">
        {{ message }}<br>
      </div>
      <div>
        <br><span>{{ myinfo.auth }}계정, 접속을 환영합니다.</span>
      </div>
    </div>
    <!-- 로그인 하지 않았을 경우 -->
    <div id="header" v-else>
      <button id="login" @click="$router.push('LoginPage')">
        Login
      </button>
      <button id="login" @click="$router.push('AdminSetupPage')">
        Register Admin
      </button>
      <router-link :to="{ name: 'Home' }"
          class="nav-link"
          active-class="active">
        Home
      </router-link>
      <router-link :to="{ name: 'About' }"
          class="nav-link"
          active-class="active">
        About Us
      </router-link>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
/* eslint-disable no-unused-vars */
import store from '../store'
import Vue from 'vue'
// import cookies from 'vue-cookies'

import { mapState, mapGetters, mapActions } from 'vuex'

// Vue.use(cookies)

export default {
  name: 'Home',
  data: function () {
    return {
      message: 'Vue Test Message'
    }
  },
  methods: {
    onClickLogout () {
      this.logout()
      alert('Success Logout')
      this.$router.push({ name: 'Home' })
    },
    ...mapActions(['logout'])
  },
  computed: {
    ...mapState(['myinfo']),
    ...mapGetters(['isAuthorized'])
  },
  components: {
  }
}
</script>

<style scoped>
div {
  border: 1px solid #ccc;
}

#header {
  padding: 30px;
  margin-bottom: 20px;
  margin: 10px 10px;
}

img {
  width: auto;
  height: auto;
  max-width: 1000px;
  max-height: 350px;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

#login {
  background-color: #77aadd;
  color: #ffffff;
  font-weight: bold;
  float: right;
}

</style>
