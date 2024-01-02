package umc.teamY.nottomeet;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
import umc.teamY.nottomeet.dto.ScheduleDto;
import umc.teamY.nottomeet.dto.SchedulesResponse;
import umc.teamY.nottomeet.schedule.Schedule;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotToMeetController {

    private final NotToMeetService notToMeetService;

    @PostMapping("/not-to-meet")
    public Response<Void> create(@RequestBody NotToMeetCreateRequest request) {
        notToMeetService.create(request.getUserId(), request.getUserId(), request.getDate());
        return Response.success();
    }

    @PostMapping("/not-to-meet/{notToMeetId}")
    public Response<Void> addSchedule(@PathVariable Long notToMeetId, @RequestBody ScheduleAddRequest request) {
        notToMeetService.addSchedule(notToMeetId, request.getUserId(), request.getStartTime(), request.getEndTime());
        return Response.success();
    }

    @GetMapping("/not-to-meet")
    public Response<AvailableTimesResponse> availableTimes(@RequestParam String date, @RequestParam Long projectId) {
        List<LocalTime[]> availableTimes = notToMeetService.findAvailableTimes(projectId, date);
        return Response.success(new AvailableTimesResponse(availableTimes));
    }

    @GetMapping("/schedules")
    public Response<SchedulesResponse> getSchedules(@RequestParam String date, @RequestParam Long projectId) {
        List<Schedule> schedules = notToMeetService.getSchedules(date, projectId);
        List<ScheduleDto> scheduleDtos = schedules.stream()
                .map(ScheduleDto::fromSchedule)
                .sorted(Comparator.comparing(ScheduleDto::getUserName)
                        .thenComparing(ScheduleDto::getStartTime))
                .collect(Collectors.toList());
        return Response.success(new SchedulesResponse(scheduleDtos));
    }
}
