package umc.teamY.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.teamY.todo.Todo;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class TodoAddOwnerRequest {

    private Long ownerId;

    public Todo toEntity(Long ownerId) {
        return Todo.builder()
                .ownerId(ownerId)
                .build();
    }
}
