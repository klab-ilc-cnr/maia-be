package it.cnr.ilc.maia.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.cnr.ilc.maia.model.TileType;
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
