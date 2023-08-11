package sin.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sin.backend.domain.Address;
import sin.backend.service.AddressAjaxService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("auto")
@Controller
public class AutoController {
    private AddressAjaxService addressAjaxService;

    @GetMapping("auto.do") // 주로 데이터 조회에 사용
    public String showView() {
        return "auto/autocomplete";// jsp를 리턴
    }
    @PostMapping("autoData.do")//  주로 데이터 생성, 수정, 삭제 등에 사용
    public @ResponseBody List<Address> getAddressList(String writer){// Json으로 리턴하기 위해
        System.out.println("@@AutoController getAddressList() writer: " + writer);
        List<Address> list = addressAjaxService.getListByNames(writer);
        return list;
    }

}
