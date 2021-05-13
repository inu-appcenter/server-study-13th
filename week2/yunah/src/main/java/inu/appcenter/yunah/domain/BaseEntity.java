package inu.appcenter.yunah.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 클래스를 상속받으면 이 클래스의 필드가 entity의 속성이 된다.
@EntityListeners(AuditingEntityListener.class) // JPA aditting
@Getter
public abstract class BaseEntity {  // 추상 클래스(상속 가능)

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
