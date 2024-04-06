package com.fpt.mtatmilkteaapi.repository;

import com.fpt.mtatmilkteaapi.entity.AdditionOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddOptionRepository extends JpaRepository<AdditionOption, Long> {
    Page<AdditionOption> findAdditionByNameLike(String keyword, Pageable pageable);
}
