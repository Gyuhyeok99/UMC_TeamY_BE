package umc.teamY.user.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.teamY.project.Project;
import umc.teamY.project.ProjectRepository;
import umc.teamY.project.ProjectTerm;
import umc.teamY.user.User;
import umc.teamY.user.UserRepository;
import umc.teamY.user_project.UserProject;
import umc.teamY.user_project.UserProjectRepository;


@Service
@RequiredArgsConstructor
public class DummyDataLoader {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UserProjectRepository userProjectRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadDummyData() {
        User user1 = userRepository.save(User.builder()
                .studentId("S1")
                .password(passwordEncoder.encode("password1"))
                .school("SchoolA")
                .name("test1")
                .contribution(100L)
                .build());

        User user2 = userRepository.save(User.builder()
                .studentId("S2")
                .password(passwordEncoder.encode("password2"))
                .school("SchoolB")
                .name("test2")
                .contribution(150L)
                .build());

        Project project1 = projectRepository.save(Project.builder()
                .title("Project1")
                .projectTerm(ProjectTerm.TERM_24_1)
                .build());

        Project project2 = projectRepository.save(Project.builder()
                .title("Project2")
                .projectTerm(ProjectTerm.TERM_24_2)
                .build());

        userProjectRepository.save(UserProject.builder()
                .user(user1)
                .project(project1)
                .build());

        userProjectRepository.save(UserProject.builder()
                .user(user2)
                .project(project2)
                .build());
    }
}