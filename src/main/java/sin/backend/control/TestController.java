package sin.backend.control;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sin.backend.control.domain.Human;// domain패키지의 Human클래스로 import
import sin.backend.control.domain.HumanList;
import sin.backend.control.domain.ToDo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RequestMapping("test")// test/base1.do 에서 test를 공통분모로 만들어준다
@Controller
public class TestController {
    @RequestMapping("")
    public void m00(){
        pln("#m00(): default URL");
    }
    @RequestMapping("/base1")// test/base1을 만들어준다
    public void m01(){
        pln("#m01(): Get, Post, Put , Delete, .. 지원");
    }
    @RequestMapping(value = "/base2", method = RequestMethod.GET)//@GetMapping("/base2")
    public void m02(){
        pln("#m02(): only get방식");
    }
    @RequestMapping(value = "/base3", method = {RequestMethod.GET, RequestMethod.POST})
    public void m03(){
        pln("#m03(): only get방식 & Post방식");
    }
    @GetMapping(value = "/param1")
    public void m04(Human dto){//이렇게 하면 알아서 판단을 해준다
        pln("#m04(): dto: " + dto);
    }
    @GetMapping(value = "/param2")
    public void m05(@RequestParam("name") String na, @RequestParam("age") int a){//이렇게 하면 알아서 판단을 해준다
        pln("#m05(): na: " + na + ", a: " + a);
    }
    @GetMapping(value = "/param3")
    public void m06(@RequestParam ArrayList<String> names){//이렇게 하면 알아서 판단을 해준다
        pln("#m06(): names: " +names);
    }
    @GetMapping(value = "/param4")
    public void m07(@RequestParam("ns") ArrayList<String> names){//이름 여러개 들어와도 그대로 출력
        pln("#m07(): names: " + names);
    }
    @GetMapping("/param5")
    public void m08(@RequestParam String[] names) {
        pln("#m08(): names: " +names);// 그냥 배열명으로 출력시 주소값이 나온다
        for(String name: names) pln("name: " + name);
    }
    @GetMapping("/param6")
    public void m09(HumanList list) {
        pln("#m09(): list: " + list);
    }
    @GetMapping("/param7")
    public void m10(HumanList dto, @RequestParam int page) {
        pln("#m10(): dto: " + dto + ", page: " + page);
    }
    @RequestMapping("/param8")
    public void m11(ToDo dto) {
        pln("#m11() - dto: " + dto);

        Date d = dto.getCdate();
        Calendar c  = Calendar.getInstance();
        c.setTime(d);

        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH) + 1;
        int dt = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR);
        int mi = c.get(Calendar.MINUTE);
        int s = c.get(Calendar.SECOND);

        pln(y + "년 " + m + "월 " + dt + "일 "+ h + ":"+ mi +":" + s);
    }
    @GetMapping("/json1")// 예전 방식이라서 안씀
    public ResponseEntity<String> m12() {// ResponseEntity 객체를 사용하여 HTTP 응답을 정의
        String msg = "{\"name\":\"홍길동\", \"age\":20}"; //JSON
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=utf-8");

        pln(msg + headers + HttpStatus.OK);
        return new ResponseEntity<String>(msg, headers, HttpStatus.OK);

    }
    @GetMapping("/json2")
    public @ResponseBody Human m13(){// @ResponseBody 어노테이션이 붙어있으므로 객체 자체가 HTTP 응답으로 반환
        Human man = new Human("강감찬", 50);
        return man;
    }

    void pln(String str){
        System.out.println(str);
    }
}
