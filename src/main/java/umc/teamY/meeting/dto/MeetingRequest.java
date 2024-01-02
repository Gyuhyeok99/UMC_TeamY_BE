package umc.teamY.meeting.dto;

import lombok.*;
import umc.teamY.meeting.Meeting;
import umc.teamY.project.Project;
import umc.teamY.project.ProjectRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingRequest {

    private Long projectId;
    private String title;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private Boolean isOnline;

    public Meeting toEntity(Project project) {
        return Meeting.builder()
                .project(project)
                .title(title)
                .startDate(startDate)
                .startTime(startTime)
                .endDate(endDate)
                .isOnline(isOnline)
                .build();
    }
}
