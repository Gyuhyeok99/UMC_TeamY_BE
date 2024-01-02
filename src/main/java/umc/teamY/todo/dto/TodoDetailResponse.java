package umc.teamY.todo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoDetailResponse {

    private Long todoId;
    private String content;
    private Boolean isCompleted;
    private Long ownerId;
}
