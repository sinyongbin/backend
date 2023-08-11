package sin.backend.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import sin.backend.domain.Address;

import java.util.List;

//@Repository
public class JpaOracleAddressRepository implements AddressRepository {
    //@Autowired
    private final EntityManager em;

    //@Autowired
    public JpaOracleAddressRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Address> list() {
        List<Address> list = em.createQuery("select a from Address a ", Address.class)
                .getResultList();

        return list;
    }

    @Override
    public Address insert(Address address) {// insert, update, delete 기능은 jpql이 필요없음
        em.persist(address);// persist의 메서드 이름이 중요함 !!!

        return address;
    }

    public List<Address> findByName(String name) {
        List<Address> list = em.createQuery("select a from Address a where a.name=:name", Address.class)
                .setParameter("name", name)//왜 setParameter을 하냐면 변수 name으로 select문 안에 name(String값)에 대입불가
                .getResultList();
        return list;
    }

    public Address findBySeq(long seq) {//필요에 의해 추가되는 메서드(address를 리턴하기 위해서)
        Address address = em.find(Address.class, seq);//하나의 address가 튀어나온다

        return address;
    }

    @Override
    public boolean delete(long seq) {// interface에 void delete(long seq) 또는 Address delete(long seq)로 리턴할걸
        Address address = findBySeq(seq);
        em.remove(address);

        return true;
    }
}
