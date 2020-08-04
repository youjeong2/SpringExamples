package com.example.demo.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

// 랜덤한 숫자 생성하고 값요청 들어오면 랜덤값 주는 것
public class RandNumMessage {
    private final static Logger log =
            LoggerFactory.getLogger(RandNumMessage.class);

    static Random random = new Random();
    private Integer randNumber;
    public RandNumMessage() {
        this.randNumber =  random.nextInt(32) + 3;
        log.info("RandomMessage() : " + randNumber);
    }
    public Integer getRandNumber() {
        return randNumber;
    }
}
