package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.CreateFeatureDto;
import it.cnr.ilc.projectx.dto.FeatureDto;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.request.CreateFeatureRequest;
import it.cnr.ilc.projectx.service.FeatureService;
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
@RequestMapping(value = "/api/features")
public class FeatureController {
    @NonNull
    private final Mediator mediator;

    @NonNull
    private final FeatureService featureService;

    @GetMapping("/{layerId}")
    public ResponseEntity<List<FeatureDto>> retrieveFeaturesByLayerId(@PathVariable @NotNull Long layerId) {
        return ResponseEntity.ok(featureService.retrieveAllByLayerId(layerId));
    }

    @PostMapping
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<CreateFeatureDto> createFeature(@Valid @RequestBody @NotNull CreateFeatureDto featureDto) throws Exception {
        XResult<CreateFeatureDto> response = mediator.sendXResult(new CreateFeatureRequest(featureDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        CreateFeatureDto responseDto = response.getPayload();
        return ResponseEntity.ok(responseDto);
    }
/*
    @PutMapping
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<UpdateTagsetDto> updateTagset(@Valid @RequestBody UpdateTagsetDto updateTagsetDto) throws Exception {
        XResult<UpdateTagsetDto> response = mediator.sendXResult(new UpdateTagsetRequest(updateTagsetDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        UpdateTagsetDto responsePayload = response.getPayload();
        return ResponseEntity.ok(responsePayload);
    }

    @GetMapping("canbedeleted/{id}")
    public ResponseEntity<Boolean> canBeDeleted(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(tagsetService.canBeDeleted(id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<Long> deleteTagset(@PathVariable @NotNull Long tagsetId) throws Exception {
        XResult<Long> response = mediator.sendXResult(new DeleteTagsetRequest(tagsetId));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(response.getPayload());
    }*/
}
