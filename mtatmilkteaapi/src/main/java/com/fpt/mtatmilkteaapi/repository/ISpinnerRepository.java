package com.fpt.mtatmilkteaapi.repository;

import com.fpt.mtatmilkteaapi.entity.Spinner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpinnerRepository extends JpaRepository<Spinner, Long> {
}
