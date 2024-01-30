package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.dto.AnnotationFeatureDto;
import it.cnr.ilc.maia.mediator.Mediator;
import it.cnr.ilc.maia.request.CreateAnnotationFeatureRequest;
import it.cnr.ilc.maia.request.DeleteAnnotationFeatureRequest;
import it.cnr.ilc.maia.request.UpdateAnnotationFeatureRequest;
import it.cnr.ilc.maia.xresults.XResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
//@RestController
//@RequestMapping(value = "/api/annotations")
public class AnnotationController {

    @NonNull
    private final Mediator mediator;

    @PostMapping
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
    public ResponseEntity<AnnotationFeatureDto> updateAnnotation(@Valid @RequestBody AnnotationFeatureDto annotationFeatureDto) throws Exception {
        XResult<AnnotationFeatureDto> response = mediator.sendXResult(new UpdateAnnotationFeatureRequest(annotationFeatureDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        AnnotationFeatureDto responsePayload = response.getPayload();
        return ResponseEntity.ok(responsePayload);
    }

    @DeleteMapping("{annotationId}")
    public ResponseEntity<Boolean> deleteAnnotation(@PathVariable @NotNull Long annotationId) throws Exception {
        XResult<Boolean> response = mediator.sendXResult(new DeleteAnnotationFeatureRequest(annotationId));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(response.getPayload());
    }
}
