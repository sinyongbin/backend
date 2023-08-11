package sin.backend.domain;
// CommonEntity를 상속받아서 날짜를 생성한다

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Entity // 추가
@Table(name="MEMBER_LOGIN")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(name="MEMBER_LOGIN_SEQ_GENERATOR", sequenceName="MEMBER_LOGIN_SEQ", initialValue=1, allocationSize=1)
public class MemberLogin extends CommonEntity{// CommonEntity를 상속받아 넣어주면 날짜는 다 해결된다( 엄~청 나게 강력 )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_LOGIN_SEQ_GENERATOR")
    private long seq;
    // DB의 컬럼이름이 다를 경우 @Column(name="username") 을 써준다 == DB컬럼이름이 username일 경우
    private String name;
    private String email;
    private String pwd;
    private String phone;



}
