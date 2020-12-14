package com.gaga.shorteningserver.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class DefaultResponseDTO {
    private int status;
    private boolean success;
    private String message;
    private String resultUrl;

    public DefaultResponseDTO(int status, boolean success, String message, String resultUrl) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.resultUrl = resultUrl;
    }
}
