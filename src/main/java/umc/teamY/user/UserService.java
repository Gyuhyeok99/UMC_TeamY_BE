package umc.teamY.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamY.exception.CustomException;
import umc.teamY.user.dto.UserJoinRequest;
import umc.teamY.user.dto.UserJoinResponse;
import umc.teamY.user.dto.UserListResponse;

import java.util.List;
import java.util.stream.Collectors;

import static umc.teamY.exception.ErrorCode.DUPLICATED_STUDENT_ID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserJoinResponse join(UserJoinRequest request) {
        // 먼저 studentId가 이미 존재하는지 확인
        if (userRepository.existsByStudentId(request.getStudentId())) {
            throw new CustomException(DUPLICATED_STUDENT_ID);
        }
        // User 객체 생성
        User user = User.builder()
                        .studentId(request.getStudentId())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .school(request.getSchool())
                        .name(request.getName())
                        .contribution(0L)   // 기본값 0으로 설정
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
