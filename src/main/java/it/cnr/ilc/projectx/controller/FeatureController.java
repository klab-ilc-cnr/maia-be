package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.CreateFeatureDto;
import it.cnr.ilc.projectx.dto.FeatureDto;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.request.CreateFeatureRequest;
import it.cnr.ilc.projectx.request.DeleteFeatureRequest;
import it.cnr.ilc.projectx.request.UpdateFeatureRequest;
import it.cnr.ilc.projectx.service.eventHandler.DeleteHandler;
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

    @PutMapping
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<FeatureDto> updateFeature(@Valid @RequestBody FeatureDto updateFeaturetDto) throws Exception {
        XResult<FeatureDto> response = mediator.sendXResult(new UpdateFeatureRequest(updateFeaturetDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        FeatureDto responsePayload = response.getPayload();
        return ResponseEntity.ok(responsePayload);
    }

    @GetMapping("canbedeleted/{layerId}/{featureId}")
    public ResponseEntity<Boolean> canBeDeleted(@PathVariable @NotNull Long layerId,
                                                @PathVariable @NotNull Long featureId) {
        return ResponseEntity.ok(featureService.canBeDeleted(layerId, featureId));
    }

    @DeleteMapping("{layerId}/{featureId}")
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<Boolean> deleteFeature(@PathVariable @NotNull Long layerId,
                                              @PathVariable @NotNull Long featureId) throws Exception {

        XResult<Boolean> response = mediator.sendXResult(new DeleteFeatureRequest(layerId, featureId));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(response.getPayload());
    }
}
