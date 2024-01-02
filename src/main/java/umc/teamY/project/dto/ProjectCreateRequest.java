package umc.teamY.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.teamY.project.ProjectTerm;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectCreateRequest {

    String title;
    ProjectTerm projectTerm;
    List<Long> userIds;
}
