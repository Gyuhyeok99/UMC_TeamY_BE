package umc.teamY.meeting.dto;

import lombok.Getter;
import umc.teamY.meeting.Meeting;
import java.util.List;

@Getter
public class MeetingTotalResponse {

    private List<Meeting> meetingList;

    public MeetingTotalResponse (List<Meeting> meetingList) {
        this.meetingList = meetingList;
    }
}
