package umc.teamY.nottomeet.dto;

import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleAddRequest {
    private Long userId;
    private Time startTime;
    private Time endTime;
}
