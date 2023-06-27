package kr.ejmc.codeGroup.domain;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ejmc.codeGroup.payload.CodeGroupAddRequest;
import kr.ejmc.codeGroup.payload.CodeGroupResponse;
import kr.ejmc.codeGroup.payload.CodeGroupUpdateRequest;
import kr.ejmc.codeGroup.service.CodeGroupService;
import kr.ejmc.common.response.ErrorMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Tag(name = "CODE Group", description = "코드 그룹 서비스")
@RequestMapping("/code/group")
@RestController
public class CodeGroupController {

    @Autowired
    private CodeGroupService codeGroupService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",content = @Content(schema = @Schema(implementation = CodeGroupResponse.class))),
            @ApiResponse(responseCode = "500", description = "조회 에러")
    })
    @Operation(summary = "코드 그룹 조회")
    @GetMapping("/list")
    public ResponseEntity codeGroups() {
        return codeGroupService.codeGroups();
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "등록 요청 실패",content = @Content(schema = @Schema(implementation = ErrorMessageResponse.class))),
            @ApiResponse(responseCode = "500", description = "등록 에러")
    })
    @Operation(summary = "코드 그룹 등록")
    @PostMapping("/add")
    public ResponseEntity addCodeGroup(@RequestBody CodeGroupAddRequest codeGroupAddRequest) {
        return codeGroupService.addCodeGroup(codeGroupAddRequest);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "수정 요청 실패",content = @Content(schema = @Schema(implementation = ErrorMessageResponse.class))),
            @ApiResponse(responseCode = "500", description = "수정 에러")
    })
    @Operation(summary = "코드 그룹 수정")
    @PatchMapping("/update")
    public ResponseEntity updateCodeGroup(@RequestBody CodeGroupUpdateRequest codeGroupUpdateRequest) {
        return codeGroupService.updateCodeGroup(codeGroupUpdateRequest);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "삭제 요청 실패",content = @Content(schema = @Schema(implementation = ErrorMessageResponse.class))),
            @ApiResponse(responseCode = "500", description = "삭제 에러")
    })
    @Operation(summary = "코드 그룹 삭제")
    @DeleteMapping("/{idx}")
    public ResponseEntity deleteCodeGroup(@PathVariable Long idx) {
        return codeGroupService.deleteCodeGroup(idx);
    }


}
