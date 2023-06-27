package kr.ejmc.codeGroup.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class CodeGroupAddRequest {
    @Schema(description = "코드 그룹 명", example = "CODE1")
    private String codeGroup;

    @Schema(description = "코드 그룹 명", example = "코드그룹1")
    private String codeGroupName;

    public CodeGroupAddRequest(String codeGroup, String codeGroupName) {
        this.codeGroup = codeGroup;
        this.codeGroupName = codeGroupName;
    }

    public void validationCheck() {
        if(StringUtils.isEmpty(codeGroup)) {
            throw new IllegalArgumentException("코드그룹을 입력해주세요.");
        }

        if(StringUtils.isEmpty(codeGroupName)) {
            throw new IllegalArgumentException("코드그룹명을 입력해주세요.");
        }
    }
}
