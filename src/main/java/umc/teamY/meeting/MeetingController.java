package umc.teamY.meeting;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.teamY.meeting.dto.MeetingRequest;
import umc.teamY.meeting.dto.MeetingTotalResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    /** 회의 생성 */
    @PostMapping("/")
    public ResponseEntity<Void> createMeeting(@RequestBody MeetingRequest request) {
        meetingService.createMeeting(request);
        return ResponseEntity.ok().build();
    }

    /** 회의 전체 조회 */
    @GetMapping("/{projectId}")
    public ResponseEntity<MeetingTotalResponse> getTotalMeeting(@PathVariable("projectId") Long projectId) {
        MeetingTotalResponse response = meetingService.getTotalMeeting(projectId);
        return ResponseEntity.ok(response);
    }
}
