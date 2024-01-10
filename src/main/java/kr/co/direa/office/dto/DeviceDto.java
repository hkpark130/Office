package kr.co.direa.office.dto;

import kr.co.direa.office.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class DeviceDto implements Serializable {
    private String id;
    private Users userId;
    private String username;
    private Departments manageDep;
    private String manageDepName;
    private Categories categoryId;
    private Projects projectId;
    private String spec;
    private Long price;
    private String model;
    private String description;
    private String tag;
    private String company;
    private String sn;
    private Boolean status;
    private String purpose;
    private Date purchaseDate;
    private String categoryName;
    private String projectName;
    private String approvalInfo;
    private String approvalType;
    private LocalDateTime deadline;
    private List<Map<String, Object>> history;

    @Builder
    public DeviceDto(Devices entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.username = (this.userId != null) ? this.userId.getUsername() : null;
        this.manageDep = entity.getManageDep();
        this.manageDepName = (this.manageDep != null) ? this.manageDep.getName() : null;
        this.categoryId = entity.getCategoryId();
        this.categoryName = (this.categoryId != null) ? this.categoryId.getName() : null;
        this.projectId = entity.getProjectId();
        this.spec = entity.getSpec();
        this.price = entity.getPrice();
        this.model = entity.getModel();
        this.description = entity.getDescription();
        this.tag = entity.getTag();
        this.company = entity.getCompany();
        this.sn = entity.getSn();
        this.status = entity.getStatus();
        this.purpose = entity.getPurpose();
        this.purchaseDate = entity.getPurchaseDate();
        this.projectName = (this.projectId != null) ? this.projectId.getName() : null;

        Optional<ApprovalDevices> latestApprovalDevice = entity.getApprovalDevices().stream()
                .max(Comparator.comparing(ApprovalDevices::getCreatedDate,
                        Comparator.nullsFirst(Comparator.naturalOrder())));
        if (latestApprovalDevice.isPresent()) {
            this.approvalInfo = latestApprovalDevice.get().getApprovalInfo();
            this.approvalType = latestApprovalDevice.get().getType();
            this.deadline = latestApprovalDevice.get().getDeadline();
        } else {
            this.approvalInfo = "사용가능";
        }
    }

    public Devices toEntity() {
        return Devices.builder()
                .id(id)
                .userId(userId)
                .manageDep(manageDep)
                .categoryId(categoryId)
                .projectId(projectId)
                .spec(spec)
                .price(price)
                .model(model)
                .description(description)
                .tag(tag)
                .company(company)
                .sn(sn)
                .status(status)
                .purpose(purpose)
                .purchaseDate(purchaseDate)
                .build();
    }

}