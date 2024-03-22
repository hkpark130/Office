package kr.co.direa.office.service;

import kr.co.direa.office.domain.ApprovalDevices;
import kr.co.direa.office.domain.Approvals;
import kr.co.direa.office.domain.Comments;
import kr.co.direa.office.domain.Users;
import kr.co.direa.office.dto.CommentDto;
import kr.co.direa.office.dto.NotificationDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.repository.ApprovalDevicesRepository;
import kr.co.direa.office.repository.ApprovalsRepository;
import kr.co.direa.office.repository.CommentsRepository;
import kr.co.direa.office.vo.ApplicationCommentVo;
import kr.co.direa.office.vo.DeviceApplicationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static kr.co.direa.office.constant.Constants.COMMENT_TYPE;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final ApprovalDevicesRepository approvalDevicesRepository;
    private final UsersService usersService;
    @Value("${constants.admin}") private String admin;

    public void save(CommentDto requestDto){
        Approvals approvalDevices = approvalDevicesRepository.findById(requestDto.getApprovalId()).orElseThrow(
                ()-> new CustomException(CustomErrorCode.NOT_FOUND_APPROVAL));
        commentsRepository.save(requestDto.toEntity(approvalDevices));
    }

    public void update(Long id, CommentDto requestDto){
        Comments comments = commentsRepository.findById(id).orElseThrow(
                ()-> new CustomException(CustomErrorCode.NOT_FOUND_COMMENT,
                        "해당 댓글 없음 id=" + id));
        comments.setComment(requestDto.getComment());
        commentsRepository.save(comments);

    }

    public void delete(Long id){
        Comments comments = commentsRepository.findById(id).orElseThrow(
                ()-> new CustomException(CustomErrorCode.NOT_FOUND_COMMENT,
                        "해당 댓글 없음 id=" + id));
        commentsRepository.delete(comments);
    }

    public List<CommentDto> findAllByApprovalId(Long approval_id) {
        Approvals approvalDevices = approvalDevicesRepository.findById(approval_id).orElseThrow(
                ()-> new CustomException(CustomErrorCode.NOT_FOUND_APPROVAL,
                        "해당 신청 없음 approval_id=" + approval_id));
        return commentsRepository.findAllByApprovalId(approvalDevices).stream()
                .map(CommentDto::new)
                .toList();
    }


    public CommentDto convertFromRequest(ApplicationCommentVo request) {
        Users user = usersService.findByUsername(request.getUserName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=" + request.getUserName()));

        CommentDto commentDto = new CommentDto();
        commentDto.setApprovalId(request.getApprovalId());
        commentDto.setComment(request.getComment());
        commentDto.setUserId(user);
        commentDto.setApplicant(request.getApplicant());
        return commentDto;
    }

    public void convertNotificationFromComment(NotificationDto notificationDto, CommentDto commentDto) {
        notificationDto.setUserName(commentDto.getUserId().getUsername());
        notificationDto.setType(COMMENT_TYPE);
        notificationDto.setReceiver((admin.equals(notificationDto.getUserName()))?commentDto.getApplicant():admin);

        notificationDto.setSubject(
                "신청 번호 " + commentDto.getApprovalId() + " 에 대한 " +
                        notificationDto.getUserName() + " 님의 댓글이 등록되었습니다."
        );
        notificationDto.setLink("/detail-approval-device/"+commentDto.getApprovalId());
        notificationDto.setDate(notificationDto.getFormattedCreatedDate(commentDto.getCreatedDate()));
        notificationDto.setIcon();
    }
}
