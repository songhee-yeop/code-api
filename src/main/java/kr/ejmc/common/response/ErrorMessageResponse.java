package kr.ejmc.common.response;

import lombok.Getter;

@Getter
public class ErrorMessageResponse {
    private String message;

    private ErrorMessageResponse(String message) {
        this.message = message;
    }

    public static ErrorMessageResponse of(Exception e) {
        return new ErrorMessageResponse(e.getMessage());
    }
}
