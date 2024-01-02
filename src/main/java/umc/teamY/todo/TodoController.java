package umc.teamY.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.teamY.global.Response;
import umc.teamY.tag.TagService;
import umc.teamY.todo.dto.TodoCreateRequest;
import umc.teamY.todo.dto.TodoCreateResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/todo")
public class TodoController {

    private final TodoService todoService;

    /** 체크리스트 추가 */
    @PostMapping("/")
    public Response<TodoCreateResponse> addTodoToMeeting(@RequestBody TodoCreateRequest request) {
        return Response.success(todoService.addTodoMeeting(request));
    }
}
