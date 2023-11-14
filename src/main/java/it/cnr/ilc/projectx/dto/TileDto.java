package it.cnr.ilc.projectx.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.cnr.ilc.projectx.model.TileType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class TileDto<T> implements Serializable {

    Long id;
    Long workspaceId;
    TileType type;
    T content;
    String tileConfig;
}
