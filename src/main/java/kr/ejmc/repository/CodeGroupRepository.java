package kr.ejmc.repository;

import kr.ejmc.entity.CodeGroupEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CodeGroupRepository extends JpaRepository<CodeGroupEntity, Long> {
    boolean existsByCodeGroup(String codeGroup);

    @EntityGraph(attributePaths = {"codeGroup"})
    CodeGroupEntity findByIdx(Long codeIdx);
}
