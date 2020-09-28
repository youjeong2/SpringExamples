<template>
  <v-app id="inspire">
    <!-- v-app-bar는상단 메뉴바 -->
    <v-app-bar
      app
      clipped-right
      color="blue"
      dark
    >
      <!-- 네비게이션 drawer가 v-list를 icon과 drawer로 서포트
       toolbar는 상단에 이름 보여줌
       spacer은 컴포넌트 사이에 공간을 만들고 싶을 때 slot-name="menubar"을 서포트함
       슬롯으로 메뉴바를 연결하는거는 크롤링카테고리의 menubar정보를 여기에 연결하겠다는 뜻-->
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title><div @click="home">Crawl Project</div></v-toolbar-title>
      <v-spacer></v-spacer>
      <slot name="menubar"></slot>
    </v-app-bar>
    <v-navigation-drawer
      v-model="drawer"
      app
    >
      <!--list-dense는 리스트 정렬
      action안에 icon 모양넣을 수 있고 content안에 홈으로 이동누르면 이동할 수 있는거 넣을 수 있음
      mdi-exit은 아이콘 모양 / 포토샵으로 만들 수 있음-->
      <v-list dense>
        <v-list-item @click.stop="left = !left">
          <v-list-item-action>
            <v-icon>mdi-exit-to-app</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title @click="home">홈으로 이동</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-list dense>
        <v-list-item @click.stop="left = !left">
          <v-list-item-action>
            <v-icon>mdi-exit-to-app</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title @click="category()">카테고리별 분류 보기</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-list dense>
        <v-list-item @click.stop="left = !left">
          <v-list-item-action>
            <div>>>></div>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title @click="suggest">추천 뉴스 보기</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-list dense>
        <v-list-item @click.stop="left = !left">
          <v-list-item-action>
            <v-icon>mdi-exit-to-app</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title @click="savednews()">저장된 뉴스 보기</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <!-- 크롤링카테고리에서 보면 template #content Layout으로 감싸져서 여기로 전달할 수 있게함
     크롤링한 리스트 정보를 가져오고 리스트넘버랑 제목을 뿌려주는 것 -->
    <v-container>
      <v-content id="content">
        <slot name="content" class="font">
        </slot>
      </v-content>
    </v-container>
    <!-- 하단 부분 &copy가 coportaition C 가 되는 것 -->
    <v-footer
      app
      color="blue"
      class="white--text"
    >
      <v-spacer></v-spacer>
      <span>&copy; Crawl Project</span>
    </v-footer>
  </v-app>
</template>

<script>
import router from '../router'

export default {
  // -data  return방법 이 방법 데이터 초기화 하는 방법!2가지
  data: () => ({
    drawer: false,
    left: false
  }),
  // 라우터가 루트라면 ~로 보내고 그게 아니라면 ~로 보내기
  methods: {
    home () {
      (window.location.pathname !== '/') ? router.push('/') : router.go(0)
    },
    category () {
      (window.location.pathname !== '/CrawlCategory') ? router.push('/CrawlCategory') : router.go(0)
    },
    suggest () {
      (window.location.pathname !== '/suggestednews/list') ? router.push('/suggestednews/list') : router.go(0)
    },
    savednews () {
      (window.location.pathname !== '/savednews/list') ? router.push('/savednews/list') : router.go(0)
    }
  }
}
</script>
