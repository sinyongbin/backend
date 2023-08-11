package sin.backend.repository;
// Spring에서 인터페이스가 중간다리 역할을 한다
import sin.backend.domain.Address;

import java.util.List;

public interface AddressRepository {
    List<Address> list();
    Address insert(Address address);
    boolean delete(long seq);
}

