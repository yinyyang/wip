package com.springcloud.support.repository.auth;

import com.springcloud.support.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}