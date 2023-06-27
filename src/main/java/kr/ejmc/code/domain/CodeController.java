package kr.ejmc.code.domain;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "CODE", description = "코드 서비스")
@RequestMapping("/code")
@RestController
public class CodeController {
}
