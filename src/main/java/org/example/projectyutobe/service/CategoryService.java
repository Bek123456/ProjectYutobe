package org.example.projectyutobe.service;


import org.example.projectyutobe.dto.CategoryDTO;
import org.example.projectyutobe.entity.CategoryEntity;
import org.example.projectyutobe.exp.AppBadException;
import org.example.projectyutobe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
     @Autowired
     private CategoryRepository categoryRepository;
    public String created(CategoryDTO dto) {
        CategoryEntity categoryEntity=new CategoryEntity();
        categoryEntity.setName(dto.getName());
        categoryEntity.setCreatedDate(LocalDateTime.now());
        categoryRepository.save(categoryEntity);
        return "created Category";
    }

    public String update(CategoryDTO dto, Integer categoryId) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
        if (categoryEntity.isEmpty()){
            throw new AppBadException("not found category");
        }
        CategoryEntity categoryEntity1 = categoryEntity.get();
        categoryEntity1.setName(dto.getName());
        categoryEntity1.setUpdatedDate(LocalDateTime.now());
        categoryRepository.save(categoryEntity1);
        return "update category";
    }

    public String delete(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
        return "deleted category";
    }

    public List<CategoryDTO> getAll() {
        List<CategoryEntity> all = categoryRepository.findAll();
        List<CategoryDTO>dtos=new ArrayList<>();
        for (CategoryEntity entity:all){
            dtos.add(toDO(entity));
        }
        return dtos;
    }
    public CategoryDTO toDO(CategoryEntity entity){
        CategoryDTO dto=new CategoryDTO();
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        return dto;
    }
}
