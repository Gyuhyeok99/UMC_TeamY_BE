package umc.teamY.meeting.dto;

import lombok.Builder;
import lombok.Getter;
import umc.teamY.todo.dto.TodoDetailResponse;

import java.util.List;

@Getter
@Builder
public class MeetingDetailResponse {

    private String tagName;
    private List<TodoDetailResponse> todoList;
}
