package umc.teamY.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.teamY.project.Project;
import umc.teamY.project.ProjectTerm;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectListReponse {
    private Long projectId;
    private String title;
    private ProjectTerm projectTerm;

    public ProjectListReponse(Project project) {
        this.projectId = project.getId();
        this.title = project.getTitle();
        this.projectTerm = project.getProjectTerm();
    }
}
