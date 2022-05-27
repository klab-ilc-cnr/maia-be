package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.*;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.request.*;
import it.cnr.ilc.projectx.service.LayerService;
import it.cnr.ilc.projectx.service.WorkspaceService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
}
