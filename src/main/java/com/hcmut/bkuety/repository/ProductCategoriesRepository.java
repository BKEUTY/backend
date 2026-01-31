package com.hcmut.bkuety.repository;

import com.hcmut.bkuety.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoriesRepository extends JpaRepository<Categories, Integer> {

}
