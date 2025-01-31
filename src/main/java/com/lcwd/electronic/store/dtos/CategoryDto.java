package com.lcwd.electronic.store.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {


    private  String categoryId;

    @NotBlank
    @Min(value = 4, message = "category  title must be of minimum 4 characters !!")
    private String title;

    @NotBlank(message = "description required for the category")
    private String description;

@NotBlank(message = "cover image can not be coverImage")
    private String coverImage;

}
