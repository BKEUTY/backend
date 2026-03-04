package com.hcmut.bkuety.repository;

import java.util.List;
import com.hcmut.bkuety.entity.ProductOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValue, Integer> {
    List<ProductOptionValue> findAllByProductId(Integer optionId);
}
