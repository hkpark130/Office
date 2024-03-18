package kr.co.direa.office.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.direa.office.domain.Notifications;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static kr.co.direa.office.constant.Constants.*;

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
    private String receiver;

    @Builder
    public NotificationDto(Notifications entity){
        this.id = entity.getId();
        this.subject = entity.getSubject();
        this.link = entity.getLink();
        this.isRead = entity.getIs_read();
        this.type = entity.getType();
        this.date = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.receiver = entity.getReceiver();
    }

    public Notifications toEntity() {
        return Notifications.builder()
                .subject(subject)
                .link(link)
                .type(type)
                .receiver(receiver)
                .build();
    }

    public String getFormattedCreatedDate(LocalDateTime createdDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return createdDate.format(formatter);
    }

    public NotificationDto setIcon() {
        switch (this.type) {
            case "대여":
                this.icon = "inbox";
                this.iconClass = "primary";
                break;
            case "반납":
                this.icon = "corner-down-left";
                this.iconClass = "primary";
                break;
            case "폐기":
                this.icon = "trash";
                this.iconClass = "secondary";
                break;
            case "구매":
                this.icon = "dollar-sign";
                this.iconClass = "primary";
                break;
            case "수정":
                this.icon = "edit";
                this.iconClass = "success";
                break;
            case "댓글":
                this.icon = "message-square";
                this.iconClass = "primary";
                break;
            default:
                this.icon = "bell";
                this.iconClass = "secondary";
                break;
        }
        return this;
    }

}
