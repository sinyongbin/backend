package sin.backend.service;

import sin.backend.domain.Address;

import java.util.List;

public interface AddressService {
    List<Address> listS();
    Address insertS(Address address);
    boolean deleteS(long seq);
}
