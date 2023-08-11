package sin.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sin.backend.domain.FileUp;

@Repository
public interface FileRepository extends JpaRepository<FileUp, Long> { //FileUp은 도메인, 제네릭에서는 Long로 대문자만 가능하다. 질문!! 제네릭이 뭔지?
    // FileUp: 이 부분은 제네릭을 사용한 타입 파라미터
    // JpaRepository 인터페이스는 제네릭 타입을 받는데, 첫 번째 파라미터로는 엔티티 클래스를, 두 번째 파라미터로는 기본 키(PK)의 타입을 지정
    // FileRepository는 FileUp 엔티티를 다루고, Long 타입을 기본 키로 사용한다
    // Long: FileRepository가 다루는 엔티티인 FileUp의 기본 키 타입이 Long임을 나타낸다
}
