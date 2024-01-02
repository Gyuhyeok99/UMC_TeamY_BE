package umc.teamY.contribution.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContributionResponse {
    private List<ContributionDto> contributionDtos;
}
