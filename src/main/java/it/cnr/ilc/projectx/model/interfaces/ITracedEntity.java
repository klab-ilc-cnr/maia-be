package it.cnr.ilc.projectx.model.interfaces;

import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public interface ITracedEntity {

    LocalDateTime getCreated();

    void setCreated(LocalDateTime created);

    Long getCreatedBy();

    void setCreatedBy(@Positive Long createdBy);

    LocalDateTime getUpdated();

    void setUpdated(LocalDateTime updated);

    Long getUpdatedBy();

    void setUpdatedBy(@Positive Long updatedBy);

}
