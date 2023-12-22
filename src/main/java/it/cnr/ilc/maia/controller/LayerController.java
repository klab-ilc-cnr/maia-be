package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.dto.LayerChoiceDto;
import it.cnr.ilc.maia.dto.LayerDto;
import it.cnr.ilc.maia.dto.UpdateLayerChoiceDto;
import it.cnr.ilc.maia.mediator.Mediator;
import it.cnr.ilc.maia.request.CreateLayerRequest;
import it.cnr.ilc.maia.request.DeleteLayerRequest;
import it.cnr.ilc.maia.request.UpdateLayerChoiceRequest;
import it.cnr.ilc.maia.service.LayerService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/layers")
public class LayerController {

    @NonNull
    private final Mediator mediator;

    @NonNull
    private final LayerService layerService;

    @GetMapping
    public ResponseEntity<List<LayerDto>> getLayers() {
        return ResponseEntity.ok(layerService.retrieveAllLayers());
    }

    @PostMapping
    public ResponseEntity<LayerDto> createLayer(@Valid @RequestBody @NotNull LayerDto layerDto) throws Exception {
        XResult<LayerDto> response = mediator.sendXResult(new CreateLayerRequest(layerDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        LayerDto responseDto = response.getPayload();
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("{layerId}")
    public ResponseEntity<Boolean> deleteLayer(@PathVariable @NotNull Long layerId) throws Exception {
        XResult<Boolean> response = mediator.sendXResult(new DeleteLayerRequest(layerId));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(response.getPayload());
    }

    @PutMapping
    public ResponseEntity<LayerChoiceDto> updateLayer(@Valid @RequestBody UpdateLayerChoiceDto updateLayerChoiceDto) throws Exception {
        XResult<LayerChoiceDto> response = mediator.sendXResult(new UpdateLayerChoiceRequest(updateLayerChoiceDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        LayerChoiceDto responsePayload = response.getPayload();
        return ResponseEntity.ok(responsePayload);
    }
}
