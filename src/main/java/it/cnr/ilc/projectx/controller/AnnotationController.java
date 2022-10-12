package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.AnnotationFeatureDto;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.request.CreateAnnotationFeatureRequest;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/annotations")
public class AnnotationController {
    @NonNull
    private final Mediator mediator;

    @NonNull
    private final AnnotationFeatureService annotationFeatureService;

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
}
