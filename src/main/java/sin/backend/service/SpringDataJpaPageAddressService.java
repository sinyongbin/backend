package sin.backend.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.backend.domain.Address;
import sin.backend.dto.AddressListResult;
import sin.backend.repository.SpringDataJpaOracleAddressRepository;

//@Transactional(readOnly = true) //DML커밋을 막음
@Transactional
@RequiredArgsConstructor
@Service
public class SpringDataJpaPageAddressService implements PageAddressService {
    @Autowired
    private final SpringDataJpaOracleAddressRepository repository;

    @Override
    public Page<Address> findAll(Pageable pageable) {
        pln("@findAll() pageable: " + pageable);
        return repository.findByOrderBySeqDesc(pageable);
    }
    @Override
    public AddressListResult getAddressListResult(Pageable pageable) {
        Page<Address> list = findAll(pageable);
        int page = pageable.getPageNumber();
        long totalCount = repository.count();
        int size = pageable.getPageSize();
        pln("@getAddressListResult() page: "
                +page+", totalCount: "+totalCount+", size: "+size);

        return new AddressListResult(page, totalCount, size, list);
    }
    @Override
    public Address insertS(Address address) {
        address = repository.save(address);
        return address;
    }
    @Override
    public void deleteS(long seq) {
        repository.deleteById(seq);
    }

    void pln(String str){
        System.out.println(str);
    }
}
