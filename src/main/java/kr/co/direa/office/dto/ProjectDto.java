package kr.co.direa.office.dto;

import kr.co.direa.office.domain.Categories;
import kr.co.direa.office.domain.Projects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDto implements Serializable {
    private Long id;
    private String name;

    @Builder
    public ProjectDto(Projects entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Projects toEntity() {
        return Projects.builder()
                .id(id)
                .name(name)
                .build();
    }

}
