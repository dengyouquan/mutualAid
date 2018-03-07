package com.dyq.demo.Repository;

import com.dyq.demo.entity.Role;
import com.dyq.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
