package kr.ejmc.repository;

import kr.ejmc.entity.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeRepository extends JpaRepository<CodeEntity, Long> {
    List<CodeEntity> findByCodeGroup_Idx(Long codeGroupIdx);
}
