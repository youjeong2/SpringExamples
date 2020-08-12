package com.example.demo.controller;

import com.example.demo.entity.VueBoard;
import com.example.demo.service.VueBoardService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Log
@RestController
@RequestMapping("/boards")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class VueBoardController {
    //static final Logger log = LoggerFactory.getLogger(VueBoardController.class);

    @Autowired
    private VueBoardService service;

    @GetMapping("/{boardNo}")
    public ResponseEntity<VueBoard> read(
            @PathVariable("boardNo") Long boardNo) throws Exception {
        log.info("read()");

        VueBoard board = service.read(boardNo);
        System.out.println("VueBoardController: " + board);

        return new ResponseEntity<VueBoard>(board, HttpStatus.OK);

    }

    @GetMapping("")
    public ResponseEntity<List<VueBoard>> list() throws Exception {
        log.info("list()");

        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @PostMapping("")
    //String->VueBoard로 바꿨음 db에서 꺼내와서 보여주는 거니까
    public ResponseEntity<VueBoard> register(
            @Validated @RequestBody VueBoard board,
            UriComponentsBuilder uriBuilder) throws Exception {
        log.info("POST register()");

        service.register(board);

        log.info("register board.getBoardNo() = " + board.getBoardNo());
        /* url로 boards/{boardNo으로 정보를싸줬음
        URI resourceURI = uriBuilder.path("boards/{boardNo}")
                .buildAndExpand(board.getBoardNo())
                .encode()
                .toUri();

        return ResponseEntity.created(resourceURI).build(); */
        // 새로운 엔티티 객체를 만들면서 리턴
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> remove(
            @PathVariable("boardNo") Long boardNo) throws Exception {
        log.info("remove");

        service.remove(boardNo);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{boardNo}")
    // Void-> VueBoard
    public ResponseEntity<VueBoard> modify(
            @PathVariable("boardNo") Long boardNo,
            @Validated @RequestBody VueBoard board) throws Exception {
        log.info("Put - modify()");
        // 변경한 정보 가지고옴 -> 안가져옴 -> 보내는 edit부분이 문제라는 것
        System.out.println(board);

        board.setBoardNo(boardNo);
        service.modify(board);
        // 수정된 정보를 알려줘야
        return new ResponseEntity<>(board, HttpStatus.OK);
    }
}
