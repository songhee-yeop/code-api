package kr.ejmc.code.domain;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ejmc.code.payload.CodeAddRequest;
import kr.ejmc.code.payload.CodeUpdateRequest;
import kr.ejmc.code.payload.CodesResponse;
import kr.ejmc.code.service.CodeService;
import kr.ejmc.common.response.ErrorMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Tag(name = "CODE", description = "코드 서비스")
@RequestMapping("/code")
@RestController
public class CodeController {

    @Autowired
    private CodeService codeService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",content = @Content(schema = @Schema(implementation = CodesResponse.class))),
            @ApiResponse(responseCode = "400", description = "등록 요청 실패",content = @Content(schema = @Schema(implementation = ErrorMessageResponse.class))),
            @ApiResponse(responseCode = "500", description = "조회 에러")
    })
    @Operation(summary = "코드 리스트 조회")
    @GetMapping("/list/{groupIdx}")
    public ResponseEntity codes(@PathVariable Long groupIdx) {
        return codeService.codes(groupIdx);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",content = @Content(schema = @Schema(implementation = CodesResponse.class))),
            @ApiResponse(responseCode = "500", description = "조회 에러")
    })
    @Operation(summary = "코드 단건 조회")
    @GetMapping("/{idx}")
    public ResponseEntity code(@PathVariable Long idx) {
        return codeService.code(idx);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "등록 요청 실패",content = @Content(schema = @Schema(implementation = ErrorMessageResponse.class))),
            @ApiResponse(responseCode = "500", description = "등록 에러")
    })
    @Operation(summary = "코드 등록")
    @PostMapping("/add")
    public ResponseEntity addCode(CodeAddRequest codeAddRequest) {
        return codeService.addCode(codeAddRequest);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "수정 요청 실패",content = @Content(schema = @Schema(implementation = ErrorMessageResponse.class))),
            @ApiResponse(responseCode = "500", description = "수정 에러")
    })
    @Operation(summary = "코드 수정")
    @PatchMapping("/update")
    public ResponseEntity updateCode(CodeUpdateRequest codeUpdateRequest) {
        return codeService.updateCode(codeUpdateRequest);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "삭제 요청 실패",content = @Content(schema = @Schema(implementation = ErrorMessageResponse.class))),
            @ApiResponse(responseCode = "500", description = "삭제 에러")
    })
    @Operation(summary = "코드 삭제")
    @DeleteMapping("/{idx}")
    public ResponseEntity deleteCode(@PathVariable Long idx) {
        return codeService.deleteCode(idx);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "삭제 요청 실패",content = @Content(schema = @Schema(implementation = ErrorMessageResponse.class))),
            @ApiResponse(responseCode = "500", description = "삭제 에러")
    })
    @Operation(summary = "코드그룹 내 코드 모두 삭제")
    @DeleteMapping("/delete/{groupIdx}")
    public ResponseEntity deleteCodeByGroup(@PathVariable Long groupIdx) {
        return codeService.deleteCodeByGroup(groupIdx);
    }
}
