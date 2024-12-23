package com.alten.ecommerce.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserEntityDto {

    private long id;
    private String name;
    private String email;
    private String password;
    private String aboutMe;
}
