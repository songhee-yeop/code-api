package kr.ejmc.codeGroup.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class CodeGroupUpdateRequest {

    @Schema(description = "코드 그룹 명", example = "code_group6")
    private String codeGroup;

    @Schema(description = "코드 그룹 명", example = "코드그룹1")
    private String codeGroupName;

    @Schema(description = "코드 그룹 idx", example = "1")
    private Long codeIdx;

    public CodeGroupUpdateRequest(String codeGroup, String codeGroupName, Long codeIdx) {
        this.codeGroup = codeGroup;
        this.codeGroupName = codeGroupName;
        this.codeIdx = codeIdx;
    }

    public void validationCheck() {
        if(StringUtils.isEmpty(codeGroup)) {
            throw new IllegalArgumentException("코드그룹을 입력해주세요.");
        }

        if(StringUtils.isEmpty(codeGroupName)) {
            throw new IllegalArgumentException("코드그룹명을 입력해주세요.");
        }
        
        if(codeIdx == null) {
            throw new IllegalArgumentException("원래 코드그룹명을 입력해주세요");
        }
    }
}
