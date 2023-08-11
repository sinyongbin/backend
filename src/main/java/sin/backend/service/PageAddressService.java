package sin.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sin.backend.domain.Address;
import sin.backend.dto.AddressListResult;

import java.util.List;

public interface PageAddressService {
    Page<Address> findAll(Pageable pageable);
    AddressListResult getAddressListResult(Pageable pageable);
    Address insertS(Address address);
    void deleteS(long seq);

}
