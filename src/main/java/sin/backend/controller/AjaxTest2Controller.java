package sin.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sin.backend.domain.Address;
import sin.backend.service.AddressAjaxService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@AllArgsConstructor
@RequestMapping("ajax2")
@Controller
public class AjaxTest2Controller {
    private AddressAjaxService addressAjaxService;
    @GetMapping("search1.do")
    public void search1(long seq, HttpServletResponse response){// response은 브라우저 객체의 출력 스트림을 뽑아내기 위해서 필요하다
        Address address = addressAjaxService.getBySeqS(seq);
        //pln("#AjaxTest1Controller search1() address: " + address);

        ObjectMapper om = new ObjectMapper();
        try{
            String addressJson = om.writeValueAsString(address);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.println(addressJson);
        }catch(JsonProcessingException je){
        }catch(IOException ie){}
    }
    @PostMapping("search2.do")
    public void search2(String name, HttpServletResponse response){
        List<Address> list = addressAjaxService.getListByNames(name);
        //pln("#AjaxTest1Controller search2() list: " + list);

        ObjectMapper om = new ObjectMapper();
        try {
            String addressJson = om.writeValueAsString(list);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.println(addressJson);
        }catch(JsonProcessingException je) {
        }catch(IOException ie) {}
    }

    void pln(String str){
        System.out.println(str);
    }

}
