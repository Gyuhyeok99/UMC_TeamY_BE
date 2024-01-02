package umc.teamY.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.teamY.global.Response;
import umc.teamY.meeting.dto.MeetingCreateRequest;
import umc.teamY.meeting.dto.MeetingCreateResponse;
import umc.teamY.meeting.dto.MeetingDetailResponse;
import umc.teamY.meeting.dto.MeetingTotalResponse;
import umc.teamY.todo.TodoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meeting")
public class MeetingController {

    private final MeetingService meetingService;
    // private final TodoService todoService;

    /** 회의 생성 */
    @PostMapping("/")
    public Response<MeetingCreateResponse> createMeeting(@RequestBody MeetingCreateRequest request) {
        return Response.success(meetingService.createMeeting(request));
    }

    /** 회의 전체 조회 */
    @GetMapping("/total/{projectId}")
    public Response<MeetingTotalResponse> getTotalMeeting(@PathVariable("projectId") Long projectId) {
        return Response.success(meetingService.getTotalMeeting(projectId));
    }

    /** 회의 상세 조회 */
    @GetMapping("/detail/{meetingId}")
    public Response<List<MeetingDetailResponse>> getDetailMeeting(@PathVariable("meetingId") Long meetingId) {
        return Response.success(meetingService.getDetailMeeting(meetingId));
    }
}
