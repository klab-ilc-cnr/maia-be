package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.AnnotationRelationDto;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.service.AnnotationRelationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/relations")
public class RelationController {
    @NonNull
    private final Mediator mediator;

    @NonNull
    private final AnnotationRelationService annotationRelationService;

    @GetMapping("{id}")
    public ResponseEntity<AnnotationRelationDto> retrieveById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(annotationRelationService.retrieveById(id));
    }

//    @PostMapping
//    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
//    public ResponseEntity<LayerDto> createLayer(@Valid @RequestBody @NotNull LayerDto layerDto) throws Exception {
//        XResult<LayerDto> response = mediator.sendXResult(new CreateLayerRequest(layerDto));
//        if (response.IsFailed()) {
//            ResponseEntity.badRequest();
//        }
//        LayerDto responseDto = response.getPayload();
//        return ResponseEntity.ok(responseDto);
//    }
//
//    @DeleteMapping("{layerId}")
//    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
//    public ResponseEntity<Boolean> deleteLayer(@PathVariable @NotNull Long layerId) throws Exception {
//        XResult<Boolean> response = mediator.sendXResult(new DeleteLayerRequest(layerId));
//        if (response.IsFailed()) {
//            ResponseEntity.badRequest();
//        }
//        return ResponseEntity.ok(response.getPayload());
//    }
//
//    @PutMapping
//    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
//    public ResponseEntity<LayerChoiceDto> updateLayer(@Valid @RequestBody UpdateLayerChoiceDto updateLayerChoiceDto) throws Exception {
//        XResult<LayerChoiceDto> response = mediator.sendXResult(new UpdateLayerChoiceRequest(updateLayerChoiceDto));
//        if (response.IsFailed()) {
//            ResponseEntity.badRequest();
//        }
//        LayerChoiceDto responsePayload = response.getPayload();
//        return ResponseEntity.ok(responsePayload);
//    }
}
