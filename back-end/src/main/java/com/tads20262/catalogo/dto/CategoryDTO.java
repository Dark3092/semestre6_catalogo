package com.tads20262.catalogo.dto;

import com.tads20262.catalogo.entity.Category;
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
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
