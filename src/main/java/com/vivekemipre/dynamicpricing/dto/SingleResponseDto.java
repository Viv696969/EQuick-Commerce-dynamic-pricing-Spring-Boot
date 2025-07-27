package com.vivekemipre.dynamicpricing.dto;

import lombok.Data;

@Data
public class SingleResponseDto {

    private String message;

    public SingleResponseDto(String message) {
        this.message = message;
    }
}
