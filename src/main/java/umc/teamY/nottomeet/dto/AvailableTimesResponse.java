package umc.teamY.nottomeet.dto;

import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AvailableTimesResponse {
    private List<LocalTime[]> availableTimes;
}
