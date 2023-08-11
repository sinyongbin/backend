package sin.backend.repository;
// Spring에서 인터페이스가 중간다리 역할을 한다
import sin.backend.domain.Board;

import java.util.List;

public interface BoardRepository {
    List<Board> list();
    Board insert(Board board);
    Board content(long seq);
    void update(Board board);
    boolean delete(long seq);
}
