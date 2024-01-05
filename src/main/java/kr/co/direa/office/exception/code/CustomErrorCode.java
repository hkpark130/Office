package kr.co.direa.office.exception.code;

public enum CustomErrorCode {

    METHOD_NOT_ALLOWED(500, "D009", "해당 API에서 지원하지 않는 Method 입니다."),
    LOAD_BALANCER_ERROR(500, "D006", "LoadBalancer Error!");

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
