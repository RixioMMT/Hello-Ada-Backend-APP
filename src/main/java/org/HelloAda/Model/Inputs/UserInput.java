package org.HelloAda.Model.Inputs;

import lombok.*;
import org.HelloAda.Model.Enum.RolePosition;

import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class UserInput {
    private String email;
    private String password;
}