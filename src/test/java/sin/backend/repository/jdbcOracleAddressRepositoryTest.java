package sin.backend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.backend.domain.Address;

import java.util.List;

@SpringBootTest// 객체를 스프링이 알아서만든다
class jdbcOracleAddressRepositoryTest {
    @Autowired// repository를 주입시켜줘야 한다
    JdbcOracleAddressRepository repository;// estGetConnection()를 테스트 해주기위해 이렇게 쓴다(jdbcOracleAddressRepository의 객체를 얻어온걸 확인)

    @Test
        void testGetConnection() {// test를 붙여주는것이 관례
            pln("repository: " + repository);// repository 객체가 잘 생성 되었는지
            pln("#con: " + repository.getConnection());// DB랑 잘 연결되어있는지 봐주는 부분
        }

        @Test
        void testList() {
            List<Address> list = repository.list();
            pln("#list: " + list);
        }

        @Test
        void testInsert() {
            Address address = new Address(-1L, "현주", "부산", null);
            Address addressDB = repository.insert(address);
            if (addressDB != null) {
                pln("#addressDB: " + addressDB);
            } else {
                pln("Insert failed.");
            }

        }

        @Test
        void testDelete() {
            boolean flag = repository.delete(5);
            pln("#flag: " + flag);
        }

    void pln(String str) {
        System.out.println(str);
    }

}