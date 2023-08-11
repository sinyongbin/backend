package sin.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sin.backend.domain.Address;
import sin.backend.service.AddressAjaxService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("ajax4")
@RestController// 모든 메서드를 JSON이나 STRING로 리턴하고 싶을 때 사용
// JSP를 리턴하고 싶으면 @Controller와 각 메서드마다 @ResponseBody를 써주고 리턴에서
// ""를 붙이지 말아야 한다

public class AjaxTest4Controller {
    private AddressAjaxService addressAjaxService;

    @GetMapping("search1.do")
    //@ResponseBody// @RestController을 쓰면 생략이 가능하다
    public Address search1(long seq) {
        // AddressAjaxService를 통해 주어진 seq에 해당하는 주소를 조회하여 반환하는 메서드
        Address address = addressAjaxService.getBySeqS(seq);
        return address;// 조회된 주소 객체를 JSON 형태로 반환
    }


    @PostMapping("search2.do")
    public List<Address> search2(String name) {
        // AddressAjaxService를 통해 주어진 이름에 해당하는 주소 리스트를 조회하여 반환하는 메서드
        List<Address> list = addressAjaxService.getListByNames(name);
        return list;// 조회된 주소 리스트를 JSON 형태로 반환
    }


    @GetMapping("txt")
    public String getText() {
        return "good"; // "good" 문자열을 반환하여 JSON 형태로 출력
    }

}
