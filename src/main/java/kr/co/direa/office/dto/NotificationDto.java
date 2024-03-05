package kr.co.direa.office.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.direa.office.domain.Notifications;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public NotificationDto convertNotificationFromApproval(ApprovalDeviceDto approvalDeviceDto,
                                                           Long approvalId, String approvalInfo) {
        this.userName = approvalDeviceDto.getUserId().getUsername();
        this.type = approvalDeviceDto.getType();
        this.receiver = this.userName;
        this.link = "/detail-approval-device/"+approvalId;
        if (approvalInfo != null) {
            switch (approvalInfo) {
                case APPROVAL_COMPLETED:
                    this.subject = approvalDeviceDto.getDeviceId() + "의 " +
                            this.type + " 신청이 승인되었습니다.";
                    break;
                case APPROVAL_REJECT:
                    this.subject = approvalDeviceDto.getDeviceId() + "의 " +
                            this.type + " 신청이 반려되었습니다.";
                    break;
                default:
                    this.receiver = ADMIN;
                    this.subject = approvalDeviceDto.getDeviceId() + "의 " +
                            this.type + " 신청에 대한 " +
                            this.userName + " 님의 승인 요청이 있습니다.";
                    this.link = "/check-approval-device/"+approvalId;
                    break;
            }
        } else {
            this.receiver = ADMIN;
            this.subject = approvalDeviceDto.getDeviceId() + "의 " +
                    this.type + " 신청에 대한 " +
                    this.userName + " 님의 승인 요청이 있습니다.";
            this.link = "/check-approval-device/"+approvalId;
        }

        this.date = getFormattedCreatedDate(approvalDeviceDto.getCreatedDate());
        setIcon(this);

        return this;
    }

    public NotificationDto convertNotificationFromComment(CommentDto commentDto) {
        this.userName = commentDto.getUserId().getUsername();
        this.type = "댓글";
        this.receiver = (ADMIN.equals(this.userName))?commentDto.getApplicant():ADMIN;
        this.subject = "신청 번호 " +
                commentDto.getApprovalId() + " 에 대한 " +
                this.userName + " 님의 댓글이 등록되었습니다.";
        this.link = "/detail-approval-device/"+commentDto.getApprovalId();
        this.date = getFormattedCreatedDate(commentDto.getCreatedDate());
        setIcon(this);

        return this;
    }

    public String getFormattedCreatedDate(LocalDateTime createdDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return createdDate.format(formatter);
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
            case "댓글":
                notificationDto.icon = "message-square";
                notificationDto.iconClass = "primary";
                break;
            default:
                notificationDto.icon = "bell";
                notificationDto.iconClass = "secondary";
                break;
        }
        return notificationDto;
    }

}
