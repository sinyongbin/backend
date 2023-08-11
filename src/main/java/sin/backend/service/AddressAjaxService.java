package sin.backend.service;

import sin.backend.domain.Address;

import java.util.List;

public interface AddressAjaxService {

    List<Address> listS();
    Address insertS(Address address);
    boolean deleteS(long seq);


    //for Ajax
    Address getBySeqS(long seq); // seq 값을 사용하여 데이터베이스에서 해당 seq에 해당하는 주소 정보를 조회하는 기능을 수행
    List<Address> getListByNames(String name);// name 값으로 데이터베이스에서 해당 이름을 가진 주소 목록을 조회하는 기능을 수행

}
