package umc.teamY.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findTodosByMeetingIdAndTagId(Long meetingId, Long tagId);

    @Query("SELECT t.id FROM Todo t WHERE t.meeting.id = :meetingId AND t.isCompleted = :isCompleted")
    List<Long> findTodoIdsByMeetingIdAndIsCompleted(@Param("meetingId") Long meetingId, @Param("isCompleted") Boolean isCompleted);
}
