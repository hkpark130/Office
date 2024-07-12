package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "approvals", cascade = CascadeType.REMOVE)
    private List<Approver> approvers;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deadline")
    private LocalDateTime deadline;

    public Approvals() {

    }

    public Approvals(Long id, Users userId, String approvalInfo, String reason,
                     List<Approver> approvers, LocalDateTime deadline) {
        this.id = id;
        this.userId = userId;
        this.approvalInfo = approvalInfo;
        this.reason = reason;
        this.deadline = deadline;
        this.approvers = approvers;
    }

}
