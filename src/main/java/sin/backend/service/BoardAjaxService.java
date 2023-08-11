package sin.backend.service;

import sin.backend.domain.Board;

import java.util.List;

public interface BoardAjaxService {

    List<Board> listB();

    Board insertB(Board board);

    Board contentB(long seq);

    void updateB(Board board);

    boolean deleteB(long seq);

    //for Ajax
    Board getBySeqB(long seq);
    List<Board> getListByNames(String writer);
}
