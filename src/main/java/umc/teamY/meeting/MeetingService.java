package umc.teamY.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamY.exception.CustomException;
import umc.teamY.meeting.dto.MeetingCreateRequest;
import umc.teamY.meeting.dto.MeetingCreateResponse;
import umc.teamY.meeting.dto.MeetingDetailResponse;
import umc.teamY.meeting.dto.MeetingTotalResponse;
import umc.teamY.project.Project;
import umc.teamY.project.ProjectRepository;
import umc.teamY.tag.Tag;
import umc.teamY.tag.TagRepository;
import umc.teamY.todo.TodoRepository;
import umc.teamY.todo.TodoService;
import umc.teamY.todo.dto.TodoDetailResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static umc.teamY.exception.ErrorCode.MEETING_NOT_EXIST;
import static umc.teamY.exception.ErrorCode.PROJECT_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final TagRepository tagRepository;
    private final TodoRepository todoRepository;
    private final MeetingRepository meetingRepository;
    private final ProjectRepository projectRepository;

    /** 회의 생성 */
    public MeetingCreateResponse createMeeting (MeetingCreateRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new CustomException(PROJECT_NOT_EXIST));

        Meeting meeting = request.toEntity(project);
        meetingRepository.save(meeting);

        return new MeetingCreateResponse(meeting.getId());
    }

    /** 회의 전체 조희 */
    public MeetingTotalResponse getTotalMeeting(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new CustomException(PROJECT_NOT_EXIST));

        List<Meeting> meetingList = meetingRepository.findMeetingsByProjectId(project.getId());
        return new MeetingTotalResponse(meetingList);
    }

    /** 회의 상세 조회 */
    public List<MeetingDetailResponse> getDetailMeeting(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new CustomException(MEETING_NOT_EXIST));

        return tagRepository.findTagsByMeetingId(meeting.getId())
                .stream()
                .map(tag -> MeetingDetailResponse.builder()
                        .tagName(tag.getTagName())
                        .todoList(getTodoList(meeting, tag))
                        .build())
                .collect(Collectors.toList());
    }

    /** 체크리스트 상세 조회 */
    private List<TodoDetailResponse> getTodoList(Meeting meeting, Tag tag) {
        return todoRepository.findTodosByMeetingIdAndTagId(meeting.getId(), tag.getId())
                .stream()
                .map(todo -> TodoDetailResponse.builder()
                        .todoId(todo.getId())
                        .content(todo.getContent())
                        .isCompleted(todo.getIsCompleted())
                        .ownerId(todo.getOwnerId())
                        .build())
                .collect(Collectors.toList());
    }
}
