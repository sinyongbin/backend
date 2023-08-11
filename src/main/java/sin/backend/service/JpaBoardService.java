package sin.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sin.backend.domain.Board;
import sin.backend.repository.BoardRepository;

import java.util.List;

//@Service
@Transactional// 추가( jpa는 트렌젝션 안에서 사용해야 한다 )
public class JpaBoardService implements BoardService {
    //@Autowired
    private final BoardRepository repository;

    //@Autowired
    public JpaBoardService(BoardRepository repository) {
        this.repository=repository;
    }

    @Override
    public List<Board> listB() {
        pln("@listB() by Jpa");
        return repository.list();
    }

    @Override
    public Board insertB(Board board) {
        pln("@insertB() by Jpa: (Before) board: " + board);
        board = repository.insert(board);
        pln("@insertB() by Jpa: (after) board: " + board);// DB에 넣었다가 다시 가져오는 것이기 때문에 seq와 date도 포함
        pln("@insertB() by Jpa: seq: " + board.getSeq());
        pln("@insertB() by Jpa: rdate: " + board.getRdate());

        return board;
    }

    @Override
    public Board contentB(long seq) {
        pln("contentB() by jpa");
        Board board = repository.content(seq);
        pln("contentB() board: " + board);
        return board;
    }

    @Override
    public void updateB(Board board) {
        pln("@updateB() by jpa");
        repository.update(board);
    }

    @Override
    public boolean deleteB(long seq) {
        pln("@deleteB() by jpa");
        return repository.delete(seq);
    }

    void pln(String str) {
        System.out.println(str);
    }
}
