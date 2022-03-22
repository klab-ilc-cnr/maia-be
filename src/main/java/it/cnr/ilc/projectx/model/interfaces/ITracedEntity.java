package it.cnr.ilc.projectx.model.interfaces;

import javax.validation.constraints.Positive;
import java.sql.Timestamp;

public interface ITracedEntity {

    Timestamp getCreated();

    void setCreated(Timestamp timestamp);


    Long getCreatedBy();

    void setCreatedBy(@Positive Long createdBy);


    Timestamp getUpdated();

    void setUpdated(Timestamp updated);


    Long getUpdatedBy();

    void setUpdatedBy(@Positive Long updatedBy);

}
