package sin.backend.controller;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import sin.backend.domain.Board;
import sin.backend.service.BoardAjaxService;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.List;

@AllArgsConstructor
@RequestMapping("rest_board")
@RestController
public class BoardRestController {

    private BoardAjaxService boardAjaxService;

    // insert
    @GetMapping("create1")
    public void create1(Board board) {
        boardAjaxService.insertB(board);
    }

    @PostMapping("create2")
    public void create2(@RequestBody Board board) {
        boardAjaxService.insertB(board);
    }

    //select
    @GetMapping("read")
    public List<Board> read() {
        List<Board> list = boardAjaxService.listB();
        return list;
    }
    //http://127.0.0.1:8080/rest_board/read

    @GetMapping("read/{seq}")
    public Board read(@PathVariable long seq) {
        Board board = boardAjaxService.getBySeqB(seq);
        return board;
    }
    //http://127.0.0.1:8080/rest_board/read/62

    @GetMapping(value="read", params = {"nab"})
    public List<Board> read(@RequestParam("nab") String writer) {
        List<Board> list = boardAjaxService.getListByNames(writer);
        return list;
    }
    //http://127.0.0.1:8080/rest_board/read?nab=김


    //update

    @PutMapping("update")
    public void update(@RequestBody Board board) {
        // @RequestBody를 사용하면 위 JSON 데이터를 Board 객체로 변환하여 컨트롤러 메소드의 매개변수로 받아올 수 있다
        boardAjaxService.updateB(board); // 업데이트된 게시물 저장
    }

    /*
    @PostMapping("update")
    public void update(Board board) {
        boardAjaxService.updateB(board); // 업데이트된 게시물 저장
    }
     */

    // delete
    @DeleteMapping("delete/{seq}")// @PathVariable 덕분에 delete?seq=70 이런식으로 안쓰고 delete/70으로 써진다
    public void delete(@PathVariable long seq) {
        boardAjaxService.deleteB(seq);
    }

}
