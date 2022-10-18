package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.AnnotationRelationDto;
import it.cnr.ilc.projectx.dto.CreateAnnotationRelationDto;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.request.CreateAnnotationRelationRequest;
import it.cnr.ilc.projectx.request.DeleteAnnotationRelationRequest;
import it.cnr.ilc.projectx.request.UpdateAnnotationRelationRequest;
import it.cnr.ilc.projectx.service.AnnotationRelationService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/relations")
public class RelationController {
    @NonNull
    private final Mediator mediator;

    @NonNull
    private final AnnotationRelationService annotationRelationService;

    @GetMapping("{textId}")
    public ResponseEntity<List<AnnotationRelationDto>> retrieveByTextId(@PathVariable @NotNull Long textId) {
        return ResponseEntity.ok(annotationRelationService.retrieveByTextId(textId));
    }

    @PostMapping
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<AnnotationRelationDto> createRelation(@Valid @RequestBody @NotNull CreateAnnotationRelationDto annotationRelationDto) throws Exception {
        XResult<AnnotationRelationDto> response = mediator.sendXResult(new CreateAnnotationRelationRequest(annotationRelationDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        AnnotationRelationDto responseDto = response.getPayload();
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<AnnotationRelationDto> updateRelation(@Valid @RequestBody AnnotationRelationDto annotationRelationDto) throws Exception {
        XResult<AnnotationRelationDto> response = mediator.sendXResult(new UpdateAnnotationRelationRequest(annotationRelationDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        AnnotationRelationDto responsePayload = response.getPayload();
        return ResponseEntity.ok(responsePayload);
    }

    @DeleteMapping("{relationId}")
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<Boolean> deleteRelation(@PathVariable @NotNull Long relationId) throws Exception {
        XResult<Boolean> response = mediator.sendXResult(new DeleteAnnotationRelationRequest(relationId));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(response.getPayload());
    }
}
