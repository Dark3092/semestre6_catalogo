package com.tads20262.catalogo.resource;

import com.tads20262.catalogo.dto.RoleDTO;
import com.tads20262.catalogo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/roles")
public class RoleResource {
    @Autowired
    private RoleService service;


    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll(){
        List<RoleDTO> list = service.findAll();

        return ResponseEntity.ok(list);

    }
    @GetMapping (value = "/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable Long id){
        RoleDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
}
