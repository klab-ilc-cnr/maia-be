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

/**
 * Description of CreateUserDto
 * <p>
 * Created at 24/03/2022 15:34
 * Author Bianca Barattolo (BB) - <b.barattolo@xeel.tech>
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateUserDto implements Serializable {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private Role role;

    private boolean active;

}
