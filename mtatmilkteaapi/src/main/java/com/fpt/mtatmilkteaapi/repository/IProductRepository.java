package com.fpt.mtatmilkteaapi.repository;

import com.fpt.mtatmilkteaapi.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, String> {
    Page<Product> findProductsByNameLike(String keyword, Pageable pageable);

    List<Product> findProductsByCategoryId_Name(String name, Sort sort);
    List<Product> findProductsByCategoryId_NameNotLikeAndCategoryId_NameNotLike(String cate, String cate2, Sort sort);

    Page<Product> findProductBySaleOffDiscountLike(Double discount, Pageable pageable);
    Page<Product> findProductBySaleOff_EndDateGreaterThan(Timestamp timeNow, Pageable pageable);
    Page<Product> findProductBySaleOffNull(Pageable pageable);
    Page<Product> findProductBySaleOffNotNull(Pageable pageable);
}
