package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    private static final Logger log =
            LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService service;

    @GetMapping("/getRegister")
    public String getRegister(Board board, Model model)
                                        throws Exception{
        log.info("getRegister()");

        return "board/register";
    }


    @PostMapping("/postRegister")
    public String postRegister(Board board, Model model)
                                        throws Exception {
        log.info("postRegister()");

        service.register(board);

        model.addAttribute("msg","Register Success");
        return "board/success";
    }
    @GetMapping("/list")
    public String list(Model model)
                throws Exception{
        log.info("list()");
        // 속성 추가(이름 list)
        // Controller -> Service => Repository 방식으로 동작하게 설계되어 있다.
        // JPA
        //10. DB에서 읽어온 정보
        model.addAttribute("list", service.list());
        // HTML 파일 됨
        // 11. 여기로 넘겨주게
        return "board/list";
    }
}