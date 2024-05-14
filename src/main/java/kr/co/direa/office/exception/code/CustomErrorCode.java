package kr.co.direa.office.exception.code;

public enum CustomErrorCode {

    NOT_FOUND_USER(404, "B001", "해당 유저가 없습니다."),
    NOT_FOUND_APPROVAL(404, "B002", "해당 신청이 없습니다."),
    NOT_FOUND_DEVICE(404, "B003", "해당 장비가 없습니다."),
    NOT_FOUND_COMMENT(404, "B004", "해당 댓글이 없습니다."),
    NOT_FOUND_NOTIFICATION(404, "B005", "해당 알림이 없습니다."),
    NOT_FOUND_PROJECT(404, "B006", "해당 프로젝트가 없습니다."),
    NOT_FOUND_DEPARTMENT(404, "B007", "해당 부서가 없습니다."),

    METHOD_NOT_ALLOWED(500, "D009", "해당 API에서 지원하지 않는 Method 입니다."),
    LOAD_BALANCER_ERROR(500, "D006", "LoadBalancer Error!"),


    INTERNAL_SERVER_ERROR(500, "D099", "서버 에러");

    private int status;
    private final String rsp_code;
    private final String rsp_msg;

    public int getStatus() {
        return this.status;
    }

    public String getRsp_code() {
        return this.rsp_code;
    }

    public String getRsp_msg(){
        return this.rsp_msg;
    }

    CustomErrorCode(int status, final String rsp_code, final String rsp_msg) {
        this.status = status;
        this.rsp_code = rsp_code;
        this.rsp_msg = rsp_msg;
    }
}
