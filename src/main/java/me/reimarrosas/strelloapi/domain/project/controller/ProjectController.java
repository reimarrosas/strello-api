package me.reimarrosas.strelloapi.domain.project.controller;

import lombok.RequiredArgsConstructor;
import me.reimarrosas.strelloapi.domain.project.dto.ProjectDto;
import me.reimarrosas.strelloapi.domain.project.mapper.ProjectResponseMapper;
import me.reimarrosas.strelloapi.domain.project.service.ProjectService;
import me.reimarrosas.strelloapi.domain.user.dto.UsernameDto;
import me.reimarrosas.strelloapi.utils.ResponsePayload;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponsePayload getUserProjects() {
        return ResponsePayload.builder()
                .message("User projects fetched successfully!")
                .success(true)
                .payload(ProjectResponseMapper.INSTANCE.destToSrc(projectService.getUserProjects()))
                .build();
    }

    @PostMapping
    public ResponsePayload createUserProject(@Valid @RequestBody ProjectDto dto) {
        projectService.createUserProject(dto);

        return ResponsePayload.builder()
                .message("User project created successfully!")
                .success(true)
                .build();
    }

    @PatchMapping("/{id}")
    public ResponsePayload updateUserProject(@PathVariable Long id, @Valid @RequestBody ProjectDto dto) {
        projectService.updateUserProject(id, dto);

        return ResponsePayload.builder()
                .message("User project " + id + " updated successfully!")
                .success(true)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponsePayload deleteUserProject(@PathVariable Long id) {
        projectService.deleteUserProject(id);

        return ResponsePayload.builder()
                .message("User project " + id + " deleted successfully!")
                .success(true)
                .build();
    }

    @PostMapping("/{id}")
    public ResponsePayload addUserAccessToProject(@PathVariable Long id, @Valid @RequestBody UsernameDto dto) {
        projectService.addUserAccessToProject(id, dto);

        return ResponsePayload.builder()
                .message("User " + dto.getEmail() + " added successfully to project " + id + "!")
                .success(true)
                .build();
    }
}
