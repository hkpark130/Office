package kr.co.direa.office.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.direa.office.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DeviceDto implements Serializable {
    private Long id;
    private Users userId;
    private Departments manageDep;
    private Categories categoryId;
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
    private String img;

    @Builder
    public DeviceDto(Devices entity){
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.manageDep = entity.getManageDep();
        this.categoryId = entity.getCategoryId();
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
    }

    public Devices toEntity() {
        return Devices.builder()
                .userId(userId)
                .manageDep(manageDep)
                .categoryId(categoryId)
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
