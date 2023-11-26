package kr.co.direa.office.domain;

import jakarta.persistence.*;

@Entity(name = "notifications")
@Table(name = "notifications")
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

}
