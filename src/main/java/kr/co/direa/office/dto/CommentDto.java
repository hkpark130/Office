package kr.co.direa.office.dto;

import kr.co.direa.office.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class CommentDto implements Serializable {
    private Long id;
    private Long approvalId;
    private Users userId;
    private String comment;
    private LocalDateTime createdDate;

    public CommentDto() {
        this.createdDate = LocalDateTime.now();
    }

    @Builder
    public CommentDto(Comments entity){
        this.id = entity.getId();
        this.approvalId = entity.getApprovalId().getId();
        this.userId = entity.getUser_id();
        this.comment = entity.getComment();
        this.createdDate = entity.getCreatedDate();
    }

    public Comments toEntity(Approvals approval) {
        return Comments.builder()
                .approvalId(approval)
                .user_id(userId)
                .comment(comment)
                .build();
    }

}
