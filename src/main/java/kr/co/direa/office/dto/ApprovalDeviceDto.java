package kr.co.direa.office.dto;

import kr.co.direa.office.domain.ApprovalDevices;
import kr.co.direa.office.domain.Devices;
import kr.co.direa.office.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ApprovalDeviceDto implements Serializable {
    private String approvalInfo;
    private Long approvalId;
    private Users userId;
    private String userName;
    private String realUser;
    private String reason;
    private Users approverId;
    private String deviceId;
    private String deviceStatus;
    private String devicePurpose;
    private String categoryName;
    private String img;
    private String type;
    private LocalDateTime createdDate;
    private LocalDateTime deadline;

    public ApprovalDeviceDto() {
        this.createdDate = LocalDateTime.now();
    }

    @Builder
    public ApprovalDeviceDto(ApprovalDevices entity) {
        this.approvalInfo = entity.getApprovalInfo();
        this.approvalId = entity.getId();
        this.userId = (entity.getUserId() != null) ? entity.getUserId() : null;
        this.userName = (this.userId != null) ? this.userId.getUsername() : null;
        this.realUser = (entity.getDeviceId() != null) ? entity.getDeviceId().getRealUser() : null;
        this.reason = entity.getReason();
        this.approverId = entity.getApproverId();
        this.deviceId = (entity.getDeviceId() != null) ? entity.getDeviceId().getId() : null;
        this.categoryName = (entity.getDeviceId() != null) ? entity.getDeviceId().getCategoryId().getName() : null;
        this.deviceStatus = (entity.getDeviceId() != null) ? entity.getDeviceId().getStatus() : null;
        this.devicePurpose = (entity.getDeviceId() != null) ? entity.getDeviceId().getPurpose() : null;
        this.img = entity.getImg();
        this.type = entity.getType();
        this.createdDate = (entity.getCreatedDate() != null) ? entity.getCreatedDate() : null;
        this.deadline = (entity.getDeadline() != null) ? entity.getDeadline() : null;
    }

    public ApprovalDevices toEntity() {
        return ApprovalDevices.builder()
                .approvalInfo(approvalInfo)
                .approverId((approverId != null) ? approverId : null)
                .userId((userId != null) ? userId : null)
                .deviceId((deviceId != null) ? Devices.builder().id(deviceId).build() : null )
                .reason(reason)
                .type(type)
                .img(img)
                .deadline(deadline)
                .build();
    }

}
