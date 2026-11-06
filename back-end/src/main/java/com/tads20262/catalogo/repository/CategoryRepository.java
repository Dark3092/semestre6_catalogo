package com.tads20262.catalogo.repository;

import com.tads20262.catalogo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{

}
