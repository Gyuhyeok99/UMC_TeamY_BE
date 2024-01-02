package umc.teamY.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamY.exception.CustomException;
import umc.teamY.meeting.Meeting;
import umc.teamY.meeting.MeetingRepository;
import umc.teamY.tag.dto.TagCreateRequest;
import umc.teamY.tag.dto.TagCreateResponse;

import static umc.teamY.exception.ErrorCode.MEETING_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class TagService {

    private final MeetingRepository meetingRepository;
    private final TagRepository tagRepository;

    /** 태그 추가 */
    @Transactional
    public TagCreateResponse addTagMeeting(Long meetingId, TagCreateRequest request) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new CustomException(MEETING_NOT_EXIST));

        Tag tag = request.toEntity(meeting);
        tagRepository.save(tag);

        return new TagCreateResponse(tag.getId());
    }
}
