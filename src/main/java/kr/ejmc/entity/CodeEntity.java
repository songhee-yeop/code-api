package kr.ejmc.entity;

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
}
