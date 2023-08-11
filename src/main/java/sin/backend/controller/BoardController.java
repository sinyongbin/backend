package sin.backend.controller;
// Service 클래스를 가지고와서 핸들링
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sin.backend.domain.Board;
import sin.backend.service.BoardService;

import java.util.List;

@RequestMapping("board")//  웹 요청과 매핑되는 컨트롤러의 메소드를 지정하는데 사용된다
// 이 어노테이션을 사용하여 특정 URL 패턴과 HTTP 메소드에 대해 어떤 메소드가 처리할지를 매핑
@Controller
public class BoardController {
    @Autowired
    private BoardService service;

    @GetMapping("list.do")
    public String list(Model model){
        List<Board> listb = service.listB();// service.listB()로 연결
        model.addAttribute("list", listb);
        return "/board/list";
    }

    @GetMapping("write.do")
    public String write(){// 들어가는 메서드
        return "/board/write";
    }

    @PostMapping("write.do")
    public String write(Board board){// 들어가서 버튼클릭
        service.insertB(board);
        return "redirect:list.do";

    }

    @GetMapping("content.do")
    public String content(long seq, Model model){
        Board contentb = service.contentB(seq);
        model.addAttribute("contentb", contentb);
        return "/board/content";
    }
    @GetMapping("update.do")// 업데이트 하기위한 화면
    public String update(long seq, Model model){
        Board update = service.contentB(seq);
        model.addAttribute("update", update);
        return "/board/update";
    }

    @PostMapping("update.do")// 실제 업데이트
    public String update(Board board){
        service.updateB(board);
        return "redirect:list.do";
    }

    @GetMapping("del.do")
    public String delete(long seq){
        service.deleteB(seq);
        return "redirect:list.do";
    }


}
