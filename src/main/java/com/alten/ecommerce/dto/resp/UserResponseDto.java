package com.alten.ecommerce.dto.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String email;
    private String aboutMe;
    private long  id;
}
// tout ce qui est tres important ou bien sensible ne dois pas s'afficher ou bien s'exposer
