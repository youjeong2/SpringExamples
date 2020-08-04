package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 3. 클래스 implements 인터페이스
// Service 어노테이션
// Spring <<- Event Driven 방식으로 동작을 하고 있음
@Service
public class BoardServiceImpl implements BoardService {
    //4. 클래스 사용할 때? 객체가 필요하다.
    // new를 해야한다는 의미
    // Autowired가 붙으면 이것을 자동으로 연결해줌
    // CONSTRUCTOR -> default로 생성자
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public void register(Board board) throws Exception {
        boardRepository.create(board);
    }
    // 자료구조 쓰는 것
    @Override
    public List<Board> list() throws Exception {
        return boardRepository.list();
    }
    @Override
    public Board read(Integer boardNo) throws Exception {
        return boardRepository.read(boardNo);
    }

    @Override
    public void remove(Integer boardNo) throws Exception {
     boardRepository.remove(boardNo);
    }
    //  보서비스로 요청 ->리파지토리 -> db접근 -> 쿼리 날려서->컨트롤

    @Override
    public void modify(Board boardNo) throws Exception {
        boardRepository.modify(boardNo);
    }
}

