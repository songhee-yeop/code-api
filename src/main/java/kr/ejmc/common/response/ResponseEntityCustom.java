package kr.ejmc.common.response;

import org.springframework.http.ResponseEntity;

public class ResponseEntityCustom {
    public static ResponseEntity makeResponseEntity(ResponseType responseType, Object result) {
        return ResponseEntity.status(responseType.getStatusCodeInt()).body(new ResponseResult(responseType.getStatusCodeInt(), result));
    }

    public static ResponseEntity makeResponseEntityOnlyType(ResponseType responseType) {
        return ResponseEntity.status(responseType.getStatusCodeInt()).body(new ResponseResult(responseType.getStatusCodeInt()));
    }

    public static ResponseEntity makeResponseEntityIsOk(Object result) {
        return makeResponseEntity(ResponseType.OK, result);
    }

    public static ResponseEntity makeAgentResponseEntity(ResponseType responseType, Object result) {
        return ResponseEntity.status(responseType.getStatusCodeInt()).body(result);
    }

    public static ResponseEntity makeAgentResponseEntityOnlyType(ResponseType responseType) {
        return ResponseEntity.status(responseType.getStatusCodeInt()).body(null);
    }
}
