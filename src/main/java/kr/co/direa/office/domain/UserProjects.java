package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "user_projects")
@Table(name = "user_projects")
@Getter
public class UserProjects extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name ="user_id", referencedColumnName = "id")
    private Users users;

    @ManyToOne
    @JoinColumn(name ="project_id", referencedColumnName = "id")
    private Projects projects;

    @Builder
    public UserProjects(Users users, Projects projects) {
        this.users = users;
        this.projects = projects;
    }

    public UserProjects() {

    }
}
