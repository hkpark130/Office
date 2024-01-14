package kr.co.direa.office.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "approvals")
@Table(name = "approvals")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Approvals extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name ="user_id", referencedColumnName = "id")
    private Users userId;

    @Column(name = "approval_info")
    private String approvalInfo;

    @Column(columnDefinition = "TEXT", name = "reason")
    private String reason;

    @Column(name = "urgency")
    private Boolean urgency;

    @ManyToOne
    @JoinColumn(name ="approver_id", referencedColumnName = "id")
    private Users approverId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deadline")
    private LocalDateTime deadline;

    public Approvals() {

    }

    public Approvals(Users userId, String approvalInfo, String reason,
                     Boolean urgency, Users approverId, LocalDateTime deadline) {
        this.userId = userId;
        this.approvalInfo = approvalInfo;
        this.reason = reason;
        this.urgency = urgency;
        this.approverId = approverId;
        this.deadline = deadline;
    }

}
