package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "approval_devices")
@Table(name = "approval_devices")
@Getter
@Setter
public class ApprovalDevices extends Approvals {
    @ManyToOne
    @JoinColumn(name ="device_id", referencedColumnName = "id")
    private Devices deviceId;

    @Column(name = "img", nullable = true)
    private String img;

    @Column(name = "type")
    private String type;

    @Builder
    public ApprovalDevices(Users userId, String approvalInfo, String reason, Users approverId,
                           Devices deviceId, String img, Boolean urgency, String type) {
        super(userId, approvalInfo, reason, urgency, approverId);
        this.deviceId = deviceId;
        this.img = img;
        this.type = type;
    }

    public ApprovalDevices() {

    }
}
