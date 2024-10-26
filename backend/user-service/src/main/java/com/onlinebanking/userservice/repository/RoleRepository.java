package com.onlinebanking.userservice.repository;

import com.onlinebanking.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r JOIN FETCH r.rolePermissions rp JOIN FETCH rp.permission WHERE r.roleName IN :roles")
    List<Role> findAllByRoleNameIn(@Param("roles") Set<String> roles);
}
