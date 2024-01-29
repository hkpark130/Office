package kr.co.direa.office.dto;

import kr.co.direa.office.domain.Categories;
import kr.co.direa.office.domain.Tags;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TagDto {
    private Long id;
    private String name;

    @Builder
    public TagDto(Tags entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Tags toEntity() {
        return Tags.builder()
                .id(id)
                .name(name)
                .build();
    }

}
