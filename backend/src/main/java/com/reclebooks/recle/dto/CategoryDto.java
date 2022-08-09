package com.reclebooks.recle.dto;


import com.reclebooks.recle.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class CategoryDto {


    private Long id;

    private String name;

    public static CategoryDto from(Category category){

        return new CategoryDto(category.getId(), category.getName());

    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
