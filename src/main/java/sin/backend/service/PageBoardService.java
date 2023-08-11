package sin.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sin.backend.domain.Board;
import sin.backend.dto.BoardListResult;

public interface PageBoardService {
    Page<Board> findAll(Pageable pageable);
    BoardListResult getBoardListResult(Pageable pageable);
    Board insertB(Board board);
    Board contentB(long seq);
    void updateB(Board board);
    void deleteB(long seq);

}
