package umc.teamY.todo;

import static umc.teamY.exception.ErrorCode.MEETING_NOT_EXIST;
import static umc.teamY.exception.ErrorCode.TAG_NOT_EXIST;
import static umc.teamY.exception.ErrorCode.TODO_NOT_EXIST;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamY.contribution.Contribution;
import umc.teamY.contribution.ContributionRepository;
import umc.teamY.exception.CustomException;
import umc.teamY.exception.ErrorCode;
import umc.teamY.meeting.Meeting;
import umc.teamY.meeting.MeetingRepository;
import umc.teamY.tag.Tag;
import umc.teamY.tag.TagRepository;
import umc.teamY.todo.dto.TodoCreateRequest;
import umc.teamY.todo.dto.TodoCreateResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;
    private final MeetingRepository meetingRepository;
    private final TagRepository tagRepository;
    private final ContributionRepository contributionRepository;
//    private final UserRepository userRepository;

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

    /** 체크리스트 팀원 지정 */
    public TodoCreateResponse assginOwner(Long todoId, Long ownerId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new CustomException(TODO_NOT_EXIST));

        todo.assignOnwer(ownerId);
        todoRepository.save(todo);

        return new TodoCreateResponse(todo.getId());
    }

    /** 체크리스트 체크 */
    public TodoCreateResponse updateTodoCompleted(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new CustomException(TODO_NOT_EXIST));

        Boolean isCompleted = todo.getIsCompleted();
        isCompleted = !isCompleted;

//        userRepository.incrementContribution(todo.getOwnerId());
        Contribution contribution = contributionRepository.findByUserUserIdAndProjectProjectId(todo.getOwnerId(),
                        todo.getMeeting().getProject().getId())
                .orElseThrow(() -> new CustomException(ErrorCode.CONTRIBUTION_NOT_FOUND));
        contributionRepository.incrementContribution(contribution.getId());

        todo.updateTodoCompleted(isCompleted);
        todoRepository.save(todo);

        return new TodoCreateResponse(todo.getId());
    }
}
