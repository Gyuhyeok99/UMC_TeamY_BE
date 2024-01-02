package umc.teamY.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import umc.teamY.exception.CustomException;
import umc.teamY.meeting.Meeting;
import umc.teamY.meeting.MeetingRepository;
import umc.teamY.tag.Tag;
import umc.teamY.tag.TagRepository;
import umc.teamY.todo.dto.TodoCreateRequest;
import umc.teamY.todo.dto.TodoCreateResponse;

import static umc.teamY.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MeetingRepository meetingRepository;
    private final TagRepository tagRepository;

    /** 체크리스트 생성 */
    public TodoCreateResponse addTodoMeeting(TodoCreateRequest request) {
        Meeting meeting = meetingRepository.findById(request.getMeetingId())
                .orElseThrow(() -> new CustomException(MEETING_NOT_EXIST));

        Tag tag = tagRepository.findById(request.getTagId())
                .orElseThrow(() -> new CustomException(TAG_NOT_EXIST));

        Todo todo = request.toEntity(meeting, tag);
        todoRepository.save(todo);

        return new TodoCreateResponse(todo.getId());
    }

    /** 체크리스트 조회 */


    /** 체크리스트 팀원 지정 */
    public void assginOwner(Long todoId, Long ownerId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new CustomException(TODO_NOT_EXIST));


    }

    /** 체크리스트 체크 */
    public void updateTodoCompleted(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new CustomException(TODO_NOT_EXIST));

        Boolean isCompleted = todo.getIsCompleted();
        isCompleted = !isCompleted;

        todo.updateTodoCompleted(isCompleted);
        todoRepository.save(todo);

    }

}
