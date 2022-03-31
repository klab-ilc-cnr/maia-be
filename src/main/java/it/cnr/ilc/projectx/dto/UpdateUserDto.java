package it.cnr.ilc.projectx.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.cnr.ilc.projectx.model.Role;
import it.cnr.ilc.projectx.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateUserDto implements Serializable {

    private String name;

    private String surname;

    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT_ISO_LOCAL_DATE_TIME)
    private LocalDateTime updated;

    private Role role;

    private boolean active;
}
