package kr.co.direa.office.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.direa.office.domain.Notifications;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationDto implements Serializable {
    private Long id;
    private String subject;
    private String link;
    @JsonProperty(value = "is_read")
    private boolean isRead = false;
    private String icon;
    private String userName;
    private String iconClass;
    private String date;
    private String type;

    @Builder
    public NotificationDto(Notifications entity){
        this.id = entity.getId();
        this.subject = entity.getSubject();
        this.link = entity.getLink();
        this.isRead = entity.getIs_read();
        this.type = entity.getType();
        this.date = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    public Notifications toEntity() {
        return Notifications.builder()
                .subject(subject)
                .link(link)
                .type(type)
                .build();
    }

    public NotificationDto convertNotificationFromApproval(ApprovalDeviceDto approvalDeviceDto) {
        this.userName = approvalDeviceDto.getUserId().getUsername();
        this.type = approvalDeviceDto.getType();
        this.subject = approvalDeviceDto.getDeviceId() + "의 " +
                this.type + " 신청에 대한 " +
                this.userName + " 님의 승인 요청";
        this.link = "#";
        this.date = approvalDeviceDto.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        setIcon(this);

        return this;
    }

    public NotificationDto setIcon(NotificationDto notificationDto) {
        switch (notificationDto.type) {
            case "대여":
                notificationDto.icon = "inbox";
                notificationDto.iconClass = "primary";
                break;
            case "반납":
                notificationDto.icon = "corner-down-left";
                notificationDto.iconClass = "primary";
                break;
            case "폐기":
                notificationDto.icon = "trash";
                notificationDto.iconClass = "secondary";
                break;
            case "구매":
                notificationDto.icon = "dollar-sign";
                notificationDto.iconClass = "primary";
                break;
            case "수정":
                notificationDto.icon = "edit";
                notificationDto.iconClass = "success";
                break;
            default:
                notificationDto.icon = "bell";
                notificationDto.iconClass = "secondary";
                break;
        }
        return notificationDto;
    }

}
