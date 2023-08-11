package sin.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sin.backend.domain.Board;
import sin.backend.mapper.BoardMapper;

import java.util.List;

//@Service
public class MybatisBoardService implements BoardService {

    private final BoardMapper mapper;

    //@Autowired
    public MybatisBoardService(BoardMapper mapper){
        this.mapper=mapper;

    }

    @Override
    public List<Board> listB() {
        pln("@listB() by mapper");
        return mapper.list();
    }

    @Override
    public Board insertB(Board board) {
        pln("@insetB() by mapper");
        mapper.insertSelectKey(board);
        long seq = board.getSeq();
        pln("insertB() board.getSeq(): " + seq);
        board = mapper.selectBySeq(seq);
        pln("insertB() board: " + board);

        return board;
    }

    @Override
    public Board contentB(long seq) {
        pln("contentB() by mapper");
        Board board = mapper.content(seq);
        pln("contentB() board: " + board);

        return board;
    }

    @Override
    public void updateB(Board board) {
        pln("updateB() by mapper");
        mapper.update(board);
    }

    @Override
    public boolean deleteB(long seq) {
        pln("@deleteB() by mapper");
        mapper.delete(seq);

        return true;
    }

    void pln(String str) {
        System.out.println(str);
    }
}
