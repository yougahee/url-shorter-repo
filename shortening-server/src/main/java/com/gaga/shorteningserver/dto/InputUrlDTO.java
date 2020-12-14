package com.gaga.shorteningserver.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class InputUrlDTO {
    @NotBlank(message = "url을 입력해주세요.")
    private String url;
}
