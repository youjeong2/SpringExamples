package com.example.demo.controller;

import com.example.demo.entity.Music;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @Controller는 HTML 핸들링에 특화되어 있으므로
// HTMl을 많이 쓰는 경우 일반 Controller
// 데이터를 많이 처리하는 경우 modle은 코드 칠게 많으니까 restcontroller
@RestController
@RequestMapping("musics")
public class MusicController {
    final static Logger log =
            LoggerFactory.getLogger(MusicController.class);

    @GetMapping("")
    public ModelAndView musicIndex() {
        log.info("musicIndex()");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("music/musicIndex");

        return modelAndView;
    }

    @GetMapping("/test")
    public Music musicTest() {
        log.info("musicTest()");

        Music music = new Music();

        return music;
    }

    @GetMapping("/play")
    public ModelAndView musicPlay() {
        log.info("musicPlay()");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("music/musicPlay");

        return modelAndView;
    }

    @GetMapping("/menu")
    public ModelAndView musicMenu() {
        log.info("musicMenu()");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("music/musicMenu");

        return modelAndView;
    }

    @GetMapping("/record")
    public ModelAndView musicRecord() {
        log.info("musicRecord()");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("music/musicRecord");

        return modelAndView;
    }
    // **
    // 복수라는 것을 암시
    // musicindex 로 리턴
    @GetMapping("/titles")
    public List<Music> musicTitles() {
        log.info("musicTitles()");

        List<Music> list = new ArrayList<Music>();

        Music music1 = new Music();
        list.add(music1);

        Music music2 = new Music();
        list.add(music2);

        return list;
    }

    @GetMapping("/artists")
    // HashMap
    // 아티스트에 아티스트가 어떤 것을 했는지 알 수 있게
    // Map에 데이터 세팅하려면 put
    // 키값 : 아티스트 명과 벨류 값 : 아티스트가 만든 노래
    // musicindex
    public Map<String, Music> musicArtists() {
        log.info("musicArtists()");

        Map<String, Music> map =
                new HashMap<String, Music>();

        Music music1 = new Music();
        map.put("Jiha", music1);

        Music music2 = new Music();
        map.put("ABC", music2);

        return map;
    }
    @GetMapping("/resptest")
    // 파이썬에 보낼 때 RestpoonseEntity
    // 일반 컨트롤러에서 json처리할  때
    public ResponseEntity<Void> musicRespTest() {
        log.info("musicRespTest");

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/respstring")
    // 리스폰스하면서 문자도 넣어줘야함  string 타입이기 때문에
    public ResponseEntity<String> musicRespString() {
        log.info("musicRespString()");

        return new ResponseEntity<String>(
                "Success", HttpStatus.OK
        );
    }
    @GetMapping("/respclass")
    // test랑 같은 결과
    // test :return 해서 자동으로 처리  restcontroller라서
    // respclass : 얘는 일반 controller는 ResponseEntity 써야
    public ResponseEntity<Music> musicRespClass() {
        log.info("musicRespClass()");

        Music music = new Music();

        return new ResponseEntity<Music> (music, HttpStatus.OK);
    }
    @GetMapping("/resplist")
    // list에 music을 넘긴다
    public ResponseEntity<List<Music>> musicRespList() {
        log.info("musicRespList");
        List<Music> list = new ArrayList<Music>();

        Music music1 = new Music();
        list.add(music1);

        Music music2 = new Music();
        list.add(music2);

        return new ResponseEntity<List<Music>>(list, HttpStatus.OK);
    }

    @GetMapping("/respfile")
    // 이미지는 행렬데이터기 때문에  byte를 씀
    // exception 발생하기 때문에 throw를 넣어줘야
    public ResponseEntity<byte[]> musicImgFile()throws Exception {
        log.info("musicImgFile()");

        InputStream in = null;
        ResponseEntity<byte[]> entity = null;
        // try로 head를 날려야함
        // 이미지 파일 넣는 곳
        try{
            HttpHeaders headers = new HttpHeaders();

            in = new FileInputStream("/home/bitai/proj/PythonWorkshop2/test_image_2.jpg");
            headers.setContentType(MediaType.IMAGE_JPEG);

            entity = new ResponseEntity<byte[]>(
                    IOUtils.toByteArray(in),
                    headers,
                    HttpStatus.CREATED
            );
        }catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(
                    HttpStatus.BAD_REQUEST);
        }finally {
            in.close();
        }
        return entity;
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