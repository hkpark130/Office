package kr.co.direa.office.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "categories")
@Table(name = "categories")
@Getter
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "img", nullable = true)
    private String img;

    public Categories() {

    }

    @Builder
    public Categories(Long id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

}
