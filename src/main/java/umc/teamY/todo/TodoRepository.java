package umc.teamY.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findTodosByMeetingIdAndTagId(Long meetingId, Long tagId);
    List<Long> findTodoIdsByMeetingIdAndIsCompleted(Long meetingId, Boolean isCompleted);
}
