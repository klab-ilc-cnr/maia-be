package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.dto.CreateWorkspaceDto;
import it.cnr.ilc.maia.dto.TextChoiceDto;
import it.cnr.ilc.maia.dto.UpdateWorkspaceChoiceDto;
import it.cnr.ilc.maia.dto.WorkspaceChoiceDto;
import it.cnr.ilc.maia.dto.WorkspaceDto;
import it.cnr.ilc.maia.mediator.Mediator;
import it.cnr.ilc.maia.request.CreateWorkspaceRequest;
import it.cnr.ilc.maia.request.DeleteWorkspaceRequest;
import it.cnr.ilc.maia.request.SaveWorkspaceRequest;
import it.cnr.ilc.maia.request.UpdateWorkspaceChoiceRequest;
import it.cnr.ilc.maia.service.WorkspaceService;
import it.cnr.ilc.maia.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
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

    /*    @GetMapping("/texts/{id}")
    public ResponseEntity<TextTileDto> getText(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(workspaceService.getText(id));
        //return ResponseEntity.ok(result);
    }*/
    @GetMapping("/status/{workspaceId}")
    public ResponseEntity<WorkspaceDto> getWorkspaceStatus(@PathVariable @NotNull Long workspaceId) {
        return ResponseEntity.ok(workspaceService.getWorkspace(workspaceId));
    }

    @GetMapping("/name/{workspaceId}")
    public ResponseEntity<String> getWorkspaceName(@PathVariable @NotNull Long workspaceId) {
        return ResponseEntity.ok(workspaceService.getWorkspaceName(workspaceId));
    }

    @PutMapping("/layout")
    public ResponseEntity<Boolean> saveWorkspace(@Valid @RequestBody @NotNull WorkspaceDto workspaceDto) throws Exception {
        mediator.sendXResult(new SaveWorkspaceRequest(workspaceDto));
        return ResponseEntity.ok(true);
    }
}
