package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity(name = "approvals")
@Table(name = "approvals")
@Getter
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

    public Approvals() {

    }

    public Approvals(Users userId, String approvalInfo, String reason,
                     Boolean urgency, Users approverId) {
        this.userId = userId;
        this.approvalInfo = approvalInfo;
        this.reason = reason;
        this.urgency = urgency;
        this.approverId = approverId;
    }

}
