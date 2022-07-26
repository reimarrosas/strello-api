package me.reimarrosas.strelloapi.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponseDto {
    private Long id;

    private String name;

    private String description;

    private Date createdAt;

    private Date updatedAt;
}
