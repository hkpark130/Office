package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "notifications")
@Table(name = "notifications")
@Getter
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "link")
    private String link;

    @Column(name = "is_read")
    private Boolean is_read;

    public Notifications() {

    }

    @Builder
    public Notifications(Long id, String subject, String link, Boolean is_read) {
        this.id = id;
        this.subject = subject;
        this.link = link;
        this.is_read = is_read;
    }
}
