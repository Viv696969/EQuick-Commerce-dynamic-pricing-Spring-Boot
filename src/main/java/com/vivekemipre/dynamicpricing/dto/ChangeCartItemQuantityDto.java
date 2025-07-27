package com.vivekemipre.dynamicpricing.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class ChangeCartItemQuantityDto {

    String cartItemId;
    int quantityChange;
    String action;


}
