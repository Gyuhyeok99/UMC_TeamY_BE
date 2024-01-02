package umc.teamY.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.teamY.global.Response;
import umc.teamY.tag.dto.TagCreateRequest;
import umc.teamY.tag.dto.TagCreateResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tag")
public class TagController {

    private final TagService tagService;

    /** 태그 추가 */
    @PostMapping("/")
    public Response<TagCreateResponse> addTagToMeeting(@RequestBody TagCreateRequest request) {
        return Response.success(tagService.addTagMeeting(request.getMeetingId(), request));
    }
}
