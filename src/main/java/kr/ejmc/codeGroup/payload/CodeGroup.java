package kr.ejmc.codeGroup.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.ejmc.entity.CodeGroupEntity;
import lombok.Getter;

@Getter
public class CodeGroup {

    @Schema(description = "코드 그룹 고유 idx", example = "1")
    private Long idx;
    
    @Schema(description = "코드 그룹 명", example = "CODE1")
    private String codeGroup;

    @Schema(description = "코드 그룹 명", example = "코드그룹1")
    private String codeGroupName;

    public CodeGroup(CodeGroupEntity codeGroupEntity) {
        this.codeGroup = codeGroupEntity.getCodeGroup();
        this.codeGroupName = codeGroupEntity.getCodeGroupName();
        this.idx = codeGroupEntity.getIdx();
    }
}
