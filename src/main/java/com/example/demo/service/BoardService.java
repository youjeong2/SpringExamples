package com.example.demo.service;

import com.example.demo.entity.Board;

import java.util.List;
// interface를 쓰는 이유? : 다형성
// A - test(), B - test(). C -test()
//  각각의 이름을 통일하면서 사용방식은 다르게 사용
// 동일한 게시판 서비스를 여러개 다룰 때 일관되게 처리가 가능
// 데이터 처리의 일관성을 제공해줄 수 있다.
public interface BoardService {
    // DB를 처리할 때 오류가 발생할 수 있으므로
    // 예외처리 루틴에게 해당 부분의 처리를 위임
    public void register(Board board) throws Exception;
    // list 는 목록, read는 읽어온것 보여주는 것
    public List<Board> list() throws Exception;
    public Board read(Integer boardNo) throws Exception;
    public void remove(Integer boardNo) throws Exception;
    public void modify(Board boardNo) throws Exception;
}
