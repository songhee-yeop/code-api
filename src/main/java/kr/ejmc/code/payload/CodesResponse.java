package kr.ejmc.code.payload;

import lombok.Getter;

import java.util.List;

@Getter
public class CodesResponse {
    private List<Code> codes;

    public CodesResponse(List<Code> codes) {
        this.codes = codes;
    }
}
