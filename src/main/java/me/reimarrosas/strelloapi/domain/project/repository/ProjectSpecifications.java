package me.reimarrosas.strelloapi.domain.project.repository;

import me.reimarrosas.strelloapi.domain.project.entity.ProjectEntity;
import me.reimarrosas.strelloapi.domain.user.service.UserService;
import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecifications {
    public static Specification<ProjectEntity> isOwnedByCurrentUser() {
        return (root, query, cb) -> cb.equal(
                root.join("allowedUsers").get("id"), UserService.getCurrentUser().getId()
        );
    }
}
