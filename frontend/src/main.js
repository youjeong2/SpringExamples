import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import cookies from 'vue-cookies'

Vue.config.productionTip = false

Vue.use(cookies)
// 뷰객체를 초기화 하면서 정보를 전달
function init () {
  const savedToken = cookies.get('accessToken')
  // 토큰이 있으면 dispatch를 해서 loginByToken를 전달하고, 저장한다.
  // 토큰이 있으면 뭘 하든 막지 마라
  if (savedToken) {
    return store.dispatch('loginByToken', savedToken)
  } else {
    // Promise는 비동기 처리
    // 로그인이 안되어 있으면(토큰이 없으면) 넘어가서 다른 작업을 하게한다(then)
    // 스레드, 컨텍스트 스위칭, 뮤텍스, 세마포어, 비동기 처리, 동기 처리
    // 못넘어가게해서 아무것도 없는거처럼 보이거나 사이트가 꺼진거처럼 보이게 하는건 동기처리
    return Promise.resolve()
  }
}
// 위에꺼 실행하게 하고(init) 다음작업을 하게함(then)
init().then(() => {
  new Vue({
    router,
    store,
    render: h => h(App)
  }).$mount('#app')
})
