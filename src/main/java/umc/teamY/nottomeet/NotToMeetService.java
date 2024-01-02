package umc.teamY.nottomeet;

import java.sql.Time;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamY.exception.CustomException;
import umc.teamY.exception.ErrorCode;
import umc.teamY.nottomeet.schedule.Schedule;
import umc.teamY.nottomeet.schedule.ScheduleRepository;
import umc.teamY.user.User;
import umc.teamY.user.UserRepository;
import umc.teamY.user_project.UserProject;
import umc.teamY.user_project.UserProjectRepository;

@Service
@RequiredArgsConstructor
public class NotToMeetService {

    private final NotToMeetRepository notToMeetRepository;
    private final UserProjectRepository userProjectRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(Long userId, Long projectId, Date date) {
        UserProject userProject = userProjectRepository.findByUserIdAndProjectId(userId, projectId).orElseThrow(() ->
                new CustomException(ErrorCode.USER_PROJECT_NOT_FOUND));
        notToMeetRepository.save(NotToMeet.of(userProject, date));
    }

    @Transactional
    public void addSchedule(Long notToMeetId, Long userId, Time startTime, Time endTime) {
        NotToMeet notToMeet = notToMeetRepository.findById(notToMeetId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_TO_MEET_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Schedule schedule = Schedule.of(user, notToMeet, startTime, endTime);
        scheduleRepository.save(schedule);
        notToMeet.addSchedule(schedule);
    }


}
