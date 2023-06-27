package kr.ejmc.entity;

import kr.ejmc.code.payload.CodeAddRequest;
import kr.ejmc.code.payload.CodeUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "code")
public class CodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(name = "code")
    private String code;

    @Column(name = "code_name")
    private String codeName;

    @ManyToOne
    @JoinColumn(name = "codeGroup", nullable = false)
    private CodeGroupEntity codeGroup;

    @CreatedDate
    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "upd_date")
    private LocalDateTime updDate;

    public CodeEntity(CodeAddRequest codeAddRequest, CodeGroupEntity codeGroupEntity) {
        this.codeGroup = codeGroupEntity;
        this.codeName = codeAddRequest.getCodeName();
        this.code = codeAddRequest.getCode();
    }

    public void updateCode(CodeUpdateRequest codeUpdateRequest, CodeGroupEntity codeGroupEntity) {
        this.codeGroup = codeGroupEntity;
        this.codeName = codeUpdateRequest.getCodeName();
        this.code = codeUpdateRequest.getCode();
    }
}
