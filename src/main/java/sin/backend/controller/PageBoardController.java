package sin.backend.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sin.backend.domain.Board;
import sin.backend.dto.BoardListResult;
import sin.backend.service.PageAddressService;
import sin.backend.service.PageBoardService;

@AllArgsConstructor
@RequestMapping("board_page")
@Controller
public class PageBoardController {
    private final PageBoardService service; // AutoInjection

    @GetMapping("list.do")
    public String list(@PageableDefault(size=3, sort="seq", direction= Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        BoardListResult listResult = service.getBoardListResult(pageable);
        model.addAttribute("listResult", listResult);

        return "/board_page/list";
    }


    @GetMapping("write.do")
    public String write() {
        return "/board_page/write";
    }
    @PostMapping("write.do")
    public String write(Board board) {
        service.insertB(board);
        return "redirect:list.do";
    }


    @GetMapping("content.do")
    public String content(long seq, Model model){
        Board contentb = service.contentB(seq);
        model.addAttribute("contentb", contentb);
        return "/board_page/content";
    }


    @GetMapping("update.do")// 업데이트 하기위한 화면
    public String update(long seq, Model model){
        Board update = service.contentB(seq);
        model.addAttribute("update", update);
        return "/board_page/update";
    }


    @PostMapping("update.do")// 실제 업데이트
    public String update(Board board){
        service.updateB(board);
        return "redirect:list.do";
    }


    @GetMapping("del.do")
    public String delete(long seq, ServletContext application, HttpSession session,
                         HttpServletRequest request, Object page, HttpServletResponse response ) {
        //pln("@@application: " + application); //불가 !!!
        application = session.getServletContext();// 참고로 알아두기(ServletContext application만 쓰는 것은 아무런 의미가 없다
        pln("##application: " + application);
        pln("@@session: " + session);
        pln("@@request: " + request);
        pln("@@page: " + page);
        pln("@@response: " + response);

        service.deleteB(seq);
        return "redirect:list.do";
    }

    void pln(String str) {

        System.out.println(str);
    }
}
