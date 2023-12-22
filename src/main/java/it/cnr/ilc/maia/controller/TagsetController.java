package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.dto.CreateTagsetDto;
import it.cnr.ilc.maia.dto.TagsetDto;
import it.cnr.ilc.maia.dto.UpdateTagsetDto;
import it.cnr.ilc.maia.mediator.Mediator;
import it.cnr.ilc.maia.request.CreateTagsetRequest;
import it.cnr.ilc.maia.request.DeleteTagsetRequest;
import it.cnr.ilc.maia.request.UpdateTagsetRequest;
import it.cnr.ilc.maia.service.TagsetService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/tagsets")
public class TagsetController {

    @NonNull
    private final Mediator mediator;

    @NonNull
    private final TagsetService tagsetService;

    @GetMapping
    public ResponseEntity<List<TagsetDto>> getTagsets() {
        return ResponseEntity.ok(tagsetService.retrieveAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagsetDto> retrieveById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(tagsetService.retrieveById(id));
    }

    @PostMapping
    public ResponseEntity<CreateTagsetDto> createTagset(@Valid @RequestBody @NotNull CreateTagsetDto tagsetDto) throws Exception {
        try {
            XResult<CreateTagsetDto> response = mediator.sendXResult(new CreateTagsetRequest(tagsetDto));
            if (response.IsFailed()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            CreateTagsetDto responseDto = response.getPayload();
            return ResponseEntity.ok(responseDto);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(418).body(null);
        }
    }

    @PutMapping
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
    public ResponseEntity<Boolean> deleteTagset(@PathVariable @NotNull Long id) throws Exception {
        XResult<Boolean> response = mediator.sendXResult(new DeleteTagsetRequest(id));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(response.getPayload());
    }
}
