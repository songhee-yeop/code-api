package kr.ejmc.code.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.ejmc.entity.CodeEntity;
import lombok.Getter;

@Getter
public class Code {
    
    @Schema(description = "코드 고유 idx", example = "1")
    private Long idx;
    
    @Schema(description = "코드", example = "code")
    private String code;
    
    @Schema(description = "코드명", example = "코드")
    private String codeName;

    public Code(CodeEntity codeEntity) {
        this.idx = codeEntity.getIdx();
        this.code = codeEntity.getCode();
        this.codeName = codeEntity.getCodeName();
    }
}
