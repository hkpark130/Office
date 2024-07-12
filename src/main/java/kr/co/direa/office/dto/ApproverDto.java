package kr.co.direa.office.dto;

import kr.co.direa.office.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApproverDto {
    private Long userId;
    private Long approvalId;
    private String username;
    private Boolean isApproved;
    private int step;

    @Builder
    public ApproverDto(Approver entity) {
        this.username = entity.getUsers().getUsername();
        this.userId = entity.getUsers().getId();
        this.approvalId = entity.getApprovals().getId();
        this.isApproved = entity.getIsApproved();
        this.step = entity.getStep();
    }

    @Builder
    public ApproverDto(Users user, Long approvalId, Boolean isApproved, int step) {
        this.username = user.getUsername();
        this.userId = user.getId();
        this.approvalId = approvalId;
        this.isApproved = isApproved;
        this.step = step;
    }

    public Approver toEntity() {
        return Approver.builder()
                .users((username != null) ? Users.builder().username(username).build() : null)
                .approvals((approvalId != null) ? ApprovalDevices.builder().id(approvalId).build() : null)
                .isApproved(isApproved)
                .step(step)
                .build();
    }

}
