package kr.ejmc.code.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class CodeAddRequest {

    @Schema(description = "코드 그룹 고유 idx", example = "1")
    private Long codeGroupIdx;

    @Schema(description = "코드", example = "CODE1")
    private String code;

    @Schema(description = "코드명", example = "코드명1")
    private String codeName;

    public CodeAddRequest(Long codeGroupIdx, String code, String codeName) {
        this.codeGroupIdx = codeGroupIdx;
        this.code = code;
        this.codeName = codeName;
    }

    public void validationCheck() {
        if(codeGroupIdx == null) {
            throw new IllegalArgumentException("코드 그룹은 필수입니다.");
        }

        if(StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("코드를 입력해주세요");
        }

        if(StringUtils.isEmpty(codeName)) {
            throw new IllegalArgumentException("코드 명을 입력해주세요.");
        }
    }
}
