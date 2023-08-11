package sin.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sin.backend.domain.Address;
import sin.backend.domain.Board;

import java.util.List;

public interface SpringDataJpaOracleBoardRepository extends JpaRepository<Board, Long> {
    // extends JpaRepository<Board, Long> 에서 Long은 seq의 타입
    List<Board> findByWriter(String writer);// 테이블 컬럼에 의존적인 select는 직접 만들어줘야 한다
    //JPQL -> select a from Address a where a.name=:name // :name은 넘어온 파라미터의 name
    List<Board>findByWriterAndEmail(String writer, String email);// findByNameAndAddr이름이 엄청 중요하다 And, Or, ...등등 바꿔 쓸 수 있다

    List<Board> findByWriterContaining(String writer);// XXXContaining()은 like(%신%) 연산자 역할

    // 위의 3개는 JpaRepository에 있는 것이 아니라 따로 정의한 것이다!!!

    Page<Board> findByOrderBySeqDesc(Pageable pageable); //for 페이징(Board)

}
