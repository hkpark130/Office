package kr.co.direa.office.dto;

import kr.co.direa.office.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
<<<<<<< HEAD
import java.util.List;
=======
import java.util.Optional;
>>>>>>> 71d3fd363d4637316c12f2a75900780f6443628c

@Getter
@Setter
@NoArgsConstructor
public class DeviceDto implements Serializable {
    private String id;
    private Users userId;
    private Departments manageDep;
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
<<<<<<< HEAD
    private List<ApprovalDevices> approvalDevices;
=======
    private String approvalInfo;
>>>>>>> 71d3fd363d4637316c12f2a75900780f6443628c

    @Builder
    public DeviceDto(Devices entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.manageDep = entity.getManageDep();
        this.categoryId = entity.getCategoryId();
        this.categoryName = this.categoryId.getName();
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
<<<<<<< HEAD
        this.approvalDevices = entity.getApprovalDevices();
=======

        Optional<ApprovalDevices> latestApprovalDevice = entity.getApprovalDevices().stream()
                .max(Comparator.comparing(ApprovalDevices::getCreatedDate));
        if (latestApprovalDevice.isPresent()) {
            this.approvalInfo = latestApprovalDevice.get().getApprovalInfo();
        } else {
            this.approvalInfo = "사용가능";
        }
>>>>>>> 71d3fd363d4637316c12f2a75900780f6443628c
    }

    public Devices toEntity() {
        return Devices.builder()
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
                .approvalDevices(approvalDevices)
                .purchaseDate(purchaseDate)

                .build();
    }

}
