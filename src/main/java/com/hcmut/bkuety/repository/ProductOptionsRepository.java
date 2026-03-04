package com.hcmut.bkuety.repository;

import java.util.List;
import com.hcmut.bkuety.entity.ProductOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionsRepository extends JpaRepository<ProductOptions, Integer> {
    List<ProductOptions> findAllByProductId(Integer productId);
}
