package umc.teamY.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamY.exception.CustomException;
import umc.teamY.user.dto.join.UserJoinRequest;
import umc.teamY.user.dto.join.UserJoinResponse;
import umc.teamY.user.dto.list.UserListResponse;
import umc.teamY.user.dto.login.UserLoginRequest;
import umc.teamY.user.dto.login.UserLoginResponse;

import java.util.List;
import java.util.stream.Collectors;

import static umc.teamY.exception.ErrorCode.DUPLICATED_STUDENT_ID;
import static umc.teamY.exception.ErrorCode.INVALID_LOGIN_INFO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserJoinResponse join(UserJoinRequest request) {
        // 먼저 studentId가 이미 존재하는지 확인
        if (userRepository.existsByStudentId(request.getStudentId())) {
            throw new CustomException(DUPLICATED_STUDENT_ID);
        }
        // User 객체 생성
        User user = User.builder()
                        .studentId(request.getStudentId())
                        .password(request.getPassword())
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

    @Transactional
    public UserLoginResponse login(UserLoginRequest request) {
        // studentId로 User 객체를 가져옴
        User user = userRepository.findByStudentId(request.getStudentId())
                .orElseThrow(() -> new CustomException(INVALID_LOGIN_INFO));

        // password가 일치하는지 확인
        if (request.getPassword().equals(user.getPassword())) {
            // password가 일치하지 않는 경우, 사용자 정의 예외 발생
            throw new CustomException(INVALID_LOGIN_INFO);
        }

        log.info("유저 로그인 완료 : {}", user.getName());
        // UserLoginResponse 생성하여 반환
        return new UserLoginResponse(user.getId());
    }

    public List<UserListResponse> findAllUsers() {
        log.info("유저 전체 조회");
        //추후 요청한 유저 제외하고 조회하는 것으로 변경해야함
        return userRepository.findAll().stream()
                .map(user -> new UserListResponse(user.getId(), user.getName()))
                .collect(Collectors.toList());
    }
}
