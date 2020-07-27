package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
// 6. DB 처리에 대한 어노테이션(Component)
@Repository
// BoardDataAccessObject(BoardDAO)
public class BoardRepository {
    static final Logger log =
            LoggerFactory.getLogger(BoardRepository.class);
// 7.jdbcTemplate은 DB Query를 생성하는 데 활용한다. ->insert, select
    @Autowired
    private JdbcTemplate jdbcTemplate;
// 8. insert는 데이터를 입력함
// insert는 데이터를 입력함
// board는 create table로 만든 내용
// board에 있는 title, content, writer에
// 특정값 3 개를 삽입하기 위해 ?, ?, ?를 셋팅한 상태
    public void create (Board board) throws Exception{
    String query = "insert into board(" +
            "title, content, writer) values(?, ?, ?)";
        // 아래 getter를 이용해서 ? 부분들의 값을 채운다.
        // 즉 물음표가 2개면 getter도 2개를 사용하면 된다.
        jdbcTemplate.update(query, board.getTitle(),
                board.getContent(), board.getWriter());
}

    public List<Board> list() throws Exception {
        log.info("Repository list()");
        // select는 값을 선택해오는 녀석이고
        // board, title ... 등등은 board에 있는 정보들
        // 조건을 줄 때 where를 사용한다.
        // board_no가 0 보다 크다는 조건을 가지고 있음
        // order by는 정렬 조건에 해당한다.
        // board_no, reg_date에 대해 정렬 조건을 줬음
        List<Board> results = jdbcTemplate.query(
                "select board_no, title, content, " +
                        "writer, reg_date from board " +
                        "where board_no > 0 order by " +
                        "board_no desc, reg_date desc",
                //9. RowMapper board에 대한 행값을 뽑아옴
                // 메모리 할당 위해 new
                // 타이틀 값을 읽어와서 데이터
                // 세팅이 다 되었으면 세팅하세요 -> 모든 정보는 result에
                // 리턴한 보드로
                // 서비스 리스트에 db다 가져오게 됨
                new RowMapper<Board>() {
                    @Override
                    public Board mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Board board = new Board();
                        board.setBoardNo(rs.getInt("board_no"));
                        board.setTitle(rs.getString("title"));
                        board.setContent(rs.getString("content"));
                        board.setWriter(rs.getString("writer"));
                        board.setRegDate(rs.getDate("reg_date"));
                        return board;
                    }
                });

        return results;
    }
}
