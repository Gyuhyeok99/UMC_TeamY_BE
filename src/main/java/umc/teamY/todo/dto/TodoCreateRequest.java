package umc.teamY.todo.dto;

import lombok.*;
import umc.teamY.meeting.Meeting;
import umc.teamY.tag.Tag;
import umc.teamY.todo.Todo;

import static lombok.AccessLevel.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class TodoCreateRequest {

    private Long meetingId;
    private Long tagId;
    private String content;
    private Boolean isCompleted;
    private Long ownerId;

    public Todo toEntity (Meeting meeting, Tag tag) {
        return Todo.builder()
                .meeting(meeting)
                .tag(tag)
                .content(content)
                .isCompleted(isCompleted)
                .ownerId(null)
                .build();
    }
}
