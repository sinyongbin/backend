package sin.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sin.backend.domain.Address;
import sin.backend.repository.AddressRepository;

import java.util.List;

//@Service
@Transactional// 추가( jpa는 트렌젝션 안에서 사용해야 한다 )
public class JpaAddressService implements AddressService {
    //@Autowired
    private final AddressRepository repository;
    //@Autowired
    public JpaAddressService(AddressRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Address> listS() {
        pln("@listS() by Jpa");
        return repository.list();
    }

    @Override
    public Address insertS(Address address) {
        pln("@insertS() by Jpa: (Before) address: " + address);
        address = repository.insert(address);
        pln("@insertS() by Jpa: (after) address: " + address);// DB에 넣었다가 다시 가져오는 것이기 때문에 seq와 date도 포함
        pln("@insertS() by Jpa: seq: " + address.getSeq());
        pln("@insertS() by Jpa: rdate: " + address.getRdate());

        return address;
    }

    @Override
    public boolean deleteS(long seq) {
        pln("@deleteS() by jpa");
        return repository.delete(seq);
    }

    void pln(String str) {
        System.out.println(str);
    }
}
