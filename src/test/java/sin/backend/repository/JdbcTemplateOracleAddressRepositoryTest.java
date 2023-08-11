package sin.backend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import sin.backend.domain.Address;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest// Test클래스인지 알기위해
class JdbcTemplateOracleAddressRepositoryTest {
    @Autowired
    JdbcTemplateOracleAddressRepository repository;


    @Test
    void getRepository(){
        pln("#repository: " + repository);
    }

    @Test
    void list() {
        List<Address> list = repository.list();
        pln("#list: " + list);
    }

    @Test
    void insert() {
        Address address = new Address(-1L, "현주", "부산", null);
        Address addressDB = repository.insert(address);
        if (addressDB != null) {
            pln("#addressDB: " + addressDB);
        } else {
            pln("Insert failed.");
        }
    }


    @Test
    void delete() {
        boolean flag = repository.delete(17);
        pln("#flag: " + flag);

    }
    private void pln(String str){
        System.out.println(str);
    }
}