package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.AnnotationFeatureDto;
import it.cnr.ilc.projectx.dto.AnnotationRelationDto;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.request.*;
import it.cnr.ilc.projectx.service.AnnotationFeatureService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/annotations")
public class AnnotationController {
    @NonNull
    private final Mediator mediator;

    @PostMapping
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<AnnotationFeatureDto> create(@Valid @RequestBody @NotNull AnnotationFeatureDto annotationDto) throws Exception {
        XResult<AnnotationFeatureDto> response = mediator.sendXResult(new CreateAnnotationFeatureRequest(annotationDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        AnnotationFeatureDto responseDto = response.getPayload();
        return ResponseEntity.ok(responseDto);
    }

    //TODO Finchè non viene fatto il refactoring e portate le associazioni tra annotazione e feature sul nostro
    // backend questo metodo non è utilizzato
    @PutMapping
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<AnnotationFeatureDto> updateAnnotation(@Valid @RequestBody AnnotationFeatureDto annotationFeatureDto) throws Exception {
        XResult<AnnotationFeatureDto> response = mediator.sendXResult(new UpdateAnnotationFeatureRequest(annotationFeatureDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        AnnotationFeatureDto responsePayload = response.getPayload();
        return ResponseEntity.ok(responsePayload);
    }

    @DeleteMapping("{annotationId}")
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<Boolean> deleteAnnotation(@PathVariable @NotNull Long annotationId) throws Exception {
        XResult<Boolean> response = mediator.sendXResult(new DeleteAnnotationFeatureRequest(annotationId));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(response.getPayload());
    }
}
