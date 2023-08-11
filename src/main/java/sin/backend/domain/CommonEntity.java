package sin.backend.domain;
// 날짜정보의 Entity만 빼서 관리하는 도메인
// 테이블 개수가 늘어날수록 정말 강력하다
// sin.backend.BackendApplication에 @EnableJpaAuditing를 추가해줘야 등록이 된다 ( 정말 중요 !!!!!!!!!!!!!!!! ) --> 그래야 Spring에 등록이되서 사용이 가능하다
// 기존의 Entity에서 상속을 받아서 사용이 가능하다

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass// Superclass로부터 상속을 받는다 (공통되는 Entity를 정의하기 위해서 이걸 쓴다)
@AllArgsConstructor(access = AccessLevel.PROTECTED)// AccessLevel.PROTECTED: protected(접근제한자)생성자를 만든다는 말
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@EntityListeners(AuditingEntityListener.class)//main클래스로부터 entity를 인식하게끔 만들어줘야하기 때문에 이렇게 쓴다(즉, Entity어노테이션을 쓰지않더라도 Spring에 등록이 가능하다)

public class CommonEntity {
    @CreatedDate// 처음 등록했을때 날짜를 넣어줌
    @Column(updatable = false, nullable = false)// updatable = false: 처음입력할때 날짜가 한번들어가면 업데이트가 되지 않는다
                                                // nullable = false: 이 속성은 null 값을 허용하지 않는다( not null )
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    // java.time.LocalDateTime 타입의 속성이 데이터베이스에 저장될 때 이를 변환기를 통해 어떻게 변환하고 저장할지를 설정한다는 의미
    private LocalDateTime rdate;
    @LastModifiedDate// 마지막 가장 최근에 수정된 날짜를 넣어준다
    @Column(updatable = false)// 업데이트는 된다
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime udate;
}
