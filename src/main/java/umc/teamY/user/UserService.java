package umc.teamY.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamY.user.dto.UserJoinRequest;
import umc.teamY.user.dto.UserJoinResponse;
import umc.teamY.user.dto.UserListResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserJoinResponse join(UserJoinRequest request) {
        // User 객체 생성
        User user = User.builder()
                        .studentId(request.getStudentId())
                        .password(request.getPassword())
                        .school(request.getSchool())
                        .name(request.getName())
                        .build();
        // 데이터베이스에 User 저장
        userRepository.save(user);
        log.info("유저 회원 가입 완료 : {}", user.getName());
        // UserJoinResponse 생성하여 반환
        return new UserJoinResponse(user.getId());
    }

    public List<UserListResponse> findAllUsers() {
        log.info("유저 전체 조회");
        //추후 요청한 유저 제외하고 조회하는 것으로 변경해야함
        return userRepository.findAll().stream()
                .map(user -> new UserListResponse(user.getId(), user.getName()))
                .collect(Collectors.toList());
    }
}
