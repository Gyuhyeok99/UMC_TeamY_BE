package umc.teamY.tag.dto;

import lombok.*;
import umc.teamY.meeting.Meeting;
import umc.teamY.tag.Tag;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagCreateRequest {

    private Long meetingId;
    private String tagName;

    public Tag toEntity (Meeting meeting) {
        return Tag.builder()
                .meeting(meeting)
                .tagName(tagName)
                .build();
    }
}
