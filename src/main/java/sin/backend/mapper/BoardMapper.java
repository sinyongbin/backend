package sin.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sin.backend.domain.Address;
import sin.backend.domain.Board;

import java.util.List;

@Mapper// Mapper이라는 것을 이용하는
@Repository// Repository이다
public interface BoardMapper {

    List<Board> list();

    Board selectBySeq(long seq);
    void insertSelectKey(Board board);

    Board content(long seq);

    void update(Board board);

    void delete(long seq);
}
