package umc.teamY.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.teamY.global.Response;
import umc.teamY.meeting.dto.MeetingCreateRequest;
import umc.teamY.meeting.dto.MeetingCreateResponse;
import umc.teamY.meeting.dto.MeetingTotalResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    /** 회의 생성 */
    @PostMapping("/")
    public Response<MeetingCreateResponse> createMeeting(@RequestBody MeetingCreateRequest request) {
        return Response.success(meetingService.createMeeting(request));
    }

    /** 회의 전체 조회 */
    @GetMapping("/{projectId}")
    public Response<MeetingTotalResponse> getTotalMeeting(@PathVariable("projectId") Long projectId) {
//        MeetingTotalResponse response = meetingService.getTotalMeeting(projectId);
//        return ResponseEntity.ok(response);

        return Response.success(meetingService.getTotalMeeting(projectId));
    }
}
