package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "departments")
@Table(name = "departments")
@Getter
@Setter
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
