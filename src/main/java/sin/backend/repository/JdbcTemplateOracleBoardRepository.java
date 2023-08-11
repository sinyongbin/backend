package sin.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import sin.backend.domain.Address;
import sin.backend.domain.Board;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Repository
public class JdbcTemplateOracleBoardRepository implements BoardRepository {

    private final JdbcTemplate jdbcTemplate;
    //@Autowired
    public JdbcTemplateOracleBoardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);// 원래는 this가 생략되어 있다
    }


    @Override
    public List<Board> list() {
        String sql = "select * from BOARD order by SEQ asc";
        List<Board> list = jdbcTemplate.query(sql, boardRowMapper());
        return list;// list로 리턴을 받는건 정보들을 쭉 반환받는 것
    }

    private RowMapper<Board> boardRowMapper(){// list는 RowMapper이 필요?? RowMapper는 데이터베이스의 반환 결과인 ResultSet을 객체로 변환해주는 클래스
        return new RowMapper<Board>() {
            @Override
            public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                Board board = new Board();
                board.setSeq(rs.getLong("seq"));
                board.setWriter(rs.getString("writer"));
                board.setEmail(rs.getString("email"));
                board.setSubject(rs.getString("subject"));
                board.setContent(rs.getString("content"));
                board.setRdate(rs.getDate("rdate"));
                board.setUdate(rs.getDate("udate"));
                return board;// board로 리턴을 받는것은 한판(한 seq에 해당되는 데이터)을 반환받는 것
            }
        };
    }

    /*
    private  RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setSeq(rs.getLong("seq"));
            board.setWriter(rs.getString("writer"));
            board.setEmail(rs.getString("email"));
            board.setSubject(rs.getString("subject"));
            board.setContent(rs.getString("content"));
            board.setRdate(rs.getDate("rdate"));
            board.setUdate(rs.getDate("udate"));
            return board;
        };
    }
    */

    @Override
    public Board insert(Board board) {
        long seq = jdbcTemplate.queryForObject("select BOARD_SEQ.nextval from dual", Long.class);//  조회 대상이 객체가 아닌 단순 데이터라면 타입을 아래처럼 지정
        // 형식: jdbcTemplate.queryForObject("select count(*) from t_actor",Integer.class);
        // 메소드의 인수로 객체타입만이 요구되면, 기본 타입의 데이터를 그대로 사용할 수 없다, 이때에 기본 타입의 데이터(long)를 먼저 객체로 변환(변환하기위해 Long.class 써줌)한 수 작업을 수행해야 한다

        String sql = "insert into BOARD values(?, ?, ?, ?, ?, SYSDATE, SYSDATE)";
        //String sql = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE, SYSDATE)";// 이걸 아래처럼 바꿔준다

        jdbcTemplate.update(sql, seq, board.getWriter(), board.getEmail(), board.getSubject(), board.getContent());
        board.setSeq(seq);

        return board;
    }

    @Override
    public Board content(long seq) {

        String sql = "select * from BOARD where SEQ = " + seq;
        Board board = jdbcTemplate.queryForObject(sql, boardRowMapper());// queryForObject: 한줄만 받는 것

        // (sql, boardRowMapper(), seq)는 update할때 써주면 좋음
        return board;
    }

    @Override
    public void update(Board board) {

        String sql = "update BOARD set email=?, subject=?, content=? where SEQ=?";
        int i = jdbcTemplate.update(sql, board.getEmail(), board.getSubject(), board.getContent(), board.getSeq());
        board.setSeq(board.getSeq());
        pln("i: " + i);
    }

    @Override
    public boolean delete(long seq) {
        String sql = "delete from BOARD where SEQ=?";
        int count = jdbcTemplate.update(sql, seq);
        if(count>0) return true;
        else return false;
    }
    void pln(String str){
        System.out.println(str);
    }
}
