package me.reimarrosas.strelloapi.domain.project.controller;

import lombok.RequiredArgsConstructor;
import me.reimarrosas.strelloapi.domain.project.dto.ProjectDto;
import me.reimarrosas.strelloapi.domain.project.mapper.ProjectResponseMapper;
import me.reimarrosas.strelloapi.domain.project.service.ProjectService;
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
                .message("User project successfully created!")
                .success(true)
                .build();
    }
}