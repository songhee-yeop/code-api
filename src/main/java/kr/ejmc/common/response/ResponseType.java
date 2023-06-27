package kr.ejmc.common.response;

import lombok.Getter;

@Getter
public enum ResponseType {
    OK("200", "클라이언트의 요청을 서버가 정상적으로 처리했다."),
    CREATED("201", "클라이언트의 요청을 서버가 정상적으로 처리했고 새로운 리소스가 생겼다."),
    ACCEPTED("202", "클라이언트의 요청을 서버가 정상적으로 처리했고 새로운 리소스가 생겼다."),
    NO_CONTENT("204", "클라이언트의 요청은 정상적이다. 하지만 컨텐츠를 제공하지 않는다."),
    ALREADY_REPORTED("208", "클라이언트의 요청은 정상적이지만 이미 기록된 코드"),
    BAD_REQUEST("400", "클라이언트의 요청이 유효하지 않아 더 이상 작업을 진행하지 않는 경우"),
    UNAUTHORIZED("401", "클라이언트가 권한이 없기 때문에 작업을 진행할 수 없는 경우(인증을 받지않음)"),
    FORBIDDEN("403", "클라이언트가 권한이 없기 때문에 작업을 진행할 수 없는 경우(인증을 받았지만 권한이 없음)"),

    NOT_ACCEPTABLE("406", "허용되지 않음"),
    NOT_FOUND("404", "클라이언트가 요청한 자원이 존재하지 않다."),
    METHOD_NOT_ALLOWED("405", "클라이언트의 요청이 허용되지 않는 메소드인 경우"),
    CONFLICT("409", "클라이언트의 요청이 서버의 상태와 충돌이 발생한 경우"),
    TOO_MANY_REQUESTS("429", "클라이언트가 일정 시간 동안 너무 많은 요청을 보낸 경우"),
    INTERNAL_SERVER_ERROR("500", "서버에서 에러"),
    DUPLICATED_ADMIN_ID("3001", "중복된 경우"),
    INVALID_SAVE_CHANGED("1488", "저장잘 안됨"),
    ;


    private String statusCode;
    private String message;

    ResponseType(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCodeInt() {
        return Integer.parseInt(statusCode);
    }
}
