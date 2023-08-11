package sin.backend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sin.backend.domain.Address;
import sin.backend.domain.Board;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest// 스프링 부트 애플리케이션의 통합 테스트를 지원하기 위한 어노테이션
class JdbcTemplateOracleBoardRepositoryTest {
    @Autowired// 컨테이너가 자동으로 의존성을 주입할 수 있도록 지정하는 역할
    JdbcTemplateOracleBoardRepository repository;

    @Test
    void getRepository() {
        pln("#repository: " + repository);
    }

    @Test
    void list() {
        List<Board> list = repository.list();
        pln("list: " + list);
    }

    @Test
    void insert() {
        Board board = new Board(-1L, "용빈", "vv", "제목!!", "내용ㅎㅎ", null, null);
        Board boardDB = repository.insert(board);
        pln("#boardDB: " + boardDB);
    }

    @Test
    void content() {
        Board board = repository.content(11);
        pln("#board: " + board);
    }

    @Test
    void update() {
        Board board = new Board(15, "용빈1", "vv1", "제목1!!", "내용ㅎㅎ", null, null);
        repository.update(board);
    }

    @Test
    void delete() {
        boolean flag = repository.delete(17);
        pln("#flag: " + flag);
    }

    private void pln(String str) {
        System.out.println(str);
    }

}