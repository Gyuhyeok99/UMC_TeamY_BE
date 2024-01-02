package umc.teamY.contribution.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ContributionResponse {
    private List<Double> contributions;
}
