package sin.backend.control.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor// 파라미터 생성자
@NoArgsConstructor// 기본 생성자
@Data// getter, setter 자동으로
public class Human {// 기본 생성자는 생략한다
    private String name;
    private int age;
}
