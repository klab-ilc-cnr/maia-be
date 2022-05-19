package it.cnr.ilc.projectx.controller;

import it.cnr.ilc.projectx.dto.CreateUserDto;
import it.cnr.ilc.projectx.dto.TextDto;
import it.cnr.ilc.projectx.dto.UpdateUserDto;
import it.cnr.ilc.projectx.dto.UserDto;
import it.cnr.ilc.projectx.mediator.Mediator;
import it.cnr.ilc.projectx.model.User;
import it.cnr.ilc.projectx.request.CreateUserRequest;
import it.cnr.ilc.projectx.request.UpdateUserRequest;
import it.cnr.ilc.projectx.service.UserService;
import it.cnr.ilc.projectx.service.WorkspaceService;
import it.cnr.ilc.projectx.utils.UserUtils;
import it.cnr.ilc.projectx.xresults.XResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
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

    @GetMapping("/texts")
    public ResponseEntity<List<TextDto>> getTexts() {
        return ResponseEntity.ok(workspaceService.getTexts());
        //return ResponseEntity.ok(result);
    }
}
