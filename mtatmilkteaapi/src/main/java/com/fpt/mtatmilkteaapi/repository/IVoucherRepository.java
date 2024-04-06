package com.fpt.mtatmilkteaapi.repository;

import com.fpt.mtatmilkteaapi.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoucherRepository extends JpaRepository<Voucher, Long> {

    Boolean existsByCodeName(String codeName); // Exception: used

}
