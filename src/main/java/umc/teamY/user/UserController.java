package umc.teamY.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.teamY.global.Response;
import umc.teamY.user.dto.join.UserJoinRequest;
import umc.teamY.user.dto.join.UserJoinResponse;
import umc.teamY.user.dto.list.UserListResponse;
import umc.teamY.user.dto.login.UserLoginRequest;
import umc.teamY.user.dto.login.UserLoginResponse;

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

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        return Response.success(userService.login(request));
    }
    @GetMapping
    public Response<List<UserListResponse>> getAllUsers() {
        return Response.success(userService.findAllUsers());
    }
}
