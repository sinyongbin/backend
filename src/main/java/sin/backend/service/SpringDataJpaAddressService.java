package sin.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sin.backend.domain.Address;
import sin.backend.repository.SpringDataJpaOracleAddressRepository;

import java.util.List;

//@Service

public class SpringDataJpaAddressService implements AddressService {
    //@Autowired
    public final SpringDataJpaOracleAddressRepository repository;
    //@Autowired
    public SpringDataJpaAddressService(SpringDataJpaOracleAddressRepository repository) {
        this.repository=repository;
    }
    @Override
    public List<Address> listS() {
        pln("@listS() by SpringDataJpa");
        // return repository.findAll();// 기본셋팅인 오름차순으로 정렬
        return repository.findAll(Sort.by(Sort.Direction.DESC, "seq"));// 내림차순으로 정렬해주기 위해서 썼다
        //return repository.findByName("신용빈");// 검증 완료!!
        //return repository.findByNameAndAddr("신용빈", "서울시");// 검증 완료!!
        //return repository.findByNameContaining("홍");// 검증 완료!!
    }

    @Override
    public Address insertS(Address address) {
        pln("@insertS() by SpringDataJpa");
        address = repository.save(address);
        pln("@inserS() address: " + address);// seq와 date까지 있다 위의 address와는 다르다 ==> 이부분 질문 왜그런지 정확히 모름!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        return address;
    }

    @Override
    public boolean deleteS(long seq) {
        pln("deleteS() by SpringDataJpa");
        repository.deleteById(seq);

        return true;
    }

    public List<Address>findByName(String name) {// Repository 에서 정의한것을 가져다 쓴다, Service메서드나 Repository메서드나 이름을 같게해도 상관이 없다!! (현재 findByName으로 동일)
        List<Address> list = repository.findByName(name);
        pln("@findByName() by SpringDataJpa list: " + list);

        return list;
    }

    public List<Address>findByNameAndAddr(String name, String addr) {
        List<Address> list = repository.findByNameAndAddr(name, addr);
        pln("@findByNameAndAddr() SpringDataJpa list: " + list);

        return list;
    }

    public List<Address> findByNameContaining(String name) {
        List<Address> list = repository.findByNameContaining(name);
        pln("@findByNameContaining() by StringDateJpa list: " + list);

        return list;
    }

    void pln(String str) {
        System.out.println(str);
    }
}
