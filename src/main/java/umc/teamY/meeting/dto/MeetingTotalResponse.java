package umc.teamY.meeting.dto;

import lombok.Getter;
import umc.teamY.meeting.Meeting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MeetingTotalResponse {

    private List<MeetingResponse> meetingList;

    public MeetingTotalResponse(List<Meeting> meetingList) {
        this.meetingList = meetingList.stream()
                .map(MeetingResponse::new)
                .collect(Collectors.toList());
    }

    @Getter
    public static class MeetingResponse {
        private Long id;
        private String title;
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalTime startTime;
        private Boolean isOnline;
        private Long projectId;

        public MeetingResponse(Meeting meeting) {
            this.id = meeting.getId();
            this.title = meeting.getTitle();
            this.startDate = meeting.getStartDate();
            this.endDate = meeting.getEndDate();
            this.startTime = meeting.getStartTime();
            this.isOnline = meeting.getIsOnline();
            this.projectId = meeting.getProject().getId();
        }
    }
}
