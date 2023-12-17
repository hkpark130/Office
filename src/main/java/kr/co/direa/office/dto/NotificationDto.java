package kr.co.direa.office.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.direa.office.domain.Notifications;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class NotificationDto implements Serializable {
    private Long id;
    private String subject;
    private String link;
    @JsonProperty(value = "is_read")
    private boolean isRead = false;

    @Builder
    public NotificationDto(Notifications entity){
        this.id = entity.getId();
        this.subject = entity.getSubject();
        this.link = entity.getLink();
        this.isRead = entity.getIs_read();
    }

    public Notifications toEntity() {
        return Notifications.builder()
                .subject(subject)
                .link(link)
                .build();
    }

    public NotificationDto convertNotificationFromApproval(ApprovalDeviceDto approvalDeviceDto) {
        this.subject = approvalDeviceDto.getDeviceId().getId() + "의 " +
                approvalDeviceDto.getType() + "에 대한 " +
                approvalDeviceDto.getUserId().getUsername() + " 님의 승인 요청";
        this.link = "#";
        return this;
    }

}
