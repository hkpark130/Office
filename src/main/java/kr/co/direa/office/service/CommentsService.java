package kr.co.direa.office.service;

import kr.co.direa.office.domain.ApprovalDevices;
import kr.co.direa.office.domain.Approvals;
import kr.co.direa.office.domain.Comments;
import kr.co.direa.office.domain.Users;
import kr.co.direa.office.dto.CommentDto;
import kr.co.direa.office.exception.CustomException;
import kr.co.direa.office.exception.code.CustomErrorCode;
import kr.co.direa.office.repository.ApprovalDevicesRepository;
import kr.co.direa.office.repository.ApprovalsRepository;
import kr.co.direa.office.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final ApprovalDevicesRepository approvalDevicesRepository;
    private final UsersService usersService;

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


    public CommentDto convertFromRequest(Map<String, Object> request) {
        Users user = usersService.findByUsername(request.get("userName").toString())
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_USER,
                        "해당 유저가 없습니다. username=" + request.get("userName")));

        CommentDto commentDto = new CommentDto();
        commentDto.setApprovalId(Long.valueOf(request.get("approvalId").toString()));
        commentDto.setComment(request.get("comment").toString());
        commentDto.setUserId(user);
        commentDto.setApplicant(request.get("applicant").toString());
        return commentDto;
    }
}
