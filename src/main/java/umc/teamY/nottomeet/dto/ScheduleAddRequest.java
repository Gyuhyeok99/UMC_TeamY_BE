package umc.teamY.nottomeet.dto;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleAddRequest {
    private Long userId;
    private LocalTime startTime;
    private LocalTime endTime;
}
