package com.blog.application.patloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {
	
     private Integer categoryId;
     @NotBlank
     @Size(min = 4 , message="Min size must be of 4 char")
     private String categoryTitle;
     @NotBlank
     @Size(min= 10 , message="Min size must be 10 char")
     private String categoryDescription;


}
