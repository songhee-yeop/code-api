package kr.ejmc.codeGroup.payload;

import lombok.Getter;

import java.util.List;

@Getter
public class CodeGroupResponse {
    private List<CodeGroup> codeGroupValues;

    public CodeGroupResponse(List<CodeGroup> codeGroupValues) {
        this.codeGroupValues = codeGroupValues;
    }
}
