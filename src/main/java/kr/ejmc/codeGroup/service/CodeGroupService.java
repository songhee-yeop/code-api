package kr.ejmc.codeGroup.service;

import kr.ejmc.codeGroup.payload.CodeGroup;
import kr.ejmc.codeGroup.payload.CodeGroupAddRequest;
import kr.ejmc.codeGroup.payload.CodeGroupResponse;
import kr.ejmc.codeGroup.payload.CodeGroupUpdateRequest;
import kr.ejmc.common.response.ErrorMessageResponse;
import kr.ejmc.common.response.ResponseEntityCustom;
import kr.ejmc.common.response.ResponseType;
import kr.ejmc.entity.CodeGroupEntity;
import kr.ejmc.repository.CodeGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CodeGroupService {

    @Autowired
    private CodeGroupRepository codeGroupRepository;

    public ResponseEntity codeGroups() {
        try {
            List<CodeGroup> codeGroups = codeGroupRepository.findAll()
                    .stream()
                    .map(CodeGroup::new)
                    .collect(Collectors.toList());
            return ResponseEntityCustom.makeResponseEntityIsOk(new CodeGroupResponse(codeGroups));
        } catch (Exception e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntityOnlyType(ResponseType.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity addCodeGroup(CodeGroupAddRequest codeGroupAddRequest) {
        try {
            codeGroupAddRequest.validationCheck();
            if (codeGroupRepository.existsByCodeGroup(codeGroupAddRequest.getCodeGroup())) {
                throw new IllegalArgumentException("존재하는 코드그룹명입니다.");
            }

            CodeGroupEntity codeGroupEntity = new CodeGroupEntity(codeGroupAddRequest);
            codeGroupRepository.save(codeGroupEntity);
            return ResponseEntityCustom.makeResponseEntity(ResponseType.CREATED, codeGroupEntity.getIdx());

        } catch (IllegalArgumentException e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntity(ResponseType.BAD_REQUEST, ErrorMessageResponse.of(e));
        } catch (Exception e) {
            log.error("error", e);
            return ResponseEntityCustom.makeResponseEntityOnlyType(ResponseType.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity updateCodeGroup(CodeGroupUpdateRequest codeGroupUpdateRequest) {
        try {
            codeGroupUpdateRequest.validationCheck();
            if (codeGroupRepository.existsByCodeGroup(codeGroupUpdateRequest.getCodeGroup())) {
                throw new IllegalArgumentException("존재하는 코드그룹명입니다.");
            }

            CodeGroupEntity codeGroupEntity = getCodeGroupEntityByIdx(codeGroupUpdateRequest.getCodeIdx());
            if (codeGroupEntity == null) {
                throw new IllegalArgumentException("바꾸려는 코드 그룹이 존재하지 않습니다.");
            }

            codeGroupEntity.updateCodeGroup(codeGroupUpdateRequest);

            codeGroupRepository.save(codeGroupEntity);
            return ResponseEntityCustom.makeResponseEntityOnlyType(ResponseType.NO_CONTENT);

        } catch (IllegalArgumentException e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntity(ResponseType.BAD_REQUEST, ErrorMessageResponse.of(e));
        } catch (Exception e) {
            log.error("error", e);
            return ResponseEntityCustom.makeResponseEntityOnlyType(ResponseType.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity deleteCodeGroup(Long idx) {
        try {
            CodeGroupEntity codeGroupEntity = getCodeGroupEntityByIdx(idx);
            if(!codeGroupEntity.getCodes().isEmpty()) {
                throw new IllegalArgumentException("코드그룹 안에있는 코드를 모두 삭제한 이후에 삭제 가능합니다.");
            }

            codeGroupRepository.delete(codeGroupEntity);
            return ResponseEntityCustom.makeResponseEntityOnlyType(ResponseType.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntity(ResponseType.BAD_REQUEST, ErrorMessageResponse.of(e));
        } catch (Exception e) {
            log.error("error", e);
            return ResponseEntityCustom.makeResponseEntityOnlyType(ResponseType.INTERNAL_SERVER_ERROR);
        }
    }

    public CodeGroupEntity getCodeGroupEntityByIdx(Long idx) {
        return codeGroupRepository.findByIdx(idx);
    }
}
