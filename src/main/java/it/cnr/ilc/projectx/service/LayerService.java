package it.cnr.ilc.projectx.service;

import it.cnr.ilc.projectx.dto.*;
import it.cnr.ilc.projectx.model.Layer;
import it.cnr.ilc.projectx.model.Workspace;
import it.cnr.ilc.projectx.repository.LayerRepository;
import it.cnr.ilc.projectx.repository.WorkspaceRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.NotFoundException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@Slf4j
@RequiredArgsConstructor
public class LayerService {

    @NonNull
    private final LayerRepository layerRepository;

    public List<LayerDto> retrieveAllLayers() {
        return mapToLayerDto(layerRepository.findAll());
    }

    public LayerDto saveLayer(LayerDto layerDto) {
        Layer layer = layerRepository.save(mapToEntity(layerDto));
        return mapToLayerDto(layer);
    }

    private Layer mapToEntity(LayerDto layerDto) {
        Layer layer = new Layer();
        BeanUtils.copyProperties(layerDto, layer);
        return layer;
    }

    private List<LayerDto> mapToLayerDto(List<Layer> layers) {
        return layers.stream().map(layer -> mapToLayerDto(layer)).collect(Collectors.toList());
    }

    private LayerDto mapToLayerDto(Layer layer) {
        LayerDto layerDto = new LayerDto();
        BeanUtils.copyProperties(layer, layerDto);
        return layerDto;
    }
}
