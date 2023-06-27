package kr.ejmc.entity;

import kr.ejmc.codeGroup.payload.CodeGroupAddRequest;
import kr.ejmc.codeGroup.payload.CodeGroupUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "code_group")
public class CodeGroupEntity {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "code_group")
    private String codeGroup;

    @Column(name = "code_group_name")
    private String codeGroupName;

    @CreatedDate
    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "upd_date")
    private LocalDateTime updDate;

    @OneToMany(mappedBy = "codeGroup")
    private List<CodeEntity> codes = new ArrayList<>();

    public CodeGroupEntity(CodeGroupAddRequest codeGroupAddRequest) {
        this.codeGroup = codeGroupAddRequest.getCodeGroup();
        this.codeGroupName = codeGroupAddRequest.getCodeGroupName();
    }

    public void updateCodeGroup(CodeGroupUpdateRequest codeGroupUpdateRequest) {
        this.codeGroup = codeGroupUpdateRequest.getCodeGroup();
        this.codeGroupName = codeGroupUpdateRequest.getCodeGroupName();
    }
}
