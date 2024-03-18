package kr.co.direa.office.vo;

import lombok.Data;

@Data
public class ApplicationCommentVo {
    private Long approvalId;
    private String userName;
    private String applicant;
    private String comment;

}
