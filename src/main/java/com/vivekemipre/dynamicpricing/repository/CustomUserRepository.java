package com.vivekemipre.dynamicpricing.repository;

import com.vivekemipre.dynamicpricing.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomUserRepository extends JpaRepository<CustomUser,String> {

    Optional<CustomUser> findByEmail(String email);

}
