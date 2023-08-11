package sin.backend.domain;
//  서로 다른 계층 간에 데이터를 주고받는 데 사용
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity // 추가
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(name="BOARD_SEQ_GENERATOR", sequenceName="BOARD_SEQ", initialValue=1, allocationSize=1)
// @SequenceGenerator은 데이터베이스 시퀀스를 활용하여 엔티티의 기본 키 값을 자동으로 생성하는 메커니즘을 설정하는 데 사용
// name: 시퀀스 생성기의 이름을 지정, sequenceName: 데이터베이스에서 사용될 시퀀스의 이름을 지정
// initialValue: 시퀀스가 시작하는 값입니다. 일반적으로 1로 설정되며, 기본값은 1, allocationSize: 이 값은 다음 시퀀스 값과의 간격
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
    private long seq;
    private String writer;
    private String email;
    private String subject;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp //추가
    private Date rdate;

    private Date udate;

}
