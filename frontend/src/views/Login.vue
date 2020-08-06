<template>
  <div class="Login">
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
    <h3>로그인</h3>
    <input type="text"  v-model="id" placeholder="id"<br>
    <input type="password" placeholder="password"<br>
    <input type="submit" value="Login">
    <button>로그인</button>
    <p>만약 계정이 없다면, 회원가입을 해주세요</p>
    </div>
</div>
