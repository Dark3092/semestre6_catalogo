package com.tads20262.catalogo.service;

import com.tads20262.catalogo.dto.ProductDTO;
import com.tads20262.catalogo.entity.Product;
import com.tads20262.catalogo.repository.ProductRepository;
import com.tads20262.catalogo.service.exceptions.DatabaseException;
import com.tads20262.catalogo.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional
    public Page<ProductDTO> findAllPaged(Pageable pageable)
    {

        Page<Product> list = repository.findAll(pageable);

        return list.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id
    ){
        Optional<Product> obj = repository.findById(id);

        Product entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity Not found"));

        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto)
    {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto)
    {
        try{
            Product entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new ProductDTO(entity);

        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found" + id);

        }
    }

    public void delete(Long id)
    {
        try {
            Optional<Product> obj = repository.findById(id);

            obj.orElseThrow(()-> new ResourceNotFoundException("Id " + id + " not found"));

            repository.deleteById(id);
        }
        catch(DataIntegrityViolationException e)
        {
            throw new DatabaseException("Integrity violation");
        }
    }

}
