package it.cnr.ilc.projectx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.cnr.ilc.projectx.model.interfaces.ITracedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class TracedEntity implements Serializable, ITracedEntity {

    @Column
    @CreatedDate
    private LocalDateTime created;

    @Column
    @CreatedBy
    private Long createdBy;

    @Column
    @LastModifiedDate
    private LocalDateTime updated;

    @Column
    @LastModifiedBy
    private Long updatedBy;

}
