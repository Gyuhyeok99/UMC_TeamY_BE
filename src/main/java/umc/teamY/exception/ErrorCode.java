package umc.teamY.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post not founded"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid password"),
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "Duplicated user name"),
    ALREADY_LIKED_POST(HttpStatus.CONFLICT, "user already like the post"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "User has invalid permission"),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurs"),
    NOTIFICATION_CONNECT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Connect to notification occurs error"),
    
    PROJECT_NOT_EXIST(HttpStatus.NOT_FOUND, "존재하지 않는 프로젝트입니다."),
    MEETING_NOT_EXIST(HttpStatus.NOT_FOUND, "존재하지 않는 회의입니다."),
    TAG_NOT_EXIST(HttpStatus.NOT_FOUND, "존재하지 않는 태그입니다."),
    TODO_NOT_EXIST(HttpStatus.NOT_FOUND, "존재하지 않는 체크리스트 입니다."),

    USER_PROJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "프로젝트를 찾을 수 없습니다."),
    NOT_TO_MEET_NOT_FOUND(HttpStatus.NOT_FOUND, "회의를 찾을 수 없습니다."),

    // User
    DUPLICATED_STUDENT_ID(HttpStatus.CONFLICT, "이미 존재하는 회원입니다."),
    INVALID_LOGIN_INFO(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다."),

    ;

    private final HttpStatus status;
    private final String message;
}
