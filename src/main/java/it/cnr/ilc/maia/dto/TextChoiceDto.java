package it.cnr.ilc.maia.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class TextChoiceDto implements Serializable {

    private Long id;

    private String title;

    private String updatedOn;

    private String createdBy;

    private String status;

    /*    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT_ISO_LOCAL_DATE_TIME)
    private LocalDateTime created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT_ISO_LOCAL_DATE_TIME)
    private LocalDateTime updated;

    private Role role;

    private boolean active;

    private List<Language> languages;*/
}
