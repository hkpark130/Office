package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "projects")
@Table(name = "projects")
@Getter
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "code", nullable = true)
    private String code;

    public Projects() {

    }

    @Builder
    public Projects(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

}
