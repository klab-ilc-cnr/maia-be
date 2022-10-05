package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.*;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.request.*;
import it.cnr.ilc.projectx.service.WorkspaceService;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/workspaces")
public class WorkspaceController {
    @NonNull
    private final Mediator mediator;

    @NonNull
    private final WorkspaceService workspaceService;

    @DeleteMapping("{workspaceId}")
    public ResponseEntity<Long> deleteWorkspace(@PathVariable @NotNull Long workspaceId) throws Exception {
        XResult<Long> response = mediator.sendXResult(new DeleteWorkspaceRequest(workspaceId));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(response.getPayload());
    }

    @PutMapping
    public ResponseEntity<WorkspaceChoiceDto> updateWorkspace(@Valid @RequestBody UpdateWorkspaceChoiceDto updateWorkspaceChoiceDto) throws Exception {
        XResult<WorkspaceChoiceDto> response = mediator.sendXResult(new UpdateWorkspaceChoiceRequest(updateWorkspaceChoiceDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        WorkspaceChoiceDto responseWorkspaceDto = response.getPayload();
        return ResponseEntity.ok(responseWorkspaceDto);
    }

    @PostMapping
    public ResponseEntity<WorkspaceChoiceDto> createWorkspace(@Valid @RequestBody @NotNull CreateWorkspaceDto createWorkspaceDto) throws Exception {
        XResult<WorkspaceChoiceDto> response = mediator.sendXResult(new CreateWorkspaceRequest(createWorkspaceDto));
        if (response.IsFailed()) {
            ResponseEntity.badRequest();
        }
        WorkspaceChoiceDto workspaceDto = response.getPayload();
        return ResponseEntity.ok(workspaceDto);
    }

    @GetMapping("/workspaceChoiceList")
    public ResponseEntity<List<WorkspaceChoiceDto>> getWorkspaceChoiceList() {
        return ResponseEntity.ok(workspaceService.retrieveAll());
    }

    @GetMapping("/textChoiceList")
    public ResponseEntity<List<TextChoiceDto>> getTextChoiceList() {
        return ResponseEntity.ok(workspaceService.getTextChoiceList());
        //return ResponseEntity.ok(result);
    }

    @GetMapping("/texts/{id}")
    public ResponseEntity<TextTileDto> getText(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(workspaceService.getText(id));
        //return ResponseEntity.ok(result);
    }

    @GetMapping("/status/{workspaceId}")
    public ResponseEntity<WorkspaceDto> getWorkspaceStatus(@PathVariable @NotNull Long workspaceId) {
        return ResponseEntity.ok(workspaceService.getWorkspace(workspaceId));
    }

    @PutMapping("/layout")
    public ResponseEntity<Boolean> saveWorkspace(@Valid @RequestBody @NotNull WorkspaceDto workspaceDto) throws Exception {
        mediator.sendXResult(new SaveWorkspaceRequest(workspaceDto));

        return ResponseEntity.ok(true);
    }
}