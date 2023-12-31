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
@NoArgsConstructor
public class ApprovalDeviceDto implements Serializable {
    private String approvalInfo;
    private Long approvalId;
    private Users userId;
    private String userName;
    private String reason;
    private Boolean urgency;
    private Users approverId;
    private String deviceId;
    private Boolean deviceStatus;
    private String devicePurpose;
    private String categoryName;
    private String img;
    private String type;
    private LocalDateTime createdDate;

    @Builder
    public ApprovalDeviceDto(ApprovalDevices entity) {
        this.approvalInfo = entity.getApprovalInfo();
        this.approvalId = entity.getId();
        this.userId = (entity.getUserId() != null) ? entity.getUserId() : null;
        this.userName = (this.userId != null) ? this.userId.getUsername() : null;
        this.reason = entity.getReason();
        this.urgency = entity.getUrgency();
        this.approverId = entity.getApproverId();
        this.deviceId = (entity.getDeviceId() != null) ? entity.getDeviceId().getId() : null;
        this.categoryName = (entity.getDeviceId() != null) ? entity.getDeviceId().getCategoryId().getName() : null;
        this.deviceStatus = (entity.getDeviceId() != null) ? entity.getDeviceId().getStatus() : null;
        this.devicePurpose = (entity.getDeviceId() != null) ? entity.getDeviceId().getPurpose() : null;
        this.img = entity.getImg();
        this.type = entity.getType();
        this.createdDate = (entity.getCreatedDate() != null) ? entity.getCreatedDate() : null;
    }

    public ApprovalDevices toEntity() {
        return ApprovalDevices.builder()
                .approvalInfo(approvalInfo)
                .approverId((approverId != null) ? approverId : null)
                .userId((userId != null) ? userId : null)
                .deviceId((deviceId != null) ? Devices.builder().id(deviceId).build() : null )
                .reason(reason)
                .urgency(urgency)
                .type(type)
                .img(img)
                .build();
    }

}
