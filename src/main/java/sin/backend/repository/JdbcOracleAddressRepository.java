package sin.backend.repository;
// 구현 클래스(DAO)

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import sin.backend.domain.Address;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Repository
public class JdbcOracleAddressRepository implements AddressRepository {
    //@Autowired// ( 의존관계를 주입시킨다 )
    private final DataSource dataSource;
    //@Autowired// 해당되는 객체가 있다면 객체를 자동으로 주입시켜라
    public JdbcOracleAddressRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }
    Connection getConnection(){
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection con) throws SQLException {
        DataSourceUtils.releaseConnection(con, dataSource);
    }

    @Override
    public List<Address> list() {
        ArrayList<Address> list = new ArrayList<>();
        String sql = "select * from ADDRESS order by NAME";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                long seq = rs.getLong(1);
                String name = rs.getString(2);
                String addr = rs.getString(3);
                Date rdate = rs.getDate(4);
                list.add(new Address(seq, name, addr, rdate));
            }
            return list;
        }catch(SQLException se){
            return null;
        }finally{
            close(con, stmt, rs);
        }
    }
    private void close(Connection con, Statement stmt, ResultSet rs){
        try{
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(con != null) con.close();
        }catch(SQLException se){}
    }

    @Override
    public Address insert(Address address) {
        String sql = "insert into ADDRESS values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);// RETURN_GENERATED_KEYS가 집어넣은 키값(ADDRESS_SEQ.nextval)을 리턴해준다
            pstmt.setString(1, address.getName());
            pstmt.setString(2, address.getAddr());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();// 집어넣은 객체를 다시 가져온다

            // rs = pstmt.getGeneratedKeys();는 INSERT 작업으로 생성된 키값을 조회하는데 사용되며, 이를 통해 새로 삽입된 레코드의 기본 키값을 가져올 수 있다
            if(rs.next()){
                String rowid = rs.getString(1); //rs.getString("ROWID");는 안되네?
                // 여기서는 자동으로 생성된 키(ADDRESS_SEQ.nextval) 하나의 값을 갖는 것, rs.getString(1)을 사용하여 첫 번째 컬럼 값을 가져와서 생성된 키 값을 가져
                Address addressDb = getAddress(rowid);
                return addressDb;
            }else{
                return null;// 데이터가 들어가지 않았다
            }
        }catch(SQLException se){
            return null;
        }finally {
            close(con, pstmt, rs);
        }
    }
    private Address getAddress(String rowid){// rowid가 한 row전체를 불러온다
        String sql = "select * from ADDRESS where ROWID=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, rowid);

            rs = pstmt.executeQuery();
            if(rs.next()){
                long seq = rs.getLong(1);
                String name = rs.getString(2);
                String addr = rs.getString(3);
                Date rdate = rs.getDate(4);
                return new Address(seq, name, addr, rdate);
            }else{
                return null;
            }
        }catch(SQLException se){
            return null;
        }finally{
            close(con, pstmt, rs);
        }
    }

    @Override
    public boolean delete(long seq) {
        String sql = "delete from ADDRESS where SEQ=?";
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
            return false;
        }finally {
            close(con, pstmt, rs);
        }
    }
}
