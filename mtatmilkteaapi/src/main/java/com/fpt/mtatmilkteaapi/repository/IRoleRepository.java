package com.fpt.mtatmilkteaapi.repository;

import com.fpt.mtatmilkteaapi.config.ERole;
import com.fpt.mtatmilkteaapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
