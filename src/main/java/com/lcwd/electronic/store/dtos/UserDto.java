package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.validate.ImageNameValid;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {

    private String userId;

@Size(min = 3, max = 25, message = "invalid name")
    private String name;

@Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([a-z0-9]+\\.)+[a-z]{2,5}$", message = "invalid user email")
@NotBlank(message = "email is required")
    private String email;

@NotBlank(message = "password can not be blank")
    private String password;

@Size(min = 4,max = 6,message = "invalid gender")
    private String gender;

@NotBlank(message = "please  abosomethin about yourself , it is invalid about section because it is blank")
    private String about;

                   // @ImageNameValid annotation created under validate custom package by the help of two class
                   //ImageNameValid and ImageNameValidator implementing ConstraintValidator

    @ImageNameValid
    private String imageName;

}
