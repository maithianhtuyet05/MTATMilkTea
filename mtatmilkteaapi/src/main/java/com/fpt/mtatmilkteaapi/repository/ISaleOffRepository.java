package com.fpt.mtatmilkteaapi.repository;

import com.fpt.mtatmilkteaapi.entity.SaleOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleOffRepository extends JpaRepository<SaleOff, Long> {
}
