package umc.teamY.contribution;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamY.exception.CustomException;
import umc.teamY.exception.ErrorCode;
import umc.teamY.todo.TodoRepository;

@Service
@RequiredArgsConstructor
public class ContributionService {

    private final ContributionRepository contributionRepository;
    private final TodoRepository todoRepository;

    public List<Double> getPersonal(Long projectId) {
        List<Contribution> contributions = contributionRepository.findByProjectProjectId(projectId)
                .orElseThrow(() -> new CustomException(ErrorCode.CONTRIBUTION_NOT_FOUND))
                .stream().sorted(Comparator.comparing(Contribution::getCount).reversed())
                .collect(Collectors.toList());

        int totalCount = todoRepository.countTodosByProjectId(projectId);
        return calculatePercent(contributions, totalCount);
    }

    private List<Double> calculatePercent(List<Contribution> contributions, int totalCount) {
        return contributions.stream()
                .map(contribution -> (double) Math.round((double) contribution.getCount() / totalCount * 10) / 10)
                .collect(Collectors.toList());
    }
}
