package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "departments")
@Table(name = "departments")
@Getter
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Departments() {

    }

    @Builder
    public Departments(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
