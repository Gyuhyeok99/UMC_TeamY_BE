package umc.teamY.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import umc.teamY.global.Response;
import umc.teamY.user.dto.UserJoinRequest;
import umc.teamY.user.dto.UserJoinResponse;
import umc.teamY.user.dto.UserListResponse;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        return Response.success(userService.join(request));
    }

    @GetMapping
    public Response<List<UserListResponse>> getAllUsers() {
        return Response.success(userService.findAllUsers());
    }
}
