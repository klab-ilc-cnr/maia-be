package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.*;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.request.*;
import it.cnr.ilc.projectx.service.TagsetService;
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
    @PreAuthorize("hasRole(T(it.cnr.ilc.projectx.model.Role).AMMINISTRATORE)")
    public ResponseEntity<CreateTagsetDto> createTagset(@Valid @RequestBody @NotNull CreateTagsetDto tagsetDto) throws Exception {
        XResult<CreateTagsetDto> response = mediator.sendXResult(new CreateTagsetRequest(tagsetDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        CreateTagsetDto responseDto = response.getPayload();
        return ResponseEntity.ok(responseDto);
    }



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
    }
}
