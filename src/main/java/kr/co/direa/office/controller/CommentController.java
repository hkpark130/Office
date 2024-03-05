package kr.co.direa.office.controller;

import kr.co.direa.office.dto.CategoryDto;
import kr.co.direa.office.dto.CommentDto;
import kr.co.direa.office.dto.NotificationDto;
import kr.co.direa.office.service.CategoriesService;
import kr.co.direa.office.service.CommentsService;
import kr.co.direa.office.service.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static kr.co.direa.office.constant.Constants.ADMIN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {
    private final CommentsService commentsService;
    private final NotificationsService notificationsService;

    @PostMapping(value = "/save")
    ResponseEntity<?> save(
            @RequestBody Map<String, Object> request
    ) {
        CommentDto commentDto = commentsService.convertFromRequest(request);
        commentsService.save(commentDto);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.convertNotificationFromComment(commentDto);

        notificationsService.save(notificationDto);
        notificationsService.sendNotification("/topic/"+ADMIN, notificationsService.findByUsername(ADMIN));

        return ResponseEntity.ok(
                "success"
        );
    }

    @GetMapping(value = "/{approvalId}")
    ResponseEntity<?> findAllByApprovalId(@PathVariable Long approvalId) {
        List<CommentDto> commentDtoList = commentsService.findAllByApprovalId(approvalId);
        return ResponseEntity.ok(
                commentDtoList
        );
    }

}
