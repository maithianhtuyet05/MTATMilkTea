package com.fpt.mtatmilkteaapi.repository;

import com.fpt.mtatmilkteaapi.entity.WheelHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWheelHistoryRepository extends JpaRepository<WheelHistory, Long> {

}
