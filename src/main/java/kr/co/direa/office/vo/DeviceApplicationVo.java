package kr.co.direa.office.vo;

import kr.co.direa.office.domain.ApprovalDevices;
import kr.co.direa.office.domain.Devices;
import kr.co.direa.office.domain.Users;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class DeviceApplicationVo {
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
    private String description;
    private String categoryName;
    private String img;
    private String type;
    private String status;
    private Boolean isUsable;
    private LocalDateTime createdDate;
    private LocalDateTime deadline;
    private List<String> tag;

}
