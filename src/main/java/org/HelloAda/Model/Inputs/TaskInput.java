package org.HelloAda.Model.Inputs;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class TaskInput {
    private String title;
    private String description;
    private Long userId;
}