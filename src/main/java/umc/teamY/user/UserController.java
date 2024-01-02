package umc.teamY.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.teamY.global.Response;
import umc.teamY.user.dto.UserListResponse;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

//    @PostMapping("/join")
//    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
//        return Response.success(UserJoinResponse.fromUser(userService.join(request.getName(), request.getPassword())));
//    }

    @GetMapping
    public Response<List<UserListResponse>> getAllUsers() {
        return Response.success(userService.findAllUsers());
    }
}
