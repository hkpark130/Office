package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Entity(name = "tags")
@Table(name = "tags")
@Getter
public class Tags extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = true)
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<DeviceTag> deviceTags;

    public Tags() {

    }

    @Builder
    public Tags(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tags(String name) {
        this.name = name;
    }
}
