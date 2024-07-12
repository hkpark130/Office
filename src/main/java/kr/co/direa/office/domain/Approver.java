package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table(name = "approver")
@Entity(name = "approver")
@Getter
@Setter
public class Approver extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name ="approval_id")
    private Approvals approvals;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "step")
    private int step;

    public Approver() {

    }

    @Builder
    public Approver(Users users, Approvals approvals, Boolean isApproved, int step) {
        this.users = users;
        this.approvals = approvals;
        this.isApproved = isApproved;
        this.step = step;
    }
}
