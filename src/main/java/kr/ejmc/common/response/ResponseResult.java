package kr.ejmc.common.response;

import lombok.Getter;

@Getter
public class ResponseResult {

    private int statusCode;
    private Object result;

    public ResponseResult(int statusCode) {
        this.statusCode = statusCode;
        this.result = null;
    }

    public ResponseResult(int statusCode, Object result) {
        this.statusCode = statusCode;
        this.result = result;
    }
}
