package it.cnr.ilc.projectx.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.cnr.ilc.projectx.model.TileType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class TileDto<T> implements Serializable {
    Long id;
    Long workspaceId;
    TileType type;
    T content;
}
