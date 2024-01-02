package umc.teamY.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import umc.teamY.global.Response;
import umc.teamY.project.dto.ProjectCreateReponse;
import umc.teamY.project.dto.ProjectCreateRequest;
import umc.teamY.project.dto.ProjectListReponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public Response<List<ProjectListReponse>> getProjectList(@RequestParam("term") ProjectTerm term) {
        return Response.success(projectService.findByProjectTerm(term));
    }

    @PostMapping
    public Response<ProjectCreateReponse> createProject(@RequestBody ProjectCreateRequest request) {
        return Response.success(projectService.createProject(request));
    }


}
