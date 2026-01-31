package com.hcmut.bkuety.repository;

import com.hcmut.bkuety.entity.ProductOptionValue;
import com.hcmut.bkuety.entity.ProductOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionsRepository extends JpaRepository<ProductOptions, Integer> {
}
