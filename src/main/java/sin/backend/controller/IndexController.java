package sin.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
    @GetMapping("")// "index.do"를 적어줘도 되지만 없는게 더 바람직하다 왜냐하면 http://127.0.0.1:8080/이렇게만 적어도 호출이 되기 위해서
    public String index() {
        return "index";
    }
}