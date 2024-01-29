package kr.co.direa.office.dto;

import kr.co.direa.office.domain.Departments;
import kr.co.direa.office.domain.Projects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDto implements Serializable {
    private Long id;
    private String name;
    private String code;

    @Builder
    public DepartmentDto(Departments entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
    }

    public Departments toEntity() {
        return Departments.builder()
                .id(id)
                .name(name)
                .code(code)
                .build();
    }

}
