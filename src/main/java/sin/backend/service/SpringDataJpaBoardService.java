package sin.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sin.backend.domain.Board;
import sin.backend.repository.SpringDataJpaOracleAddressRepository;
import sin.backend.repository.SpringDataJpaOracleBoardRepository;

import java.util.List;

//@Service
public class SpringDataJpaBoardService implements BoardService {
    //@Autowired
    public final SpringDataJpaOracleBoardRepository repository;
    //@Autowired
    public SpringDataJpaBoardService(SpringDataJpaOracleBoardRepository repository) {
        this.repository=repository;
    }
    @Override
    public List<Board> listB() {
        pln("@listB() by SpringDataJpa");

        return repository.findAll(Sort.by(Sort.Direction.DESC,"seq"));
    }

    @Override
    public Board insertB(Board board) {
        pln("@insertB() by SpringDataJpa");
        board = repository.save(board);
        pln("insertB() board: " + board);

        return board;
    }

    @Override
    public Board contentB(long seq) {
        pln("contentB() by SpringDataJpa");
        //Board board = repository.findById(seq).orElse(null);
        // findById() 메서드는 Optional 객체를 반환하므로, 조회한 결과가 null인 경우에 대한 처리가 필요

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

    /*
    Board boardupdate = repository.getById(board.getSeq());
    boardupdate.setWriter(board.getWriter());
    boardupdate.setEmail(board.getEmail());
    boardupdate.setSubject(board.getSubject());
    boardupdate.setContent(board.getContent());

    Board updatedBoard = repository.save(boardupdate);
    return updatedBoard;
    */

    @Override
    public boolean deleteB(long seq) {
        pln("deleteB() by SpringDataJpa");
        repository.deleteById(seq);

        return true;
    }

    void pln(String str) {
        System.out.println(str);
    }
}
