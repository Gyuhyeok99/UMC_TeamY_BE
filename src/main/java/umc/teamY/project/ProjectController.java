package umc.teamY.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.teamY.global.Response;
import umc.teamY.project.dto.ProjectCreateReponse;
import umc.teamY.project.dto.ProjectCreateRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;
    @PostMapping
    public Response<ProjectCreateReponse> createProject(@RequestBody ProjectCreateRequest request) {
        return Response.success(projectService.createProject(request));
    }


}
