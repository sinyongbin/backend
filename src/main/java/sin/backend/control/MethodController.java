package sin.backend.control;

import org.springframework.stereotype.Controller;// 이 클래스가 스프링 프레임워크에서 컨트롤러임을 나타내는 어노테이션
import org.springframework.ui.Model;// 스프링 프레임워크에서 제공하는 데이터를 뷰로 전달하는데 사용되는 클래스
import org.springframework.web.bind.annotation.GetMapping;// GET HTTP 요청을 처리하는 메서드임을 나타내는 어노테이션
import org.springframework.web.bind.annotation.RequestParam;// HTTP 요청 파라미터 값을 가져올 때 사용하는 어노테이션
import org.springframework.web.bind.annotation.ResponseBody;// 메서드의 반환값을 HTTP 응답 본문으로 사용하도록 나타내는 어노테이션

@Controller
public class MethodController {
    @GetMapping("template.do")
    public String m1(@RequestParam(name="name", required=false) String name, Model model){// @RequestParam(name="name", required=false)를 써주는 이유는
                                                                                          // name가 na로 바뀌면 쉽게 변경해주기 위해서
        System.out.println("name: " + name);
        model.addAttribute("name", name);//Model 객체에 데이터를 추가하면, 해당 데이터는 View로 전달되어 뷰 템플릿에서 사용(template 페이지로 추가)
        return "template";// 일반적으로는 template(jsp)을 반환
    }

    @ResponseBody// 메서드의 반환값을 HTTP 응답 본문으로 사용하도록 나타내는 어노테이션
    @GetMapping("string.do")
    public String m2(@RequestParam(name="na") String name){//@RequestParam(name="na") 어노테이션은 na라는 이름의 필수 파라미터를 받아온다
        System.out.println("#m2() name: " + name);
        return name;// 문자열 리턴
    }

    // m3()메서드는 MethodController 의 메서드이고 내부클래스인 Book 클래스를 내부 클래스로 사용할 수 있다
    // 이렇게 하면 Book 클래스를 다른 메서드에서도 사용할 필요 없이 m3() 메서드 내에서 필요한 기능을 구현할 수 있다
    // 자바에서 내부 클래스(inner class)는 외부 클래스(outer class)의 멤버로 존재하며, 외부 클래스의 멤버 변수 및 메서드에 직접 접근할 수 있다
    @ResponseBody
    @GetMapping("json.do")// GetMapping("json.do") 어노테이션이 붙은 "/json.do" URL로 GET 요청을 처리
    public Book m3(String title, int price){
        Book bk = new Book(title, price);
        System.out.println("#m3(): title: " + title + ", price: " + price);
        return bk;// jsp리턴
    }
    static class Book{// Book 클래스의 인스턴스를 만들기 위해 외부 클래스의 인스턴스를 생성할 필요가 없다
        private String title;
        private int price;
        Book(String title,int price){
            this.title = title;
            this.price = price;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public void setPrice(int price){
            this.price =  price;
        }
        public String getTitle(){
            return title;
        }
        public int getPrice(){
            return price;
        }
    }
}