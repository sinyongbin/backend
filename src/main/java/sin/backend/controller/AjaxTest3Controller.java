package sin.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sin.backend.domain.Address;
import sin.backend.service.AddressAjaxService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@AllArgsConstructor
@RequestMapping("ajax3")
@Controller
// @Controller 어노테이션을 사용하여 웹 페이지를 반환하는 뷰 컨트롤러를 정의
// 하지만 @RestController 어노테이션을 사용하면 웹 페이지를 반환하는 것이 아니라,
// API 엔드포인트로 사용되는 데이터를 반환하는 컨트롤러를 정의
// @Controller 어노테이션을 사용할 때는 주로 JSP 뷰를 반환하고, @ResponseBody 어노테이션을 사용할 때는 JSON, XML 등의 데이터를 반환!!!
public class AjaxTest3Controller {
    private AddressAjaxService addressAjaxService;

    @GetMapping("search1.do")
    @ResponseBody// String을 리턴할 때 써준다
    public Address search1(long seq) {
        Address address = addressAjaxService.getBySeqS(seq);
        return address;
    }

    @PostMapping("search2.do")
    @ResponseBody
    public List<Address> search2(String name) {
        List<Address> list = addressAjaxService.getListByNames(name);
        return list;
    }

    void pln(String str) {
        System.out.println(str);
    }
}
