package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class HtmlBoardController {
    private static final Logger logger =
            LoggerFactory.getLogger(HtmlBoardController.class);
    // 파라미터 넣기
    @GetMapping(value = "/get", params = "register")
    public String getRegister()
    {
        logger.info("getRegister()");
        return "register";
    }
    @GetMapping(value = "/get", params = "read")
    public String getRead()
    {
        logger.info("getRegister()");
        return "htmlRead";
    }

    @GetMapping("/html/register")
    public String registerForm() {
        logger.info("registerForm");

        return "htmlRegister";
    }
    @PostMapping("/html/register")
    public String register() {
        logger.info("register(POST)");

        return "htmlRegister";
    }

    @GetMapping("/html/modify")
    public String modifyForm() {
        logger.info("modifyForm()");

        return "htmlModifyForm";
    }
    @PostMapping("/html/modify")
    public String modify() {
        logger.info("modify(POST)");

        return "htmlModifyForm";
    }

    @PostMapping("/html/delete")
    public String deleteForm() {
        logger.info("deleteForm()");

        return "htmlDeleteForm";
    }

    @GetMapping("/html/list")
    public String listForm() {
        logger.info("listForm()");

        return "htmlListForm";
    }
    // 1. PathVariable : 모든 게시판에는 게시판 번호가 있다.
    // 번호를 일일이 다 정해주기 어렵기 때문에
    // {boarNO}에 들어오는 숫자값을 boardNO로 넣고 read 페이지로 넘김
    // Path를 통해 가변 번호를 받을 수 있게 만든다.
    // /html/board/read를 8080에 넣으면 Read나오고 readForm:에 값 찍힘
    @GetMapping("/html/board/read/{boardNo}")
    public String readForm(
            @PathVariable("boardNo") int boardNo) {
        logger.info("readForm: " + boardNo);

        return "htmlRead";
    }
    @GetMapping("/regtestpage")
    public String registerTestPage() {
        logger.info("registerTestPage()");

        return "form/registerForm";
    }
    @PostMapping("regtest")
    public String registerTest(String userId, String passwd) {
        logger.info("registerTest");

        logger.info("userId = " + userId);
        logger.info("passwd = " + passwd);

        return  "form/success";
    }
    @GetMapping("/getRegTest")
    public <userId> String getRegTest(String userId, Date date) {
        logger.info("getRegTest()");
        logger.info("userId=" + userId);
        logger.info("date = " + date);

        return "form/success";
    }
}

// MusicController 를 하나 만든다.
// Controller 기능으로는 음악, 재생, 메뉴보기, 녹음
// 이와 관련된 HTML 및 Cotroller 구성을 직접 해보자!
// (음악이 재생, 녹음이 될 필요는 없다.)

// DB 접속하기
//  mysql -u bitai -p
// show databases;
//create database testdb;
//        use testdb;
//        show tables;
//        create table board(
//        -> board_no int not null auto_increment,
//        -> title varchar(200) not null,
//        -> content text null,
//        -> writer varchar(50) not null,
//        -> reg_date timestamp not null default now(),
//        -> primary key(board_no)
//        -> );