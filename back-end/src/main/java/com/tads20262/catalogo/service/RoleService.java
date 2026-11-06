package com.tads20262.catalago.service;

import com.tads20262.catalago.dto.RoleDTO;
import com.tads20262.catalago.entity.Product;
import com.tads20262.catalago.entity.Role;
import com.tads20262.catalago.repository.RoleRepository;
import com.tads20262.catalago.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    @Transactional
    public List<RoleDTO> findAll(){


        List<Role> list = repository.findAll();


        return list
                .stream()
                .map(RoleDTO::new)
                .collect(Collectors.toList());



    }
    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        Optional<Role> obj = repository.findById(id);

        Role entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity Not found"));

        return new RoleDTO(entity);
    }

}
