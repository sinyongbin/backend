package sin.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sin.backend.domain.Board;
import sin.backend.repository.SpringDataJpaOracleBoardRepository;

import java.util.List;

@Service
public class BoardAjaxServiceImpl implements BoardAjaxService {
    @Autowired
    private final SpringDataJpaOracleBoardRepository repository;

    @Autowired
    public BoardAjaxServiceImpl(SpringDataJpaOracleBoardRepository repository) {
        this.repository=repository;
    }

    @Override
    public List<Board> listB() {
        pln("@listB() by Ajax");

        return repository.findAll(Sort.by(Sort.Direction.DESC, "seq"));
    }

    @Override
    public Board insertB(Board board) {
        pln("@insertB() by Ajax");
        board = repository.save(board);
        pln("@insertB() board: " + board);

        return board;
    }

    @Override
    public Board contentB(long seq) {
        pln("contentB() by Ajax");

        Board board = repository.getById(seq);
        pln("contentB() by board: " + board);

        return board;
    }

    @Override
    public void updateB(Board board) {
        pln("@updateB() by board");

        Board boardupdate = repository.getById(board.getSeq());
        boardupdate.setWriter(board.getWriter());
        boardupdate.setEmail(board.getEmail());
        boardupdate.setSubject(board.getSubject());
        boardupdate.setContent(board.getContent());

        repository.save(boardupdate);
    }

    @Override
    public boolean deleteB(long seq) {
        pln("deleteB() by Ajax");
        repository.deleteById(seq);

        return true;
    }

    @Override
    public Board getBySeqB(long seq) {
        Board board = repository.findById(seq).get();

        return board;
    }

    @Override
    public List<Board> getListByNames(String writer) {
        List<Board> list = repository.findByWriterContaining(writer);

        return list;
    }

    void pln(String str) {
        System.out.println(str);
    }
}
