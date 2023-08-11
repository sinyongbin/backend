package sin.backend;
// 수동으로 원하는 파일로 가서 주석처리를 해준다 (//@Service, //Repository)와 같이
// 수동의 장점은 쓰던 서비스를 주석처리 하고 새로운 서비스 객체를 등록해주면 된다
// DI (의존성 주입) --> 완벽하게 역할을 분담해 놓을 수 있다(분업)
// springconfig
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sin.backend.mapper.AddressMapper;
import sin.backend.mapper.BoardMapper;
import sin.backend.repository.*;
import sin.backend.service.*;

import javax.sql.DataSource;

@Configuration// 해당 클래스가 스프링의 설정 정보를 포함하는 클래스임을 나타내는 역할
public class SpringConfig {
    @Autowired
    DataSource dataSource;// JDBC + JDBCTemplate

    @Autowired
    EntityManager emaddress;// JPA

    @Autowired
    EntityManager emboard;// JPA

    @Autowired
    AddressMapper addressmapper;// MyBatis

    @Autowired
    BoardMapper boardmapper;// MyBatis

    @Autowired
    SpringDataJpaOracleAddressRepository addressrepository;// SpringDataJpa를 구현한 객체를 인터페이스 안에 넣어준 것

    @Autowired
    SpringDataJpaOracleBoardRepository boardrepository;

    /*
    @Bean// 개발자가 직접 스프링 컨테이너에 빈 객체를 등록할 수 있다
    public AddressRepository addressRepository() {// JDBC + JDBCTemplate + JPA 이렇게 3가지 메서드가 쓰인다
        //return new JdbcOracleAddressRepository(dataSource);// 이걸 쓰면 name 내림차순 기준으로 리턴
        //return new JdbcTemplateOracleAddressRepository(dataSource);// 이걸 쓰면 seq 내림차순 기준으로 리턴
        // 이게 Repository이기 때문에 충돌이 난다 따라서 @Repository를 전부 주석처리 해준다
        return new JpaOracleAddressRepository(emaddress);
    }
    */

    @Bean// 바로 위와 같은 뿐만 필요하다(Mybatis)
    public AddressService addressService() {// JDBC + MyBatis + JPA + SpringDataJpa
        //return new JdbcOracleAddressService(addressRepository());// JDBC
        //return new MybatisAddressService(addressmapper);
        //return new JpaAddressService(addressRepository());
        return new SpringDataJpaAddressService(addressrepository);
    }


// -------------------------------------------------------------------------------------------------------------------

    /*
    @Bean
    public BoardRepository boardRepository() {// JDBC + JDBCTemplate + JPA 이렇게 3가지 메서드가 쓰인다
        //return new JdbcOracleBoardRepository(dataSource);
        //return new JdbcTemplateOracleBoardRepository(dataSource);
        return new JpaOracleBoardRepository(emboard);
    }
     */

    @Bean
    public BoardService boardService() {// JDBC + MyBatis + JPA + SpringDataJpa
        //return new JdbcOracleBoardService(boardRepository());
        //return new MybatisBoardService(boardmapper);
        //return new JpaBoardService(boardRepository());
        return new SpringDataJpaBoardService(boardrepository);
    }
}