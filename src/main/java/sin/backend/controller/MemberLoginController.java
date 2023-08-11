package sin.backend.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sin.backend.domain.MemberLogin;
import sin.backend.service.MemberLoginService;

import static sin.backend.service.MemberLoginConst.YES_ID_PWD;

@Controller
@RequestMapping("login")
@AllArgsConstructor
public class MemberLoginController {
    private MemberLoginService memberLoginService;

    @GetMapping("form.do")
    public String form() {

        return "login/form";
    }
    @PostMapping("login.do")// 로그인 할 때 구현되게끔 만드는 메서드~!
    public String login(MemberLogin memberLogin, HttpSession session, Model model) {// 로그인한 사용자의 인증 정보, 장바구니 내용 등이 세션에 저장될 수 있다.
                                                    // 세션은 보통 서버 측에서 관리되며, 클라이언트와 서버 간의 상호작용을 통해 세션 ID를 사용하여 식별
        int result = memberLoginService.check(memberLogin.getEmail(), memberLogin.getPwd());
        if(result == YES_ID_PWD) {// 로그인 성공일때
            MemberLogin loginOkUser = memberLoginService.getLogin(memberLogin.getEmail());// MemberLogin에서 Pwd가 공백처리된 도메인이 날라온다 !!!!!
            session.setAttribute("loginOkUser", loginOkUser);
        }
        model.addAttribute("result", result);//result값에 의해서 ID가 없습니다, 일치합니다 등이 결졍되기 때문에 써준다

        return "login/msg";
    }
    @GetMapping("logout.do")
    public String logout(HttpSession session){
        session.removeAttribute("loginOkUser");// 세션 방 안의 한개의 객체를 삭제한다 --> 장바구니가 있다면 남을 수 있다
        session.invalidate();// 하나씩 삭제되는것이 아니고 세션방에 있는 모든 객체가 전부 삭제된다 --> 장바구니가 있다면 그거까지 다 삭제되기 때문에 더 깔끔하고 좋다
        return "redirect:../";
    }


}
