package kr.co.direa.office.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.direa.office.domain.Categories;
import kr.co.direa.office.domain.Notifications;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto implements Serializable {
    private Long id;
    private String name;
    private String img;

    @Builder
    public CategoryDto(Categories entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.img = entity.getImg();
    }

    public Categories toEntity() {
        return Categories.builder()
                .id(id)
                .name(name)
                .img(img)
                .build();
    }

}
