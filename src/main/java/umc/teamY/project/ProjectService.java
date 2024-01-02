package umc.teamY.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamY.exception.CustomException;
import umc.teamY.project.dto.ProjectCreateReponse;
import umc.teamY.project.dto.ProjectCreateRequest;
import umc.teamY.project.dto.ProjectListReponse;
import umc.teamY.user.User;
import umc.teamY.user.UserRepository;
import umc.teamY.user_project.UserProject;
import umc.teamY.user_project.UserProjectRepository;

import java.util.List;
import java.util.stream.Collectors;

import static umc.teamY.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final UserProjectRepository userProjectRepository;

    public List<ProjectListReponse> findByProjectTerm(ProjectTerm projectTerm) {
        List<Project> projects = projectRepository.findByProjectTerm(projectTerm);
        return projects.stream()
                .map(ProjectListReponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectCreateReponse createProject(ProjectCreateRequest request) {
        Project project = Project.builder()
                .title(request.getTitle())
                .projectTerm(request.getProjectTerm())
                .build();
        projectRepository.save(project);
        // 유저-프로젝트 연결
        for (Long userId : request.getUserIds()) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
            UserProject userProject = UserProject.builder()
                    .user(user)
                    .project(project)
                    .build();
            userProjectRepository.save(userProject);
        }
        return new ProjectCreateReponse(project.getId());
    }

}
