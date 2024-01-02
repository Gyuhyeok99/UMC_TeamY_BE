package umc.teamY.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.teamY.global.Response;
import umc.teamY.tag.TagService;
import umc.teamY.todo.dto.TodoAddOwnerRequest;
import umc.teamY.todo.dto.TodoCreateRequest;
import umc.teamY.todo.dto.TodoCreateResponse;

import java.util.List;

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

    /** 체크리스트 팀원 지정 */
    @PatchMapping("/team/{todoId}")
    public Response<TodoCreateResponse> assginOwner (@PathVariable("todoId") Long todoId,
                                                     @RequestBody TodoAddOwnerRequest request) {
        return Response.success(todoService.assignOwner(todoId, request.getOwnerId()));
    }

    /** 체크리스트 체크 */
    @PatchMapping("/todoCompleted/{todoId}")
    public Response<TodoCreateResponse> updateTodoCompleted (@PathVariable("todoId") Long todoId) {
        return Response.success(todoService.updateTodoCompleted(todoId));
    }

    /** 하루 남은 미완료 체크리스트 ID 목록 조회 */
    @GetMapping("/nearbyDeadline")
    public Response<List<Long>> findTodoIdsWithNearbyDeadline() {
        List<Long> todoIds = todoService.getTodoIdListNotCompleted();
        return Response.success(todoIds);
    }
}