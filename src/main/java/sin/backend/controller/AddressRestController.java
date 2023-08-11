package sin.backend.controller;
// Ajax 잘 되는지 테스트
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sin.backend.domain.Address;
import sin.backend.service.AddressAjaxService;

import java.util.List;


@AllArgsConstructor
@RequestMapping("rest_addr")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)//매우 중요!!! 외부에서 접근할때 이걸 써주지 않으면 이 Api를 사용할 수 없게 된다
public class AddressRestController {
    private AddressAjaxService addressAjaxService;

    //(1) CRUD에서 Create( SQL에서는 insert ) --> insert는 다 post방식이다
    @GetMapping("create1")
    public void create1(Address address){// 파라미터를 jsObj로 받을 때
        //pln("@@AddressRestController create1() address: " + address);
        addressAjaxService.insertS(address);
    }
    //@GetMapping("create1")로 테스팅
    //http://127.0.0.1:8080/rest_addr/create1?name=오늘은&addr=수요일

    @PostMapping("create2")
    public void create2(@RequestBody Address address){// url창에서 파라미터를 JSON으로 받을때는 @RequestBody를 써준다
        addressAjaxService.insertS(address);
    }
    //http://127.0.0.1:8080/rest_addr/create2
    //{"seq":-1, "name":"현동", "addr":"오예스"}
    //{"name":"현동2", "addr":"오예스2"}


    //(2) Read( select ) --> select는 무조건 get방식이다
    @GetMapping("read")
    public List<Address> read(){
        List<Address> list = addressAjaxService.listS();
        return list;
    }
    //http://127.0.0.1:8080/rest_addr/read


    @GetMapping("read/{seq}")
    public Address read( @PathVariable long seq ) {// @PathVariable를 써주면 pk 값만 넣어주면 된다
        Address address = addressAjaxService.getBySeqS(seq);
        return address;
    }
    //http://127.0.0.1:8080/rest_addr/read/70


    @GetMapping(value="read", params={"na"})
    public List<Address> read(@RequestParam("na") String name) {
        List<Address> list = addressAjaxService.getListByNames(name);
        return list;
    }
    //http://127.0.0.1:8080/rest_addr/read?na=신

    //아래의 방법도 가능은 하지만.. @GetMapping("read/{seq}")와 공존할 순 없음
    /*@GetMapping("read/{na}")
    public List<Address> read(@PathVariable String na){
        List<Address> list = service.getListByNames(na);
        return list;
    }*/


    //(3) delete

    @DeleteMapping("delete/{seq}")// @PathVariable 덕분에 delete?seq=70 이런식으로 안쓰고 delete/70으로 써진다
    public void delete(@PathVariable long seq) {
        addressAjaxService.deleteS(seq);
    }
    //http://127.0.0.1:8080/rest_addr/delete/70

    private void pln(String str){
        System.out.println(str);
    }
}
