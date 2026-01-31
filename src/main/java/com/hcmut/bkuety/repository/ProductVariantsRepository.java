package com.hcmut.bkuety.repository;

import com.hcmut.bkuety.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantsRepository extends JpaRepository<ProductVariant,Integer> {

    @Query("SELECT pv FROM ProductVariant pv WHERE pv.product.id = :productId")
    List<ProductVariant> findAllByProductId(Integer productId);
}
