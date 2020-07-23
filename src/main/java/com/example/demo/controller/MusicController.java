package com.example.demo.controller;

import com.example.demo.entity.Music;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("musics")
public class MusicController {
    final static Logger log =
            LoggerFactory.getLogger(MusicController.class);

    @GetMapping("")
    public String musicIndex() {
        log.info("musicIndex()");


        return "music/musicIndex";
    }
    @GetMapping("/test")
    @ResponseBody
    public Music musicTest() {
        log.info("musicTest()");

        Music music = new Music();

        return music;
    }

    @GetMapping("/play")
    public String musicPlay() {
        log.info("musicPlay()");

        return "music/musicPlay";
    }

    @GetMapping("/menu")
    public String musicMenu() {
        log.info("musicMenu()");

        return "/music/musicMenu";
    }

    @GetMapping("/record")
    public String musicRecord() {
        log.info("musicRecord()");

        return "/music/musicRecord";
    }

}

//package com.example.demo.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("musics")
//public class MusicController {
//    final static Logger log =
//            LoggerFactory.getLogger(MusicController.class);
//    // 파라미터 넣기
//    @GetMapping(value = "/get", params = "register")
//    public String getRegister()
//    {
//        logger.info("getRegister()");
//        return "htmlMusicplay";
//    }
//    @GetMapping("/menu")
//
//    @GetMapping("/rcord")