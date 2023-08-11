package sin.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sin.backend.domain.Address;

import java.util.List;

@Mapper// Mapper이라는 것을 이용하는
@Repository// Repository이다
public interface AddressMapper {

    List<Address> list();
    Address selectBySeq(long seq);// AddressMapper.xml을 보고 쓰는 것이다
    void insertSelectKey(Address address);// 리턴값(result type)이 없으니까 void

    void delete(long seq);

}
