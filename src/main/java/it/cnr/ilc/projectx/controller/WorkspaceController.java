package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.TextChoiceDto;
import it.cnr.ilc.projectx.dto.TextTileDto;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.service.WorkspaceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
