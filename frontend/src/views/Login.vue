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
    <h3>로그인</h3><br>
    <input type="text" v-model="id" placeholder="아이디를 입력해주세"><br>
    <input type="password" placeholder="비밀번호를 입력해주세요"><br>
    <input type="submit" value="LogIn">
    <input type="submit" value="SignUp"><br>
    <p>계정이 없다면, 회원가입을 해주세요</p>
    </div>
