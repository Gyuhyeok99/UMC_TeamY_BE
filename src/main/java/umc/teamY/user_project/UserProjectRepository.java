package umc.teamY.user_project;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Long> {

    Optional<UserProject> findByUserIdAndProjectId(Long userId, Long projectId);
}
