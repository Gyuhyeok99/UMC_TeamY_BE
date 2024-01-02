package umc.teamY.nottomeet.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchedulesResponse {
    private List<ScheduleDto> scheduleDtos;
}
