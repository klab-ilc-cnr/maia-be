package it.cnr.ilc.maia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.cnr.ilc.maia.model.Language;
import it.cnr.ilc.maia.model.Role;
import it.cnr.ilc.maia.utils.DateUtils;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

    private long id;

    private String username;

    private String name;

    private String surname;

    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT_ISO_LOCAL_DATE_TIME)
    private LocalDateTime created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT_ISO_LOCAL_DATE_TIME)
    private LocalDateTime updated;

    private Role role;

    private boolean active;

    private List<Language> languages;

}
