package kr.co.direa.office.domain;

import jakarta.persistence.*;
import kr.co.direa.office.dto.ApprovalDeviceDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity(name = "notifications")
@Table(name = "notifications")
@Getter
public class Notifications extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "link")
    private String link = "#";

    @Column(name = "is_read")
    private Boolean is_read = false;

    @Column(name = "type")
    private String type;

    @Column(name = "receiver")
    private String receiver;

    public Notifications() {

    }

    @Builder
    public Notifications(String subject, String link, String type, String receiver) {
        this.subject = subject;
        this.link = link;
        this.type = type;
        this.receiver = receiver;
    }

}
