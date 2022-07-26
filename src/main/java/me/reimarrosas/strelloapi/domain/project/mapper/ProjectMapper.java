package me.reimarrosas.strelloapi.domain.project.mapper;

import me.reimarrosas.strelloapi.domain.project.dto.ProjectDto;
import me.reimarrosas.strelloapi.domain.project.entity.ProjectEntity;
import me.reimarrosas.strelloapi.utils.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper extends IMapper<ProjectDto, ProjectEntity> {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
}
