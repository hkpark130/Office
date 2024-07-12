package kr.co.direa.office.dto;

import kr.co.direa.office.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ApprovalDeviceDto implements Serializable {
    private String approvalInfo;
    private Long approvalId;
    private Users userId;
    private String userName;
    private String realUser;
    private String reason;
    private String deviceId;
    private String deviceStatus;
    private String devicePurpose;
    private String description;
    private String categoryName;
    private String img;
    private String type;
    private LocalDateTime createdDate;
    private LocalDateTime deadline;
    private String projectName;
    private Long projectId;
    private Long tmpProjectId;
    private String tmpProjectName;
    private Long tmpDepartmentId;
    private String tmpDepartmentName;
    private List<ApproverDto> approvers = new ArrayList<>();

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
        if (entity.getApprovers() != null) {
            for (Approver approver : entity.getApprovers()) {
                approvers.add(new ApproverDto(approver));
            }
        }
        this.deviceId = (entity.getDeviceId() != null) ? entity.getDeviceId().getId() : null;
        this.categoryName = (entity.getDeviceId() != null) ? entity.getDeviceId().getCategoryId().getName() : null;
        this.deviceStatus = (entity.getDeviceId() != null) ? entity.getDeviceId().getStatus() : null;
        this.devicePurpose = (entity.getDeviceId() != null) ? entity.getDeviceId().getPurpose() : null;
        this.description = (entity.getDeviceId() != null) ? entity.getDeviceId().getDescription() : null;
        this.img = entity.getImg();
        this.type = entity.getType();
        this.createdDate = (entity.getCreatedDate() != null) ? entity.getCreatedDate() : null;
        this.deadline = (entity.getDeadline() != null) ? entity.getDeadline() : null;
        this.projectName = (entity.getProjectId() != null) ? entity.getProjectId().getName() : null;
        this.projectId = (entity.getProjectId() != null) ? entity.getProjectId().getId() : null;
        this.tmpProjectId = (entity.getTmpProject() != null) ? entity.getTmpProject().getId() : null;
        this.tmpProjectName = (entity.getTmpProject() != null) ? entity.getTmpProject().getName() : null;
        this.tmpDepartmentId = (entity.getTmpDepartment() != null) ? entity.getTmpDepartment().getId() : null;
        this.tmpDepartmentName = (entity.getTmpDepartment() != null) ? entity.getTmpDepartment().getName() : null;
    }

    public ApprovalDevices toEntity() {
        return ApprovalDevices.builder()
                .approvalInfo(approvalInfo)
                .approvers(approvers.stream().map(ApproverDto::toEntity).collect(Collectors.toList()))
                .userId((userId != null) ? userId : null)
                .deviceId((deviceId != null) ? Devices.builder().id(deviceId).build() : null )
                .reason(reason)
                .type(type)
                .img(img)
                .deadline(deadline)
                .projectId((projectId != null) ? Projects.builder().id(projectId).build() : null)
                .tmpProject((tmpProjectId != null) ? Projects.builder().id(tmpProjectId).build() : null)
                .tmpDepartment((tmpDepartmentId != null) ? Departments.builder().id(tmpDepartmentId).build() : null)
                .build();
    }

}
