package sin.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sin.backend.domain.Board;
import sin.backend.repository.BoardRepository;

import java.util.List;

//@Service
public class JdbcOracleBoardService implements BoardService {

    private final BoardRepository repository;
    //@Autowired
    public JdbcOracleBoardService(BoardRepository repository ){// 생성자에서 BoardRepository의 객체를 초기화 해서 사용

        this.repository = repository;
    }
    @Override
    public List<Board> listB() {

        return repository.list();
    }
    @Override
    public Board insertB(Board board) {

        return repository.insert(board);
    }

    @Override
    public Board contentB(long seq) {

        return repository.content(seq);
    }

    @Override
    public void updateB(Board board) {

        repository.update(board);
    }

    @Override
    public boolean deleteB(long seq) {

        return repository.delete(seq);
    }
}
