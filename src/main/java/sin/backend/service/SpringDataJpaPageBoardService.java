package sin.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sin.backend.domain.Address;
import sin.backend.domain.Board;
import sin.backend.dto.AddressListResult;
import sin.backend.dto.BoardListResult;
import sin.backend.repository.SpringDataJpaOracleBoardRepository;

@Transactional// 트랜잭션 처리를 위한 어노테이션
@RequiredArgsConstructor// 생성자 자동 생성을 위한 Lombok 어노테이션
@Service// Spring Bean으로 등록되는 서비스 클래스임을 나타내는 어노테이션
public class SpringDataJpaPageBoardService implements PageBoardService {
    @Autowired// 의존성 주입을 위한 어노테이션
    private final SpringDataJpaOracleBoardRepository repository;

    @Override
    public Page<Board> findAll(Pageable pageable) {
        pln("@findAll() pageable: " + pageable);// 디버깅용 출력
        Page page = repository.findByOrderBySeqDesc(pageable);
        pln("@findAll() page: " + page);
        return page;// 페이지네이션을 적용하여 내림차순으로 정렬된 게시판 데이터 조회
    }

    @Override
    public BoardListResult getBoardListResult(Pageable pageable) {
        Page<Board> list = findAll(pageable);// 페이지네이션을 적용하여 게시판 데이터 조회
        int page = pageable.getPageNumber();// 현재 페이지 번호를 가져온다
        long totalCount = repository.count();// 전체 게시물 수를 가져옵니다.
        int size = pageable.getPageSize();// 페이지 크기를 가져옵니다.
        pln("@getBoardListResult() page: "
                +page+", totalCount: "+totalCount+", size: "+size+ ", list: " + list);// 디버깅용 출력

        return new BoardListResult(page, totalCount, size, list);// BoardListResult 객체 생성 및 반환
    }

    @Override
    public Board insertB(Board board) {
        pln("@insertB() by page: " + board);
        Board board2 = repository.save(board);// 게시물 저장
        pln("insertB() by page " + board2);

        return board2;// 저장된 게시물 반환
    }

    @Override
    public Board contentB(long seq) {
        Board board = repository.getById(seq);// 주어진 seq에 해당하는 게시물 조회
        return board;
    }

    @Override
    public void updateB(Board board) {
        pln("@updateB() by board");
        Board boardupdate = repository.getById(board.getSeq());// 주어진 게시물의 seq를 이용하여 게시물 조회 --> 이렇게 해야 date까지 같이 나온다
        boardupdate.setWriter(board.getWriter());// 작성자 업데이트
        boardupdate.setEmail(board.getEmail()); // 이메일 업데이트
        boardupdate.setSubject(board.getSubject());// 제목 업데이트
        boardupdate.setContent(board.getContent());// 내용 업데이트

        repository.save(boardupdate); // 업데이트된 게시물 저장
    }

    @Override
    public void deleteB(long seq) {
        repository.deleteById(seq);// 주어진 seq에 해당하는 게시물 삭제
    }

    void pln(String str){
        System.out.println(str);
    }
}
