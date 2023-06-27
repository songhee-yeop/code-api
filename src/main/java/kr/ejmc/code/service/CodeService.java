package kr.ejmc.code.service;

import kr.ejmc.code.payload.Code;
import kr.ejmc.code.payload.CodeAddRequest;
import kr.ejmc.code.payload.CodeUpdateRequest;
import kr.ejmc.code.payload.CodesResponse;
import kr.ejmc.codeGroup.service.CodeGroupService;
import kr.ejmc.common.response.ErrorMessageResponse;
import kr.ejmc.common.response.ResponseEntityCustom;
import kr.ejmc.common.response.ResponseType;
import kr.ejmc.entity.CodeEntity;
import kr.ejmc.entity.CodeGroupEntity;
import kr.ejmc.repository.CodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CodeService {

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private CodeGroupService codeGroupService;

    public ResponseEntity codes(Long groupIdx) {
        try {
            List<Code> codes = codeRepository.findByCodeGroup_Idx(groupIdx)
                    .stream()
                    .map(Code::new)
                    .collect(Collectors.toList());
            return ResponseEntityCustom.makeResponseEntityIsOk(new CodesResponse(codes));
        } catch (IllegalArgumentException e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntity(ResponseType.BAD_REQUEST, ErrorMessageResponse.of(e));
        } catch (Exception e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntityOnlyType(ResponseType.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity code(Long idx) {
        try {
            CodeEntity codeEntity = codeRepository.findByIdx(idx);
            return ResponseEntityCustom.makeResponseEntityIsOk(new Code(codeEntity));
        } catch (Exception e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntityOnlyType(ResponseType.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity addCode(CodeAddRequest codeAddRequest) {
        try {
            codeAddRequest.validationCheck();
            CodeGroupEntity codeGroupEntity = codeGroupService.getCodeGroupEntityByIdx(codeAddRequest.getCodeGroupIdx());
            if (codeGroupEntity == null) {
                throw new IllegalArgumentException("해당하는 코드 그룹이 존재하지 않습니다.");
            }
            List<CodeEntity> groupInCode = codeGroupEntity.getCodes();
            if(checkGroupCodeDuplicatation(groupInCode, codeAddRequest.getCode())) {
                throw new IllegalArgumentException("해당하는 코드 그룹 내 동일한 코드가 존재합니다.");
            }

            CodeEntity codeEntity = new CodeEntity(codeAddRequest, codeGroupEntity);
            codeRepository.save(codeEntity);
            return ResponseEntityCustom.makeResponseEntityOnlyType(ResponseType.CREATED);
        } catch (IllegalArgumentException e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntity(ResponseType.BAD_REQUEST, ErrorMessageResponse.of(e));
        } catch (Exception e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntityOnlyType(ResponseType.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean checkGroupCodeDuplicatation(List<CodeEntity> codeEntities, String code) {
        if(codeEntities == null) {
            return false;
        }

        return codeEntities.stream()
                .anyMatch(codeEntity -> codeEntity.getCode().equals(code));
    }

    public ResponseEntity updateCode(CodeUpdateRequest codeUpdateRequest) {
        try {
            codeUpdateRequest.validationCheck();
            CodeGroupEntity codeGroupEntity = codeGroupService.getCodeGroupEntityByIdx(codeUpdateRequest.getCodeGroupIdx());
            if (codeGroupEntity == null) {
                throw new IllegalArgumentException("해당하는 코드 그룹이 존재하지 않습니다.");
            }

            CodeEntity codeEntity = codeRepository.findByIdx(codeUpdateRequest.getIdx());
            if (codeEntity == null) {
                throw new IllegalArgumentException("코드가 존재하지 않습니다.");
            }
            List<CodeEntity> groupInCode = codeGroupEntity.getCodes();
            groupInCode.remove(codeEntity);
            groupInCode.stream()
                    .forEach(codeEntity1 -> System.out.println(codeEntity1.getCode()));
            if(checkGroupCodeDuplicatation(groupInCode, codeUpdateRequest.getCode())) {
                throw new IllegalArgumentException("해당하는 코드 그룹 내 동일한 코드명이 존재합니다.");
            }

            codeEntity.updateCode(codeUpdateRequest, codeGroupEntity);
            codeRepository.save(codeEntity);
            return ResponseEntityCustom.makeAgentResponseEntityOnlyType(ResponseType.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntity(ResponseType.BAD_REQUEST, ErrorMessageResponse.of(e));
        } catch (Exception e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntityOnlyType(ResponseType.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity deleteCode(Long idx) {
        try {
            CodeEntity codeEntity = codeRepository.findByIdx(idx);
            if (codeEntity == null) {
                throw new IllegalArgumentException("코드가 존재하지 않습니다.");
            }
            codeRepository.delete(codeEntity);
            return ResponseEntityCustom.makeAgentResponseEntityOnlyType(ResponseType.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntity(ResponseType.BAD_REQUEST, ErrorMessageResponse.of(e));
        } catch (Exception e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntityOnlyType(ResponseType.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity deleteCodeByGroup(Long groupIdx) {
        try {
            List<CodeEntity> codes = codeRepository.findByCodeGroup_Idx(groupIdx);
            if(codes != null) {
                codeRepository.deleteAll(codes);
            }
            return ResponseEntityCustom.makeAgentResponseEntityOnlyType(ResponseType.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntity(ResponseType.BAD_REQUEST, ErrorMessageResponse.of(e));
        } catch (Exception e) {
            log.error("error", e);
            return ResponseEntityCustom.makeAgentResponseEntityOnlyType(ResponseType.INTERNAL_SERVER_ERROR);
        }
    }
}
