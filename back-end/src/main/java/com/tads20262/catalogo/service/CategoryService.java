package com.tads20262.catalogo.service;

import com.tads20262.catalogo.dto.CategoryDTO;
import com.tads20262.catalogo.entity.Category;
import com.tads20262.catalogo.repository.CategoryRepository;
import com.tads20262.catalogo.service.exceptions.DatabaseException;
import com.tads20262.catalogo.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest)
    {

        Page<Category> list = repository.findAll(pageRequest);


       return list.map(CategoryDTO::new);

    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id
    ){
        Optional<Category> obj = repository.findById(id);

        Category entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity Not found"));

        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto)
    {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto)
    {
        try{
            Category entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);

        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found" + id);

        }
    }

    public void delete(Long id)
    {
        try {
            Optional<Category> obj = repository.findById(id);

            obj.orElseThrow(()-> new ResourceNotFoundException("Id " + id + " not found"));

            repository.deleteById(id);
        }
        catch(DataIntegrityViolationException e)
        {
            throw new DatabaseException("Integrity violation");
        }
    }

}
