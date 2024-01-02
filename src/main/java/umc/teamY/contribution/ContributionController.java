package umc.teamY.contribution;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.teamY.contribution.dto.ContributionResponse;
import umc.teamY.global.Response;

@RestController
@RequestMapping("/api/contribution")
@RequiredArgsConstructor
public class ContributionController {

    private final ContributionService contributionService;

    @GetMapping
    public Response<ContributionResponse> getPersonal(@RequestParam Long projectId) {
        List<Double> personalResult = contributionService.getPersonal(projectId);
        return Response.success(new ContributionResponse(personalResult));
    }
}
