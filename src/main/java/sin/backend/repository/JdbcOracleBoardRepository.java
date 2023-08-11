package sin.backend.repository;
// DAO: DB에서 SQL문의 데이터를 가져오는 역할(DB랑 관련)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import sin.backend.domain.Address;
import sin.backend.domain.Board;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Repository
public class JdbcOracleBoardRepository implements BoardRepository {
    //@Autowired
    private final DataSource dataSource;

    //@Autowired
    public JdbcOracleBoardRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }
    Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection con) throws SQLException {
        DataSourceUtils.releaseConnection(con, dataSource);
    }

    private void close(Connection con, Statement stmt, ResultSet rs){// close메서드를 하나 따로 만들어줌 쓰기쉽게
        try{
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        }catch(SQLException se){}
    }

    @Override
    public List<Board> list() {
        ArrayList<Board> listb = new ArrayList();
        String sql = "select * from BOARD order by SEQ desc";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                long seq = rs.getLong(1);
                String writer = rs.getString(2);
                String email = rs.getString(3);
                String subject = rs.getString(4);
                String content = rs.getString(5);
                Date rdate = rs.getDate(6);
                Date udate = rs.getDate(7);
                listb.add(new Board(seq, writer, email, subject, content, rdate, udate));
                pln("wirter: " + writer);
            }
            return listb;
        }catch(SQLException se) {
            return null;
        }finally {
            close(con, stmt, rs);
        }
    }

    @Override
    public Board insert(Board board) {
        String sql = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE, SYSDATE)";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, board.getWriter());
            pstmt.setString(2, board.getEmail());
            pstmt.setString(3, board.getSubject());
            pstmt.setString(4, board.getContent());
            pstmt.executeUpdate();

        }catch(SQLException se){
            return null;
        }finally {
            close(con, pstmt, rs);
        }
        return null;
    }
    @Override
    public Board content(long seq){
        String sql = "select * from BOARD where SEQ=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, seq);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                seq = rs.getLong(1);
                String writer = rs.getString(2);
                String email = rs.getString(3);
                String subject = rs.getString(4);
                String content = rs.getString(5);
                Date rdate = rs.getDate(6);
                Date udate = rs.getDate(7);
                Board board = new Board(seq, writer, email, subject, content, rdate, udate);
                return board;
            }
        }catch(SQLException se){
            return null;
        }finally {
            close(con, pstmt, rs);
        }
        return null;
    }

    @Override
    public void update(Board board) {
        String sql = "update BOARD set email=?, subject=?, content=? where SEQ=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, board.getEmail());
            pstmt.setString(2, board.getSubject());
            pstmt.setString(3, board.getContent());
            pstmt.setLong(4, board.getSeq());
            pstmt.executeUpdate();

        }catch(SQLException se){

        }finally {
            close(con, pstmt, rs);
        }
    }

    @Override
    public boolean delete(long seq) {
        String sql = "delete from BOARD where SEQ=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, seq);
            int i = pstmt.executeUpdate();
            if(i>0) return true;
            else return false;
        }catch(SQLException se){

        }finally {
            close(con, pstmt, rs);
        }
        return false;
    }
    void pln(String str){
        System.out.println(str);
    }
}
