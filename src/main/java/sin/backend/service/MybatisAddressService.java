package sin.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sin.backend.domain.Address;
import sin.backend.mapper.AddressMapper;

import java.util.List;

//@Service
public class MybatisAddressService implements AddressService {

    private final AddressMapper mapper;
    //@Autowired
    public MybatisAddressService(AddressMapper mapper){
        this.mapper=mapper;
    }

    @Override
    public List<Address> listS() {
        pln("@listS() by mapper");

        return mapper.list();
    }

    @Override
    public Address insertS(Address address) {// 왜 이렇게 하냐면 rdate를 뽑아내기 위해서이다 mapper.insertSelectKey(address)에는 rdate가 없다
        pln("@insertS() by mapper");
        mapper.insertSelectKey(address);
        long seq = address.getSeq();// 원래 seq자리가 -1인데 seq를 미리 만들어서 가져와서 {seq, name, addr..}이런식으로 적어주기위해
        //seq를 유지시키기 위해 쓰는 방법이다 (AddressMapper.xml 보면서 참고)
        pln("@insertS() address.getSeq(): " + seq);// 결국 seq도 셋팅을 해주는 것이다 -1이 아니고
        address = mapper.selectBySeq(seq);// Address DB에다가 셋팅하기 위해 쓴다
        pln("@insertS() address: " + address);

        return address;
    }

    @Override
    public boolean deleteS(long seq) {
        pln("@deleteS() by mapper");
        mapper.delete(seq);

        return true;
    }

    void pln(String str){
        System.out.println(str);
    }
}
