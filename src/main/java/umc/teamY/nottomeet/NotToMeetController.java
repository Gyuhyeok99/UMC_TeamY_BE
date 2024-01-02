package umc.teamY.nottomeet;

import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.teamY.global.Response;
import umc.teamY.nottomeet.dto.AvailableTimesResponse;
import umc.teamY.nottomeet.dto.NotToMeetCreateRequest;
import umc.teamY.nottomeet.dto.ScheduleAddRequest;

@RestController
@RequestMapping("/api/not-to-meet")
@RequiredArgsConstructor
public class NotToMeetController {

    private final NotToMeetService notToMeetService;

    @PostMapping
    public Response<Void> create(@RequestBody NotToMeetCreateRequest request) {
        notToMeetService.create(request.getUserId(), request.getUserId(), request.getDate());
        return Response.success();
    }

    @PostMapping("/{notToMeetId}")
    public Response<Void> addSchedule(@PathVariable Long notToMeetId, @RequestBody ScheduleAddRequest request) {
        notToMeetService.addSchedule(notToMeetId, request.getUserId(), request.getStartTime(), request.getEndTime());
        return Response.success();
    }

    @GetMapping
    public Response<AvailableTimesResponse> availableTimes(@RequestParam String date, @RequestParam Long projectId) {
        List<LocalTime[]> availableTimes = notToMeetService.findAvailableTimes(projectId, date);
        return Response.success(new AvailableTimesResponse(availableTimes));
    }

}
