package umc.teamY.nottomeet;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
    public void create(Long userId, Long projectId, String date) {
        UserProject userProject = userProjectRepository.findByUserIdAndProjectId(userId, projectId).orElseThrow(() ->
                new CustomException(ErrorCode.USER_PROJECT_NOT_FOUND));
        notToMeetRepository.save(NotToMeet.of(userProject, date));
    }

    @Transactional
    public void addSchedule(Long notToMeetId, Long userId, LocalTime startTime, LocalTime endTime) {
        NotToMeet notToMeet = notToMeetRepository.findById(notToMeetId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_TO_MEET_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Schedule schedule = Schedule.of(user, notToMeet, startTime, endTime);
        scheduleRepository.save(schedule);
        notToMeet.addSchedule(schedule);
    }

    public List<LocalTime[]> findAvailableTimes(Long projectId, String date) {
        NotToMeet notToMeet = notToMeetRepository.findByUserProjectProjectIdAndDate(projectId,date)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_TO_MEET_NOT_FOUND));
        List<Schedule> schedules = notToMeet.getSchedules();
        return findAvailableTimes(schedules);
    }

    public List<Schedule> getSchedules(String date, Long projectId) {
        NotToMeet notToMeet = notToMeetRepository.findByUserProjectProjectIdAndDate(projectId,date)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_TO_MEET_NOT_FOUND));
        return notToMeet.getSchedules();
    }

    private List<LocalTime[]> findAvailableTimes(List<Schedule> schedules) {
        List<LocalTime[]> availableTimes = new ArrayList<>();

        Collections.sort(schedules, Comparator.comparing(s -> s.getStartTime()));

        if (!schedules.isEmpty() && !schedules.get(0).getStartTime().equals(LocalTime.MIN)) {
            availableTimes.add(new LocalTime[]{LocalTime.MIN, schedules.get(0).getStartTime()});
        }

        for (int i = 0; i < schedules.size() - 1; i++) {
            LocalTime end = schedules.get(i).getEndTime();
            LocalTime start = schedules.get(i + 1).getStartTime();
            if (end.isBefore(start)) {
                availableTimes.add(new LocalTime[]{end, start});
            }
        }

        if (!schedules.isEmpty() && !schedules.get(schedules.size() - 1).getEndTime().equals(LocalTime.MAX)) {
            availableTimes.add(new LocalTime[]{schedules.get(schedules.size() - 1).getEndTime(), LocalTime.of(23, 59)});
        }

        return availableTimes;
    }
}
