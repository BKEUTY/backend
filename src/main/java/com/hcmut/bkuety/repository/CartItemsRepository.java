package com.hcmut.bkuety.repository;

import com.hcmut.bkuety.entity.CartItems;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {
    List<CartItems> findByUserId(Integer userId);

    CartItems findByUserIdAndProductVariantId(Integer userId, Integer productVariantId);
}
