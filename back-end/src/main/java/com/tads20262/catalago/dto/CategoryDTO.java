package com.tads20262.catalago.dto;

import com.tads20262.catalago.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;

    public CategoryDTO(Category entity){
        id = entity.getId();
        name = entity.getName();
    }
}
