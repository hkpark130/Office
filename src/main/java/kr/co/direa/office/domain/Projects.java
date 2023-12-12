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

    public Projects() {

    }

    @Builder
    public Projects(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
