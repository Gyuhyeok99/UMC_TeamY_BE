package umc.teamY.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamY.exception.CustomException;
import umc.teamY.meeting.dto.MeetingCreateRequest;
import umc.teamY.meeting.dto.MeetingCreateResponse;
import umc.teamY.meeting.dto.MeetingTotalResponse;
import umc.teamY.project.Project;
import umc.teamY.project.ProjectRepository;

import java.util.List;
import static umc.teamY.exception.ErrorCode.PROJECT_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class MeetingService {

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

    /** 전체 회의 조희 */
    public MeetingTotalResponse getTotalMeeting(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new CustomException(PROJECT_NOT_EXIST));

        List<Meeting> meetingList = meetingRepository.findMeetingsByProjectId(project.getId());
        return new MeetingTotalResponse(meetingList);
    }
}
