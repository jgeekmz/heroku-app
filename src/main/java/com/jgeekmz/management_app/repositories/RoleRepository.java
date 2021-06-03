package com.jgeekmz.management_app.repositories;

import com.jgeekmz.management_app.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(@Param("name") String name);

}