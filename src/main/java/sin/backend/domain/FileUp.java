package sin.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//setter, getter
@NoArgsConstructor//  파라미터가 없는 기본 생성자 자동으로 생성
@Table(name="fileup") // list.jsp에서 var="fileUp"부분과 동일하다, 지금은 필요 없다 파일이름과 Entity이름이 같기 때문에 !!!
@Entity
@SequenceGenerator(name="FILEUP_SEQ_GENERATOR", sequenceName = "FILEUP_SEQ", initialValue = 1, allocationSize = 1)// 시퀀스 생성

public class FileUp {// 위에 @Table(name="fileup")로 테이블 명을 맞춰준다
    @Id// 엔티티 클래스의 필드를 기본 키(primary key)로 지정하는 역할( 지금은 id에 지정 되어 있다 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILEUP_SEQ_GENERATOR")// 엔티티의 기본 키(primary key) 값을 자동으로 생성하기 위해 사용
    @Column(name = "file_id")// long id;에 @Column을 걸어줘서 테이블 및 컬럼과 매핑하기 위해 사용, file_id로 접근하면 된다
    private long id;// 필드 1: Long으로 쓰든 long로 쓰든 상관이 없다(자동호환[오토박싱, 언박싱]이 되기 때문에)
    private String orgnm;// 필드 2
    private String savednm;// 필드 3
    private String savedpath;// 필드 4


    @Builder// 도메인 쓸 때 추천 ==  @AllArgsConstructor와 같은 의미 따라서 이부분 없이 위에 @Column위쪽에 @Builder써줘도 상관 없다
    public FileUp(long id, String orgnm, String savednm, String savedpath){
        this.id = id;
        this.orgnm = orgnm;
        this.savednm = savednm;
        this.savedpath = savedpath;
    }

}
