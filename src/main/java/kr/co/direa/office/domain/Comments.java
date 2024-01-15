package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "comments")
@Table(name = "comments")
@Getter
@Setter
public class Comments extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name ="approval_id", referencedColumnName = "id")
    private Approvals approvalId;

    @ManyToOne
    @JoinColumn(name ="user_id", referencedColumnName = "id")
    private Users user_id;

    @Column(columnDefinition = "TEXT", name = "comment")
    private String comment;

    public Comments() {

    }

    @Builder
    public Comments(Approvals approvalId, Users user_id, String comment) {
        this.approvalId = approvalId;
        this.user_id = user_id;
        this.comment = comment;
    }

}
