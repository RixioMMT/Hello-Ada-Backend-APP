package org.HelloAda.Repository;

import org.HelloAda.Model.Entity.Role;
import org.HelloAda.Model.Enum.RolePosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRolePosition(RolePosition rolePosition);
}