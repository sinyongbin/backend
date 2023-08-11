package sin.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import sin.backend.domain.Address;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class JdbcTemplateOracleAddressRepository implements AddressRepository {
    private final JdbcTemplate jdbcTemplate;

    //@Autowired
    public JdbcTemplateOracleAddressRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //@Override
    public List<Address> list() {
        String sql = "select * from ADDRESS order by SEQ desc";
        List<Address> list = jdbcTemplate.query(sql, addressRowMapper());
        return list;
    }
    private RowMapper<Address> addressRowMapper(){
        return new RowMapper<Address>() {
            @Override
            public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
                Address address = new Address();
                address.setSeq(rs.getLong("seq"));
                address.setName(rs.getString("name"));
                address.setAddr(rs.getString("addr"));
                address.setRdate(rs.getDate("rdate"));
                return address;
            }
        };
    }
    @Override
    public Address insert(Address address) {
        long seq = jdbcTemplate.queryForObject("select ADDRESS_SEQ.nextval from dual", Long.class);
        // select ADDRESS_SEQ.nextval from dual을 하면 nextval이 나온다(강제 seq를 늘려서 거기에 row를 형성한다)
        String sql = "insert into ADDRESS values(?, ?, ?, SYSDATE)";
        jdbcTemplate.update(sql, seq, address.getName(), address.getAddr());
        address.setSeq(seq);

        return address;
    }
    /*
    @Override
    public Address insert(Address address) {
        //String sql = "insert into ADDRESS values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";// 필요없다
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("ADDRESS").usingGeneratedKeyColumns("SEQ");// primary key 컬럼

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", address.getName());
        parameters.put("addr", address.getAddr());
        parameters.put("rdate", LocalDate.now());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        System.out.println("#key.longValue(): " + key.longValue());
        address.setSeq(key.longValue());

        return address;
    }
    */

    /*
    @Override
    public boolean insert(Address address){// boolean을 리턴할 경우 ..
        String sql = "insert into ADDRESS values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";
        int conunt= jdbcTemplate.update(sql, address.getName(), address.getAddr());
        if(count>0){
            return true;
        }else {
            return false;
        }
    }
    */

    @Override
    public boolean delete(long seq) {
        String sql = "delete from ADDRESS where SEQ=?";
        int count = jdbcTemplate.update(sql, seq);// 삭제된 row의 갯수 리턴
        if(count>0) return true;
        else return false;

    }
}
