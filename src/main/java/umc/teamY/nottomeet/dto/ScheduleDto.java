package umc.teamY.nottomeet.dto;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.teamY.nottomeet.schedule.Schedule;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDto {
    private String userName;
    private LocalTime startTime;
    private LocalTime endTime;

    public static ScheduleDto fromSchedule(Schedule schedule) {
        return ScheduleDto.builder()
                .userName(schedule.getUser().getName())
                .startTime(schedule.getStartTime())
                .endTime(schedule.getEndTime())
                .build();
    }
}
