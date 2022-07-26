package me.reimarrosas.strelloapi.domain.project.mapper;

import me.reimarrosas.strelloapi.domain.project.dto.ProjectResponseDto;
import me.reimarrosas.strelloapi.domain.project.entity.ProjectEntity;
import me.reimarrosas.strelloapi.utils.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectResponseMapper extends IMapper<ProjectResponseDto, ProjectEntity> {
    ProjectResponseMapper INSTANCE = Mappers.getMapper(ProjectResponseMapper.class);
}
