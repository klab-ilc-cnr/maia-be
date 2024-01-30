package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.dto.CreateFeatureDto;
import it.cnr.ilc.maia.dto.FeatureDto;
import it.cnr.ilc.maia.mediator.Mediator;
import it.cnr.ilc.maia.request.CreateFeatureRequest;
import it.cnr.ilc.maia.request.DeleteFeatureRequest;
import it.cnr.ilc.maia.request.UpdateFeatureRequest;
import it.cnr.ilc.maia.service.FeatureService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
//@RestController
//@RequestMapping(value = "/api/features")
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
    public ResponseEntity<CreateFeatureDto> createFeature(@Valid @RequestBody @NotNull CreateFeatureDto featureDto) throws Exception {
        XResult<CreateFeatureDto> response = mediator.sendXResult(new CreateFeatureRequest(featureDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        CreateFeatureDto responseDto = response.getPayload();
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping
    public ResponseEntity<FeatureDto> updateFeature(@Valid @RequestBody FeatureDto updateFeaturetDto) throws Exception {
        XResult<FeatureDto> response = mediator.sendXResult(new UpdateFeatureRequest(updateFeaturetDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        FeatureDto responsePayload = response.getPayload();
        return ResponseEntity.ok(responsePayload);
    }

    public ResponseEntity<Boolean> canBeDeleted(@PathVariable @NotNull Long layerId,
            @PathVariable @NotNull Long featureId) {
        return ResponseEntity.ok(featureService.canBeDeleted(layerId, featureId));
    }

    @DeleteMapping("{layerId}/{featureId}")
    public ResponseEntity<Boolean> deleteFeature(@PathVariable @NotNull Long layerId,
            @PathVariable @NotNull Long featureId) throws Exception {

        XResult<Boolean> response = mediator.sendXResult(new DeleteFeatureRequest(layerId, featureId));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(response.getPayload());
    }
}
