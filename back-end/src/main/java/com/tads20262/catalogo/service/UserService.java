package com.tads20262.catalogo.service;

import com.tads20262.catalogo.entity.User;
import com.tads20262.catalogo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Transactional
    public List<User> findAll(){

        return repository.findAll();

    }
}
