package umc.teamY.meeting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findMeetingsByProjectId(Long projectId);
    List<Meeting> findMeetingsByEndDate(LocalDate endDate);

}
