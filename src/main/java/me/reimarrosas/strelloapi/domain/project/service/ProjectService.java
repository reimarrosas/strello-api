package me.reimarrosas.strelloapi.domain.project.service;

import lombok.RequiredArgsConstructor;
import me.reimarrosas.strelloapi.domain.project.mapper.ProjectMapper;
import me.reimarrosas.strelloapi.domain.project.dto.ProjectDto;
import me.reimarrosas.strelloapi.domain.project.entity.ProjectEntity;
import me.reimarrosas.strelloapi.domain.project.repository.ProjectRepository;
import me.reimarrosas.strelloapi.domain.project.repository.ProjectSpecifications;
import me.reimarrosas.strelloapi.exception.HttpForbiddenException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static me.reimarrosas.strelloapi.domain.project.repository.ProjectSpecifications.isOwnedByCurrentUser;
import static me.reimarrosas.strelloapi.domain.user.service.UserService.getCurrentUser;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public void createUserProject(ProjectDto dto) {
        var newProject = ProjectMapper.INSTANCE.srcToDest(dto);
        var currentUser = getCurrentUser();

        newProject.getAllowedUsers().add(currentUser);

        projectRepository.save(newProject);
    }

    public List<ProjectEntity> getUserProjects() {
        return projectRepository.findAll(ProjectSpecifications.isOwnedByCurrentUser());
    }

    public void updateUserProject(Long id, ProjectDto dto) {
        var project = authorizedFindOneProject(id);

        project.setName(dto.getName());
        project.setDescription(dto.getDescription());

        projectRepository.save(project);
    }

    public void deleteUserProject(Long id) {
        projectRepository.delete(authorizedFindOneProject(id));
    }

    private ProjectEntity authorizedFindOneProject(Long id) throws HttpForbiddenException {
        return projectRepository.findOne(isOwnedByCurrentUser(id))
                .orElseThrow(() -> new HttpForbiddenException("Cannot access given project id " + id + "!"));
    }
}
