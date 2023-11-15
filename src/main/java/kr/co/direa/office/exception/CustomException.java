package kr.co.direa.office.exception;

import kr.co.direa.office.exception.code.CustomErrorCode;
import org.springframework.stereotype.Component;

public class CustomException extends RuntimeException {
    private CustomErrorCode customErrorCode;

    public CustomException() {
    }

    public CustomException(Exception e) {
        super(e);
    }

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getRsp_msg());
        this.customErrorCode = customErrorCode;
    }

    public CustomException(CustomErrorCode customErrorCode, String extMessage) {
        super(customErrorCode.getRsp_msg() + " 상세내용[" + extMessage + "]");
        this.customErrorCode = customErrorCode;
    }

    public CustomErrorCode getErrorCode() {
        return customErrorCode;
    }
}
