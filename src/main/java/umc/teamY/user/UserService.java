package umc.teamY.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamY.user.dto.UserListResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public List<UserListResponse> findAllUsers() {
        log.info("유저 전체 조회");
        //추후 요청한 유저 제외하고 조회하는 것으로 변경해야함
        return userRepository.findAll().stream()
                .map(user -> new UserListResponse(user.getId(), user.getName()))
                .collect(Collectors.toList());
    }
}
