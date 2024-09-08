package org.HelloAda.Model.Inputs;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class LogInInput {
    private String email;
    private String password;
}