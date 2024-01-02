package umc.teamY.contribution;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamY.exception.CustomException;
import umc.teamY.exception.ErrorCode;

@Service
@RequiredArgsConstructor
public class ContributionService {

    private final ContributionRepository contributionRepository;
    public List<Contribution> getPersonal(Long projectId) {
        return contributionRepository.findByProjectProjectId(projectId)
                .orElseThrow(() -> new CustomException(ErrorCode.CONTRIBUTION_NOT_FOUND))
                .stream().sorted(Comparator.comparing(Contribution::getCount).reversed())
                .collect(Collectors.toList());
    }
}
