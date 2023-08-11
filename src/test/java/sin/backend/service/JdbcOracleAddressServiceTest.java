package sin.backend.service;
// Service가 DAO랑 잘 연결되어 있는지 TEST
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.backend.domain.Address;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcOracleAddressServiceTest {
    @Autowired
    AddressService service;
    @Test
    void listS() {
        List<Address> list = service.listS();
        pln("@list: " + list);
    }
    @Test
    void insertS() {
        Address address = new Address(-1L, "수경", "서울", null);
        Address addressDb = service.insertS(address);
        pln("@addressDb: " + addressDb);
    }
    @Test
    void deleteS() {
        long seq = 5;
        boolean flag = service.deleteS(seq);
        pln("@falg: " + flag);
    }
    void pln(String str){
        System.out.println(str);
    }
}