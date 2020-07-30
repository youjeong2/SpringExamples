package com.example.demo.entity;

import java.util.Date;

public class Board {
    private int boardNo;
    private String title;
    private String content;
    private String writer;
    private Date regDate;

    public int getBoardNo() {
        return boardNo; // 힘수에서 보내는
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo; // 함수로 들어온 값을 자기것으로 만드는 것것
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
