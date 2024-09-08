package org.HelloAda.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.HelloAda.Model.Entity.Role;
import org.HelloAda.Model.Enum.RolePosition;
import org.HelloAda.Repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    @PostConstruct
    public void roles() {
        if (roleRepository.findByRolePosition(RolePosition.CLIENT) == null) {
            Role clientRole = Role.builder()
                    .rolePosition(RolePosition.CLIENT)
                    .build();
            roleRepository.save(clientRole);
        }
    }
}