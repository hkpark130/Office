package kr.co.direa.office.dto;

import kr.co.direa.office.domain.ApprovalDevices;
import kr.co.direa.office.domain.Devices;
import kr.co.direa.office.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ApprovalDeviceDto implements Serializable {
    private String approvalInfo;
    private Users userId;
    private String reason;
    private Boolean urgency;
    private Users approverId;
    private Devices deviceId;
    private String img;
    private String type;

    @Builder
    public ApprovalDeviceDto(ApprovalDevices entity){
        this.approvalInfo = entity.getApprovalInfo();
        this.userId = entity.getUserId();
        this.reason = entity.getReason();
        this.urgency = entity.getUrgency();
        this.approverId = entity.getApproverId();
        this.deviceId = entity.getDeviceId();
        this.img = entity.getImg();
        this.type = entity.getType();
    }

    public ApprovalDevices toEntity() {
        return ApprovalDevices.builder()
                .approvalInfo(approvalInfo)
                .approverId(approverId)
                .userId(userId)
                .deviceId(deviceId)
                .reason(reason)
                .urgency(urgency)
                .type(type)
                .img(img)
                .build();
    }

}
